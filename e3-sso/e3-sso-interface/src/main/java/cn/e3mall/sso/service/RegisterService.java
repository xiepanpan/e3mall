package cn.e3mall.sso.service;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.pojo.TbUser;

public interface RegisterService {
	
	/**
	 * 校验数据
	 * <p>Title: checkData</p>
	 * <p>Description: </p>
	 * @param param
	 * @param type
	 * @return
	 */
	E3Result checkData(String param,int type);
	
	E3Result register(TbUser user);
}
