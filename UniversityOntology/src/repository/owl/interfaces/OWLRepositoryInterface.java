package repository.owl.interfaces;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public interface OWLRepositoryInterface {

	public void insertObjectProperty(OWLObjectProperty property, OWLIndividual source, OWLIndividual target) throws OWLOntologyCreationException, OWLOntologyStorageException;
	
	public void insertDataProperty(OWLDataProperty property, OWLIndividual source, OWLLiteral literal) throws OWLOntologyCreationException, OWLOntologyStorageException;
	
	public Set<OWLClass> getClasses() throws OWLOntologyCreationException;
	
	public Set<OWLObjectProperty> getObjectProperties() throws OWLOntologyCreationException;
	
	public Set<OWLDataProperty> getDataProperties() throws OWLOntologyCreationException;

	public Set<String> getIndividuals(String toSearch) throws OWLOntologyCreationException;
	
	public Set<String> getEquivalentClasses(String toSearch) throws OWLOntologyCreationException;
	
	public Set<String> getSubClasses(String toSearch) throws OWLOntologyCreationException;
	
	public Set<String> getSuperClasses(String toSearch) throws OWLOntologyCreationException;
}
