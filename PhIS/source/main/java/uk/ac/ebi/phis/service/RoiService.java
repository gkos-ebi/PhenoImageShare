package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import uk.ac.ebi.phis.solrj.dto.ChannelPojo;
import uk.ac.ebi.phis.solrj.dto.RoiPojo;
import uk.ac.ebi.phis.utils.web.JSONRestUtil;


public class RoiService {

	private HttpSolrServer solr;
	

	public static final class RoiField {
		
	}

	public RoiService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);
	}
	
	public String getRoiAsJsonString(String channelId){
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFilterQueries(RoiPojo.ID + ":\""+ channelId + "\"");
		solrQuery.set("wt", "json");
		
		System.out.println("------ ROI" + getQueryUrl(solrQuery));

		try {
			return JSONRestUtil.getResults(getQueryUrl(solrQuery)).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "Couldn't get anything back from solr.";
	}	

	public void addBeans(List<RoiPojo> docs){
		try {
			solr.addBeans(docs);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Removes all documents from the core
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public void clear() throws SolrServerException, IOException{
		solr.deleteByQuery("*:*");
	}
	

	public String getQueryUrl(SolrQuery q){
		return solr.getBaseURL() + "/select?" + q.toString();
	}
	
}
