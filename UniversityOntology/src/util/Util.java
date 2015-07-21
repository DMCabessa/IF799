package util;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLEntity;
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
	
	public static String toString(Set<? extends OWLEntity> entities) {
		String toReturn = "";
		if (!entities.isEmpty()) {
			for (OWLEntity entity : entities) {
				toReturn = toReturn + entity.getIRI().getFragment() + ", ";
			}
		}
		if (toReturn != "") {
			toReturn = toReturn.substring(0, toReturn.length()-2) + ".";
		}
		return toReturn;
	}

}
