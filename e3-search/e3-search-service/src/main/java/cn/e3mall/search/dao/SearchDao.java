package cn.e3mall.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;

/**
 * 商品搜索Dao
 * <p>Title: SearchDao</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Repository
public class SearchDao {
	
	@Autowired
	private SolrServer solrServer;
	
	public SearchResult search(SolrQuery solrQuery) throws Exception {
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		long numFound = solrDocumentList.getNumFound();
		SearchResult searchResult = new SearchResult();
		searchResult.setRecordCount(numFound);
		//取商品列表 高亮显示
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		List<SearchItem> itemList = new ArrayList<>();
		for (SolrDocument solrDocument : solrDocumentList) {
			SearchItem searchItem = new SearchItem();
			searchItem.setId((String)solrDocument.get("id"));
			searchItem.setCategory_name((String) solrDocument.get("item_category_name"));
			searchItem.setImage((String) solrDocument.get("item_image"));
			searchItem.setPrice((long) solrDocument.get("item_price"));
			searchItem.setSell_point((String) solrDocument.get("item_sell_point"));
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title="";
			if (list!=null && list.size()>0) {
				title=list.get(0);
			}else {
				title=(String) solrDocument.get("item_title");
			}
			searchItem.setTitle(title);
			itemList.add(searchItem);
		}
		searchResult.setItemList(itemList);
		return searchResult;
	}
	
}
