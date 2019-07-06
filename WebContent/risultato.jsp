<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.13/moment-timezone-with-data-2012-2022.min.js"></script>

<script type="text/javascript">
prelevaDati();
function prelevaDati(){
	var myVar = <%= request.getAttribute("jsonArray") %>;    

	myVar = JSON.stringify(myVar);

	alert(myVar);      // display complete json
    
	console.info(myVar);
    
    
}


</script>

</head>
<body>
<h1>PROVINCE</h1>
</body>
</html>