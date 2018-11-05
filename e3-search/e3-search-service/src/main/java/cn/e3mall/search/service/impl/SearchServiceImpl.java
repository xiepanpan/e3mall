package cn.e3mall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.search.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;
	
	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(keyword);
		if (page<=0) {
			page=1;
		}
		solrQuery.setStart((page-1)*rows);//从第几条开始
		solrQuery.setRows(rows);//条数
		//设置默认搜索域
		solrQuery.set("df", "item_title");
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		//调用dao执行查询
		SearchResult searchResult = searchDao.search(solrQuery);
		long recordCount = searchResult.getRecordCount();
		int totalPage = (int)(recordCount/rows);
		//不足一页
		if (recordCount%rows>0) {
			totalPage++;
		}
		searchResult.setTotalPages(totalPage);
		return searchResult;
	}

}
