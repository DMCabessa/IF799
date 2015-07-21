<%@page import="facade.OWLFacade"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String obj = request.getParameter("obj");
String value = request.getParameter("value");
String attribute = request.getParameter("attribute");

OWLFacade.getInstance().insertDataProperty(attribute, obj, value);
%>