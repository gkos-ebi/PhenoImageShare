/*******************************************************************************
 * Copyright 2015 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 *******************************************************************************/
package uk.ac.ebi.phis.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.json.JSONObject;
import uk.ac.ebi.phis.dto.solrj.RoiDTO;
import uk.ac.ebi.phis.utils.web.JSONRestUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;


public class RoiService extends BasicService{

	
	public RoiService(String solrUrl) {
		super(solrUrl);
	}
	
	public String getRoiAsJsonString(String roiId, Integer resNo){
		
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		roiId = handleSpecialCharacters(roiId);
		solrQuery.setFilterQueries(RoiDTO.ID + ":"+ roiId);
		if (resNo != null){
			solrQuery.setRows(resNo);
		} else {
			solrQuery.setRows(10000);
		}
		solrQuery.set("wt", "json");
		
		System.out.println("------ ROI" + getQueryUrl(solrQuery));

		try {
			return JSONRestUtil.getResults(getQueryUrl(solrQuery)).toString();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "Couldn't get anything back from solr.";
	}

	public String getDownloadInfo(String imageId) throws SolrServerException {

		List<RoiDTO>rois = getRoisDTOs(imageId);
		return RoiDTO.getTabbedHeader() + "\n" + rois.stream().map(item -> item.getTabbedToString()).collect(Collectors.joining("\n"));

	}


	public void addBeanInstant(RoiDTO doc){

		try {
			solr.addBean(doc);
			solr.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RoiDTO getRoiById(String id){
		
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(RoiDTO.ID + ":"+ handleSpecialCharacters(id) );
		System.out.println("---- " + solr.getBaseURL() + "/select?" + solrQuery);
		try {
			List<RoiDTO> results = solr.query(solrQuery).getBeans(RoiDTO.class);
			if (results.size() > 0){
				return results.get(0);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<RoiDTO> getRoisDTOs(String imageId) throws SolrServerException {

		SolrQuery solrQuery = getRoiQuery(imageId,null, null, null, null, null, null, null,  Integer.MAX_VALUE);

		return solr.query(solrQuery).getBeans(RoiDTO.class);

	}


	public String getRois(String imageId, String roiId, String owner, String group, 
			String createdAfter, String createdBefore, String lastEditAfter, String lastEditBefore, Integer resNo){
		
		SolrQuery solrQuery = getRoiQuery(imageId, roiId, owner, group, createdAfter, createdBefore, lastEditAfter, lastEditBefore, resNo);
		
		try {
			return JSONRestUtil.getResults(getQueryUrl(solrQuery)).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "Couldn't get anything back from solr.";
	}


	private SolrQuery getRoiQuery(String imageId, String roiId, String owner, String group,
						  String createdAfter, String createdBefore, String lastEditAfter, String lastEditBefore, Integer resNo){

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		imageId = handleSpecialCharacters(imageId);
		if (imageId != null){
			solrQuery.setFilterQueries(RoiDTO.ASSOCIATED_IMAGE_ID + ":"+ imageId);
		}
		if (roiId != null){
			solrQuery.addFilterQuery(RoiDTO.ID + ":" + handleSpecialCharacters(roiId) );
		}
		if (owner != null){
			solrQuery.addFilterQuery(RoiDTO.USER_OWNER + ":\"" + owner + "\"");
		}
		if (group != null){
			solrQuery.addFilterQuery(RoiDTO.USER_GROUP + ":\"" + group + "\"");
		}
		if (lastEditAfter != null){
			if (lastEditBefore != null){
				solrQuery.addFilterQuery(RoiDTO.EDIT_DATE + ":[" + lastEditAfter + " TO " + lastEditBefore + "]");
			} else {
				solrQuery.addFilterQuery(RoiDTO.EDIT_DATE + ":[" + lastEditAfter + " TO *]");
			}
		}
		if (lastEditBefore != null){
			if (lastEditAfter != null){
				solrQuery.addFilterQuery(RoiDTO.EDIT_DATE + ":[" + lastEditAfter + " TO " + lastEditBefore + "]");
			} else {
				solrQuery.addFilterQuery(RoiDTO.EDIT_DATE + ":[* TO " + lastEditBefore + "]");
			}
		}
		if (createdAfter != null){
			if (createdBefore != null){
				solrQuery.addFilterQuery(RoiDTO.CREATION_DATE + ":[" + createdAfter + " TO " + createdBefore + "]");
			} else {
				solrQuery.addFilterQuery(RoiDTO.CREATION_DATE + ":[" + createdAfter + " TO *]");
			}
		}
		if (createdBefore != null){
			if (createdAfter != null){
				solrQuery.addFilterQuery(RoiDTO.CREATION_DATE + ":[" + createdAfter + " TO " + createdBefore + "]");
			} else {
				solrQuery.addFilterQuery(RoiDTO.CREATION_DATE + ":[* TO " + createdBefore + "]");
			}
		}

		if (resNo != null){
			solrQuery.setRows(resNo);
		} else {
			solrQuery.setRows(10000);
		}
		solrQuery.set("wt", "json");

		return solrQuery;
	}



	public void updateRoi(RoiDTO roi){
		addBeanInstant(roi);
	}

	public void addRoi(RoiDTO roi){
		addBeanInstant(roi);
	}


	public void addBean(RoiDTO doc){
		try {
			solr.addBean(doc, 30000);
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}



	public void deleteRoi(String roiId){

		try {
			solr.deleteByQuery(RoiDTO.ID + ":" + handleSpecialCharacters(roiId));
			solr.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes all documents from the core
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public void clear() 
	throws SolrServerException, IOException{
		solr.deleteByQuery("*:*");
		solr.commit();
	}
	

	public String getQueryUrl(SolrQuery q){
		return solr.getBaseURL() + "/select?" + q.toString();
	}
	
	/**
	 * @since 2015/08/11
	 * @return ReleaseDocument with #images and #rois filled
	 * @throws SolrServerException 
	 */
	public int getNumberOfDocuments() 
	throws SolrServerException{
		
		SolrQuery q = new SolrQuery()
		.setQuery("*:*")
		.setRows(0);
		
		QueryResponse res = solr.query(q);
		JSONObject obj = new JSONObject(res.getResponse().get("response"));
		
		return obj.getInt("numFound");
		
	}
	
}
