/*
 * The MIT License
 *
 * Copyright 2016 fernando.tsuda.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.senac.tads3.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando.tsuda
 */
public class Agenda extends ConexaoBD {

  private static Scanner entrada = new Scanner(System.in);

  public void incluir() {

    System.out.print("Digite o nome completo do contato: ");
    String nome = entrada.nextLine();

    System.out.print("Digite a data de nascimento no formato dd/mm/aaaa: ");
    String strDataNasc = entrada.nextLine();

    System.out.print("Digite o e-mail");
    String email = entrada.nextLine();

    System.out.print("Digite o telefone no formato 99 99999-9999");
    String telefone = entrada.nextLine();

    // 1) Abrir conexao
    PreparedStatement stmt = null;
    Connection conn = null;

    String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO, "
	    + "VL_TELEFONE, VL_EMAIL, DT_CADASTRO) "
	    + "VALUES (?, ?, ?, ?, ?)";

    try {
      conn = obterConexao();
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, nome);

      DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
      Date dataNasc = null;
      try {
	dataNasc = formatador.parse(strDataNasc);
      } catch (ParseException ex) {
	System.out.println("Data de nascimento inválida.");
	return;
      }
      stmt.setDate(2, new java.sql.Date(dataNasc.getTime()));
      stmt.setString(3, telefone);
      stmt.setString(4, email);
      stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));

      // 2) Executar SQL
      stmt.executeUpdate();
      System.out.println("Contato cadastrado com sucesso");

    } catch (SQLException e) {
      System.out.println("Não foi possível executar.");
    } catch (ClassNotFoundException e) {
      System.out.println("Não foi possível executar.");
    } finally {
      // 3) Fechar conexao
      if (stmt != null) {
	try {
	  stmt.close();
	} catch (SQLException ex) {
	  System.out.println("Erro ao fechar stmt.");
	}
      }
      if (conn != null) {
	try {
	  conn.close();
	} catch (SQLException ex) {
	  System.out.println("Erro ao fechar conn.");
	}
      }
    }
  }

  public void listar() {
    Statement stmt = null;
    Connection conn = null;

    String sql = "SELECT ID_CONTATO, NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL "
	    + "FROM TB_CONTATO";

    try {
      conn = obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sql);

      DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

      while (resultados.next()) {
	Long id = resultados.getLong("ID_CONTATO");
	String nome = resultados.getString("NM_CONTATO");
	Date dataNasc = resultados.getDate("DT_NASCIMENTO");
	String email = resultados.getString("VL_EMAIL");
	String telefone = resultados.getString("VL_TELEFONE");
	System.out.println(String.valueOf(id) + ", " + nome + ", "
		+ formatadorData.format(dataNasc) + ", " + email + ", "
		+ telefone);
      }

    } catch (SQLException ex) {
      System.out.println("Erro");
    } catch (ClassNotFoundException ex) {
      System.out.println("Erro");
    } finally {
      // Código colocado aqui para garantir que a conexão com o banco
      // seja sempre fechada, independentemente se executado com sucesso
      // ou erro.
      if (stmt != null) {
	try {
	  stmt.close();
	} catch (SQLException ex) {
	  System.out.println("Erro");
	}
      }
      if (conn != null) {
	try {
	  conn.close();
	} catch (SQLException ex) {
	  System.out.println("Erro");
	}
      }
    }
  }

  public static void main(String[] args) {
    Agenda instancia = new Agenda();

    do {
      System.out.println("***** DIGITE UMA OPÇÃO *****");
      System.out.println("(1) Listar contatos");
      System.out.println("(2) Incluir novo contato");
      System.out.println("(9) Sair");
      System.out.print("Opção: ");

      String strOpcao = entrada.nextLine();
      int opcao = Integer.parseInt(strOpcao);
      switch (opcao) {
	case 1:
	  instancia.listar();
	  break;
	case 2:
	  instancia.incluir();
	  break;
	case 9:
	  System.exit(0);
	  break;
	default:
	  System.out.println("OPÇÃO INVÁLIDA");
      }
    } while (true);

  }

}
