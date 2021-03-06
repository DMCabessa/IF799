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
	String prefix = windows_prefix;
	String sufix = "/owl/project_ontology.owl";
	String other = "/owl/temporary_ontology.owl";
	File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();
	
	File ONTOLOGY_FILE = new File(catalinaBase,prefix+sufix);
	File ONTOLOGY_TEMP = new File(catalinaBase,prefix+other);
	//File ONTOLOGY_FILE = new File("WebContent/WEB-INF/owl/project_ontology.owl");
	IRI PROJECT_IRI = IRI.create(ONTOLOGY_FILE);
	IRI ANOTHER_IRI = IRI.create(ONTOLOGY_TEMP);
	OWLDataFactory DATA_FACTORY = OWLManager.getOWLDataFactory();
}
