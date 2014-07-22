package uk.ac.ebi.phis.utils;

import j.Annotation;
import j.Coordinates;
import j.Dimensions;
import j.Image;
import j.OntologyTerm;
import j.OntologyTermArray;
import j.Roi;

import java.util.List;

import uk.ac.ebi.phis.utils.ontology.OntologyUtils;

public class ValidationUtils {

	private OntologyUtils ou;


	public ValidationUtils() {

		ou = new OntologyUtils();
	}


	// positiveDimensions
	public boolean hasPositieDimensions(Dimensions d) {

		if (d.getImageHeight() > 0 && d.getImageWidth() > 0) {
			if (d.getImageDepth() == null || (d.getImageDepth() != null && d.getImageDepth() > 0)) { return true; }
		}
		return false;
	}


	// percentageCoords()

	public boolean arePercentagesOk(Coordinates coords) {

		for (Float p : coords.getXCoordinates().getEl()) {
			if (p < 0 || p > 100) { return false; }
		}
		for (Float p : coords.getYCoordinates().getEl()) {
			if (p < 0 || p > 100) { return false; }
		}
		if (coords.getZCoordinates() != null) {
			for (Float p : coords.getXCoordinates().getEl()) {
				if (p < 0 || p > 100) { return false; }
			}
		}
		return true;
	}


	public boolean hasValidOntologyTerms(Image img) {

		boolean res = true;
		Annotation ann = img.getDepictedAnatomicalStructure();
		// depicted anatomy
		res = res && checkOntologyTerm(ann, "anatomy");
		if (!res) {
			System.out.println(">>> Ontology term is not a velid entry for anatomy.");
			return false;
		}
		// stage
		if (img.getOrganism().getStage() != null) {
			res = res && checkOntologyTerm(img.getOrganism().getStage(), "stage");
			if (!res) {
				System.out.println(">>> Ontology term " + img.getOrganism().getStage().getTermId() + "(" + img.getOrganism().getStage().getTermLabel() + ") is not a velid entry for stage.");
				return false;
			}
		}
		List<OntologyTerm> ontologyTermArray;
		if (img.getImageDescription().getSamplePreparation() != null) {
			ontologyTermArray = img.getImageDescription().getSamplePreparation().getEl();
			if (ontologyTermArray != null) {
				for (OntologyTerm ot : ontologyTermArray) {
					res = res && checkOntologyTerm(ot, "samplePreparation");
					if (!res) {
						System.out.println(">>> Ontology term " + ot.getTermId() + "(" + ot.getTermLabel() + ") is not a velid entry for samplePreparation.");
						return false;
					}
				}
			}
		}
		if (img.getImageDescription().getVisualisationMethod() != null) {
			ontologyTermArray = img.getImageDescription().getVisualisationMethod().getEl();
			if (ontologyTermArray != null) {
				for (OntologyTerm ot : ontologyTermArray) {
					res = res && checkOntologyTerm(ot, "visualisationMethod");
					if (!res) {
						System.out.println(">>> Ontology term " + ot.getTermId() + "(" + ot.getTermLabel() + ") is not a velid entry for visualisationMethod.");
						return false;
					}
				}
			}
		}
		if (img.getImageDescription().getImagingMethod() != null) {
			ontologyTermArray = img.getImageDescription().getImagingMethod().getEl();
			if (ontologyTermArray != null) {
				for (OntologyTerm ot : ontologyTermArray) {
					res = res && checkOntologyTerm(ot, "imagingMethod");
					if (!res) {
						System.out.println(">>> Ontology term " + ot.getTermId() + "(" + ot.getTermLabel() + ") is not a velid entry for imagingMethod.");
						return false;
					}
				}
			}
		}
		return res;
	}


	public boolean isValidOntologyTerms(Roi roi) {

		List<Annotation> annList;
		boolean res = true;
		if (roi.getAbnormalityInAnatomicalStructure() != null) {
			// Do anatomy terms first
			annList = roi.getAbnormalityInAnatomicalStructure().getEl();
			annList.addAll(roi.getDepictedAnatomicalStructure().getEl());
			// Check if any ids present
			if (annList != null) {
				for (Annotation ann : annList) {
					res = res && checkOntologyTerm(ann, "anatomy");
				}
			}
		}

		if (roi.getPhenotypeAnnotations() != null) {
			// Phenotype terms
			annList = roi.getPhenotypeAnnotations().getEl();
			// Check if any ids present
			if (annList != null) {
				for (Annotation ann : annList) {
					res = res && checkOntologyTerm(ann, "phenotype");
				}
			}
		}
		return res;
	}


	/**
	 * 
	 * @param ann
	 * @param type can be one of phenotype, anatomy, stage,
	 * @return
	 */
	private boolean checkOntologyTerm(Annotation ann, String type) {

		if (ann != null) { return checkOntologyTerm(ann.getOntologyTerm(), type); }

		return true;
	}


	private boolean checkOntologyTerm(OntologyTerm ot, String type) {

		if (ot != null) {
			if (ot.getTermLabel() != null && ot.getTermId() == null) {
				// We don't allow label only
				System.out.println("We don't allow label only img id");
				return false;
			} else if (ot.getTermId() != null) {
				// check they are from the right ontology
				boolean isValid = true;
				if (type.equalsIgnoreCase("anatomy")) {
					isValid = ou.isAnatomy(ot.getTermId());
				} else if (type.equalsIgnoreCase("phenotype")) {
					isValid = ou.isPhenotype(ot.getTermId());
				} else if (type.equalsIgnoreCase("stage")) {
					isValid = ou.isStage(ot.getTermId());
				} else if (type.equalsIgnoreCase("samplePreparation")) {
					isValid = ou.isSamplePreparation(ot.getTermId());
				} else if (type.equalsIgnoreCase("visualisationMethod")) {
					isValid = ou.isImaveVisualization(ot.getTermId());
				} else if (type.equalsIgnoreCase("imagingMethod")) {
					isValid = ou.isImagingMethod(ot.getTermId());
				}
				// check label and id match
				isValid = isValid && ou.labelMatchesId(ot.getTermLabel(), ot.getTermId());
				if (!isValid) {
					System.out.println(">> Label matches id? " + ou.labelMatchesId(ot.getTermLabel(), ot.getTermId()) + " for " + ot.getTermId() + " " + ot.getTermLabel() + "\n>> Or the term is not present in the ontologies known for this field. ");
				}
				return isValid;
			}
		}
		return false;
	}

}