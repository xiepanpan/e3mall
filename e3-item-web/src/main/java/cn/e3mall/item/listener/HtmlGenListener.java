package cn.e3mall.item.listener;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HtmlGenListener implements MessageListener{

	@Autowired
	private ItemService itemService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Value("${HTML_GEN_PATH}")
	private String HTML_GEN_PATH;
	
	@Override
	public void onMessage(Message message) {
		try {
			//创建模板
			//从消息中取商品id
			TextMessage textMessage = (TextMessage)message;
			String text = textMessage.getText();
			Long itemId = new Long(text);
			//等待事务提交
			Thread.sleep(1000);
			//根据商品id查询商品信息
			TbItem tbItem = itemService.geTbItemById(itemId);
			Item item = new Item(tbItem);
			//取商品描述信息
			TbItemDesc itemDesc = itemService.geTbItemDescById(itemId);
			Map data = new HashMap<>();
			data.put("item", item);
			data.put("itemDesc", itemDesc);
			//加载模板对象
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			//创建输出流 指定目录和文件名
			Writer out = new FileWriter(HTML_GEN_PATH+itemId+".html");
			//生成静态页面
			template.process(data, out);
			//关闭流
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
