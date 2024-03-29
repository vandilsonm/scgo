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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class DAOJogador {

    public static void inserir (TOJogador jogador, Connection c) throws Exception {

        String sql = " insert into sgc_jogador_jog (jog_nome, jog_posicao, jog_status, "
                    + " jog_tipo, tim_codigo, jog_celular, email, twitter)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[] {jogador.getNome(), jogador.getPosicao(),
                                jogador.getStatus(), jogador.getTipo(), 
                                jogador.getTime().getCodigo(), jogador.getCelular(),
                                jogador.getEmail(), jogador.getTwitter()});
    }

    public static void alterar (TOJogador jogador, Connection c) throws Exception {
        String sql = " update sgc_jogador_jog set jog_nome = ?, jog_posicao = ?, "
                + " jog_status = ?, jog_tipo = ?, jog_celular = ?, email = ?, twitter = ? "
                + " where jog_codigo = ? ";

        Data.executeUpdate(c, sql, new Object[] {jogador.getNome(), jogador.getPosicao(),
                                jogador.getStatus(), jogador.getTipo(), 
                                jogador.getCelular(), 
                                jogador.getEmail(), jogador.getTwitter(), jogador.getCodigo()});
    }

    public static void inativar (TOJogador jogador, Connection c) throws Exception {
        String sql = " update sgc_jogador_jog set jog_status = 'I' where jog_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {jogador.getCodigo()});

    }

    public static JSONArray  obterTodosTime(TOTime time, Connection c) throws Exception {
        String sql = " select jog_codigo, jog_nome, jog_posicao, jog_status, jog_tipo, tim_codigo, jog_celular, "
                    + " email, twitter from sgc_jogador_jog where tim_codigo = ? and jog_status = 'A' order by jog_nome ";

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
            jo.put("Celular", rs.getString("jog_celular"));
            jo.put("Email", rs.getString("email"));
            jo.put("Twitter", rs.getString("twitter"));
            ja.put(jo);
        }

        rs.close();
        return ja;
    }

    public static JSONArray obterTodos(TOCampeonato campeonato, Connection c) throws Exception {
        String sql = " select jog.jog_codigo, jog.jog_nome, jog.jog_posicao, jog.twitter as twitter, "
                    + " jog.jog_status, jog.jog_tipo, jog.tim_codigo, tim.tim_nome, jog.jog_celular "
                    + " from sgc_jogador_jog jog inner join sgc_time_tim tim "
                    + " on jog.tim_codigo = tim.tim_codigo "
                    + " where tim.cam_codigo = ? and jog.jog_status = 'A' "
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
            jo.put("Celular", rs.getString("jog_celular"));
            jo.put("Twitter", rs.getString("twitter"));
            ja.put(jo);
        }

        rs.close();

        return ja;
    }

    public static JSONObject obterUm(TOJogador jogador, Connection c) throws Exception {

       String sql = " select jog_codigo, jog_nome, jog_posicao, jog_status, jog_tipo, tim_codigo, jog_celular, "
                    + " email, twitter from sgc_jogador_jog where jog_codigo = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {jogador.getCodigo()});

        if (rs.next()) {
            jo.put("codigo", rs.getInt("jog_codigo"));
            jo.put("nome", rs.getString("jog_nome"));
            jo.put("posicao", rs.getString("jog_posicao"));
            jo.put("status", rs.getString("jog_status"));
            jo.put("tipo", rs.getString("jog_tipo"));
            jo.put("time", rs.getInt("tim_codigo"));
            jo.put("celular", rs.getString("jog_celular"));
            jo.put("email", rs.getString("email"));
            jo.put("twitter", rs.getString("twitter"));
        }

        rs.close();

        return jo;
    }
    
    public static JSONArray obterTodosJogo(TOJogo jogo, Connection c) throws Exception {
        String sql = " select jog.jog_codigo, jog.jog_nome, tim.tim_codigo, jog.jog_celular, "
                + " jog.twitter as twitter from sgc_jogador_jog jog "
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
            jo.put("celular", rs.getString("jog_celular"));
            jo.put("twitter", rs.getString("twitter"));
            ja.put(jo);
        }

        rs.close();
        return ja;
    }
    
    public static JSONArray getJogadoresConfirmados(TOJogador toJogador, Connection connection) throws Exception {

       String sql = " select jog.jog_nome as nome, jog.jog_celular as celular, jog.email as email, jog.twitter as twitter "
               + " from sgc_jogador_jog jog, sgc_jogadorjogo jj "
               + " where jj.idjogador = jog.jog_codigo "
               + " and jj.idJogo = ? and jj.confirmacao = true ";
       
       JSONArray jsonArray = new JSONArray();

       ResultSet rs = Data.executeQuery(connection, sql, new Object[]{toJogador.getCodigo()});

       while (rs.next()) {
            JSONObject jsonObejct = new JSONObject();
            //jsonObejct.put("id", rs.getInt("id"));
            jsonObejct.put("nome", rs.getString("nome"));
            jsonObejct.put("celular", rs.getString("celular"));
            jsonObejct.put("email", rs.getString("email"));
            jsonObejct.put("twitter", rs.getString("twitter"));
            jsonArray.put(jsonObejct);
        }

        rs.close();
        return jsonArray;
    }
}
