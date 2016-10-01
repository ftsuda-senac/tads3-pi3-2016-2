/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.testeweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "TesteServlet", urlPatterns = {"/teste-servlet"})
public class TesteServlet extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    //Pessoa p1 = new Pessoa("Fulano da Silva", "fulano@zmail.com");
    //Pessoa p2 = new Pessoa("Ciclano de Souza", "ciclano@zmail.com");

    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet TesteServlet</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet TesteServlet at " + request.getContextPath() + "</h1>");
      //out.println("<p>" + p1.getNome() + "</p>");
      //out.println("<p>" + p1.getEmail() + "</p>");
      out.println("</body>");
      out.println("</html>");
    }
  }

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
    
    Pessoa p1 = new Pessoa("Fulano da Silva", "fulano@zmail.com", 1);
    Pessoa p2 = new Pessoa("Ciclano de Souza", "ciclano@zmail.com", 1);
    Pessoa p3 = new Pessoa("Maria", "maria@zmail.com", 0);
    List<Pessoa> lista = Arrays.asList(p1, p2, p3);
    
    
    // Configura os objetos p1 e p2 como atributos da requisição para
    // repassá-los ao jsp
    request.setAttribute("listaPessoas", lista);
    
    // Encaminhamento para o processamento continuar no jsp.
    RequestDispatcher dispatcher =
	    request.getRequestDispatcher("teste.jsp");
    dispatcher.forward(request, response);
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
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
