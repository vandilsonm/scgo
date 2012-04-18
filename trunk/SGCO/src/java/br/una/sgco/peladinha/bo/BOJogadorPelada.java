/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.bo;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.dao.DAOJogadorPelada;
import br.una.sgco.peladinha.dao.DAOPelada;
import br.una.sgco.peladinha.to.TOJogadorPelada;
import br.una.sgco.peladinha.to.TOPelada;
import java.sql.Connection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class BOJogadorPelada {

    public static void inserir (List<TOJogadorPelada> jogadorPelada) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            
            //excluir todos antes de cadastrar
            BOJogadorPelada.excluir(jogadorPelada.get(0));
            //agora cadastra todos
            DAOJogadorPelada.inserir(jogadorPelada, connection);
        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public static void excluir (TOJogadorPelada jogadorPelada) throws Exception {
        Connection connection = null;

        try {
            connection = Data.openConnection();
            DAOJogadorPelada.excluir(jogadorPelada.getIdPelada(), connection);

        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public static JSONArray listar(TOPelada toPelada) throws Exception {
        Connection connection = null;
        JSONArray jsonArrary;

        try {
            connection = Data.openConnection();
            jsonArrary = DAOPelada.lista(toPelada, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

        return jsonArrary;
    }
    
    public static JSONArray listarJogadores(TOPelada toPelada) throws Exception {
        Connection connection = null;
        JSONArray jsonArrary;

        try {
            connection = Data.openConnection();
            jsonArrary = DAOPelada.getJogadores(toPelada, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

        return jsonArrary;
    }

    public static JSONObject get(TOPelada toPelada) throws Exception {
        Connection connection = null;
        JSONObject jsonObject;

        try {
            connection = Data.openConnection();
            jsonObject = DAOPelada.get(toPelada, connection);

        } finally {
            if (connection != null)
                connection.close();
        }

        return jsonObject;
    }

}
