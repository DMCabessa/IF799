package repository.owl;

import java.util.HashSet;
import java.util.Set;

import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.HermiT.Reasoner.ReasonerFactory;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

import rationals.converters.ToRExpression;
import repository.owl.interfaces.OWLRepositoryInterface;
import util.Constants;
import util.Util;
import util.dlquery.DLQueryEngine;
import util.dlquery.DLQueryPrinter;

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
		
		OWLReasoner r = new Reasoner.ReasonerFactory().createNonBufferingReasoner(o);
		System.out.println(!r.isConsistent());
		System.out.println(r.getUnsatisfiableClasses().getEntitiesMinusBottom().size()>0);
		
		//m.saveOntology(o);
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
	
	public Set<OWLClass> getSuperClasses(String toSearch) throws OWLOntologyCreationException {
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		OWLReasoner r = new Reasoner.ReasonerFactory().createReasoner(o);
		ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
		DLQueryPrinter dlQueryPrinter = new DLQueryPrinter(new DLQueryEngine(r, shortFormProvider), shortFormProvider);
		return dlQueryPrinter.getSuperClasses(toSearch.trim());
	}
	
	public Set<OWLClass> getSubClasses(String toSearch) throws OWLOntologyCreationException {
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		OWLReasoner r = new Reasoner.ReasonerFactory().createReasoner(o);
		ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
		DLQueryPrinter dlQueryPrinter = new DLQueryPrinter(new DLQueryEngine(r, shortFormProvider), shortFormProvider);
		return dlQueryPrinter.getSubClasses(toSearch.trim());
	}
	
	public Set<OWLClass> getEquivalentClasses(String toSearch) throws OWLOntologyCreationException {
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		OWLReasoner r = new Reasoner.ReasonerFactory().createReasoner(o);
		ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
		DLQueryPrinter dlQueryPrinter = new DLQueryPrinter(new DLQueryEngine(r, shortFormProvider), shortFormProvider);
		return dlQueryPrinter.getEquivalentClasses(toSearch.trim());
	}
	
	public Set<OWLNamedIndividual> getIndividuals(String toSearch) throws OWLOntologyCreationException {
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		OWLReasoner r = new Reasoner.ReasonerFactory().createReasoner(o);
		ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
		DLQueryPrinter dlQueryPrinter = new DLQueryPrinter(new DLQueryEngine(r, shortFormProvider), shortFormProvider);
		return dlQueryPrinter.getIndividuals(toSearch.trim());
	}
	
	public Set<OWLDataPropertyAssertionAxiom> getAxiomsByIndividual(OWLNamedIndividual individual) throws OWLOntologyCreationException{
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		
		return o.getDataPropertyAssertionAxioms(individual);
	}

	public Set<OWLDataPropertyAssertionAxiom> getAllDataProperties() throws OWLOntologyCreationException {
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		
		Set<OWLNamedIndividual> individuals = o.getIndividualsInSignature();
		Set<OWLDataPropertyAssertionAxiom> axioms = new HashSet<OWLDataPropertyAssertionAxiom>();
		
		for(OWLNamedIndividual i : individuals){
			Set<OWLDataPropertyAssertionAxiom> properties = o.getDataPropertyAssertionAxioms(i);
			axioms.addAll(properties);
		}
		
		return axioms;
	}

	public Set<OWLObjectPropertyAssertionAxiom> getAllObjectProperties() throws OWLOntologyCreationException {
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		
		Set<OWLNamedIndividual> individuals = o.getIndividualsInSignature();
		Set<OWLObjectPropertyAssertionAxiom> axioms = new HashSet<OWLObjectPropertyAssertionAxiom>(); 
		
		for(OWLNamedIndividual i : individuals){
			Set<OWLObjectPropertyAssertionAxiom> properties = o.getObjectPropertyAssertionAxioms(i);
			axioms.addAll(properties);
		}
		
		return axioms;
	}
}
