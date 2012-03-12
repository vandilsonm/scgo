/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOSubstituicao;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class DAOSubstituicao {

    public static void inserir (TOSubstituicao subs, Connection c) throws Exception {

        String sql = " insert into sgc_substituicao_sub (jgs_codigo, tim_codigo, "
                + "                                     jog_codigo_entrou, jog_codigo_saiu)"
                    + " values (?, ?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[] {subs.getJogo().getCodigo(),
                                                subs.getTime().getCodigo(),
                                                subs.getJogadorEntrou().getCodigo(),
                                                subs.getJogadorSaiu().getCodigo()});
    }

    public static void excluir (TOSubstituicao subs, Connection c) throws Exception {

        String sql = " delete from sgc_substituicao_sub where jgs_codigo = ? "
                    + " and tim_codigo = ? "
                    + " and jog_codigo_entrou = ? "
                    + " and jog_codigo_saiu = ? ";

        Data.executeUpdate(c, sql, new Object[] {subs.getJogo().getCodigo(),
                                                subs.getTime().getCodigo(),
                                                subs.getJogadorEntrou().getCodigo(),
                                                subs.getJogadorSaiu().getCodigo()});
    }

    public static JSONArray obterTodos(TOCampeonato campeonato, Connection c) throws Exception {
        String sql = " select sub.jog_codigo_entrou, sub.jog_codigo_saiu, sub.jgs_codigo, sub.tim_codigo, "
                    + " jog1.jog_nome as jog_nome_entrou, jog2.jog_nome as jog_nome_saiu, "
                    + " tim1.tim_nome as time_mandante, tim2.tim_nome as time_visitante "
                    + " from sgc_substituicao_sub sub "
                    + "   inner join sgc_jogos_jgs jgs on sub.jgs_codigo = jgs.jgs_codigo "
                    + "   inner join sgc_jogador_jog jog1 "
                    + "     on jog1.jog_codigo = sub.jog_codigo_entrou "
                    + "   inner join sgc_jogador_jog jog2 "
                    + "     on jog2.jog_codigo = sub.jog_codigo_saiu "
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
            jo.put("jogadorCodigoEntrou", rs.getInt("jog_codigo_entrou"));
            jo.put("jogadorCodigoSaiu", rs.getInt("jog_codigo_saiu"));
            jo.put("jogadorNomeEntrou", rs.getString("jog_nome_entrou"));
            jo.put("jogadorNomeSaiu", rs.getString("jog_nome_saiu"));
            jo.put("timeMandante", rs.getString("time_mandante"));
            jo.put("timeVisitante", rs.getString("time_visitante"));
            ja.put(jo);
        }

        rs.close();
        return ja;
    }

}
