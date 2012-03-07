/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOEstadio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class DAOEstadio {

    public static void inserir (TOEstadio estadio, Connection c) throws Exception {

        String sql = " insert into sgc_estadio_est (est_nome, est_end_logradouro, est_end_numero, "
                   + " est_end_complemento, est_end_bairro, est_end_cidade, est_end_estado, "
                   + " cam_codigo) values (?, ?, ?, ?, ?, ?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[] {estadio.getNome(), estadio.getLogradouro(),
                                                estadio.getNumero(), estadio.getComplemento(),
                                                estadio.getBairro(), estadio.getCidade(),
                                                estadio.getEstado(),
                                                estadio.getCampeonato().getCodigo()});
    }

    public static void alterar (TOEstadio estadio, Connection c) throws Exception {
        String sql = " update sgc_estadio_est set est_nome = ?, est_end_logradouro = ?, "
                + " est_end_numero = ?, est_end_complemento = ?, est_end_bairro = ?, "
                + " est_end_cidade = ?, est_end_estado = ? "
                + " where est_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {estadio.getNome(), estadio.getLogradouro(),
                                                    estadio.getNumero(), estadio.getComplemento(),
                                                    estadio.getBairro(), estadio.getCidade(),
                                                    estadio.getEstado(), estadio.getCodigo()});
    }

    public static void excluir (TOEstadio estadio, Connection c) throws Exception {
        String sql = " delete from sgc_estadio_est where est_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {estadio.getCodigo()});
            
    }

    public static JSONArray obterTodos(TOCampeonato campeonato, Connection c) throws Exception {
        String sql = " select est_codigo, est_nome, est_end_logradouro, est_end_numero, "
                   + " est_end_complemento, est_end_bairro, est_end_cidade, est_end_estado "
                   + " from sgc_estadio_est where cam_codigo = ? order by est_nome ";


        JSONArray ja = new JSONArray();

        ResultSet rs = Data.executeQuery(c, sql, new Object[]{campeonato.getCodigo()});
            
        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("codigo", rs.getInt("est_codigo"));
            jo.put("nome", rs.getString("est_nome"));
            jo.put("logradouro", rs.getString("est_end_logradouro"));
            jo.put("numero", rs.getString("est_end_numero"));
            jo.put("complemento", rs.getString("est_end_complemento"));
            jo.put("bairro", rs.getString("est_end_bairro"));
            jo.put("cidade", rs.getString("est_end_cidade"));
            jo.put("estado", rs.getString("est_end_estado"));

            ja.put(jo);
        }

        return ja;
    }

    public static JSONObject obterUm(TOEstadio estadio, Connection c) throws Exception {

        String sql = " select est_codigo, est_nome, est_end_logradouro, est_end_numero, "
                   + " est_end_complemento, est_end_bairro, est_end_cidade, est_end_estado "
                   + " from sgc_estadio_est where est_codigo = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {estadio.getCodigo()});

        if (rs.next()) {
            jo.put("codigo", rs.getInt("est_codigo"));
            jo.put("nome", rs.getString("est_nome"));
            jo.put("logradouro", rs.getString("est_end_logradouro"));
            jo.put("numero", rs.getString("est_end_numero"));
            jo.put("complemento", rs.getString("est_end_complemento"));
            jo.put("bairro", rs.getString("est_end_bairro"));
            jo.put("cidade", rs.getString("est_end_cidade"));
            jo.put("estado", rs.getString("est_end_estado"));
        }

        rs.close();
        return jo;
    }
}
