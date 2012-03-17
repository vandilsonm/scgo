/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.bo;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.dao.DAOConfirmacao;
import br.una.sgco.peladinha.to.TOConfirmacao;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class BOConfirmacao {

    public static void inserir (TOConfirmacao toConfirmacao) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            DAOConfirmacao.inserir(toConfirmacao, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public static void alterar (TOConfirmacao toConfirmacao) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            DAOConfirmacao.alterar(toConfirmacao, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public static void excluir (TOConfirmacao toConfirmacao) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            DAOConfirmacao.excluir(toConfirmacao, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public static JSONArray lista(TOConfirmacao toConfirmacao) throws Exception {
        Connection connection = null;
        JSONArray jsonArrary;

        try {
            connection = Data.openConnection();
            jsonArrary = DAOConfirmacao.lista(toConfirmacao, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

        return jsonArrary;
    }

    public static JSONObject get(TOConfirmacao toConfirmacao) throws Exception {
        Connection connection = null;
        JSONObject jsonObject;

        try {
            connection = Data.openConnection();
            jsonObject = DAOConfirmacao.get(toConfirmacao, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

        return jsonObject;
    }
}
