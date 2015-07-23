<%@page import="exceptions.InconsistentAssertionException"%>
<%@page import="facade.OWLFacade"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String obj = request.getParameter("obj");
String value = request.getParameter("value");
String attribute = request.getParameter("attribute");
try{
	OWLFacade.getInstance().insertDataProperty(attribute, obj, value);
	%>
	<div class="pure-u-1-3">
       	<div class="l-box">
             <div class="alert-success" style="margin-bottom: 2%">
             	Success!
            </div>
     	</div>
     </div>
	<%
}catch(InconsistentAssertionException e){
	%>
	<div class="pure-u-1-3">
       	<div class="l-box">
            <div class="alert-danger" style="margin-bottom: 2%">
            	<%=e.getMessage() %>
            </div>
     	</div>
    </div>
	<%
}
%>