package main.queries;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.util.AutoIRIMapper;

public class AddAxiomTest {
	
	@SuppressWarnings("deprecation")
	public static OWLOntologyManager create() {
		OWLOntologyManager m =
		OWLManager.createOWLOntologyManager();
		m.addIRIMapper(new AutoIRIMapper(
		new File("materializedOntologies"), true));
		return m;
	}
	
	public static void main(String[] args) throws OWLOntologyCreationException {
		OWLOntologyManager m = create();
		final File f = new File("owl/project_ontology.owl");
		final IRI project_iri = IRI.create(f);
		OWLOntology o = m.createOntology(project_iri);
		OWLDataFactory df = OWLManager.getOWLDataFactory();
		// class A and class B
		OWLClass clsA = df.getOWLClass(IRI.create(project_iri + "#A"));
		OWLClass clsB = df.getOWLClass(IRI.create(project_iri + "#B"));
		// Now create the axiom
		OWLAxiom axiom = df.getOWLSubClassOfAxiom(clsA, clsB);
		// add the axiom to the ontology.
		AddAxiom addAxiom = new AddAxiom(o, axiom);
		// We now use the manager to apply the change
		m.applyChange(addAxiom);
		// remove the axiom from the ontology
		RemoveAxiom removeAxiom = new RemoveAxiom(o,axiom);
		m.applyChange(removeAxiom);

	}
}
