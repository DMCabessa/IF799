<%@page import="util.dlquery.DLQueryEngine"%>
<%@page import="org.semanticweb.owlapi.model.OWLClass"%>
<%@page import="java.util.Set"%>
<%@page import="facade.OWLFacade"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String toSearch = request.getParameter("toSearch");
Set<String> superClasses = OWLFacade.getInstance().getSuperClasses(toSearch);
Set<String> subClasses = OWLFacade.getInstance().getSubClasses(toSearch);
Set<String> equivalentClasses = OWLFacade.getInstance().getEquivalentClasses(toSearch);
Set<String> individuals = OWLFacade.getInstance().getIndividuals(toSearch);
%>
<div>
<%=superClasses %>
<br>
<%=subClasses %>
<br>
<%=equivalentClasses %>
<br>
<%=individuals %>
</div>