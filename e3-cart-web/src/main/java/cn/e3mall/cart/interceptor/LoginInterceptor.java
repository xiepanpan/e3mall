package cn.e3mall.cart.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.e3mall.common.util.CookieUtils;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;

/**
 * 用户登录处理拦截器
 * <p>Title: LoginInterceptor</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	TokenService tokenService;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		// modelAndView之后 异常处理

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// handler之后 modelAndView之前

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//前处理 执行Handler之前执行该方法 返回true 放行 返回false拦截
		
		//有用户信息 显示 没有也不拦截。。 主要的作用是判断是否登录
		//1.从cookie中取token
		String token = CookieUtils.getCookieValue(request, "token");
		//2.没有cookie 表示未登录状态 直接放行
		if (StringUtils.isBlank(token)) {
			return true;
		}
		//3.根据token取用户信息
		E3Result e3Result = tokenService.getUserByToken(token);
		//4.没有取到用户信息 放行
		if (e3Result.getStatus()!=200) {
			return true;
		}
		//5. 取到用户信息
		TbUser user = (TbUser) e3Result.getData();
		request.setAttribute("user", user);
		return true;
	}

}
