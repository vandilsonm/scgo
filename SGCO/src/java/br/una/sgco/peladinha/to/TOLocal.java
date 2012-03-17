/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.to;

/**
 *
 * @author Jana Louback
 */
public class TOLocal {
    private int id;
    private String nome;
    private String endereco;
    private TOPelada idPelada;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TOPelada getIdPelada() {
        return idPelada;
    }

    public void setIdPelada(TOPelada idPelada) {
        this.idPelada = idPelada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
