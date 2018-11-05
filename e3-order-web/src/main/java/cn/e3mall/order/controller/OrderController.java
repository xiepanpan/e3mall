package cn.e3mall.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.order.pojo.OrderInfo;
import cn.e3mall.order.service.OrderService;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;

/**
 * 订单管理控制层
 * <p>Title: OrderController</p>
 * <p>Description: </p>
 * @version 1.0
 */
@Controller
public class OrderController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 订单详情 订单结算
	 * <p>Title: showOrderCart</p>
	 * <p>Description: </p>
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/order-cart")
	public String showOrderCart(HttpServletRequest request) {
		TbUser user = (TbUser) request.getAttribute("user");
		//取用户购物车信息
		List<TbItem> cartList = cartService.getCartList(user.getId());
		request.setAttribute("cartList", cartList);
		return "order-cart";
	}
	
	@RequestMapping(value="/order/create",method=RequestMethod.POST)
	public String createOrder(OrderInfo orderInfo,HttpServletRequest request){
		//取用户信息
		TbUser user = (TbUser) request.getAttribute("user");
		orderInfo.setBuyerNick(user.getUsername());
		orderInfo.setUserId(user.getId());
		//调用服务生成订单
		E3Result e3Result = orderService.createOrder(orderInfo);
		//如果订单生成成功 需要删除购物车
		if (e3Result.getStatus()==200) {
			//清空购物车
			cartService.clearCartItem(user.getId());
		}
		//把订单号传递给页面
		request.setAttribute("orderId", e3Result.getData());
		request.setAttribute("payment", orderInfo.getPayment());
		//返回逻辑视图
		return "success";
	}

}
