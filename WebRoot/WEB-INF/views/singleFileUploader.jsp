<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>File Upload </title>
    
</head>
<body> 
 
    
        <h1>File Upload</h1>
        <form method="POST" action="/sdnext/success.html">
         <table>
         	<tr>
         		<td>Upload a file</td>
         		<td><input type="file"  id="file"/></td>
         	</tr>
         
         	<tr>
         		<td></td>
         		<td><input type="submit" value="Upload"/></td>
         	</tr>
         
         </table>
         </form>
        <a href="<c:url value='/welcome' />">Home</a>
     
</body>
</html>