/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOArbitragem;
import br.una.sgco.to.TOCampeonato;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class DAOArbitragem {

    public static void inserir (TOArbitragem arbitro, Connection c) throws Exception {

        String sql = " insert into sgc_arbitragem_arb (arb_nome, arb_tipo, cam_codigo) "
                   + " values (?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[] {arbitro.getNome(), arbitro.getTipo(),
                                                arbitro.getCampeonato().getCodigo()});
        
    }

    public static void alterar (TOArbitragem arbitro, Connection c) throws Exception {
        String sql = " update sgc_arbitragem_arb set arb_nome = ?, arb_tipo = ? "
                    + " where arb_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {arbitro.getNome(), arbitro.getTipo(),
                                                arbitro.getCodigo()});
    }

    public static void excluir (TOArbitragem arbitro, Connection c) throws Exception {
        String sql = " delete from sgc_arbitragem_arb where arb_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {arbitro.getCodigo()});
    }

    public static JSONArray obterTodos(TOCampeonato campeonato, Connection c) throws Exception {
        String sql = " select arb_codigo, arb_nome, arb_tipo "
                   + " from sgc_arbitragem_arb where cam_codigo = ? order by arb_nome ";

        JSONArray ja = new JSONArray();

        ResultSet rs = Data.executeQuery(c, sql, new Object[]{campeonato.getCodigo()});

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("codigo", rs.getInt("arb_codigo"));
            jo.put("nome", rs.getString("arb_nome"));
            jo.put("tipo", rs.getString("arb_tipo"));

            ja.put(jo);
        }

        rs.close();
        return ja;
    }

    public static JSONObject obterUm(TOArbitragem arbitro, Connection c) throws Exception {

        String sql = " select arb_codigo, arb_nome, arb_tipo "
                   + " from sgc_arbitragem_arb where arb_codigo = ? ";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {arbitro.getCodigo()});

        if (rs.next()) {
            jo.put("codigo", rs.getInt("arb_codigo"));
            jo.put("nome", rs.getString("arb_nome"));
            jo.put("tipo", rs.getString("arb_tipo"));
        }
        
        rs.close();
        return jo;
    }
}
