package cn.e3mall.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.experimental.theories.PotentialAssignment.CouldNotGenerateValueException;

public class TestSolrCloud {
	
	/**
	 * solr集群添加测试
	 * <p>Title: testAddDocument</p>
	 * <p>Description: </p>
	 * @throws Exception
	 */
//	@Test
//	public void testAddDocument() throws Exception {
//		//创建集群的连接
//		CloudSolrServer cloudSolrServer = new CloudSolrServer("192.168.25.129:2181,192.168.25.129:2182,192.168.25.129:2183");
//		cloudSolrServer.setDefaultCollection("collection2");
//		//创建文档对象
//		SolrInputDocument document = new SolrInputDocument();
//		document.setField("id", "solrcloud01");
//		document.setField("item_title", "测试商品01");
//		document.setField("item_price", 123);
//		//文件写入索引库
//		cloudSolrServer.add(document);
//		//提交
//		cloudSolrServer.commit();
//	}
//	
//	/**
//	 * solr集群查询
//	 * <p>Title: testQueryDocument</p>
//	 * <p>Description: </p>
//	 * @throws Exception
//	 */
//	@Test
//	public void testQueryDocument() throws Exception {
//		CloudSolrServer cloudSolrServer = new CloudSolrServer("192.168.25.129:2181,192.168.25.129:2182,192.168.25.129:2183");
//		cloudSolrServer.setDefaultCollection("collection2");
//		SolrQuery solrQuery = new SolrQuery();
//		solrQuery.setQuery("*:*");
//		QueryResponse queryResponse = cloudSolrServer.query(solrQuery);
//		SolrDocumentList solrDocumentList = queryResponse.getResults();
//		System.out.println("总记录数："+solrDocumentList.getNumFound());
//		for (SolrDocument solrDocument : solrDocumentList) {
//			System.out.println(solrDocument.get("id"));
//			System.out.println(solrDocument.get("title"));
//			System.out.println(solrDocument.get("item_title"));
//			System.out.println(solrDocument.get("item_price"));
//		}
//	}
}
