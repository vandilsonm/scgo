/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOCartao;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class DAOCartoes {

    public static void inserir (TOCartao cartao, Connection c) throws Exception {

        String sql = " insert into sgc_cartoes_crt (crt_codigo, jgs_codigo, tim_codigo, jog_codigo, crt_tipo)"
                    + " values (?, ?, ?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[] {cartao.getCodigo(),
                                                cartao.getJogo().getCodigo(),
                                                cartao.getTime().getCodigo(),
                                                cartao.getJogador().getCodigo(),
                                                cartao.getTipo()});
    }

    public static void alterar (TOCartao cartao, Connection c) throws Exception {

        String sql = " update sgc_cartoes_crt set crt_tipo = ? "
                    + " where crt_codigo = ? and tim_codigo = ? ";

        Data.executeUpdate(c, sql, new Object[] {cartao.getTipo(),
                                                    cartao.getCodigo(),
                                                    cartao.getTime().getCodigo(),
                                                    });
    }

    public static void excluir (TOCartao cartao, Connection c) throws Exception {
        String sql = " delete from sgc_cartoes_crt where crt_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {cartao.getCodigo()});
    }

    public static JSONArray obterTodos(TOCampeonato campeonato, Connection c) throws Exception {
        String sql = " select crt.jog_codigo, crt.tim_codigo, crt.jgs_codigo, jog.jog_nome, "
                    + " tim1.tim_nome as time_mandante, tim2.tim_nome as time_visitante, crt.crt_tipo "
                    + " from sgc_cartoes_crt crt "
                    + "   inner join sgc_jogador_jog jog on crt.jog_codigo = jog.jog_codigo "
                    + "   inner join sgc_jogos_jgs jgs on crt.jgs_codigo = jgs.jgs_codigo "
                    + "   inner join sgc_time_tim tim1 "
                    + "     on tim1.tim_codigo = jgs.tim_codigo_mandante "
                    + "   inner join sgc_time_tim tim2 "
                    + "     on tim2.tim_codigo = jgs.tim_codigo_visitante "
                    + " where jgs.cam_codigo = ?";

        JSONArray ja = new JSONArray();

        ResultSet rs = Data.executeQuery(c, sql, new Object[]{campeonato.getCodigo()});

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("jogoCodigo", rs.getInt("jgs_codigo"));
            jo.put("timeCodigo", rs.getInt("tim_codigo"));
            jo.put("jogadorCodigo", rs.getInt("jog_codigo"));
            jo.put("jogadorNome", rs.getString("jog_nome"));
            jo.put("timeMandante", rs.getString("time_mandante"));
            jo.put("timeVisitante", rs.getString("time_visitante"));
            jo.put("tipo", rs.getString("crt_tipo"));
            ja.put(jo);
        }
        
        return ja;
    }

    public static JSONObject obterUm(TOCartao cartao, Connection c) throws Exception {
        String sql = " select jog_codigo, jgs_codigo, crt_tipo "
                    + " from sgc_cartoes_crt "
                    + " where jog_codigo = ? and jgs_codigo = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(c, sql, new Object[]{cartao.getJogador().getCodigo(),
                                                            cartao.getJogo().getCodigo()});

        while (rs.next()) {
            jo.put("jogo", rs.getInt("jgs_codigo"));
            jo.put("jogador", rs.getInt("jog_codigo"));
            jo.put("tipo", rs.getString("crt_tipo"));
        }

        return jo;
    }
}
