<%-- 
    Document   : formulario
    Created on : 07/10/2016, 20:36:36
    Author     : fernando.tsuda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Entrada</title>
    <c:url value="css/estilos.css" var="urlEstilos" />
    <link rel="stylesheet" href="${urlEstilos}" />
  </head>
  <body>
    <h1>Cadastro de pessoa</h1>
    <c:url value="formulario-servlet" var="urlFormulario" />
    <form action="${urlFormulario}" method="post" enctype="application/x-www-form-urlencoded">
      <input type="hidden" name="id" value="99" />
      <div>
	<label for="txtnome">Nome:</label>
	<input type="text" name="nome" id="txtnome" placeholder="Digite o nome completo" />
      </div>
      <div>
	<label for="txtemail">E-mail:</label>
	<input type="email" name="email" id="txtemail" />
      </div>
      <div>
	<label for="txtsenha">Senha:</label>
	<input type="password" name="senha" id="txtsenha" />
      </div>
      <div>
	<label for="txtdtnasc">Data nascimento:</label>
	<input type="text" name="dtnasc" id="txtdtnasc"
	       placeholder="Digite no formato dd/MM/aaaa"
	       maxlength="10"/>
      </div>
      <div>
	<label>Sexo</label>
	<input type="radio" name="sexo" id="radsexof" value="0" checked>
	<label for="radsexof">Feminino</label>
	<input type="radio" name="sexo" id="radsexom" value="1">
	<label for="radsexom">Masculino</label>
      </div>
      <div>
	<label>Interesses</label>
	<input type="checkbox" name="interesses" id="chkviagens" value="viagens">
	<label for="chkviagens">Viagens</label>
	<input type="checkbox" name="interesses" id="chkgastronomia" value="gastronomia">
	<label for="chkgastronomia">Gastronomia</label>
	<input type="checkbox" name="interesses" id="chktecnologia" value="tecnologia">
	<label for="chktecnologia">Tecnologia</label>
      </div>
      <div>
	<label>Selecione uma opção</label>
	<select name="opcao">
	  <option value="a" selected>Opção A</option>
	  <option value="b">Opção B</option>
	  <option value="c">Opção C</option>
	</select>
      </div>
      <div>
	<input type="submit" value="Enviar" />
	<input type="reset" value="Valores iniciais" />
      </div>
    </form>
  </body>
</html>
