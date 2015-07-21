<%@page import="org.semanticweb.owlapi.model.OWLDataProperty"%>
<%@page import="java.util.Set"%>
<%@page import="org.semanticweb.owlapi.model.OWLObjectProperty"%>
<%@page import="facade.OWLFacade"%>
<%@page contentType="text/html; charset=UTF-8" %>


<!doctype html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
<meta http-equiv="Content-Type" content="text/html;">
<title>University Ontology - CIn-UFPE</title>
	<link rel="stylesheet" href="css/libraries/pure-min.css">
	<link rel="stylesheet" href="css/libraries/jquery-ui.css">
	<link rel="stylesheet" href="css/libraries/jquery-ui.structure.css">
	<link rel="stylesheet" href="css/libraries/jquery-ui.theme.css">
	<link rel="stylesheet" href="css/libraries/bootstrap.min.css">
	<link rel="stylesheet" href="css/custom.css">
	
	
	<!-- JavaScript plugins (requires jQuery) -->
	<script src="js/libraries/jquery.js"></script>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script	src="js/libraries/jquery-ui.js"></script>
	
</head>
<body>
<script>
	$(function() {
		$("#toSearch").val("");
		$("#obj1").val("");
		$("#obj2").val("");
		$("#obj").val("");
		$("#value").val("");
		$("#attribute").val("0");
		$("#property").val("0");
		$("#tabs").tabs();
		$("#insertPropertyButton").click(function() {
			if ($("#obj1").val() == "" || $("#obj2").val() == "" || $("#property").val() == "0") {
				$('#errorInsert').slideDown(100).delay(2000).slideUp(400);	
			} else {
				$.ajax({
		            type: "POST",
		            url: "ajax-insert-property",
					data: {obj1: $("#obj1").val(), obj2: $("#obj2").val(), property: $("#property").val()},
		            success: function () {
		                $("#obj1").val("");
		                $("#obj2").val("");
		                $("#property").val("0");
		                $('#successfulInsert').slideDown(100).delay(2000).slideUp(400);
		            }
		        });
			}
		})
		$("#insertDataButton").click(function() {
			if ($("#obj").val() == "" || $("#value").val() == "" || $("#attribute").val() == "0") {
				$('#errorInsert2').slideDown(100).delay(2000).slideUp(400);	
			} else {
				$.ajax({
		            type: "POST",
		            url: "ajax-insert-data",
					data: {obj: $("#obj").val(), attribute: $("#attribute").val(), value: $("#value").val()},
		            success: function () {
		                $("#obj").val("");
		                $("#value").val("");
		                $("#attribute").val("0");
		                $('#successfulInsert2').slideDown(100).delay(2000).slideUp(400);
		            }
		        });
			}
		})
		$("#toSearch").keyup(function() {
			$.ajax({
	            type: "POST",
	            url: "ajax-search",
				data: {toSearch: $("#toSearch").val()},
	            success: function (r) {
	            	$("#result").html(r);
	            }
	        });
		})
	});
</script>
<div class="margin">
	<img src="img/logoCin.jpg" height="15%" width="15%">
	<img src="img/university ontology.png" height="50%" width="50%" style="padding-left: 10%">
	
	<div id="tabs" style="margin-top:2%">
		<ul>
			<li><a href="#search"><b>Search</b></a></li>
			<li><a href="#insert-predicate"><b>Insert predicate</b></a></li>
			<li><a href="#insert-data"><b>Insert data</b></a></li>
			
		</ul>
		<div id="insert-predicate" class="pure-form">
			<div class="center pure-u-1">
				<div id="successfulInsert" style="text-align: center; display: none;">
					<div class="pure-u-1-3">
                    	<div class="l-box">
                        	<div class="alert-success" style="margin-bottom: 2%">
                        		Success!
                       		</div>
                		</div>
                	</div>
	            </div>
	            <div id="errorInsert" style="text-align: center; display: none;">
					<div class="pure-u-1-3">
                    	<div class="l-box">
                        	<div class="alert-danger" style="margin-bottom: 2%">
                        		Fill all the fields!
                       		</div>
                		</div>
                	</div>
	            </div>
				<input id="obj1" placeholder="Object">
				<select id="property">
					<option value="0">Select a property</option>
					<%
					Set<OWLObjectProperty> properties = OWLFacade.getInstance().getObjectProperties();
					if (properties != null) {
						for (OWLObjectProperty p : properties) {
							String property = p.getIRI().getFragment();
							%>
							<option value="<%=property%>"><%=property%></option>
						<%	
						}
					}
					%>
				</select>
				<input id="obj2" placeholder="Object">
				<button id="insertPropertyButton" class="btn-custom btn"><b>Insert</b></button>
			</div>
		</div>
		<div id="insert-data" class="pure-form">
			<div class="center pure-u-1">
				<div id="successfulInsert2" style="text-align: center; display: none;">
					<div class="pure-u-1-3">
                    	<div class="l-box">
                        	<div class="alert-success" style="margin-bottom: 2%">
                        		Success!
                       		</div>
                		</div>
                	</div>
	            </div>
	             <div id="errorInsert2" style="text-align: center; display: none;">
					<div class="pure-u-1-3">
                    	<div class="l-box">
                        	<div class="alert-danger" style="margin-bottom: 2%">
                        		Fill all the fields!
                       		</div>
                		</div>
                	</div>
	            </div>
				<input id="obj" placeholder="Object">
				<select id="attribute">
					<option value="0">Select an attribute</option>
					<%
					Set<OWLDataProperty> data = OWLFacade.getInstance().getDataProperties();
					if (data != null) {
						for (OWLDataProperty p : data) {
							String datum = p.getIRI().getFragment();
							%>
							<option value="<%=datum%>"><%=datum%></option>
						<%	
						}
					}
					%>
				</select>
				<input id="value" placeholder="Value">
				<button id="insertDataButton" class="btn-custom btn"><b>Insert</b></button>
			</div>
		</div>
		<div id="search" class="pure-form">
			<div class="center pure-u-1">
				<input id="toSearch" class="pure-u-1-2">
				<div id="result">
				</div>
			</div>	
		</div>
	</div>
</div>
</body>
</html>