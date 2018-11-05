package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.util.FastDFSClient;
import cn.e3mall.common.util.JsonUtils;

@Controller
public class PictureController {
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile){
		try {
			FastDFSClient fastDFSClient =new FastDFSClient("classpath:conf/client.conf");
			//取文件扩展名
			String originalFilename=uploadFile.getOriginalFilename();
			String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			//得到图片地址和文件名
			String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			String url=IMAGE_SERVER_URL+path;
			Map result=new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			Map result=new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			return JsonUtils.objectToJson(result);
		}
	}
}
