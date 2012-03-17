/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.to;

import br.una.sgco.to.TOPlacar;

/**
 *
 * @author Jana Louback
 */
public class TOConfirmacao {
    private TOJogador idJogador;
    private TOPelada idPelada;
    private boolean confirmacao;

    public boolean isConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    public TOJogador getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(TOJogador idJogador) {
        this.idJogador = idJogador;
    }

    public TOPelada getIdPelada() {
        return idPelada;
    }

    public void setIdPelada(TOPelada idPelada) {
        this.idPelada = idPelada;
    }

}
