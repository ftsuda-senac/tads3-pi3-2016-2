<%-- 
    Document   : ajax
    Created on : 04/11/2016, 21:35:53
    Author     : fernando.tsuda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <script type="text/javascript">
      /*
       * Função que associa uma funçào listener quando o DOM é carregado
       * Obtido em http://youmightnotneedjquery.com/
       */
      var documentReady = function (fn) {
	if (document.readyState !== 'loading') {
	  fn();
	} else {
	  document.addEventListener('DOMContentLoaded', fn);
	}
      };

      documentReady(function () {

	document.getElementById("chamar-ajax").addEventListener("click", function () {
	  var request = new XMLHttpRequest();
	  request.open('GET', 'ajax-servlet?idpessoa=' + document.getElementById("xpto").value, true);

	  request.onload = function() {
	    if (request.status >= 200 && request.status < 300) {
	      // Successo!
	      var dataJSON = JSON.parse(request.responseText);
	      var htmlDom = "<section class=\"info\"><h2>Dados da pessoa</h2><ul>" +
		      "<li>Nome: " + dataJSON.nome + "</li>" +
		      "<li>E-mail: " + dataJSON.email + "</li>" +
		      "<li>Sexo: " + (dataJSON.sexo === 1 ? "Masculino" : "Feminino") + 
		      "</li></ul></section>";
	      document.getElementById("resultado").innerHTML = htmlDom;
	    } else {
	      // Servidor retornou algum erro
	      alert("ERRO");
	    }
	  };

	  request.onerror = function () {
	    // There was a connection error of some sort
	    alert("ERRO");
	  };

	  request.send();
	});

      });
    </script>
  </head>
  <body>
    <h1>Teste AJAX</h1>
    <input type="text" id="xpto" placeholder="Digite o ID da pessoa" />
    <button id="chamar-ajax">Chamar AJAX</button>
    <div id="resultado"></div>
  </body>
</html>
