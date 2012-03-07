/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOJogador;
import br.una.sgco.to.TOJogo;
import br.una.sgco.to.TOTime;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class DAOJogador {

    public static void inserir (TOJogador jogador, Connection c) throws Exception {

        String sql = " insert into sgc_jogador_jog (jog_nome, jog_posicao, jog_status, "
                    + " jog_tipo, tim_codigo)"
                    + " values (?, ?, ?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[] {jogador.getNome(), jogador.getPosicao(),
                                jogador.getStatus(), jogador.getTipo(), jogador.getTime().getCodigo()});
    }

    public static void alterar (TOJogador jogador, Connection c) throws Exception {
        String sql = " update sgc_jogador_jog set jog_nome = ?, jog_posicao = ?, "
                + " jog_status = ?, jog_tipo = ? "
                + " where jog_codigo = ? ";

        Data.executeUpdate(c, sql, new Object[] {jogador.getNome(), jogador.getPosicao(),
                                jogador.getStatus(), jogador.getTipo(), jogador.getCodigo()});
    }

    public static void inativar (TOJogador jogador, Connection c) throws Exception {
        String sql = " update sgc_jogador_jog set jog_status = 'I' where jog_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {jogador.getCodigo()});

    }

    public static JSONArray obterTodosTime(TOTime time, Connection c) throws Exception {
        String sql = " select jog_codigo, jog_nome, jog_posicao, jog_status, jog_tipo, tim_codigo"
                    + " from sgc_jogador_jog where tim_codigo = ? and jog_status = 'A' order by jog_nome ";

        JSONArray ja = new JSONArray();

        ResultSet rs = Data.executeQuery(c, sql, new Object[]{time.getCodigo()});

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("Codigo", rs.getInt("jog_codigo"));
            jo.put("Nome", rs.getString("jog_nome"));
            jo.put("Posicao", rs.getString("jog_posicao"));
            jo.put("Status", rs.getString("jog_status"));
            jo.put("Tipo", rs.getString("jog_tipo"));
            jo.put("Time", rs.getInt("tim_codigo"));
            ja.put(jo);
        }

        rs.close();
        return ja;
    }

    public static JSONArray obterTodos(TOCampeonato campeonato, Connection c) throws Exception {
        String sql = " select jog.jog_codigo, jog.jog_nome, jog.jog_posicao, "
                    + " jog.jog_status, jog.jog_tipo, jog.tim_codigo, tim.tim_nome "
                    + " from sgc_jogador_jog jog inner join sgc_time_tim tim "
                    + " on jog.tim_codigo = tim.tim_codigo "
                    + " where tim.cam_codigo = ?"
                    + " order by tim.tim_nome, jog.jog_nome ";

        JSONArray ja = new JSONArray();

        ResultSet rs = Data.executeQuery(c, sql, new Object[]{campeonato.getCodigo()});

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("Codigo", rs.getInt("jog_codigo"));
            jo.put("Nome", rs.getString("jog_nome"));
            jo.put("Posicao", rs.getString("jog_posicao"));
            jo.put("Status", rs.getString("jog_status"));
            jo.put("Tipo", rs.getString("jog_tipo"));
            jo.put("Time", rs.getString("tim_nome"));
            ja.put(jo);
        }

        rs.close();

        return ja;
    }

    public static JSONObject obterUm(TOJogador jogador, Connection c) throws Exception {

       String sql = " select jog_codigo, jog_nome, jog_posicao, jog_status, jog_tipo, tim_codigo"
                    + " from sgc_jogador_jog where jog_codigo = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {jogador.getCodigo()});

        if (rs.next()) {
            jo.put("codigo", rs.getInt("jog_codigo"));
            jo.put("nome", rs.getString("jog_nome"));
            jo.put("posicao", rs.getString("jog_posicao"));
            jo.put("status", rs.getString("jog_status"));
            jo.put("tipo", rs.getString("jog_tipo"));
            jo.put("time", rs.getInt("tim_codigo"));
        }

        rs.close();

        return jo;
    }
    
    public static JSONArray obterTodosJogo(TOJogo jogo, Connection c) throws Exception {
        String sql = " select jog.jog_codigo, jog.jog_nome, tim.tim_codigo "
                + " from sgc_jogador_jog jog "
                + "   inner join sgc_time_tim tim on jog.tim_codigo = tim.tim_codigo "
                + "   inner join sgc_jogos_jgs jgs "
                + "     on jgs.tim_codigo_mandante = tim.tim_codigo "
                + "       or jgs.tim_codigo_visitante = tim.tim_codigo "
                + "where jgs.jgs_codigo = ? order by jog.jog_nome ";

        JSONArray ja = new JSONArray();

        ResultSet rs = Data.executeQuery(c, sql, new Object[]{jogo.getCodigo()});

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("codigo", rs.getInt("jog_codigo"));
            jo.put("nome", rs.getString("jog_nome"));
            jo.put("codigoTime", rs.getInt("tim_codigo"));
            ja.put(jo);
        }

        rs.close();
        return ja;
    }


}
