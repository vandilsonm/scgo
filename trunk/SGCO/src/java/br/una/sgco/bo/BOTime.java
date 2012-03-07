/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.bo;

import br.una.sgco.dao.DAOTime;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOJogo;
import br.una.sgco.to.TOTime;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class BOTime {

    public static void inserir (TOTime time) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOTime.inserir(time, c);

        } finally {
            if (c != null)
                c.close();
        }
    }

    public static void alterar (TOTime time) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOTime.alterar(time, c);

        } finally {
            if (c != null)
                c.close();
        }
    }

    public static void excluir (TOTime time) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOTime.excluir(time, c);

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

            ja = DAOTime.obterTodos(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONArray obterTodosJogo (TOJogo jogo) throws Exception {
        Connection c = null;
        JSONArray ja = null;

        try {
            c = Data.openConnection();

            ja = DAOTime.obterTodosJogo(jogo, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONObject obterUm (TOTime time) throws Exception {
        Connection c = null;
        JSONObject jo = null;

        try {
            c = Data.openConnection();

            jo = DAOTime.obterUm(time, c);

        } finally {
            if (c != null)
                c.close();
        }

        return jo;
    }

}
