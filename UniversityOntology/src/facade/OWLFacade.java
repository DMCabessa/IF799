package facade;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import control.OWLControl;

public class OWLFacade {
	private static OWLFacade singleton;
	private OWLControl control;
	
	public static OWLFacade getInstance(){
		if(singleton == null){
			singleton = new OWLFacade();
		}
		
		return singleton;
	}
	
	private OWLFacade(){
		this.control = OWLControl.getInstance();
	}

	public void insertObjectProperty(String propertyName, String sourceName,
			String targetName) throws OWLOntologyCreationException,
			OWLOntologyStorageException {
		control.insertObjectProperty(propertyName, sourceName, targetName);
	}

	public void insertDataProperty(String propertyName, String sourceName,
			boolean value) throws OWLOntologyCreationException,
			OWLOntologyStorageException {
		control.insertDataProperty(propertyName, sourceName, value);
	}

	public void insertDataProperty(String propertyName, String sourceName,
			double value) throws OWLOntologyCreationException,
			OWLOntologyStorageException {
		control.insertDataProperty(propertyName, sourceName, value);
	}

	public void insertDataProperty(String propertyName, String sourceName,
			float value) throws OWLOntologyCreationException,
			OWLOntologyStorageException {
		control.insertDataProperty(propertyName, sourceName, value);
	}

	public void insertDataProperty(String propertyName, String sourceName,
			int value) throws OWLOntologyCreationException,
			OWLOntologyStorageException {
		control.insertDataProperty(propertyName, sourceName, value);
	}

	public void insertDataProperty(String propertyName, String sourceName,
			String value) throws OWLOntologyCreationException,
			OWLOntologyStorageException {
		control.insertDataProperty(propertyName, sourceName, value);
	}

	public Set<OWLClass> getClasses() throws OWLOntologyCreationException {
		return control.getClasses();
	}

	public Set<OWLObjectProperty> getObjectProperties()
			throws OWLOntologyCreationException {
		return control.getObjectProperties();
	}

	public Set<OWLDataProperty> getDataProperties()
			throws OWLOntologyCreationException {
		return control.getDataProperties();
	}
	
	

}
