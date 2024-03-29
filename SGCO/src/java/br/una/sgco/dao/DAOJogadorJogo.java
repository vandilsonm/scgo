/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOJogadorJogo;
import java.sql.Connection;

/**
 *
 * @author Jana Louback
 */
public class DAOJogadorJogo {

    public static void inserir(TOJogadorJogo jogadorJogo, Connection c) throws Exception {

        String sql = " insert into sgc_jogadorjogo (idJogador, idJogo, idTime, confirmacao)"
                + " values (?, ?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[]{jogadorJogo.getIdJogador(), 
                            jogadorJogo.getIdJogo(), jogadorJogo.getIdTime(),
                            jogadorJogo.isConfirmacao()});
    }
}
