/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.testeweb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {

    HttpSession sessao = request.getSession(false);

    if (sessao == null || sessao.getAttribute("usuario") == null) {
      RequestDispatcher dispatcher
	      = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
      dispatcher.forward(request, response);
      return;
    }
    response.sendRedirect(request.getContextPath() + "/teste-servlet");
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
    String nome = request.getParameter("nome");
    String senha = request.getParameter("senha");

    // Validar nome de usuario e senha
    UsuarioSistema usuario = validar(nome, senha);
    if (usuario != null) {
      HttpSession sessao = request.getSession(true);
      sessao.setAttribute("usuario", usuario);
      response.sendRedirect(request.getContextPath() + "/teste-servlet");
    } else {
      response.sendRedirect(request.getContextPath() + "/erro-login.jsp");
    }

  }

  private UsuarioSistema validar(String nome, String senha) {
    UsuarioSistema usuarioCadastrado = new UsuarioSistema("madruga",
	    "14meses", new String[]{"INQUILINO", "VIZINHO"});

    boolean valido = usuarioCadastrado.autenticar(nome, senha);
    if (valido) {
      return usuarioCadastrado;
    }
    return null;
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
