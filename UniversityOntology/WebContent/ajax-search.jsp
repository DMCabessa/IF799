<%@page import="java.util.Iterator"%>
<%@page import="java.util.Hashtable"%>
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
	%>
	<ul style="text-align: left; margin-top: 2%">
		<li><b>Superclasses:</b> <%=superClassesString %></li>
		<li><b>Subclasses:</b> <%=subClassesString %></li>
		<li><b>Equivalent classes:</b> <%=equivalentClassesString %></li>
	</ul>
	<%
	Set<OWLNamedIndividual> individuals = OWLFacade.getInstance().getIndividuals(toSearch);
	if (individuals != null) {
	%>
	<div class="center pure-u-2-3" style="margin-top: 2%">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="center">Id</th>
					<th colspan="2" class="center">Attributes</th>
				</tr>
			</thead>
			<tbody>
			<%
				//boolean odd = true;
				for (OWLNamedIndividual individual : individuals) {
					Hashtable<String, String> hashtable = OWLFacade.getInstance().getDataProperties(individual);
					Iterator<String> keysIt = hashtable.keySet().iterator();
			%>
					<tr>
						<td rowspan="<%=hashtable.size()%>"><%=individual.getIRI().getFragment() %></td>
						<%if (keysIt.hasNext()) { 
							String key = keysIt.next();
						%>
						<td><%=key %></td>
						<td><%=hashtable.get(key) %></td>
						<%} %>
					</tr>
					<%
					while (keysIt.hasNext()) {
						String key = keysIt.next();
						
					%>
					<tr>
						<td><%=key %></td>
						<td><%=hashtable.get(key) %></td>
					</tr>
					<%
					}
					%>	
							
								
			<%
				}
			%>
			</tbody>
		</table>
	</div>
<%
	}
} catch (Exception e) {
	
}
%>	