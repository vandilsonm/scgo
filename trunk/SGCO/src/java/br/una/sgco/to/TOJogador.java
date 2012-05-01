/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.to;

/**
 *
 * @author Louback
 */
public class TOJogador {
    private int _codigo;
    private String _nome;
    private String _posicao;
    private String _status;
    private String _tipo;
    private String _email;
    private TOTime _time;
    private String _celular;

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public String getCelular() {
        return _celular;
    }

    public void setCelular(String _celular) {
        this._celular = _celular;
    }

    public TOJogador () {

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
     * @return the _posicao
     */
    public String getPosicao() {
        return _posicao;
    }

    /**
     * @param posicao the _posicao to set
     */
    public void setPosicao(String posicao) {
        this._posicao = posicao;
    }

    /**
     * @return the _status
     */
    public String getStatus() {
        return _status;
    }

    /**
     * @param status the _status to set
     */
    public void setStatus(String status) {
        this._status = status;
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
     * @return the _time
     */
    public TOTime getTime() {
        return _time;
    }

    /**
     * @param time the _time to set
     */
    public void setTime(TOTime time) {
        this._time = time;
    }

}
