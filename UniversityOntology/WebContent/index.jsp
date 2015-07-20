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
		$("#tabs").tabs();
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
				<input id="obj1" placeholder="Object">
				<select id="property">
					<option value="0">Select a property</option>
					<!-- adicionar properties como opcoes -->
				</select>
				<input id="obj2" placeholder="Object">
				<button class="btn-custom btn"><b>Insert</b></button>
			</div>
		</div>
		<div id="insert-data" class="pure-form">
			<div class="center pure-u-1">
				<input id="obj" placeholder="Object">
				<select id="attribute">
					<option value="0">Select an attribute</option>
					<!-- adicionar attributes como opcoes -->
				</select>
				<input id="attr-value" placeholder="Value">
				<button class="btn-custom btn">Insert</button>
			</div>
		</div>
		<div id="search">
		
		</div>
	</div>
</div>
</body>
</html>