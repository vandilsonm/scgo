/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.to;

/**
 *
 * @author Louback
 */
public class TOPlacar {

    private TOJogo _jogo;
    private TOTime _time;
    private TOJogador _jogador;
    private int _qtdeGols;

    /**
     * @return the _jogo
     */
    public TOJogo getJogo() {
        return _jogo;
    }

    /**
     * @param jogo the _jogo to set
     */
    public void setJogo(TOJogo jogo) {
        this._jogo = jogo;
    }

    /**
     * @return the _jogador
     */
    public TOJogador getJogador() {
        return _jogador;
    }

    /**
     * @param jogador the _jogador to set
     */
    public void setJogador(TOJogador jogador) {
        this._jogador = jogador;
    }

    public TOTime getTime() {
        return _time;
    }

    public void setTime(TOTime _time) {
        this._time = _time;
    }

    /**
     * @return the _qtdeGols
     */
    public int getQtdeGols() {
        return _qtdeGols;
    }

    /**
     * @param qtdeGols the _qtdeGols to set
     */
    public void setQtdeGols(int qtdeGols) {
        this._qtdeGols = qtdeGols;
    }
}
