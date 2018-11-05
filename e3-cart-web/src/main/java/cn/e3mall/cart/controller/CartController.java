package cn.e3mall.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.Log;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.util.CookieUtils;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.common.util.JsonUtils;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.service.ItemService;

/**
 * 购物车处理Controller
 * <p>Title: CartController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class CartController {
	
	@Autowired
	private ItemService itemService;
	@Value("${COOKIE_CART_EXPIRE}")
	private Integer COOKIE_CART_EXPIRE;
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable Long itemId,@RequestParam(defaultValue="1")Integer num,
			HttpServletRequest request,HttpServletResponse response){
		//判断是否登录 
		TbUser user = (TbUser) request.getAttribute("user");
		//如果是登录状态 把购物车数据写入redis
		if (user!=null) {
			//保存服务端 
			cartService.addCart(user.getId(), itemId, num);
			//返回逻辑视图
			return "cartSuccess";
		}
		//从cookie中取购物车列表
		List<TbItem> cartList = getCartListFromCookie(request);
		//判断商品在商品列表中是否存在
		boolean flag = false;
		for (TbItem tbItem : cartList) {
			//如果存在 数量相加
			if (tbItem.getId()==itemId.longValue()) {
				flag = true;
				//找到商品 数量相加
				tbItem.setNum(tbItem.getNum()+num);
				//跳出循环
				break;
			}
		}
		//如果不存在
		if (!flag) {
			//根据商品id取商品信息
			TbItem tbItem = itemService.geTbItemById(itemId);
			tbItem.setNum(num);
			//取一张图片
			String image = tbItem.getImage();
			if (StringUtils.isNotBlank(image)) {
				tbItem.setImage(image.split(",")[0]);
			}
			//把商品添加到商品列表中
			cartList.add(tbItem);
		}
		//写入cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);
		return "cartSuccess";
	}
	
	/**
	 * 从cookie中取购物车列表的处理
	 * <p>Title: getCartListFromCookie</p>
	 * <p>Description: </p>
	 * @param request
	 * @return
	 */
	private List<TbItem> getCartListFromCookie(HttpServletRequest request){
		String json = CookieUtils.getCookieValue(request, "cart",true);
		//判断json是否为空
		if (StringUtils.isBlank(json)) {
			return new ArrayList<>();
		}
		//把json转换成商品列表
		List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
		return list;
	}
	
	/**
	 * 展示购物车列表
	 * <p>Title: showCartList</p>
	 * <p>Description: </p>
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/cart")
	public String showCartList(HttpServletRequest request,HttpServletResponse response){
		//从cookie中取购物车列表
		List<TbItem> cartList = getCartListFromCookie(request);
		//判断用户是否为登录状态
		TbUser user = (TbUser) request.getAttribute("user");
		//1.如果是登录状态
		if (user!=null) {
			//3.如果不为空 把cookie中的购物车商品和服务端的购物车商品进行合并
			cartService.mergeCart(user.getId(), cartList);
			//4.把cookie中的购物车删除
			CookieUtils.deleteCookie(request, response, "cart");
			//5.从服务端取购物车列表
			cartList=cartService.getCartList(user.getId());
		}
		
		//把列表传递到页面
		request.setAttribute("cartList", cartList);
		//返回逻辑视图
		return "cart";
	}
	
	/**
	 * 商品数量修改
	 * <p>Title: updateCartNum</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public E3Result updateCartNum(@PathVariable Long itemId,@PathVariable Integer num,
			HttpServletRequest request,HttpServletResponse response) {
		//判断用户是否为登录状态
		TbUser user = (TbUser) request.getAttribute("user");
		if (user!=null) {
			cartService.updateCartNum(user.getId(), itemId, num);
			return E3Result.ok();
		}
		//从Cookie中取购物车列表
		List<TbItem> cartList = getCartListFromCookie(request);
		//遍历商品列表 找到对应的商品 设置数量
		for(TbItem tbItem:cartList) {
			if (tbItem.getId().longValue()==itemId) {
				//更新数量
				tbItem.setNum(num);
				break;
			}
		}
		//把购物车列表写回cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);
		return E3Result.ok();
	}
	
	/**
	 * 删除购物车中的商品
	 * <p>Title: deleteCardItem</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("cart/delete/{itemId}")
	public String deleteCardItem(@PathVariable Long itemId,HttpServletRequest request,
			HttpServletResponse response) {
		TbUser user = (TbUser) request.getAttribute("user");
		if (user!=null) {			
			cartService.deleteCartItem(user.getId(), itemId);
			return "redirect:/cart/cart.html";
		}
		//从cookie中取购物车列表
		List<TbItem> cartList = getCartListFromCookie(request);
		for(TbItem tbItem:cartList) {
			if (tbItem.getId().longValue()==itemId) {
				//删除商品
				cartList.remove(tbItem);
				break;
			}
		}
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);
		//返回购物车页面
		return "redirect:/cart/cart.html";
	}
}
