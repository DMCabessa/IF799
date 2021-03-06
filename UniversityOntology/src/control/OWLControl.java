package control;

import java.util.Hashtable;
import java.util.Set;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import exceptions.InconsistentAssertionException;
import repository.owl.OWLRepository;
import repository.owl.interfaces.OWLRepositoryInterface;
import util.Constants;

public class OWLControl {
	private static OWLControl singleton;
	private OWLRepositoryInterface repository;
	
	public static OWLControl getInstance(){
		if(singleton == null){
			singleton = new OWLControl();
		}
		
		return singleton;
	}
	
	private OWLControl(){
		this.repository = OWLRepository.getInstance();
	}

	public void insertObjectProperty(String propertyName, String sourceName, String targetName)
			throws OWLOntologyCreationException, OWLOntologyStorageException, InconsistentAssertionException {
		OWLDataFactory df = Constants.DATA_FACTORY;
		
		OWLIndividual source = df.getOWLNamedIndividual(
		IRI.create("#" + sourceName));
		OWLIndividual target = df.getOWLNamedIndividual(
		IRI.create("#" + targetName));
		OWLObjectProperty property = df.getOWLObjectProperty(
		IRI.create("#" + propertyName));
		
		repository.insertObjectProperty(property, source, target, false);
	}
	
	public void insertDataProperty(String propertyName, String sourceName, String value)
			throws OWLOntologyCreationException, OWLOntologyStorageException, InconsistentAssertionException {
		
		OWLDataFactory df = Constants.DATA_FACTORY;
		
		OWLIndividual source = df.getOWLNamedIndividual(
		IRI.create("#" + sourceName));
		OWLDataProperty property = df.getOWLDataProperty(
		IRI.create("#" + propertyName));
		OWLLiteral literal;
		try {
			int intValue = Integer.parseInt(value);
			literal = df.getOWLLiteral(intValue);
		} catch (NumberFormatException e) {
			if(value.equalsIgnoreCase("true")){
				boolean boolValue = true;
				literal = df.getOWLLiteral(boolValue);
			}else if(value.equalsIgnoreCase("false")){
				boolean boolValue = false;
				literal = df.getOWLLiteral(boolValue);
			}else{
				literal = df.getOWLLiteral(value);
			}
		}		
		repository.insertDataProperty(property, source, literal, false);
	}

	public Set<OWLClass> getClasses() throws OWLOntologyCreationException {
		return repository.getClasses();
	}

	public Set<OWLObjectProperty> getObjectProperties()
			throws OWLOntologyCreationException {
		return repository.getObjectProperties();
	}

	public Set<OWLDataProperty> getDataProperties()
			throws OWLOntologyCreationException {
		return repository.getDataProperties();
	}

	public Set<OWLClass> getSuperClasses(String toSearch) throws OWLOntologyCreationException {
		return repository.getSuperClasses(toSearch);
	}
	
	public Set<OWLClass> getSubClasses(String toSearch) throws OWLOntologyCreationException {
		return repository.getSubClasses(toSearch);
	}
	
	public Set<OWLClass> getEquivalentClasses(String toSearch) throws OWLOntologyCreationException {
		return repository.getEquivalentClasses(toSearch);
	}
	
	public Set<OWLNamedIndividual> getIndividuals(String toSearch) throws OWLOntologyCreationException {
		return repository.getIndividuals(toSearch);
	}

	public Hashtable<String, String> getDataProperties(OWLNamedIndividual individual) throws OWLOntologyCreationException {
		Set<OWLDataPropertyAssertionAxiom> properties = repository.getAxiomsByIndividual(individual);
		
		Hashtable<String, String> table = new Hashtable<String, String>();
		for(OWLDataPropertyAssertionAxiom dpaa : properties){
			table.put(dpaa.getProperty().asOWLDataProperty().getIRI().getFragment(), dpaa.getObject().getLiteral());
		}
		
		return table;
	}

	public Set<OWLDataPropertyAssertionAxiom> getAllDataProperties() throws OWLOntologyCreationException {
		return repository.getAllDataProperties();
	}

	public Set<OWLObjectPropertyAssertionAxiom> getAllObjectProperties() throws OWLOntologyCreationException {
		return repository.getAllObjectProperties();
	}
	
	
}
