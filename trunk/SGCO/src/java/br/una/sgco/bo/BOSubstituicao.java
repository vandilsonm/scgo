/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.bo;

import br.una.sgco.dao.DAOSubstituicao;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOSubstituicao;
import java.sql.Connection;
import org.json.JSONArray;

/**
 *
 * @author Nathalia
 */
public class BOSubstituicao {

    public static void inserir (TOSubstituicao subs) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOSubstituicao.inserir(subs, c);

        } finally {
            if (c != null)
                c.close();
        }
    }

    public static void excluir (TOSubstituicao subs) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOSubstituicao.excluir(subs, c);

        } finally {
            if (c != null)
                c.close();
        }
    }

    public static JSONArray lista (TOCampeonato campeonato) throws Exception {
        Connection c = null;
        JSONArray ja = null;

        try {
            c = Data.openConnection();

            ja = DAOSubstituicao.obterTodos(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

}
