<%@page import="exceptions.InconsistentAssertionException"%>
<%@page import="facade.OWLFacade"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String obj1 = request.getParameter("obj1");
String obj2 = request.getParameter("obj2");
String property = request.getParameter("property");
try{
	OWLFacade.getInstance().insertObjectProperty(property, obj1, obj2);
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