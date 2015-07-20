package repository.owl;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import util.Constants;
import util.Util;

public class OWLRepository {
	private static OWLRepository singleton;
	
	private OWLRepository(){}
	
	public OWLRepository getInstance(){
		if(singleton == null){
			singleton = new OWLRepository();
		}
		return singleton;
	}
	
	public void close(){
		singleton = null;
	}
	
	public void insertEntity(OWLIndividual individual, String className) throws OWLOntologyCreationException, OWLOntologyStorageException{
		OWLOntologyManager m = Util.create();
		OWLOntology o = m.loadOntologyFromOntologyDocument(Constants.PROJECT_IRI);
		OWLDataFactory df = Constants.DATA_FACTORY;
		
		OWLClass namedClass = df.getOWLClass(IRI.create("#" + className));
        OWLAxiom axiom = df.getOWLClassAssertionAxiom(namedClass, individual);
        m.addAxiom(o, axiom);
        
        m.saveOntology(o);
	}
}
