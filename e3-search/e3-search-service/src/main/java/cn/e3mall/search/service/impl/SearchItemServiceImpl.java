package cn.e3mall.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.search.mapper.ItemMapper;
import cn.e3mall.search.service.SearchItemService;

/**
 * 索引维护Service
 * <p>Title: SearchItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	
	/**
	 * 导入所有商品索引
	 * <p>Title: importAllItems</p>
	 * <p>Description: </p>
	 * @return
	 * @see cn.e3mall.search.service.SearchItemService#importAllItems()
	 */
	@Override
	public E3Result importAllItems() {
		//商品列表集合
		try {
			List<SearchItem> itemList = itemMapper.getItemList();
			for (SearchItem searchItem : itemList) {
				SolrInputDocument solrInputDocument =new SolrInputDocument();
				solrInputDocument.addField("id",searchItem.getId());
				solrInputDocument.addField("item_title",searchItem.getTitle());
				solrInputDocument.addField("item_sell_point",searchItem.getSell_point());
				solrInputDocument.addField("item_price",searchItem.getPrice());
				solrInputDocument.addField("item_image",searchItem.getImage());
				solrInputDocument.addField("item_category_name",searchItem.getCategory_name());
			
				solrServer.add(solrInputDocument);
			} 
			solrServer.commit();
			return E3Result.ok();
		}catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "数据导入时异常");
		}
	}

}
