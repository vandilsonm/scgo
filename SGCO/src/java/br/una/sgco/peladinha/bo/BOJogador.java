/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.bo;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.dao.DAOJogador;
import br.una.sgco.peladinha.to.TOJogador;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class BOJogador {

    public static void inserir (TOJogador toJogador) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            DAOJogador.inserir(toJogador, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public static void alterar (TOJogador toJogador) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            DAOJogador.alterar(toJogador, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public static void excluir (TOJogador toJogador) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            DAOJogador.excluir(toJogador, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public static JSONArray lista(TOJogador toJogador) throws Exception {
        Connection connection = null;
        JSONArray jsonArrary;

        try {
            connection = Data.openConnection();
            jsonArrary = DAOJogador.lista(toJogador, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

        return jsonArrary;
    }

    public static JSONObject get(TOJogador toJogador) throws Exception {
        Connection connection = null;
        JSONObject jsonObject;

        try {
            connection = Data.openConnection();
            jsonObject = DAOJogador.get(toJogador, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

        return jsonObject;
    }
}