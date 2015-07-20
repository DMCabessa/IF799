package util;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;

public interface Constants {
	File ONTOLOGY_FILE = new File("owl/project_ontology.owl");
	IRI PROJECT_IRI = IRI.create(ONTOLOGY_FILE);
	OWLDataFactory DATA_FACTORY = OWLManager.getOWLDataFactory();
}
