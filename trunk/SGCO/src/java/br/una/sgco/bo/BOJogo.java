/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.bo;

import br.una.sgco.dao.DAOJogo;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOJogo;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class BOJogo {

    public static void inserir (TOJogo jogo) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            DAOJogo.inserir(jogo, c);

        } finally {
            if (c != null)
                c.close();
        }
        
    }

    public static void alterar (TOJogo jogo) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            DAOJogo.alterar(jogo, c);

        } finally {
            if (c != null)
                c.close();
        }

    }

    public static void excluir (TOJogo jogo) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            DAOJogo.excluir(jogo, c);

        } finally {
            if (c != null)
                c.close();
        }

    }

    public static JSONArray  lista(TOCampeonato campeonato) throws Exception {
        Connection c = null;
        JSONArray ja;

        try {
            c = Data.openConnection();
            ja = DAOJogo.obterTodos(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONObject obterUm(TOJogo jogo) throws Exception {
        Connection c = null;
        JSONObject jo;

        try {
            c = Data.openConnection();
            jo = DAOJogo.obterUm(jogo, c);

        } finally {
            if (c != null)
                c.close();
        }

        return jo;
    }

}
