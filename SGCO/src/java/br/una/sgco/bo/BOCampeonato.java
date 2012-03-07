/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.bo;

import br.una.sgco.dao.DAOCampeonato;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOUsuario;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class BOCampeonato {

    public static JSONArray lista(TOUsuario usuario) throws Exception {
        Connection c = null;
        JSONArray ja = null;

        try {
            c = Data.openConnection();

            ja = DAOCampeonato.lista(usuario, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONArray listaClassificacao(TOCampeonato campeonato) throws Exception {
        Connection c = null;
        JSONArray ja = null;

        try {
            c = Data.openConnection();

            ja = DAOCampeonato.listaClassificacao(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONArray listaUltimosJogosCamp(TOCampeonato campeonato) throws Exception {
        Connection c = null;
        JSONArray ja = null;

        try {
            c = Data.openConnection();

            ja = DAOCampeonato.listaUltimosJogosCamp(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONArray listaUltimosJogos() throws Exception {
        Connection c = null;
        JSONArray ja = null;

        try {
            c = Data.openConnection();

            ja = DAOCampeonato.listaUltimosJogos(c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONArray obterTodos() throws Exception {
        Connection c = null;
        JSONArray ja = null;

        try {
            c = Data.openConnection();

            ja = DAOCampeonato.obterTodos(c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static void inserir (TOCampeonato campeonato) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOCampeonato.inserir(campeonato, c);

        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public static JSONObject obterUm(TOCampeonato campeonato) throws Exception {
        Connection c = null;
        JSONObject jo = null;

        try {
            c = Data.openConnection();

            jo = DAOCampeonato.obterUm(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return jo;
    }

    public static JSONObject obterArtilheiro(TOCampeonato campeonato) throws Exception {
        Connection c = null;
        JSONObject jo = null;

        try {
            c = Data.openConnection();

            jo = DAOCampeonato.obterArtilheiro(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return jo;
    }

    public static void alterar (TOCampeonato campeonato) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOCampeonato.alterar(campeonato, c);

        } finally {
            if (c != null) {
                c.close();
            }
        }
    }
   
}
