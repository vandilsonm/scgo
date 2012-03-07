/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.to;

/**
 *
 * @author Louback
 */
public class TOArbitragem {
    
    private int _codigo;
    private String _nome;
    private String _tipo;
    private TOCampeonato _campeonato;

    public TOArbitragem () {

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
     * @return the _tipo
     */
    public String getTipo() {
        return _tipo;
    }

    /**
     * @param tipo the _tipo to set
     */
    public void setTipo(String tipo) {
        this._tipo = tipo;
    }

    public TOCampeonato getCampeonato() {
        return _campeonato;
    }

    public void setCampeonato(TOCampeonato _campeonato) {
        this._campeonato = _campeonato;
    }

}
