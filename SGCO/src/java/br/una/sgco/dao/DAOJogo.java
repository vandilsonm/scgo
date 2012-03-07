/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOJogo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class DAOJogo {

    public static void inserir (TOJogo jogo, Connection c) throws Exception {

        String sql = " insert into sgc_jogos_jgs ("
                + " cam_codigo, "
                + " est_codigo, "
                + " tim_codigo_mandante,"
                + " tim_codigo_visitante, "
                + " jgs_data_hora, "
                + " jgs_qtde_gols_mandante, "
                + " jgs_qtde_gols_visitante, "
                + " arb_codigo_juiz, "
                + " arb_codigo_juiz_aux, "
                + " arb_codigo_bandeirinha1, "
                + " arb_codigo_bandeirinha2) "
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[] {
                    jogo.getCampeonato().getCodigo(),
                    jogo.getEstadio().getCodigo(),
                    jogo.getTimeMandante().getCodigo(),
                    jogo.getTimeVisitante().getCodigo(),
                    jogo.getDataHora(),
                    jogo.getGolsMandante(),
                    jogo.getGolsVisitante(),
                    jogo.getJuiz().getCodigo(),
                    jogo.getJuizAuxiliar().getCodigo(),
                    jogo.getBandeirinha1().getCodigo(),
                    jogo.getBandeirinha2().getCodigo()
        });
    }

    public static void alterar (TOJogo jogo, Connection c) throws Exception {
        String sql = " update sgc_jogos_jgs set "
                + " est_codigo = ?, "
                + " tim_codigo_mandante = ?,"
                + " tim_codigo_visitante = ?, "
                + " jgs_data_hora = ?, "
                + " jgs_qtde_gols_mandante = ?, "
                + " jgs_qtde_gols_visitante = ?, "
                + " arb_codigo_juiz = ?, "
                + " arb_codigo_juiz_aux = ?, "
                + " arb_codigo_bandeirinha1 = ?, "
                + " arb_codigo_bandeirinha2 = ? "
                + " where jgs_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {
                jogo.getEstadio().getCodigo(),
                jogo.getTimeMandante().getCodigo(),
                jogo.getTimeVisitante().getCodigo(),
                jogo.getDataHora(),
                jogo.getGolsMandante(),
                jogo.getGolsVisitante(),
                jogo.getJuiz().getCodigo(),
                jogo.getJuizAuxiliar().getCodigo(),
                jogo.getBandeirinha1().getCodigo(),
                jogo.getBandeirinha2().getCodigo(),
                jogo.getCodigo()
        });
    }

    public static void excluir (TOJogo jogo, Connection c) throws Exception {
        String sql = " delete from  sgc_jogos_jgs "
                    + " where jgs_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {
            jogo.getCodigo()
        });
    }

    public static JSONArray obterTodos(TOCampeonato campeonato, Connection c) throws Exception {
        String sql = " select jgs.jgs_codigo, "
                    + " tim1.tim_nome as time_mandante,"
                    + " tim2.tim_nome as time_visitante,"
                    + " jgs.jgs_data_hora "
                    + " from sgc_jogos_jgs jgs "
                    + "   inner join sgc_time_tim tim1 "
                    + "     on jgs.tim_codigo_mandante = tim1.tim_codigo "
                    + "   inner join sgc_time_tim tim2 "
                    + "     on jgs.tim_codigo_visitante = tim2.tim_codigo "
                    + " where jgs.cam_codigo = ? "
                    + " order by jgs.jgs_data_hora desc";

        JSONArray ja = new JSONArray();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {campeonato.getCodigo()});

        SimpleDateFormat df = new SimpleDateFormat("dd/MM HH:mm");
        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("codigo", rs.getInt("jgs_codigo"));
            jo.put("dataHora", df.format(rs.getTimestamp("jgs_data_hora")));
            jo.put("timeMandante", rs.getString("time_mandante"));
            jo.put("timeVisitante", rs.getString("time_visitante"));

            ja.put(jo);
        }

        rs.close();
        return ja;
    }

    public static JSONObject obterUm(TOJogo jogo, Connection c) throws Exception {

        String sql = " select jgs_codigo, "
                    + " cam_codigo,"
                    + " est_codigo,"
                    + " tim_codigo_mandante,"
                    + " tim_codigo_visitante,"
                    + " jgs_data_hora,"
                    + " jgs_qtde_gols_mandante,"
                    + " jgs_qtde_gols_visitante,"
                    + " arb_codigo_juiz,"
                    + " arb_codigo_juiz_aux,"
                    + " arb_codigo_bandeirinha1,"
                    + " arb_codigo_bandeirinha2 "
                    + " from sgc_jogos_jgs"
                    + " where jgs_codigo = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {jogo.getCodigo()});

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if (rs.next()) {
            jo.put("campeonato", rs.getInt("cam_codigo"));
            jo.put("estadio", rs.getInt("est_codigo"));
            jo.put("timeMandante", rs.getInt("tim_codigo_mandante"));
            jo.put("timeVisitante", rs.getInt("tim_codigo_visitante"));
            jo.put("juiz", rs.getInt("arb_codigo_juiz"));
            jo.put("juizReserva", rs.getInt("arb_codigo_juiz_aux"));
            jo.put("bandeirinha1", rs.getInt("arb_codigo_bandeirinha1"));
            jo.put("bandeirinha2", rs.getInt("arb_codigo_bandeirinha2"));
            jo.put("dataHora", df.format(rs.getTimestamp("jgs_data_hora")));
            jo.put("golsMandante", rs.getInt("jgs_qtde_gols_mandante"));
            jo.put("golsVisitante", rs.getInt("jgs_qtde_gols_visitante"));
        }
       
        rs.close();
        return jo;
    }

}
