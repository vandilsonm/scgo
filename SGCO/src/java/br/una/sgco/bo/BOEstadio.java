/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.bo;

import br.una.sgco.dao.DAOEstadio;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOEstadio;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class BOEstadio {

    public static void inserir(TOEstadio estadio) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            DAOEstadio.inserir(estadio, c);

        } finally {
            if (c != null)
                c.close();
        }
    }

    public static void alterar(TOEstadio estadio) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            DAOEstadio.alterar(estadio, c);

        } finally {
            if (c != null)
                c.close();
        }
    }

    public static void excluir(TOEstadio estadio) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            DAOEstadio.excluir(estadio, c);

        } finally {
            if (c != null)
                c.close();
        }
    }

    public static JSONArray lista(TOCampeonato campeonato) throws Exception {
        Connection c = null;
        JSONArray ja = null;

        try {
            c = Data.openConnection();

            ja = DAOEstadio.obterTodos(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONObject obterUm(TOEstadio estadio) throws Exception {
        Connection c = null;
        JSONObject jo = null;

        try {
            c = Data.openConnection();

            jo = DAOEstadio.obterUm(estadio, c);

        } finally {
            if (c != null)
                c.close();
        }

        return jo;
    }

}
