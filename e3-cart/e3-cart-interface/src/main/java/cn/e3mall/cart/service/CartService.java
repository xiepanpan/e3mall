package cn.e3mall.cart.service;

import java.util.List;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.pojo.TbItem;

public interface CartService {

	/**
	 * 添加购物车 
	 * <p>Title: addCart</p>
	 * <p>Description: </p>
	 * @param userId 用户id
	 * @param itemId 商品id
	 * @param num 商品数量
	 * @return
	 */
	E3Result addCart(long userId,long itemId,int num);
	
	/**
	 * 合并购物车
	 * <p>Title: mergeCart</p>
	 * <p>Description: </p>
	 * @param userId
	 * @param itemList
	 * @return
	 */
	E3Result mergeCart(long userId,List<TbItem> itemList);
	
	/**
	 * 服务端根据用户id查询购物车信息
	 * <p>Title: getCartList</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 */
	List<TbItem> getCartList(long userId);
	
	/**
	 * 登录状态下更新购物车信息
	 * <p>Title: updateCartNum</p>
	 * <p>Description: </p>
	 * @param userId
	 * @param itemId
	 * @param num
	 * @return
	 */
	E3Result updateCartNum(long userId,long itemId,int num);
	
	/**
	 * 登录状态下删除购物车
	 * <p>Title: deleteCartItem</p>
	 * <p>Description: </p>
	 * @param userId
	 * @param itemId
	 * @return
	 */
	E3Result deleteCartItem(long userId,long itemId);
	
	/**
	 * 清空购物车
	 * <p>Title: clearCartItem</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 */
	E3Result clearCartItem(long userId);
}
