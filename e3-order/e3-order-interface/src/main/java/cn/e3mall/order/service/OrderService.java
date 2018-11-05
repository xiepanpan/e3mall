package cn.e3mall.order.service;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.order.pojo.OrderInfo;

public interface OrderService {
	
	/**
	 * 创建订单
	 * <p>Title: createOrder</p>
	 * <p>Description: </p>
	 * @param orderInfo
	 * @return
	 */
	E3Result createOrder(OrderInfo orderInfo);

}
