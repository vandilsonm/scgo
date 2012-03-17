/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.to.TOConfirmacao;
import br.una.sgco.peladinha.to.TOJogador;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class DAOConfirmacao {

    public static void inserir (TOConfirmacao toConfirmacao, Connection connection) throws Exception {

        String sql = " insert into sgc_jogadorPelada (idJogador, idPelada, confirmacao) "
                    + " values (?, ?, ?)";

        Data.executeUpdate(connection, sql, new Object[] {
                            toConfirmacao.getIdJogador().getId(), toConfirmacao.getIdPelada().getId(),
                            toConfirmacao.isConfirmacao()});
    }

    public static void alterar (TOConfirmacao toConfirmacao, Connection connection) throws Exception {
        String sql = " update sgc_jogadorPelada set idJogador = ?, idPelada = ?, "
                + " confirmacao = ? where idJogador = ? and idPelada= ?";

        Data.executeUpdate(connection, sql, new Object[] {
                            toConfirmacao.getIdJogador().getId(), toConfirmacao.getIdPelada().getId(),
                            toConfirmacao.isConfirmacao(), toConfirmacao.getIdJogador().getId(),
                            toConfirmacao.getIdPelada().getId()});
    }

    public static void excluir (TOConfirmacao toConfirmacao, Connection connection) throws Exception {
        String sql = " delete from sgc_jogadorPelada where idJogador = ? and idPelada = ?";

        Data.executeUpdate(connection, sql, new Object[] {toConfirmacao.getIdJogador().getId(),
                                                          toConfirmacao.getIdPelada().getId()});

    }

    public static JSONObject get(TOConfirmacao toConfirmacao, Connection connection) throws Exception {

       String sql = " select idJogador, idPelada, confirmacao"
                    + " from sgc_jogadorPelada where idJogador = ? and idPelada = ?";

        JSONObject jsonObejct = new JSONObject();

        ResultSet rs = Data.executeQuery(connection, sql, new Object[] {toConfirmacao.getIdJogador().getId(),
                                                                        toConfirmacao.getIdPelada().getId()});

        if (rs.next()) {
            jsonObejct.put("idJogador", rs.getInt("idJogador"));
            jsonObejct.put("idPelada", rs.getInt("idPelada"));
            jsonObejct.put("confirmacao", rs.getString("confirmacao"));
        }

        rs.close();

        return jsonObejct;
    }

    public static JSONArray lista(TOConfirmacao toConfirmacao, Connection connection) throws Exception {

       String sql = " select idJogador, idPelada, confirmacao from sgc_jogadorPelada";

       JSONArray jsonArray = new JSONArray();

       ResultSet rs = Data.executeQuery(connection, sql);

       while (rs.next()) {
            JSONObject jsonObejct = new JSONObject();
            jsonObejct.put("idJogador", rs.getInt("idJogador"));
            jsonObejct.put("idPelada", rs.getInt("idPelada"));
            jsonObejct.put("confirmacao", rs.getString("confirmacao"));
            jsonArray.put(jsonObejct);
        }

        rs.close();
        return jsonArray;
    }
}
