<%-- 
    Document   : teste
    Created on : 30/09/2016, 21:28:43
    Author     : fernando.tsuda
--%>

<%@page import="br.senac.tads3.testeweb.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        Pessoa p1 = new Pessoa("Fulano da Silva", "fulano@zmail.com");
        Pessoa p2 = new Pessoa("Ciclano de Souza", "ciclano@zmail.com");
        %>
        
        <p><%=p1.getNome()%></p>
        <p><%=p1.getEmail()%></p>
          
    </body>
</html>
