package util;

import java.io.File;

import org.apache.catalina.Session;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;

public interface Constants {
	String prefix = "/wtpwebapps/UniversityOntology/WEB-INF";
	String sufix = "/owl/project_ontology.owl";
	File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();
	
	File ONTOLOGY_FILE = new File(catalinaBase,prefix+sufix);
	IRI PROJECT_IRI = IRI.create(ONTOLOGY_FILE);
	OWLDataFactory DATA_FACTORY = OWLManager.getOWLDataFactory();
}
