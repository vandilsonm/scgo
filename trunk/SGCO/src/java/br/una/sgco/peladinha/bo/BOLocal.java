/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.bo;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.dao.DAOLocal;
import br.una.sgco.peladinha.to.TOLocal;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class BOLocal {

    public static void inserir (TOLocal toLocal) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            DAOLocal.inserir(toLocal, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public static void alterar (TOLocal toLocal) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            DAOLocal.alterar(toLocal, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public static void excluir (TOLocal toLocal) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            DAOLocal.excluir(toLocal, connection);

        } catch (Exception e){
            System.out.println("Não é possível excluir");
        }finally {
            if (connection != null)
                connection.close();
        }

    }

    public static JSONArray listar(TOLocal toLocal) throws Exception {
        Connection connection = null;
        JSONArray jsonArrary;

        try {
            connection = Data.openConnection();
            jsonArrary = DAOLocal.lista(toLocal, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

        return jsonArrary;
    }

    public static JSONObject get(TOLocal toLocal) throws Exception {
        Connection connection = null;
        JSONObject jsonObject;

        try {
            connection = Data.openConnection();
            jsonObject = DAOLocal.get(toLocal, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

        return jsonObject;
    }
}
