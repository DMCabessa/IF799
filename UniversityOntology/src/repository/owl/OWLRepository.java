package repository.owl;

import java.util.Set;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import repository.owl.interfaces.OWLRepositoryInterface;
import util.Constants;
import util.Util;

public class OWLRepository implements OWLRepositoryInterface{
	private static OWLRepository singleton;
	
	private OWLRepository(){}
	
	public static OWLRepository getInstance(){
		if(singleton == null){
			singleton = new OWLRepository();
		}
		return singleton;
	}
	
	public void close(){
		singleton = null;
	}
	
	public void insertObjectProperty(OWLObjectProperty property, OWLIndividual source, OWLIndividual target) throws OWLOntologyCreationException, OWLOntologyStorageException{
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		OWLDataFactory df = Constants.DATA_FACTORY;
		
		OWLAxiom assertion = df.getOWLObjectPropertyAssertionAxiom(property, source, target);
		m.addAxiom(o, assertion);
		
		m.saveOntology(o);
	}
	
	public void insertDataProperty(OWLDataProperty property, OWLIndividual source, OWLLiteral literal) throws OWLOntologyCreationException, OWLOntologyStorageException{
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		OWLDataFactory df = Constants.DATA_FACTORY;
		
		OWLAxiom assertion = df.getOWLDataPropertyAssertionAxiom(property, source, literal);
		m.addAxiom(o, assertion);
		
		m.saveOntology(o);
	}
	
	public Set<OWLClass> getClasses() throws OWLOntologyCreationException{
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		
		return o.getClassesInSignature();
	}
	
	public Set<OWLObjectProperty> getObjectProperties() throws OWLOntologyCreationException{
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		
		return o.getObjectPropertiesInSignature();
	}
	
	public Set<OWLDataProperty> getDataProperties() throws OWLOntologyCreationException{
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		
		return o.getDataPropertiesInSignature();
	}
}
