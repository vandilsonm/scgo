/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.sgco.to;

/**
 *
 * @author Jana Louback
 */
public class TOJogadorJogo {
    
    private int idJogador;
    private int idJogo;
    private int idTime;
    private boolean confirmacao;

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getIdTime() {
        return idTime;
    }

    public void setIdTime(int idTime) {
        this.idTime = idTime;
    }

    public boolean isConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

}
