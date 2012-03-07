/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.to;

/**
 *
 * @author Louback
 */
public class TOEstadio {
    private int _codigo;
    private String _nome;
    private String _logradouro;
    private String _numero;
    private String _complemento;
    private String _bairro;
    private String _cidade;
    private String _estado;
    private TOCampeonato _campeonato;

    public TOEstadio () {

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
     * @return the _logradouro
     */
    public String getLogradouro() {
        return _logradouro;
    }

    /**
     * @param logradouro the _logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this._logradouro = logradouro;
    }

    /**
     * @return the _numero
     */
    public String getNumero() {
        return _numero;
    }

    /**
     * @param numero the _numero to set
     */
    public void setNumero(String numero) {
        this._numero = numero;
    }

    /**
     * @return the _complemento
     */
    public String getComplemento() {
        return _complemento;
    }

    /**
     * @param complemento the _complemento to set
     */
    public void setComplemento(String complemento) {
        this._complemento = complemento;
    }

    /**
     * @return the _bairro
     */
    public String getBairro() {
        return _bairro;
    }

    /**
     * @param bairro the _bairro to set
     */
    public void setBairro(String bairro) {
        this._bairro = bairro;
    }

    /**
     * @return the _cidade
     */
    public String getCidade() {
        return _cidade;
    }

    /**
     * @param cidade the _cidade to set
     */
    public void setCidade(String cidade) {
        this._cidade = cidade;
    }

    /**
     * @return the _estado
     */
    public String getEstado() {
        return _estado;
    }

    /**
     * @param estado the _estado to set
     */
    public void setEstado(String estado) {
        this._estado = estado;
    }

    public TOCampeonato getCampeonato() {
        return _campeonato;
    }

    public void setCampeonato(TOCampeonato _campeonato) {
        this._campeonato = _campeonato;
    }
}
