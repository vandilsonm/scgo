/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.bo;

import br.una.sgco.dao.DAOJogador;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOJogador;
import br.una.sgco.to.TOTime;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class BOJogador {

    public static void inserir (TOJogador jogador) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOJogador.inserir(jogador, c);

        } finally {
            if (c != null)
                c.close();
        }
    }

    public static void alterar (TOJogador jogador) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOJogador.alterar(jogador, c);

        } finally {
            if (c != null)
                c.close();
        }
    }

    public static void inativar (TOJogador jogador) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOJogador.inativar(jogador, c);

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

            ja = DAOJogador.obterTodos(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONArray obterTodosTime (TOTime time) throws Exception {
        Connection c = null;
        JSONArray ja = null;

        try {
            c = Data.openConnection();

            ja = DAOJogador.obterTodosTime(time, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONObject obterUm(TOJogador jogador) throws Exception {
        Connection c = null;
        JSONObject jo = null;

        try {
            c = Data.openConnection();

            jo = DAOJogador.obterUm(jogador, c);

        } finally {
            if (c != null)
                c.close();
        }

        return jo;
    }

}
