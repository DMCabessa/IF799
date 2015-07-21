<%@page import="facade.OWLFacade"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String obj1 = request.getParameter("obj1");
String obj2 = request.getParameter("obj2");
String property = request.getParameter("property");

OWLFacade.getInstance().insertObjectProperty(property, obj1, obj2);
%>