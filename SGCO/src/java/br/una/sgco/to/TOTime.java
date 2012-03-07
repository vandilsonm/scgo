/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.sgco.to;

import java.awt.Image;

/**
 *
 * @author Louback
 */
public class TOTime {

    private int _codigo;
    private String _nome;
    private String _tecnico;
    private String _escudo;
    private int qtdJogadores = 11;
    private TOCampeonato _campeonato;

    public TOTime() {
    }

    /**
     * @return the _codigo
     */
    public int getCodigo() {
        return _codigo;
    }

    /**
     * @param codigo the _codigo to set
     */
    public void setCodigo(int codigo) {
        this._codigo = codigo;
    }

    /**
     * @return the _nome
     */
    public String getNome() {
        return _nome;
    }

    /**
     * @param nome the _nome to set
     */
    public void setNome(String nome) {
        this._nome = nome;
    }

    /**
     * @return the _tecnico
     */
    public String getTecnico() {
        return _tecnico;
    }

    /**
     * @param tecnico the _tecnico to set
     */
    public void setTecnico(String tecnico) {
        this._tecnico = tecnico;
    }

    /**
     * @return the _escudo
     */
    public String getEscudo() {
        return _escudo;
    }

    /**
     * @param escudo the _escudo to set
     */
    public void setEscudo(String escudo) {
        this._escudo = escudo;
    }

    /**
     * @return the _campeonato
     */
    public TOCampeonato getCampeonato() {
        return _campeonato;
    }

    /**
     * @param campeonato the _campeonato to set
     */
    public void setCampeonato(TOCampeonato campeonato) {
        this._campeonato = campeonato;
    }

    /**
     * @return the qtdJogadores
     */
    public int getQtdJogadores() {
        return qtdJogadores;
    }

    /**
     * @param qtdJogadores the qtdJogadores to set
     */
    public void setQtdJogadores(int qtdJogadores) {
        this.qtdJogadores = qtdJogadores;
    }
}
