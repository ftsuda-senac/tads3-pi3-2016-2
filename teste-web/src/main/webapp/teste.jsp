<%-- 
    Document   : teste
    Created on : 30/09/2016, 21:28:43
    Author     : fernando.tsuda
--%>

<%@page import="br.senac.tads3.testeweb.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
	
	<h2><c:out value="${sessionScope.usuario.nome}" /></h2>
	
	
	<c:import url="menu.jsp" />
        <c:forEach items="${listaPessoas}" var="p">
            <div>
                <p><c:out value="${p.nome}" /></p>
                <p><c:out value="${p.email}" /></p>
                <c:choose>
                    <c:when test="${p.sexo == 1}">
                        <p>Masculino</p>
                    </c:when>
                    <c:otherwise>
                        <p>Feminino</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>

    </body>
</html>
