package util.dlquery;

import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.expression.ParserException;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.util.ShortFormProvider;

public class DLQueryPrinter {
    private final DLQueryEngine dlQueryEngine;
    private final ShortFormProvider shortFormProvider;
    private String message;
//    private Set<OWLClass> superClasses;
//    private Set<OWLClass> equivalentClasses;
//    private Set<OWLClass> subClasses;
//    private Set<OWLNamedIndividual> individuals;

    public DLQueryPrinter(DLQueryEngine engine, ShortFormProvider shortFormProvider) {
        this.shortFormProvider = shortFormProvider;
        dlQueryEngine = engine;
        }

//    public void askQuery(String classExpression) {
//        if (classExpression.length() == 0) {
//            message = "No class expression specified";
//        } else {
//            try {
//                //StringBuilder sb = new StringBuilder();
//                //sb.append("<br>QUERY:   ").append(classExpression).append("<br><br>");
//                superClasses = dlQueryEngine.getSuperClasses(classExpression, false);
//                //printEntities("SuperClasses", superClasses, sb);
//                equivalentClasses = dlQueryEngine.getEquivalentClasses(classExpression);
//                //printEntities("EquivalentClasses", equivalentClasses, sb);
//                subClasses = dlQueryEngine.getSubClasses(classExpression, false);
//                //printEntities("SubClasses", subClasses, sb);
//                individuals = dlQueryEngine.getInstances(classExpression, false);
//                //printEntities("Instances", individuals, sb);
//                //toReturn = sb.toString();
//            } catch (ParserException e) {
//                message = e.getMessage();
//            }
//        }
//    }

	public String getMessage() {
		return message;
	}

	public Set<OWLClass> getSuperClasses(String classExpression) {
		Set<OWLClass> toReturn = null;
		try {
			toReturn = dlQueryEngine.getSuperClasses(classExpression, false);
		} catch (ParserException e) {
			message = e.getMessage();
		}
		return toReturn;
	}

	public Set<OWLClass> getEquivalentClasses(String classExpression) {
		Set<OWLClass> toReturn = null;
		try {
			toReturn = dlQueryEngine.getEquivalentClasses(classExpression);
		} catch (ParserException e) {
			message = e.getMessage();
		}
		return toReturn;
	}

	public Set<OWLClass> getSubClasses(String classExpression) {
		Set<OWLClass> toReturn = null;
		try {
			toReturn = dlQueryEngine.getSubClasses(classExpression, false);
		} catch (ParserException e) {
			message = e.getMessage();
		}
		return toReturn;
	}

	public Set<OWLNamedIndividual> getIndividuals(String classExpression) {
		Set<OWLNamedIndividual> toReturn = null;
		try {
			toReturn = dlQueryEngine.getInstances(classExpression, false);
		} catch (ParserException e) {
			message = e.getMessage();
		}
		return toReturn;
	}
	
	public Set<String> toString(Set<? extends OWLEntity> entities) {
		Set<String> toReturn = new HashSet<String>();
		if (!entities.isEmpty()) {
			for (OWLEntity entity : entities) {
				toReturn.add(shortFormProvider.getShortForm(entity));
			}
		}
		return toReturn;
	}

//    private void printEntities(String name, Set<? extends OWLEntity> entities,
//            StringBuilder sb) {
//        sb.append(name);
//        int length = 50 - name.length();
//        for (int i = 0; i < length; i++) {
//            sb.append(".");
//        }
//        sb.append("<br><br>");
//        if (!entities.isEmpty()) {
//            for (OWLEntity entity : entities) {
//                sb.append("\t").append(shortFormProvider.getShortForm(entity))
//                        .append("<br>");
//            }
//        } else {
//            sb.append("\t[NONE]<br>");
//        }
//        sb.append("<br>");
//    }
}