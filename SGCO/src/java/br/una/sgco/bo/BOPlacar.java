/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.bo;

import br.una.sgco.dao.DAOPlacar;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOPlacar;
import java.sql.Connection;
import org.json.JSONArray;

/**
 *
 * @author Nathalia
 */
public class BOPlacar {

    public static void inserir (TOPlacar placar) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOPlacar.inserir(placar, c);

        } finally {
            if (c != null)
                c.close();
        }
    }

    public static void excluir (TOPlacar placar) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOPlacar.excluir(placar, c);

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

            ja = DAOPlacar.obterTodos(campeonato, c);
        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

}
