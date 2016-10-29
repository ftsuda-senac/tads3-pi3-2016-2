/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.testeweb;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fernando.tsuda
 */
@WebFilter(filterName = "AutorizacaoFilter",
	servletNames = {"FormularioServlet", "TesteServlet"},
	urlPatterns = {"/protegido/*"})
public class AutorizacaoFilter implements Filter {

  /**
   *
   * @param request The servlet request we are processing
   * @param response The servlet response we are creating
   * @param chain The filter chain we are processing
   *
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet error occurs
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
	  FilterChain chain)
	  throws IOException, ServletException {

    // 1) FAZER CAST DOS OBJETOS REQUEST E RESPONSE
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    // 2) TENTAR RECUPERAR USUARIO DA SESSAO
    HttpSession sessao = httpRequest.getSession(false);
    UsuarioSistema usuario = null;
    if (sessao != null) {
      Object objSessao = sessao.getAttribute("usuario");
      if (objSessao != null) {
	usuario = (UsuarioSistema) objSessao;
      } else {
	httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
	return;
      }
    } else {
      httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
      return;
    }

    // 3) VERIFICAR SE USUARIO PODE ACESSAR PAGINA
    try {
      if (verificarAcesso(usuario, httpRequest, httpResponse)) {
	// ENCAMINHA REQUISIÇÃO PARA O PRÓXIMO FILTRO OU SERVLET
	chain.doFilter(request, response);
      } else {
	// NÃO PODE ACESSAR. APRESENTA TELA DE ERRO
	httpResponse.sendRedirect(httpRequest.getContextPath()
		+ "/erro-nao-autorizado.jsp");
      }

    } catch (Throwable t) {
      // If an exception is thrown somewhere down the filter chain,
      // we still want to execute our after processing, and then
      // rethrow the problem after that.
      t.printStackTrace();
    }

  }

  private static
	  boolean verificarAcesso(UsuarioSistema usuario,
		  HttpServletRequest request,
		  HttpServletResponse response) {

    String paginaCompleta = request.getRequestURI();
    String pagina = paginaCompleta.replace(request.getContextPath(), "");

    if (pagina.endsWith("formulario-servlet")
	    && usuario.autorizado("ADMIN")) {
      return true;
    } else if (pagina.endsWith("teste-servlet")
	    && usuario.autorizado("INQUILINO")) {
      return true;
    }

    return false;

  }

  /**
   * Destroy method for this filter
   */
  @Override
  public void destroy() {

  }

  /**
   * Init method for this filter
   */
  @Override
  public void init(FilterConfig filterConfig) {

  }

}
