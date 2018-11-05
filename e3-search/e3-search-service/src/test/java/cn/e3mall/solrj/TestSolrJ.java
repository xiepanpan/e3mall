package cn.e3mall.solrj;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrJ {
//	/**
//	 *  添加文档
//	 * <p>Title: addDocument</p>
//	 * <p>Description: </p>
//	 * @throws Exception
//	 */
//	@Test
//	public void addDocument() throws Exception {
//		//创建一个SolrServer对象 创建连接 参数solr服务的url
//		SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
//		//创建文档对象SolrInputDocument
//		SolrInputDocument document =new SolrInputDocument();
//		//向文档对象添加域， 文档中必须包含一个id域 所有的域的名称在schema.xml中定义
//		document.addField("id", "doc01");
//		document.addField("item_title", "测试商品01");
//		document.addField("item_price", 1000);
//		//吧文档写入索引库
//		solrServer.add(document);
//		//提交
//		solrServer.commit();
//	}
//	
//	/**
//	 * 删除文档
//	 * <p>Title: deleteDocument</p>
//	 * <p>Description: </p>
//	 * @throws Exception
//	 */
//	@Test
//	public void deleteDocument() throws Exception{
//		SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
//		//删除文档
////		solrServer.deleteById("doc01");
//		//根据id删除
//		solrServer.deleteByQuery("id:doc01");
//		solrServer.commit();
//	}
//	/**
//	 * 简答查询测试
//	 * <p>Title: queryIndex</p>
//	 * <p>Description: </p>
//	 * @throws Exception 
//	 */
//	@Test
//	public void queryIndex() throws Exception {
//		SolrServer solrServer =new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
//		SolrQuery solrQuery = new SolrQuery();
//		//设置执行查询
//		solrQuery.setQuery("*:*");
//		//执行查询
//		QueryResponse queryResponse = solrServer.query(solrQuery);
//		SolrDocumentList solrDocumentList = queryResponse.getResults();
//		System.out.println("查询结果总记录数："+solrDocumentList.getNumFound());
//		for (SolrDocument solrDocument : solrDocumentList) {
//			System.out.println(solrDocument.get("id"));
//			System.out.println(solrDocument.get("item_title"));
//			System.out.println(solrDocument.get("item_sell_point"));
//			System.out.println(solrDocument.get("item_price"));
//			System.out.println(solrDocument.get("item_image"));
//			System.out.println(solrDocument.get("item_category_name"));
//		}
//	}
//	
//	/**
//	 * 复杂搜索
//	 * @throws Exception 
//	 */
//	@Test
//	public void queryIndexComplex() throws Exception {
//		SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
//		SolrQuery solrQuery = new SolrQuery();
//		solrQuery.setQuery("手机");
//		solrQuery.setStart(0);//从第几条开始
//		solrQuery.setRows(20);//条数
//		solrQuery.set("df", "item_title");
//		solrQuery.setHighlight(true);
//		//设置高亮域
//		solrQuery.addHighlightField("item_title");
//		solrQuery.setHighlightSimplePre("<em>");
//		solrQuery.setHighlightSimplePost("</em>");
//		QueryResponse queryResponse = solrServer.query(solrQuery);
//		SolrDocumentList solrDocumentList = queryResponse.getResults();
//		System.out.println(solrDocumentList.getNumFound());
//		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
//		for (SolrDocument solrDocument : solrDocumentList) {
//			System.out.println(solrDocument.get("id"));
//			//取高亮显示
//			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
//			String title="";
//			if (list!=null&&list.size()>0) {
//				title=list.get(0);
//			}else {
//				title=(String) solrDocument.get("item_title");
//			}
//			//有高亮内容取高亮 没有取原来标题
//			System.out.println(title);
//			System.out.println(solrDocument.get("item_sell_point"));
//			System.out.println(solrDocument.get("item_price"));
//			System.out.println(solrDocument.get("item_image"));
//			System.out.println(solrDocument.get("item_category_name"));
//		}
//	}
}
