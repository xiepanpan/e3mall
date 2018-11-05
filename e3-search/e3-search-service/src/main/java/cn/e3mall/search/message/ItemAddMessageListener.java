package cn.e3mall.search.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.search.mapper.ItemMapper;

/**
 * 监听商品添加消息 接收消息后 将对应商品添加到索引库
 * <p>Title: ItemAddMessageListener</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class ItemAddMessageListener implements MessageListener {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	
	/**
	 * 根据id获取商品信息 完事之后创建文档对象 文档对象中添加域 完事之后导入到索引库
	 * <p>Title: onMessage</p>
	 * <p>Description: </p>
	 * @param message
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		String text;
		try {
			text = textMessage.getText();
			Long itemId = new Long(text);
			//有可能还没提交商品 但是消息已经发出了 此时搜到的商品可能为空 因此要等待一段时间
			Thread.sleep(1000);
			SearchItem searchItem = itemMapper.getItemById(itemId);
			SolrInputDocument solrInputDocument = new SolrInputDocument();
			//添加域
			solrInputDocument.addField("id",searchItem.getId());
			solrInputDocument.addField("item_title",searchItem.getTitle());
			solrInputDocument.addField("item_sell_point",searchItem.getSell_point());
			solrInputDocument.addField("item_price",searchItem.getPrice());
			solrInputDocument.addField("item_image",searchItem.getImage());
			solrInputDocument.addField("item_category_name",searchItem.getCategory_name());
			solrServer.add(solrInputDocument);
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
