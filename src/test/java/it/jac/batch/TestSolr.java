package it.jac.batch;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class TestSolr {

	public static void main(String[] args) throws Exception {
		String urlString = "http://localhost:8983/solr/images";
		HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();

//		SolrInputDocument document = new SolrInputDocument();
//		document.addField("uuid", UUID.randomUUID().toString());
//		document.addField("filename", "image1.jpg");
//		document.addField("filepath", System.getProperty("java.io.tmpdir")+"\\image1.jpg");
//		document.addField("filesize", "130kb");
//		document.addField("color", "giallo;yellow");
//		solr.add(document);
//		solr.commit();
		
		SolrQuery query = new SolrQuery();
		query.set("q", "color:yellow");
		QueryResponse response = solr.query(query);

		SolrDocumentList docList = response.getResults();

		for (SolrDocument doc : docList) {
		    System.out.println(doc);
		}
	}

}
