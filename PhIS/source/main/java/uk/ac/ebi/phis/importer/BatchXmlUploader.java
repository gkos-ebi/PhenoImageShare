package uk.ac.ebi.phis.importer;

import j.Annotation;
import j.AnnotationMode;
import j.Channel;
import j.Doc;
import j.GenotypeComponent;
import j.Image;
import j.ImageDescription;
import j.OntologyTerm;
import j.Organism;
import j.Roi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.solr.client.solrj.SolrServerException;
import org.xml.sax.SAXException;

import uk.ac.ebi.phis.service.ChannelService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;
import uk.ac.ebi.phis.solrj.pojo.ChannelPojo;
import uk.ac.ebi.phis.solrj.pojo.ImagePojo;
import uk.ac.ebi.phis.solrj.pojo.RoiPojo;
import uk.ac.ebi.phis.utils.ValidationUtils;

public class BatchXmlUploader {

	HashMap<String, Image> imageIdMap = new HashMap<>();
	HashMap<String, Channel> channelIdMap = new HashMap<>();
	HashMap<String, Roi> roiIdMap = new HashMap<>();

	ClassLoader classloader;

	
//	String solrImageBaseUrl = "http://localhost:8983/solr/collection1";
//	String solrImageBaseUrl; // = "http://localhost:8086/solr-example/images";

	ValidationUtils vu = new ValidationUtils();

	ImageService is;
	RoiService rs;
	ChannelService cs;
	
	public BatchXmlUploader(ImageService is, RoiService rs, ChannelService cs) {
		classloader = Thread.currentThread().getContextClassLoader();
		this.is = is;
		this.rs = rs;
		this.cs = cs;
	}


	public boolean validateAndUpload(String xmlLocation) {

		Doc doc;
		// Unmarshal XML
		doc = convertXmlToObjects(xmlLocation);
		boolean isValid = validate(xmlLocation, doc);
		upload(doc);
		return isValid;
	}


	private void upload(Doc doc) {

		try {
			doBatchSubmission(doc);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}


	private boolean validate(String xmlLocation, Doc doc) {

		InputStream xsd;
		InputStream xml;
		boolean isValid = false;
		try {
			xsd = classloader.getResourceAsStream("phisSchema.xsd");
			xml = classloader.getResourceAsStream(xmlLocation);
			isValid = validateAgainstXSD(xml, xsd);
			xsd.close();
			xml.close();
			isValid = (isValid && checkInformation(doc));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isValid;
	}


	private void doBatchSubmission(Doc doc)
	throws IOException, SolrServerException {

		is.clear();
		rs.clear();
		cs.clear();
		addImageDocuments(doc.getImage());
		//TODO rois
		addRoiDocuments(doc.getRoi());
		//TODO channels
		addChannelDocuments(doc.getChannel());
	}


	private void addImageDocuments(List<Image> images)
	throws IOException, SolrServerException {
		
		int i = 0;
		List<ImagePojo> imageDocs = new ArrayList<>();
		for (Image img : images) {
			// add it
			imageDocs.add(fillPojo(img));
			// flush every 1000 docs
			if (i++ % 1000 == 0) {
				is.addBeans(imageDocs);
				imageDocs = new ArrayList<>();
			}
		}
		is.addBeans(imageDocs);
	}

	private void addRoiDocuments(List<Roi> rois)
	throws IOException, SolrServerException {
		
		int i = 0;
		List<RoiPojo> roiDocs = new ArrayList<>();
		for (Roi roi : rois) {
			// add it
			roiDocs.add(fillPojo(roi));
			// flush every 1000 docs
			if (i++ % 1000 == 0) {
				rs.addBeans(roiDocs);
				roiDocs = new ArrayList<>();
			}
		}
		rs.addBeans(roiDocs);
	}

	private void addChannelDocuments(List<Channel> channels)
	throws IOException, SolrServerException {
		
		int i = 0;
		List<ChannelPojo> chDocs = new ArrayList<>();
		for (Channel channel : channels) {
			// add it
			chDocs.add(fillPojo(channel));
			// flush every 1000 docs
			if (i++ % 1000 == 0) {
				cs.addBeans(chDocs);
				chDocs = new ArrayList<>();
			}
		}
		cs.addBeans(chDocs);
	}

	// fillRoiPojo
	private RoiPojo fillPojo(Roi roi){
		
		RoiPojo bean = new RoiPojo();
		
		bean.setId(roi.getId());

		bean.setAssociatedImage(roi.getAssociatedImage());
		
		if (roi.getAssociatedChannel() != null){
			bean.setAssociatedChannel(roi.getAssociatedChannel().getEl());
		}
		
		if (roi.getDepictedAnatomicalStructure() != null){
			
			List<String> ids = new ArrayList<>(); // || with labels
			List<String> labels = new ArrayList<>(); // || with ids
			List<String> freetext = new ArrayList<>();
			List<String> computedIds = new ArrayList<>(); // || with computedLabels
			List<String> computedLabels = new ArrayList<>(); // || with computedId
			
			// Depicted anatomy
			// TODO have to check if it's expression (from channel)
			for ( Annotation ann: roi.getDepictedAnatomicalStructure().getEl()){
				if (ann.getAnatomyFreetext() != null){
					freetext.add(ann.getAnatomyFreetext());
				}
				if (ann.getOntologyTerm() != null){
					if (ann.getAnnotationMode() != null && (ann.getAnnotationMode() == AnnotationMode.AUTOMATED)){
						computedIds.add(ann.getOntologyTerm().getTermId());
						computedLabels.add(ann.getOntologyTerm().getTermLabel());
					}
					else {
						ids.add(ann.getOntologyTerm().getTermId());
						labels.add(ann.getOntologyTerm().getTermLabel());
					}
				}
			}
			if (ids.size() > 0){
				bean.setDepictedAnatomyId(ids);
				bean.setDepictedAnatomyTerm(labels);
			}
			if (freetext.size() > 0){
				bean.setDepictedAnatomyFreetext(freetext);
			}
			if (computedIds.size() > 0){
				bean.setComputedDepictedAnatomyId(computedIds);
				bean.setComputedDepictedAnatomyTerm(computedLabels);
			}
		}
		
		if (roi.getAbnormalityInAnatomicalStructure() != null){
			
			List<String> ids = new ArrayList<>(); // || with labels
			List<String> labels = new ArrayList<>(); // || with ids
			List<String> freetext = new ArrayList<>();
			List<String> computedIds = new ArrayList<>(); // || with computedLabels
			List<String> computedLabels = new ArrayList<>(); // || with computedId
			// Abnormality in anatomical part
			for ( Annotation ann: roi.getAbnormalityInAnatomicalStructure().getEl()){
				if (ann.getAnatomyFreetext() != null){
					freetext.add(ann.getAnatomyFreetext());
				}
				if (ann.getOntologyTerm() != null){
					if (ann.getAnnotationMode() != null && (ann.getAnnotationMode() == AnnotationMode.AUTOMATED)){
						computedIds.add(ann.getOntologyTerm().getTermId());
						computedLabels.add(ann.getOntologyTerm().getTermLabel());
					}
					else {
						ids.add(ann.getOntologyTerm().getTermId());
						labels.add(ann.getOntologyTerm().getTermLabel());
					}
				}
			}
			if (ids.size() > 0){
				bean.setAbnormalityAnatomyId(ids);
				bean.setAbnormalityAnatomyTerm(labels);
			}
			if (freetext.size() > 0){
				bean.setAbnormalityAnatomyFreetext(freetext);
			}
			if (computedIds.size() > 0){
				bean.setComputedAbnormalityAnatomyId(computedIds);
				bean.setComputedAbnormalityAnatomyTerm(computedLabels);
			}
		}
		
		if (roi.getPhenotypeAnnotations() != null){
			// Phenotypes
			//TODO copy this to ann_bag in images
			List<String> ids = new ArrayList<>(); // || with labels
			List<String> labels = new ArrayList<>(); // || with ids
			List<String> freetext = new ArrayList<>();
			for ( Annotation ann: roi.getPhenotypeAnnotations().getEl()){
				if (ann.getAnatomyFreetext() != null){
					freetext.add(ann.getAnatomyFreetext());
				}
				if (ann.getOntologyTerm() != null){
					ids.add(ann.getOntologyTerm().getTermId());
					labels.add(ann.getOntologyTerm().getTermLabel());
				}
			}
			if (ids.size() > 0){
				bean.setPhenotypeId(ids);
				bean.setPhenotypeTerm(labels);
			}
			if (freetext.size() > 0){
				bean.setPhenotypeFreetext(freetext);
			}
		}
		
		if (roi.getObservations() != null){
			bean.setObservations(roi.getObservations().getEl());
		}

		bean.setXCoordinates(roi.getCoordinates().getXCoordinates().getEl());
		bean.setYCoordinates(roi.getCoordinates().getYCoordinates().getEl());
		if (roi.getCoordinates().getZCoordinates() != null){
			bean.setZCoordinates(roi.getCoordinates().getZCoordinates().getEl());
		}
		
		return bean;
	}
	
	
	// fillChannelPojo
	private ChannelPojo fillPojo(Channel channel) {

		ChannelPojo bean = new ChannelPojo();
		
		bean.setId(channel.getId());
		bean.setAssociatedImage(channel.getAssociatedImage());
		if (channel.getAssociatedRoi() != null){
			bean.setAssociatedRoi(channel.getAssociatedRoi().getEl());
		}
		if (channel.getDepictsExpressionOf() != null){
			GenotypeComponent gc = channel.getDepictsExpressionOf();
			if (gc.getGeneId() != null){
				bean.setGeneId(gc.getGeneId());
				bean.setGeneSymbol(gc.getGeneSymbol());
			}
			if (gc.getGeneticFeatureEnsemblId() != null){
				bean.setGeneticFeatureEnsemlId(gc.getGeneticFeatureEnsemblId());
			}
			if (gc.getGeneticFeatureId() != null){
				bean.setGeneticFeatureId(gc.getGeneticFeatureId());
				bean.setGeneticFeatureSymbol(gc.getGeneticFeatureSymbol());
			}
			if (gc.getMarker() != null){
				bean.setMarker(gc.getMarker());
			}
			if (gc.getZygosity() != null){
				bean.setZygosity(gc.getZygosity().name());
			}
			if (gc.getGenomicLocation() != null){
				bean.setStartPos(gc.getGenomicLocation().getStartPos());
				if (gc.getGenomicLocation().getEndPos() != null){
					bean.setEndPos(gc.getGenomicLocation().getEndPos());
				}
				bean.setChromosome(gc.getGenomicLocation().getChromosone());
				bean.setStrand(gc.getGenomicLocation().getStrand());
			}
		}
		
		return bean;
	
	}

	private ImagePojo fillPojo(Image img) {

		ImagePojo bean = new ImagePojo();
		bean.setTaxon(img.getOrganism().getTaxon());
		bean.setId(img.getId());

		ImageDescription desc = img.getImageDescription();
		bean.setImageGeneratedBy(desc.getImageGeneratedBy());
		bean.setSampleGeneratedBy(desc.getOrganismGeneratedBy());
		bean.setHostName(desc.getHostName());
		bean.setHostUrl(desc.getHostUrl());
		bean.setImageUrl(desc.getImageUrl());
		bean.setOriginalImageId(desc.getOriginalImageId());
		if (desc.getImageContextUrl() != null){
			bean.setImageContextUrl(desc.getImageContextUrl());
		}

		if (img.getAssociatedRoi() != null){
			bean.setAssociatedRoi(img.getAssociatedRoi().getEl());
		}

		if (img.getAssociatedChannel() != null){
			bean.setAssociatedChannel(img.getAssociatedChannel().getEl());
		}
		
		if (desc.getImageDimensions().getImageDepth() != null){
			bean.setDepth(desc.getImageDimensions().getImageDepth());
		}

		bean.setHeight(desc.getImageDimensions().getImageHeight());

		bean.setWidth(desc.getImageDimensions().getImageWidth());

		if (desc.getImagingMethod() != null){
			for (OntologyTerm im: desc.getImagingMethod().getEl()){
				bean.setImagingMethodId(im.getTermId());
				bean.setImagingMethodLabel(im.getTermLabel());
			}
		}
		if (desc.getSamplePreparation() != null){
			for (OntologyTerm sp: desc.getSamplePreparation().getEl()){
				bean.setSamplePreparationId(sp.getTermId());
				bean.setSamplePreparationLabel(sp.getTermLabel());
			}
		}
		if (desc.getVisualisationMethod() != null){
			for (OntologyTerm vm: desc.getVisualisationMethod().getEl()){
				bean.setVisualisationMethodId(vm.getTermId());
				bean.setVisualisationMethodLabel(vm.getTermLabel());
			}
		}
		
		if (desc.getMachine() != null){
			bean.setMachine(desc.getMachine());			
		}

		// TODO bean.setThumbnailPath(thumbnailPath);;

		// Sample
		
		Organism org = img.getOrganism();
		
		if (org.getAge() != null){
			if (org.getAge().getAgeSinceBirth() != null){
				bean.setAgeSinceBirth(org.getAge().getAgeSinceBirth());
			}
			if (org.getAge().getEmbryonicAge() != null){
				bean.setAgeSinceBirth(org.getAge().getEmbryonicAge());
			}
		}

		if (org.getNcbiTaxonId() != null){
			bean.setNcbiTaxonId(org.getNcbiTaxonId());
		}

		if (org.getSex() != null){
			bean.setSex(org.getSex().name());
		}

		if (org.getStage() != null){
			bean.setStage(org.getStage().getTermLabel());
			bean.setStageId(org.getStage().getTermId());
		}

		// annotations -->
		if (img.getDepictedAnatomicalStructure() != null){
			if (img.getDepictedAnatomicalStructure().getAnatomyFreetext() != null){
				bean.setAnatomyFreetext(img.getDepictedAnatomicalStructure().getAnatomyFreetext());
			}
			if (img.getDepictedAnatomicalStructure().getOntologyTerm() != null){
				bean.setAnatomyId(img.getDepictedAnatomicalStructure().getOntologyTerm().getTermId());
				bean.setAnatomyTerm(img.getDepictedAnatomicalStructure().getOntologyTerm().getTermLabel());
			}
			
		}

		// field name="anatomy_computed_id" /-->
		// field name="anatomy_computed_term" /-->
		// field name="anatomy_ann_bag" /-->
		// field name="other_ann_bag" /-->
		// field name="phenotype_ann_bag" /-->

		if (img.getObservations() != null){
			bean.setObservations(img.getObservations().getEl());
		}

		if (img.getConditions() != null ){
			bean.setConditions(img.getConditions().getEl());
		}
		
		// genetic features -->

		if (img.getMutantGenotypeTraits() != null){
			ArrayList<String> geneIds = new ArrayList<>();
			ArrayList<String> geneSymbols = new ArrayList<>();
			ArrayList<String> gfEnsembl = new ArrayList<>();
			ArrayList<String> gfIds = new ArrayList<>();
			ArrayList<String> gfSymbols = new ArrayList<>();
			ArrayList<String> chromosome = new ArrayList<>();
			ArrayList<Long> insertionSite = new ArrayList<>();
			ArrayList<Long> startPosition = new ArrayList<>();
			ArrayList<Long> endPosition = new ArrayList<>();
			ArrayList<String> strand = new ArrayList<>();
			ArrayList<String> zygosity = new ArrayList<>();
			
			for (GenotypeComponent g : img.getMutantGenotypeTraits().getEl()){
				//TODO maybe add empty strings if null? Test first if this works for the empty fields.
				// We need to fill all of these arrays because they need to be parallel
				geneIds.add(g.getGeneId());
				geneSymbols.add(g.getGeneSymbol());
				gfEnsembl.add(g.getGeneticFeatureEnsemblId());
				gfIds.add(g.getGeneticFeatureId());
				gfSymbols.add(g.getGeneSymbol());
				if (g.getGenomicLocation() != null){
					chromosome.add(g.getGenomicLocation().getChromosone());
					startPosition.add(g.getGenomicLocation().getStartPos());
					endPosition.add(g.getGenomicLocation().getEndPos());
					strand.add(g.getGenomicLocation().getStrand());
				}
				zygosity.add(g.getZygosity().name());
			}
			
			bean.setGeneIds(geneIds);
			bean.setGeneSymbols(geneSymbols);
			bean.setGeneticFeatureIds(gfIds);
			bean.setGenetifFeatureEnsemlIds(gfEnsembl);
			bean.setGeneticFeatureSymbols(gfSymbols);
			
			bean.setChromosome(chromosome);
			bean.setInsertionSite(insertionSite);
			bean.setStartPosition(startPosition);
			bean.setEndPosition(endPosition);
			bean.setStrand(strand);
			bean.setZygosity(zygosity);
			
		}

		// field name="expressed_gf_bag" /-->
		// field name="expressed_anatomy_bag" /-->
		return bean;
	}


	boolean validateAgainstXSD(InputStream xml, InputStream xsd) {

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(xsd));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
			return true;
		} catch (SAXException e) {
			System.out.println("NOT valid");
			System.out.println("Reason: " + e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}


	boolean checkInformation(Doc doc) {

		imageIdMap = new HashMap<>();
		channelIdMap = new HashMap<>();
		roiIdMap = new HashMap<>();

		// Create maps <id, Object> for quick reference
		for (Image img : doc.getImage()) {
			imageIdMap.put(img.getId(), img);
		}
		for (Roi roi : doc.getRoi()) {
			roiIdMap.put(roi.getId(), roi);
		}
		for (Channel ch : doc.getChannel()) {
			channelIdMap.put(ch.getId(), ch);
		}

		// Check associated image/channel/roi ids are valid a) they exist , b)
		// the link is reflezive
		boolean res = checkIdsReferenceEWxistingObjects();
		if (!res) { return false; }

		for (Image img : imageIdMap.values()) {

			// Check ontoloy fields contain ontology IDs and they are from the
			// right ontology
			// Check label & id match
			if (!vu.hasValidOntologyTerms(img)) {
				System.out.println("there was something wrong with the ontology terms for img id = " + img.getId());

			}

			// positive dimensions
			if (!vu.hasPositieDimensions(img.getImageDescription().getImageDimensions())) {
				System.out.println("Dimensions are not positive! Validation failed.");
				return false;
			}

		}

		for (Roi roi : roiIdMap.values()) {

			// percentages
			if (!vu.arePercentagesOk(roi.getCoordinates())) { return false; }

			// Check ontoloy fields contain ontology IDs and they are from the
			// right ontology
			// Check label & id match
			if (!vu.isValidOntologyTerms(roi)) {
				System.out.println("there was something wrong with the ontology terms for roi id = " + roi.getId());
				return false;
			}
		}
		return true;
	}


	private boolean checkIdsReferenceEWxistingObjects() {

		// Associated roi & channel for image really exist
		for (Image img : imageIdMap.values()) {
			if (img.getAssociatedRoi() != null) {
				for (String roiId : img.getAssociatedRoi().getEl()) {
					if (!roiIdMap.containsKey(roiId)) {
						System.out.println("roi id referenced without existing in image id = " + img.getId());
						return false;
					}
				}
			}
			if (img.getAssociatedChannel() != null) {
				for (String channelId : img.getAssociatedChannel().getEl()) {
					if (!channelIdMap.containsKey(channelId)) {
						System.out.println("channel id referenced without existing in image id = " + img.getId());
						return false;
					}
				}
			}
		}

		// Associated roi & image for channels really exist
		for (Channel channel : channelIdMap.values()) {
			if (channel.getAssociatedRoi() != null) {
				for (String roiId : channel.getAssociatedRoi().getEl()) {
					if (!roiIdMap.containsKey(roiId)) {
						System.out.println("roi id referenced without existing in channel id = " + channel.getId());
						return false;
					}
				}
			}
			if (channel.getAssociatedImage() != null) {
				if (!imageIdMap.containsKey(channel.getAssociatedImage())) {
					System.out.println("image id referenced without existing in channel id = " + channel.getId());
					return false;
				}
			}
		}

		// Associated image & channel for roi really exist
		for (Roi roi : roiIdMap.values()) {
			if (roi.getAssociatedChannel() != null) {
				for (String channelId : roi.getAssociatedChannel().getEl()) {
					if (!channelIdMap.containsKey(channelId)) {
						System.out.println("channel id referenced without existing in roi id = " + roi.getId());
						return false;
					}
				}
			}
			if (roi.getAssociatedImage() != null) {
				if (!imageIdMap.containsKey(roi.getAssociatedImage())) {
					System.out.println("image id referenced without existing in roi id = " + roi.getId());
					return false;
				}
			}
		}
		return true;
	}


	private Doc convertXmlToObjects(String xmlFullPathLocation) {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			System.out.println(">>>>>>" + xmlFullPathLocation);
			Doc doc = (Doc) jaxbUnmarshaller.unmarshal(classloader.getResourceAsStream(xmlFullPathLocation));
			return doc;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}