package main.IRIMapper;

import java.io.File;
import java.io.IOException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

public class IRIMapper {

		public static void main(String[] args) throws IOException, OWLOntologyCreationException, OWLOntologyStorageException{
			OWLOntologyManager m = OWLManager.createOWLOntologyManager();
			// map the ontology IRI to a physical IRI (files for example)
			File output = File.createTempFile("saved_pizza", "owl");
			IRI documentIRI = IRI.create(output);
			final IRI example_save_iri = IRI.create("http://www.semanticweb.org/ontologies/ont.owl");
			// Set up a mapping, which maps the ontology to the document IRI
			SimpleIRIMapper mapper = new SimpleIRIMapper(example_save_iri, documentIRI);
			m.addIRIMapper(mapper);
			// set up a mapper to read local copies of ontologies
			File localFolder = new File("materializedOntologies");
			// the manager will look up an ontology IRI by checking
			// localFolder first for a local copy
			m.addIRIMapper(new AutoIRIMapper(localFolder, true));
			// Now create the ontology using the ontology IRI (not the
			//physical URI)
			OWLOntology o = m.createOntology(example_save_iri);
			// save the ontology to its physical location - documentIRI
			m.saveOntology(o);
		}
	}

