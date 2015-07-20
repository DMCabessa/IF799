package util;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.AutoIRIMapper;

public class Util {
	
	public static OWLOntologyManager create() {
		OWLOntologyManager m =
		OWLManager.createOWLOntologyManager();
		m.addIRIMapper(new AutoIRIMapper(
		new File("materializedOntologies"), true));
		return m;
	}

}
