package test;

import java.util.Set;

import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLLogicalAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import facade.OWLFacade;
import uk.ac.manchester.cs.owl.owlapi.OWLNamedIndividualImpl;
import util.Constants;
import util.Util;

public class Snippet02 {
	public static void addEntity(String name) throws OWLOntologyCreationException, OWLOntologyStorageException{
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		OWLDataFactory df = Constants.DATA_FACTORY;
		
		OWLClass namedClass = df.getOWLClass(IRI.create("#Thing"));
		OWLIndividual individual = df.getOWLNamedIndividual(IRI.create(name));
        OWLAxiom axiom = df.getOWLClassAssertionAxiom(namedClass, individual);
        m.addAxiom(o, axiom);
        
        m.saveOntology(o);
	}
	
	public static void addRelation(String A, String relation, String B) throws OWLOntologyCreationException, OWLOntologyStorageException{
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		OWLDataFactory df = Constants.DATA_FACTORY;
		
		OWLIndividual a = df.getOWLNamedIndividual(
		IRI.create(A));
		OWLIndividual b = df.getOWLNamedIndividual(
		IRI.create(B));
		OWLObjectProperty op = df.getOWLObjectProperty(
		IRI.create(relation));
		OWLAxiom assertion = df.getOWLObjectPropertyAssertionAxiom(op, a, b);
		m.addAxiom(o, assertion);
		
		m.saveOntology(o);
	}
	
	public static void addData(String name, String data, String value) throws OWLOntologyCreationException, OWLOntologyStorageException{
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		OWLDataFactory df = Constants.DATA_FACTORY;
		
		OWLIndividual individual = df.getOWLNamedIndividual(
		IRI.create(name));
		OWLDataProperty dp = df.getOWLDataProperty(
		IRI.create(data));
		OWLLiteral literal = df.getOWLLiteral(value);
		OWLAxiom assertion = df.getOWLDataPropertyAssertionAxiom(dp, individual, literal);
		m.addAxiom(o, assertion);
		
		m.saveOntology(o);
	}
	
	public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		
		Set<OWLNamedIndividual> individuals = o.getIndividualsInSignature();
		
		for(OWLNamedIndividual i : individuals){
			Set<OWLDataPropertyAssertionAxiom> properties = o.getDataPropertyAssertionAxioms(i);
			for(OWLDataPropertyAssertionAxiom dpaa : properties){
				OWLDataProperty dp = dpaa.getProperty().asOWLDataProperty();
				System.out.println(i.getIRI().getFragment() + " :: " + dp.getIRI().getFragment() + " = " + dpaa.getObject());
			}
		}

	}
}
