package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;
import util.dlquery.DLQueryEngine;
import util.dlquery.DLQueryPrinter;

public class Snippet { 
	public static final File f = new File("owl/project_ontology.owl");
	public static final IRI project_iri = IRI.create(f);
	public static final OWLDataFactory df = OWLManager.getOWLDataFactory();
	
	public static final File fi = new File("owl/inferred_ontology.owl");
	public static final IRI inferred_iri = IRI.create(fi);
	
	@SuppressWarnings("deprecation")
	public static OWLOntologyManager create() {
		OWLOntologyManager m =
		OWLManager.createOWLOntologyManager();
		m.addIRIMapper(new AutoIRIMapper(
		new File("materializedOntologies"), true));
		return m;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException, FileNotFoundException, UnsupportedEncodingException {
		
		OWLOntologyManager m = create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(project_iri);
		
//		Set<OWLClass> classes = o.getClassesInSignature();
//		Set<OWLNamedIndividual> individuals = o.getIndividualsInSignature();
//		System.out.println("ENTITIES::{");
//		for(OWLNamedIndividual ni : individuals){
//			System.out.println(ni.getIRI());
//		}
//		System.out.println("}");
		
		// Create assertion
//		OWLIndividual david = df.getOWLNamedIndividual(
//		IRI.create("#David_Billington"));
//		OWLIndividual alice = df.getOWLNamedIndividual(
//		IRI.create("#Alice_Billington"));
//		OWLObjectProperty hasSpouse = df.getOWLObjectProperty(
//		IRI.create("#hasSpouse"));
//		OWLAxiom assertion = df.getOWLObjectPropertyAssertionAxiom(hasSpouse, david, alice);
//		AddAxiom addAxiomChange = new AddAxiom(o, assertion);
//		m.applyChange(addAxiomChange);
//		
//		m.saveOntology(o, new OWLXMLOntologyFormat(), project_iri);
		
		// Reasoner step
		
		OWLReasoner r = new Reasoner.ReasonerFactory().createReasoner(o);
		//OWLReasoner r = new StructuralReasonerFactory().createReasoner(o);
//		System.out.println(r.getReasonerName());
//		r.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		
		// Use an inferred axiom generators
//		List<InferredAxiomGenerator<? extends OWLAxiom>> gens = Collections.singletonList(new InferredSubClassAxiomGenerator());
//		OWLOntology infOnt = m.createOntology();
		// create the inferred ontology generator
//		InferredOntologyGenerator iog = new InferredOntologyGenerator(r, gens);
//		iog.fillOntology(m, infOnt);

		ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
        // Create the DLQueryPrinter helper class. This will manage the
        // parsing of input and printing of results
        DLQueryPrinter dlQueryPrinter = new DLQueryPrinter(new DLQueryEngine(r,
                shortFormProvider), shortFormProvider);
        // Enter the query loop. A user is expected to enter class
        // expression on the command line.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        while (true) {
            System.out
                    .println("Type a class expression in Manchester Syntax and press Enter (or press x to exit):");
            String classExpression = null;
			try {
				classExpression = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // Check for exit condition
            if (classExpression == null || classExpression.equalsIgnoreCase("x")) {
                break;
            }
            dlQueryPrinter.askQuery(classExpression.trim());
            System.out.println();
        }
        
		
//		m.saveOntology(o, new OWLXMLOntologyFormat(), inferred_iri);
	}
}
