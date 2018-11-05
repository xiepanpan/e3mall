package com.itheima.jd.service;

import java.util.List;

import com.itheima.jd.pojo.ProductModel;

public interface JdService {
	
	//// 通过上面四个条件查询对象商品结果集
	public List<ProductModel> selectProductModelListByQuery(String queryString, String catalog_name,
			String price,String sort) throws Exception ;

}
