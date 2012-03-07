/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.sgco.to;

import java.sql.Timestamp;

/**
 *
 * @author Louback
 */
public class TOJogo {

    private int _codigo;
    private TOCampeonato _campeonato;
    private TOEstadio _estadio;
    private TOTime _timeMandante;
    private TOTime _timeVisitante;
    private Timestamp _dataHora;
    private int _golsMandante;
    private int _golsVisitante;
    private TOArbitragem _juiz;
    private TOArbitragem _juizAuxiliar;
    private TOArbitragem _bandeirinha1;
    private TOArbitragem _bandeirinha2;

    public TOJogo () {

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
     * @return the _estadio
     */
    public TOEstadio getEstadio() {
        return _estadio;
    }

    /**
     * @param estadio the _estadio to set
     */
    public void setEstadio(TOEstadio estadio) {
        this._estadio = estadio;
    }

    /**
     * @return the _timeMandante
     */
    public TOTime getTimeMandante() {
        return _timeMandante;
    }

    /**
     * @param timeMandante the _timeMandante to set
     */
    public void setTimeMandante(TOTime timeMandante) {
        this._timeMandante = timeMandante;
    }

    /**
     * @return the _timeVisitante
     */
    public TOTime getTimeVisitante() {
        return _timeVisitante;
    }

    /**
     * @param timeVisitante the _timeVisitante to set
     */
    public void setTimeVisitante(TOTime timeVisitante) {
        this._timeVisitante = timeVisitante;
    }

    /**
     * @return the _dataHora
     */
    public Timestamp getDataHora() {
        return _dataHora;
    }

    /**
     * @param dataHora the _dataHora to set
     */
    public void setDataHora(Timestamp dataHora) {
        this._dataHora = dataHora;
    }

    /**
     * @return the _golsMandante
     */
    public int getGolsMandante() {
        return _golsMandante;
    }

    /**
     * @param golsMandante the _golsMandante to set
     */
    public void setGolsMandante(int golsMandante) {
        this._golsMandante = golsMandante;
    }

    /**
     * @return the _golsVisitante
     */
    public int getGolsVisitante() {
        return _golsVisitante;
    }

    /**
     * @param golsVisitante the _golsVisitante to set
     */
    public void setGolsVisitante(int golsVisitante) {
        this._golsVisitante = golsVisitante;
    }

    /**
     * @return the _juiz
     */
    public TOArbitragem getJuiz() {
        return _juiz;
    }

    /**
     * @param juiz the _juiz to set
     */
    public void setJuiz(TOArbitragem juiz) {
        this._juiz = juiz;
    }

    /**
     * @return the _juizAuxiliar
     */
    public TOArbitragem getJuizAuxiliar() {
        return _juizAuxiliar;
    }

    /**
     * @param juizAuxiliar the _juizAuxiliar to set
     */
    public void setJuizAuxiliar(TOArbitragem juizAuxiliar) {
        this._juizAuxiliar = juizAuxiliar;
    }

    /**
     * @return the _bandeirinha1
     */
    public TOArbitragem getBandeirinha1() {
        return _bandeirinha1;
    }

    /**
     * @param bandeirinha1 the _bandeirinha1 to set
     */
    public void setBandeirinha1(TOArbitragem bandeirinha1) {
        this._bandeirinha1 = bandeirinha1;
    }

    /**
     * @return the _bandeirinha2
     */
    public TOArbitragem getBandeirinha2() {
        return _bandeirinha2;
    }

    /**
     * @param bandeirinha2 the _bandeirinha2 to set
     */
    public void setBandeirinha2(TOArbitragem bandeirinha2) {
        this._bandeirinha2 = bandeirinha2;
    }
}
