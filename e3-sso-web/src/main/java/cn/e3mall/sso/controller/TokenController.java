package cn.e3mall.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.common.util.JsonUtils;
import cn.e3mall.sso.service.TokenService;

/**
 * 根据token取用户信息Controller
 * <p>Title: TokenController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class TokenController {
	
	@Autowired
	private TokenService tokenService;
	
	//spring4.1用这个版本可以
//	@RequestMapping(value="/user/token/{token}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public String getUserByToken(@PathVariable String token,String callback){
//		E3Result result = tokenService.getUserByToken(token);
//		//判断是否为jsonp请求
//		if (StringUtils.isNotBlank(callback)) {
//			//把结果封装成js语句响应
//			return callback+"("+JsonUtils.objectToJson(result)+");";
//		}
//		return JsonUtils.objectToJson(result);
//	}
	
	//不支持spring4.1之前的版本
	@RequestMapping(value="/user/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token,String callback){
		E3Result result = tokenService.getUserByToken(token);
		//判断是否为jsonp请求
		if (StringUtils.isNotBlank(callback)) {
			//把结果封装成js语句响应
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}
}
