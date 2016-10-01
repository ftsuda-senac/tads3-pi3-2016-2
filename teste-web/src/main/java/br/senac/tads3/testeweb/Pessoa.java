/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.testeweb;

/**
 *
 * @author fernando.tsuda
 */
public class Pessoa {
  
  private String nome;
  
  private String email;

  public Pessoa() {
  }

  public Pessoa(String nome, String email) {
    this.nome = nome;
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
}
