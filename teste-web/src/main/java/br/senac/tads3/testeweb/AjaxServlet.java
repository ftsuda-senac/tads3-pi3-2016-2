/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.testeweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "AjaxServlet", urlPatterns = {"/ajax-servlet"})
public class AjaxServlet extends HttpServlet {

  private static final Map<Long, Pessoa> PESSOAS = new HashMap<Long, Pessoa>();

  static {
    PESSOAS.put(1L, new Pessoa("Fulano da Silva", "fulano@zmail.com", 1));
    PESSOAS.put(2L, new Pessoa("Ciclano de Souza", "ciclano@zmail.com", 1));
    PESSOAS.put(3L, new Pessoa("Maria", "maria@zmail.com", 0));
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
    String idStr = request.getParameter("idpessoa");
    if (idStr != null && idStr.length() > 0) {
      try {
	long idPessoa = Long.parseLong(idStr);
	Pessoa p = PESSOAS.get(idPessoa);
	if (p != null) {
	  // SUCESSO - Retornar codigo 200 OK + JSON
	  JSONObject json = new JSONObject(p);
	  response.setContentType("application/json;charset=UTF-8");
	  try (PrintWriter out = response.getWriter()) {
	    out.print(json.toString());
	  }
	} else {
	  // Pessoa nao encontrada: retornar erro 404 NOT FOUND
	  response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
      } catch (NumberFormatException e) {
	// ID invalido: retornar erro 400 BAD REQUEST
	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    } else {
      // ID nao informado: retornar erro 405 BAD REQUEST
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
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

  }

}
