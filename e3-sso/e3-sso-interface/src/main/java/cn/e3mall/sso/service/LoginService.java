package cn.e3mall.sso.service;

import cn.e3mall.common.util.E3Result;

public interface LoginService {

	/**
	 * 1.判断用户名密码是否正确
	 * 2.如果不正确 返回登录失败
	 * 3.正确生成token（SessionId）
	 * 4.把用户信息写入Redis key：token value：用户信息
	 * 5. 设置session过期时间
	 * 6.把token返回
	 */
	E3Result userLogin(String username,String password);
}
