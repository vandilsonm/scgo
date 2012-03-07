/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOJogo;
import br.una.sgco.to.TOTime;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class DAOTime {
    public static void inserir (TOTime time, Connection c) throws Exception {

        String sql = " insert into sgc_time_tim (cam_codigo, tim_nome, tim_tecnico, "
                    + " tim_escudo, TIM_QTDE_JOGADORES) values (?, ?, ?, ?,?)";

        Data.executeUpdate(c, sql, new Object[] {time.getCampeonato().getCodigo(),
                                time.getNome(), time.getTecnico(), 
                                time.getEscudo(),time.getQtdJogadores()});
    }

    public static void alterar (TOTime time, Connection c) throws Exception {
        String sql = " update sgc_time_tim set tim_nome = ?, tim_tecnico = ?, "
                + " tim_escudo = ? where tim_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {time.getNome(), time.getTecnico(),
                                time.getEscudo(), time.getCodigo()});
    }

    public static void excluir (TOTime time, Connection c) throws Exception {
        String sql = " delete from sgc_time_tim where tim_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {time.getCodigo()});

    }

    public static JSONArray obterTodos(TOCampeonato campeonato, Connection c) throws Exception {
        String sql = " select tim_codigo, cam_codigo, tim_nome, tim_tecnico, "
                    + " tim_escudo "
                    + " from sgc_time_tim where cam_codigo = ? order by tim_nome ";

        JSONArray ja = new JSONArray();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {campeonato.getCodigo()});

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("Codigo", rs.getInt("tim_codigo"));
            jo.put("Nome", rs.getString("tim_nome"));
            jo.put("Tecnico", rs.getString("tim_tecnico"));
            jo.put("Escudo", rs.getString("tim_escudo"));

            ja.put(jo);
        }
        
        return ja;
    }

    public static JSONArray obterTodosJogo(TOJogo jogo, Connection c) throws Exception {
        String sql = " select tim_codigo, tim_nome "
                    + " from sgc_time_tim tim "
                    + "   inner join sgc_jogos_jgs jgs "
                    + "     on tim.tim_codigo = jgs.tim_codigo_mandante "
                    + "       or tim.tim_codigo = jgs.tim_codigo_visitante "
                    + " where jgs.jgs_codigo = ? order by tim_nome ";

        JSONArray ja = new JSONArray();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {jogo.getCodigo()});

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("Codigo", rs.getInt("tim_codigo"));
            jo.put("Nome", rs.getString("tim_nome"));

            ja.put(jo);
        }

        return ja;
    }

    public static JSONObject obterUm(TOTime time, Connection c) throws Exception {

        String sql = " select tim_codigo, cam_codigo, tim_nome, tim_tecnico, "
                    + " tim_escudo "
                    + " from sgc_time_tim where tim_codigo = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {time.getCodigo()});

        if (rs.next()) {
            jo.put("codigo", rs.getInt("tim_codigo"));
            jo.put("nome", rs.getString("tim_nome"));
            jo.put("tecnico", rs.getString("tim_tecnico"));
            jo.put("escudo", rs.getString("tim_escudo"));
        }

        return jo;
    }

}
