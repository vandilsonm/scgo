/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.to.TOLocal;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class DAOLocal {

    public static void inserir (TOLocal toLocal, Connection connection) throws Exception {

        String sql = " insert into sgc_local (idPelada, nome, endereco) "
                    + " values (?, ?, ?)";

        Data.executeUpdate(connection, sql, new Object[] {
                            toLocal.getIdPelada().getId(), toLocal.getNome(),
                            toLocal.getEndereco()});
    }

    public static void alterar (TOLocal toLocal, Connection connection) throws Exception {
        String sql = " update sgc_local set idPelada = ?, nome = ?, "
                + " endereco = ? where id = ? ";

        Data.executeUpdate(connection, sql, new Object[] {
                            toLocal.getIdPelada().getId(), toLocal.getNome(),
                            toLocal.getEndereco(), toLocal.getId()});
    }

    public static void excluir (TOLocal toLocal, Connection connection) throws Exception {
        String sql = " delete from sgc_local where id = ?";

        Data.executeUpdate(connection, sql, new Object[] {toLocal.getId()});

    }

    public static JSONObject get(TOLocal toLocal, Connection connection) throws Exception {

       String sql = " select id, idPelada, nome, endereco from sgc_local where id = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(connection, sql, new Object[] {toLocal.getId()});

        if (rs.next()) {
            jo.put("id", rs.getInt("id"));
            jo.put("idPelada", rs.getInt("idPelada"));
            jo.put("nome", rs.getString("nome"));
            jo.put("endereco", rs.getString("endereco"));
        }

        rs.close();

        return jo;
    }

    public static JSONArray lista(TOLocal toLocal, Connection connection) throws Exception {

       String sql = " select id, idPelada, nome, endereco from sgc_local order by nome";

       JSONArray jsonArray = new JSONArray();

       ResultSet rs = Data.executeQuery(connection, sql, new Object[]{toLocal.getId()});

       while (rs.next()) {
            JSONObject jsonObejct = new JSONObject();
            jsonObejct.put("id", rs.getInt("id"));
            jsonObejct.put("idPelada", rs.getInt("idPelada"));
            jsonObejct.put("nome", rs.getString("nome"));
            jsonObejct.put("endereco", rs.getString("endereco"));
            jsonArray.put(jsonObejct);
        }

        rs.close();
        return jsonArray;
    }
}
