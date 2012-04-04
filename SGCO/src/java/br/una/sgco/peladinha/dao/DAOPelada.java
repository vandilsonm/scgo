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

        String sql = " insert into sgc_pelada (nome, descricao, horario, idLocal, criador) "
                    + " values (?, ?, ?, ?, ?)";

        Data.executeUpdate(connection, sql, new Object[] {
                            toPelada.getNome(), toPelada.getDescricao(),
                            toPelada.getHorario(), toPelada.getIdLocal().getId(),
                            toPelada.getCriador()});
    }

    public static void alterar (TOPelada toPelada, Connection connection) throws Exception {
        String sql = " update sgc_pelada set nome = ?, descricao = ?, "
                + " horario = ?, idLocal = ? where id = ? and criador = ?";

        Data.executeUpdate(connection, sql, new Object[] {
                            toPelada.getNome(), toPelada.getDescricao(),
                            toPelada.getHorario(), toPelada.getIdLocal().getId(), 
                            toPelada.getId(), toPelada.getCriador()});
    }

    public static void excluir (TOPelada toPelada, Connection connection) throws Exception {
        String sql = " delete from sgc_pelada where id = ?";

        Data.executeUpdate(connection, sql, new Object[] {toPelada.getId()});

    }

    public static JSONObject get(TOPelada toPelada, Connection connection) throws Exception {

       String sql = " select id, nome, descricao, horario, idLocal, criador from sgc_pelada where id = ?"
               + " and criador = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(connection, sql, new Object[] {toPelada.getId(), toPelada.getCriador()});

        if (rs.next()) {
            jo.put("id", rs.getInt("id"));
            jo.put("nome", rs.getString("nome"));
            jo.put("descricao", rs.getString("descricao"));
            jo.put("horario", rs.getTime("horario"));
            jo.put("idLocal", rs.getInt("idLocal"));
            jo.put("criador", rs.getInt("criador"));
        }

        rs.close();

        return jo;
    }

    public static JSONArray lista(TOPelada toPelada, Connection connection) throws Exception {

       String sql = " select id, nome, descricao, horario, idLocal, criador from sgc_pelada order by nome";

       JSONArray jsonArray = new JSONArray();

       ResultSet rs = Data.executeQuery(connection, sql);

       while (rs.next()) {
            JSONObject jsonObejct = new JSONObject();
            jsonObejct.put("id", rs.getInt("id"));
            jsonObejct.put("nome", rs.getString("nome"));
            jsonObejct.put("descricao", rs.getString("descricao"));
            jsonObejct.put("horario", rs.getTime("horario"));
            jsonObejct.put("idLocal", rs.getInt("idLocal"));   
            jsonObejct.put("criador", rs.getInt("criador"));
            jsonArray.put(jsonObejct);
        }

        rs.close();
        return jsonArray;
    }
    
    public static JSONArray getJogadores(TOPelada toPelada, Connection connection) throws Exception {

       String sql = " select id, nome, descricao, horario, idLocal, criador "
               + "from sgc_pelada where criador = ? order by nome";
       JSONArray jsonArray = new JSONArray();

       ResultSet rs = Data.executeQuery(connection, sql, new Object[]{toPelada.getCriador()});

       while (rs.next()) {
            JSONObject jsonObejct = new JSONObject();
            jsonObejct.put("id", rs.getInt("id"));
            jsonObejct.put("nome", rs.getString("nome"));
            jsonObejct.put("descricao", rs.getString("descricao"));
            jsonObejct.put("horario", rs.getTime("horario"));
            jsonObejct.put("idLocal", rs.getInt("idLocal"));   
            jsonObejct.put("criador", rs.getInt("criador"));
            jsonArray.put(jsonObejct);
        }

        rs.close();
        return jsonArray;
    }
}
