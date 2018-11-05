package cn.e3mall.item.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
public class HtmlGenController {
	
	@Autowired 
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@RequestMapping("genhtml")
	@ResponseBody
	public String genHtml() throws Exception{
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		//加载模板对象
		Template template = configuration.getTemplate("hello.ftl");
		//创建数据集
		Map data = new HashMap<>();
		data.put("hello", 123456);
		Writer out = new FileWriter(new File("D:/freemarkertest/hello2.html"));
		template.process(data, out);
		out.close();
		return "OK";
	}
}
