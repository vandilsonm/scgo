/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.to.TOJogador;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class DAOJogador {

    public static void inserir (TOJogador toJogador, Connection connection) throws Exception {

        String sql = " insert into sgc_jogador (nome, celular, email) "
                    + " values (?, ?, ?)";

        Data.executeUpdate(connection, sql, new Object[] {
                            toJogador.getNome(), toJogador.getCelular(),
                            toJogador.getEmail()});
    }

    public static void alterar (TOJogador toJogador, Connection connection) throws Exception {
        String sql = " update sgc_jogador set nome = ?, celular = ?, "
                + " email = ? where id = ? ";

        Data.executeUpdate(connection, sql, new Object[] {
                            toJogador.getNome(), toJogador.getCelular(),
                            toJogador.getEmail(), toJogador.getId()});
    }

    public static void excluir (TOJogador toJogador, Connection connection) throws Exception {
        String sql = " delete from sgc_jogador where id = ?";

        Data.executeUpdate(connection, sql, new Object[] {toJogador.getId()});

    }
    
    public static JSONObject get(TOJogador toJogador, Connection connection) throws Exception {

       String sql = " select id, nome, celular, email"
                    + " from sgc_jogador where id = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(connection, sql, new Object[] {toJogador.getId()});

        if (rs.next()) {
            jo.put("id", rs.getInt("id"));
            jo.put("nome", rs.getString("nome"));
            jo.put("celular", rs.getString("celular"));
            jo.put("email", rs.getString("email"));
        }

        rs.close();

        return jo;
    }

    public static JSONArray lista(TOJogador toJogador, Connection connection) throws Exception {

       String sql = " select id, nome, celular, email from sgc_jogador order by nome";

       JSONArray jsonArray = new JSONArray();

       ResultSet rs = Data.executeQuery(connection, sql, new Object[]{toJogador.getId()});

       while (rs.next()) {
            JSONObject jsonObejct = new JSONObject();
            jsonObejct.put("id", rs.getInt("id"));
            jsonObejct.put("nome", rs.getString("nome"));
            jsonObejct.put("celular", rs.getString("celular"));
            jsonObejct.put("email", rs.getString("email"));
            jsonArray.put(jsonObejct);
        }

        rs.close();
        return jsonArray;
    }
}
