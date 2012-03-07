/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.to;

/**
 *
 * @author Nathalia
 */
public class TOSubstituicao {

    private TOJogo _jogo;
    private TOTime _time;
    private TOJogador _jogadorSaiu;
    private TOJogador _jogadorEntrou;

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
     * @return the _jogadorSaiu
     */
    public TOJogador getJogadorSaiu() {
        return _jogadorSaiu;
    }

    /**
     * @param jogadorSaiu the _jogadorSaiu to set
     */
    public void setJogadorSaiu(TOJogador jogadorSaiu) {
        this._jogadorSaiu = jogadorSaiu;
    }

    /**
     * @return the _jogadorEntrou
     */
    public TOJogador getJogadorEntrou() {
        return _jogadorEntrou;
    }

    /**
     * @param jogadorEntrou the _jogadorEntrou to set
     */
    public void setJogadorEntrou(TOJogador jogadorEntrou) {
        this._jogadorEntrou = jogadorEntrou;
    }

    public TOTime getTime() {
        return _time;
    }

    public void setTime(TOTime _time) {
        this._time = _time;
    }

    

}
