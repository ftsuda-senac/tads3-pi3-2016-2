<%-- 
    Document   : resposta
    Created on : 07/10/2016, 20:47:52
    Author     : fernando.tsuda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <h1>Dados prenchidos</h1>
    <div>
      <p>Nome: <c:out value="${nome}" /></p>
      <p>E-mail: <c:out value="${email}" /></p>
      <p>Senha: <c:out value="${senha}" /></p>
      <p>
	Data de nascimento:
	<fmt:formatDate value="${dtnascimento}" pattern="dd/MM/yyyy" />
      </p>
      <p>
	Salário:
	
      </p>
      <p>Sexo:
	<c:choose>
	  <c:when test="${sexo == 1}">
	  <p>Masculino</p>
	</c:when>
	<c:otherwise>
	  <p>Feminino</p>
	</c:otherwise>
      </c:choose>
    </p>
    <p>Interesses: <c:out value="${fn:join(interesses, ', ')}" /></p>
    <p>Opção: opção <c:out value="${fn:toUpperCase(opcao)}" /></p>
    <a href="teste-servlet">Voltar para a tela do TesteServlet</a>
  </p>
</div>
</body>
</html>
