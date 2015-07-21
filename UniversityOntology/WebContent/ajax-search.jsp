<%@page import="util.Util"%>
<%@page import="util.dlquery.DLQueryEngine"%>
<%@page import="org.semanticweb.owlapi.model.OWLClass"%>
<%@page import="org.semanticweb.owlapi.model.OWLNamedIndividual"%>
<%@page import="java.util.Set"%>
<%@page import="facade.OWLFacade"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
try {
	String toSearch = request.getParameter("toSearch");
	Set<OWLClass> superClasses = OWLFacade.getInstance().getSuperClasses(toSearch);
	String superClassesString = "";
	if (superClasses != null) {
		superClassesString = Util.toString(superClasses);
	}
	Set<OWLClass> subClasses = OWLFacade.getInstance().getSubClasses(toSearch);
	String subClassesString = "";
	if (subClasses != null) {
		subClassesString = Util.toString(subClasses);
	}
	Set<OWLClass> equivalentClasses = OWLFacade.getInstance().getEquivalentClasses(toSearch);
	String equivalentClassesString = "";
	if (equivalentClasses != null) {
		equivalentClassesString = Util.toString(equivalentClasses);
	}
	Set<OWLNamedIndividual> individuals = OWLFacade.getInstance().getIndividuals(toSearch);
	%>
	<ul style="text-align: left; margin-top: 2%">
		<li><b>Superclasses:</b> <%=superClassesString %></li>
		<li><b>Subclasses:</b> <%=subClassesString %></li>
		<li><b>Equivalent classes:</b> <%=equivalentClassesString %></li>
	</ul>
<%
} catch (Exception e) {
	
}
%>	