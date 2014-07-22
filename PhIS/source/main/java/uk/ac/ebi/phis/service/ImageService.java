package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import uk.ac.ebi.phis.solrj.pojo.ImagePojo;

@Service
public class ImageService {

	private HttpSolrServer solr;
	

	public static final class ImageField {
		public final static String PHENOTYPE_ANN_BAG = "phenotype_ann_bag";
		public final static String ANATOMY = "anatomy_id";
		public final static String GENE = "gene_id";
	}
	

	public ImageService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);

	}
	
	public String getImageByPhenotypeGeneAnatomy(String phenotype, String gene, String anatomy) throws SolrServerException{

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		if (phenotype != null){
			solrQuery.setFilterQueries(ImageField.PHENOTYPE_ANN_BAG + ":\""+ phenotype + "\"");
		}
		if (gene != null){
			solrQuery.setFilterQueries(ImageField.GENE + ":\""+ gene + "\"");		
		}
		if (anatomy != null){
			solrQuery.setFilterQueries(ImageField.ANATOMY + ":\""+ anatomy + "\"");
		}
		solrQuery.setRows(1000000);
//		solrQuery.setFields(GeneField.MGI_ACCESSION_ID);
		System.out.println("-----------------------" + solr.getBaseURL() + "/select?" + solrQuery);
		QueryResponse rsp = null;
		rsp = solr.query(solrQuery);
		SolrDocumentList res = rsp.getResults();
		return solr.getBaseURL() + "/select?" + solrQuery;
		
	}
	
	
	public void addBeans(List<ImagePojo> imageDocs){
		try {
			solr.addBeans(imageDocs);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
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
	
}