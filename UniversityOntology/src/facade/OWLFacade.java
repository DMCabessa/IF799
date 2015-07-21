package facade;

import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
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

	public Set<String> getProperties(String type) {
		Set<String> toReturn = new HashSet<String>();
		try {
			if (type.equals("object")) {
				Set<OWLObjectProperty> properties = getObjectProperties();
				if (properties != null) {
					for (OWLObjectProperty p : properties) {
						toReturn.add(p.getIRI().getFragment());
					}
				}
			} else if (type.equals("data")) {
				Set<OWLDataProperty> properties = getDataProperties();
				if (properties != null) {
					for (OWLDataProperty p : properties) {
						toReturn.add(p.getIRI().getFragment());
					}
				}
			}
		} catch (OWLOntologyCreationException e) {
			
		}
		return toReturn;
	}
	
	public Set<OWLDataProperty> getDataProperties()
			throws OWLOntologyCreationException {
		return control.getDataProperties();
	}
	
	public Set<OWLNamedIndividual> getIndividuals(String toSearch) throws OWLOntologyCreationException {
		return control.getIndividuals(toSearch);
	}
	
	public Set<OWLClass> getSuperClasses(String toSearch) throws OWLOntologyCreationException {
		return control.getSuperClasses(toSearch);
	}
	
	public Set<OWLClass> getSubClasses(String toSearch) throws OWLOntologyCreationException {
		return control.getSubClasses(toSearch);
	}
	
	public Set<OWLClass> getEquivalentClasses(String toSearch) throws OWLOntologyCreationException {
		return control.getEquivalentClasses(toSearch);
	}
	

}
