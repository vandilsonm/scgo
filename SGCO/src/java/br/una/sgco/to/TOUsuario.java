/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.to;

/**
 *
 * @author Louback
 */
public class TOUsuario {
    private int _codigo;
    private String _nome;
    private String _status;
    private String _email;
    private String _login;
    private String _senha;

    public TOUsuario () {

    }

    public TOUsuario (int codigo, String nome, String status, String email, String login) {
        _codigo = codigo;
        _nome = nome;
        _status = status;
        _email = email;
        _login = login;
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
     * @return the _email
     */
    public String getEmail() {
        return _email;
    }

    /**
     * @param email the _email to set
     */
    public void setEmail(String email) {
        this._email = email;
    }

    /**
     * @return the _login
     */
    public String getLogin() {
        return _login;
    }

    /**
     * @param login the _login to set
     */
    public void setLogin(String login) {
        this._login = login;
    }

    /**
     * @return the _senha
     */
    public String getSenha() {
        return _senha;
    }

    /**
     * @param senha the _senha to set
     */
    public void setSenha(String senha) {
        this._senha = senha;
    }

    
}
