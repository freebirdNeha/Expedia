<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Weather Information</title>
</head>
<body>
 
<h2>Weather Information</h2>
<table>
    <tr>
        <th>City</th>
        <th>State</th>
        <th>Temperature(F)</th>
    </tr>
        <tr>
            <td><input value="${response.city}"/></td>
            <td><input value="${response.state}"/></td>
            <td><input value="${response.temp_f}"/></td>
        </tr>
</table>  
<br/> 
</body>
</html>