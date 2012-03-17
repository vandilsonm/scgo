/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.to.TOPelada;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class DAOPelada {
    
    public static void inserir (TOPelada toPelada, Connection connection) throws Exception {

        String sql = " insert into sgc_pelada (nome, descricao, horario) "
                    + " values (?, ?, ?)";

        Data.executeUpdate(connection, sql, new Object[] {
                            toPelada.getNome(), toPelada.getDescricao(),
                            toPelada.getHorario()});
    }

    public static void alterar (TOPelada toPelada, Connection connection) throws Exception {
        String sql = " update sgc_pelada set nome = ?, descricao = ?, "
                + " horario = ? where id = ? ";

        Data.executeUpdate(connection, sql, new Object[] {
                            toPelada.getNome(), toPelada.getDescricao(),
                            toPelada.getHorario(), toPelada.getId()});
    }

    public static void excluir (TOPelada toPelada, Connection connection) throws Exception {
        String sql = " delete from sgc_pelada where id = ?";

        Data.executeUpdate(connection, sql, new Object[] {toPelada.getId()});

    }

    public static JSONObject get(TOPelada toPelada, Connection connection) throws Exception {

       String sql = " select id, nome, descricao, horario from sgc_pelada where id = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(connection, sql, new Object[] {toPelada.getId()});

        if (rs.next()) {
            jo.put("id", rs.getInt("id"));
            jo.put("nome", rs.getString("nome"));
            jo.put("descricao", rs.getString("descricao"));
            jo.put("horario", rs.getTime("horario"));
        }

        rs.close();

        return jo;
    }

    public static JSONArray lista(TOPelada toPelada, Connection connection) throws Exception {

       String sql = " select id, nome, descricao, horario from sgc_pelada order by nome";

       JSONArray jsonArray = new JSONArray();

       ResultSet rs = Data.executeQuery(connection, sql, new Object[]{toPelada.getId()});

       while (rs.next()) {
            JSONObject jsonObejct = new JSONObject();
            jsonObejct.put("id", rs.getInt("id"));
            jsonObejct.put("nome", rs.getString("nome"));
            jsonObejct.put("descricao", rs.getString("descricao"));
            jsonObejct.put("horario", rs.getTime("horario"));
            jsonArray.put(jsonObejct);
        }

        rs.close();
        return jsonArray;
    }
}
