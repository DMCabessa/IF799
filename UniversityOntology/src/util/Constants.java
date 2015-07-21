package util;

import java.io.File;

import org.apache.catalina.Session;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;

public interface Constants {
	String windows_prefix = "/wtpwebapps/UniversityOntology/WEB-INF";
	String linux_prefix = "/webapps/UniversityOntology/WEB-INF";
	// Usage:
	// * Local server (on Windows) -> windows_prefix
	// * Remote server (either dev os sistemas) -> linux_prefix
	String prefix = linux_prefix;
	String sufix = "/owl/project_ontology.owl";
	File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();
	
	File ONTOLOGY_FILE = new File(catalinaBase,prefix+sufix);
	IRI PROJECT_IRI = IRI.create(ONTOLOGY_FILE);
	OWLDataFactory DATA_FACTORY = OWLManager.getOWLDataFactory();
}
