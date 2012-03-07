/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.bo;

import br.una.sgco.dao.DAOCartoes;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOCartao;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class BOCartao {

    public static void inserir (TOCartao cartao) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            DAOCartoes.inserir(cartao, c);

        } finally {
            if (c != null)
                c.close();
        }

    }

    public static void alterar (TOCartao cartao) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            DAOCartoes.alterar(cartao, c);

        } finally {
            if (c != null)
                c.close();
        }

    }

    public static void excluir (TOCartao cartao) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();
            DAOCartoes.excluir(cartao, c);

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
            ja = DAOCartoes.obterTodos(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static JSONObject obterUm(TOCartao cartao) throws Exception {
        Connection c = null;
        JSONObject jo;

        try {
            c = Data.openConnection();
            jo = DAOCartoes.obterUm(cartao, c);

        } finally {
            if (c != null)
                c.close();
        }

        return jo;
    }
}
