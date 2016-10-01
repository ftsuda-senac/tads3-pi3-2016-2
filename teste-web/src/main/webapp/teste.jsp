<%-- 
    Document   : teste
    Created on : 30/09/2016, 21:28:43
    Author     : fernando.tsuda
--%>

<%@page import="br.senac.tads3.testeweb.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! xpto</h1>

        <p><c:out value="${xpto1.nome}" /></p>
        <p><c:out value="${xpto1.email}" /></p>
          
    </body>
</html>
