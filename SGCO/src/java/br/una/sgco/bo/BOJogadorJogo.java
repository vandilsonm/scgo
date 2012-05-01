/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.sgco.bo;

import br.una.sgco.dao.DAOJogadorJogo;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOJogadorJogo;
import java.sql.Connection;

/**
 *
 * @author Jana Louback
 */
public class BOJogadorJogo {
    public static void inserir (TOJogadorJogo jogadorJogo) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOJogadorJogo.inserir(jogadorJogo, c);

        } finally {
            if (c != null)
                c.close();
        }    
    }
}
