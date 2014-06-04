<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<script type="text/javascript">

   		function enterNumber(){

  			var e = document.getElementById('text');

  			if (!/^[0-9]+$/.test(e.value)) 
			{ 
				alert("Please enter only number.");
				e.value = e.value.substring(0,e.value.length-1);
			}
		}

   		function validateLength(){

  			var e = document.getElementById('text');

			if(e.value.length != 5){
				alert("invalid zip code format.");
				e.focus();
				return false;
			}
		}   

	</script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Weather Information</title>
	</head>
	<body>
		<h2>Weather Information</h2>
		<form:form method="POST" action="/WeatherDisplayProject/displaytemperature.htm" onsubmit="validateLength()">
	   		<table>
			   
			    <tr>
			        <td><form:label path="zipCode">Zip Code:</form:label></td>
			        <td><form:input path="zipCode" type="text" id="text" maxlength="5" onkeyup="enterNumber()"/></td>
			    </tr>
			    			    
			  <tr>
			      <td colspan="2"><input type="submit" value="Submit" /></td>
		      </tr>
			</table>  
		</form:form>
	</body>
</html>