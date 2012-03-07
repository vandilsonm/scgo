/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.bo;

import br.una.sgco.dao.DAOUsuario;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOUsuario;
import br.una.sgco.util.Criptografia;
import java.sql.Connection;

/**
 *
 * @author Nathalia
 */
public class BOUsuario {

    public static void inserir (TOUsuario usuario) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            if (! DAOUsuario.verificaSeLoginExiste(usuario, c)) {
                usuario.setSenha(Criptografia.md5(usuario.getSenha()));
                DAOUsuario.inserir(usuario, c);
            }
        else
            throw new Exception("O login informado já existe.");
        }
        finally {
            if (c != null)
                c.close();
        }
    }

    public static boolean verificaLogin(TOUsuario usuario) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            
            return DAOUsuario.verificaSeLoginExiste(usuario, c);
        } finally {
            if (c != null)
                c.close();
        }
    }

    public static TOUsuario retornaUsuarioLogin(TOUsuario usuario) throws Exception {
        Connection c = null;
        TOUsuario retorno = null;

        try {
            c = Data.openConnection();

            retorno = DAOUsuario.retornaUsuarioLogin(usuario, c);
        } finally {
            if (c != null)
                c.close();
        }

        return retorno;
    }
}
