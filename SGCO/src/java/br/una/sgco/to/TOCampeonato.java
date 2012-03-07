/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.to;

/**
 *
 * @author Louback
 */
public class TOCampeonato {
    private int _codigo;
    private String _nome;
    private String _descricao;
    private String _tipo;
    private String _tabela;
    private TOUsuario _administrador;

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
     * @return the _descricao
     */
    public String getDescricao() {
        return _descricao;
    }

    /**
     * @param descricao the _descricao to set
     */
    public void setDescricao(String descricao) {
        this._descricao = descricao;
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

    /**
     * @return the _tabela
     */
    public String getTabela() {
        return _tabela;
    }

    /**
     * @param tabela the _tabela to set
     */
    public void setTabela(String tabela) {
        this._tabela = tabela;
    }

    /**
     * @return the _administrador
     */
    public TOUsuario getAdministrador() {
        return _administrador;
    }

    /**
     * @param administrador the _administrador to set
     */
    public void setAdministrador(TOUsuario administrador) {
        this._administrador = administrador;
    }

    
}
