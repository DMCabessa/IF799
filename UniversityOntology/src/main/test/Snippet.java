package main.test;

import java.io.File;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
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
import org.semanticweb.owlapi.util.AutoIRIMapper;

public class Snippet { 
	public static final File f = new File("owl/file.owl");
	public static final IRI project_iri = IRI.create(f);
	public static final OWLDataFactory df = OWLManager.getOWLDataFactory();
	
	@SuppressWarnings("deprecation")
	public static OWLOntologyManager create() {
		OWLOntologyManager m =
		OWLManager.createOWLOntologyManager();
		m.addIRIMapper(new AutoIRIMapper(
		new File("materializedOntologies"), true));
		return m;
	}
	
	public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
		
		OWLOntologyManager m = create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(project_iri);
		
		Set<OWLClass> classes = o.getClassesInSignature();
		Set<OWLNamedIndividual> individuals = o.getIndividualsInSignature();
		for(OWLNamedIndividual ni : individuals){
			System.out.println(ni.getIRI().getShortForm());
		}
		
		OWLIndividual db = df.getOWLNamedIndividual(
		IRI.create(project_iri + "#David_Billington"));
		Set<OWLObjectProperty> properties = db.getObjectPropertiesInSignature();
		for(OWLObjectProperty op : properties){
			System.out.println(op.getIRI());
		}
		
//		OWLIndividual ruy = df.getOWLNamedIndividual(
//		IRI.create(project_iri + "#Ruy_Guerra"));
//		OWLIndividual anjolina = df.getOWLNamedIndividual(
//		IRI.create(project_iri + "#Anjolina_Grisi"));
//		OWLObjectProperty hasSpouse = df.getOWLObjectProperty(
//		IRI.create(project_iri + "#hasSpouse"));
//		OWLAxiom assertion = df.getOWLObjectPropertyAssertionAxiom(hasSpouse, ruy, anjolina);
//		AddAxiom addAxiomChange = new AddAxiom(o, assertion);
//		m.applyChange(addAxiomChange);
//		
//		System.out.println("All changes applied!");
	}
}
