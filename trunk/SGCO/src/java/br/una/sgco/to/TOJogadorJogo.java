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
    
    private String idJogador;
    private String idJogo;
    private boolean confirmacao;

    public boolean isConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public String getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(String idJogo) {
        this.idJogo = idJogo;
    }
}
