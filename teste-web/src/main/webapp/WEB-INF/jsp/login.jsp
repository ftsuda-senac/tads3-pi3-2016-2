<%-- 
    Document   : login
    Created on : 28/10/2016, 20:28:29
    Author     : fernando.tsuda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <h1>Teste Web</h1>
    <div>
      <form action="login" method="post">
	<div>
	  <label for="txtnome">Nome:</label>
	  <input type="text" name="nome" id="txtnome" maxlength="100"
		 placeholder="Digite o nome cadastrado" />
	</div>
	<div>
	  <label for="txtsenha">Senha:</label>
	  <input type="password" name="senha" id="txtsenha"
		 placeholder="Digite a senha" />
	</div>
	<div><input type="submit" value="Entrar" /></div>
      </form>
    </div>
  </body>
</html>
