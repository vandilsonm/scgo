/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOUsuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class DAOCampeonato {

    public static void inserir (TOCampeonato campeonato, Connection c) throws Exception {

        String sql = " insert into sgc_campeonato_cam (cam_nome, cam_descricao, usu_codigo, "
                    + " cam_tipo, cam_tabela) "
                    + " values (?, ?, ?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[] {campeonato.getNome(), campeonato.getDescricao(),
                            campeonato.getAdministrador().getCodigo(), campeonato.getTipo(),
                            campeonato.getTabela()});
        
    }

    public static void alterar (TOCampeonato campeonato, Connection c) throws Exception {
        String sql = " update sgc_campeonato_cam set cam_nome = ?, cam_descricao = ? "
                + " where cam_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {campeonato.getNome(), campeonato.getDescricao(),
                            campeonato.getCodigo()});
        
    }

    public static JSONObject obterUm(TOCampeonato campeonato, Connection c) throws Exception {

        String sql = " select cam_codigo, cam_nome, cam_descricao, "
                    + " usu_codigo, cam_tipo, cam_tabela "
                    + " from sgc_campeonato_cam where cam_codigo = ? ";

        JSONObject jo  = new JSONObject();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {campeonato.getCodigo()});

        if (rs.next()) {
            jo.put("codigo", rs.getInt("cam_codigo"));
            jo.put("nome", rs.getString("cam_nome"));
            jo.put("descricao", rs.getString("cam_descricao"));
            jo.put("tipo", rs.getString("cam_tipo"));
            jo.put("tabela", rs.getString("cam_tabela"));
            jo.put("usuario", rs.getInt("usu_codigo"));
        }

        rs.close();
        return jo;
    }
    
    public static JSONObject obterArtilheiro(TOCampeonato campeonato, Connection c) throws Exception {

        String sql = " select jog.jog_nome, tim.tim_nome, art.qtde"
                    + " from sgc_jogador_jog jog"
                    + " inner join ("
                        + " select plc.jog_codigo, sum(plc_qtde_gols) as qtde"
                        + " from sgc_jogos_jgs jgs"
                        + "   inner join sgc_placar_plc plc"
				+ " on jgs.jgs_codigo = plc.jgs_codigo"
                        + " where jgs.cam_codigo = ?"
                        + " group by plc.jog_codigo"
                        + " order by qtde desc limit 1) art"
                    + "   on art.jog_codigo = jog.jog_codigo"
                    + " inner join sgc_time_tim tim"
                    + "   on jog.tim_codigo = tim.tim_codigo";

        JSONObject jo  = new JSONObject();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {campeonato.getCodigo()});

        if (rs.next()) {
            jo.put("nomeJogador", rs.getString("jog_nome"));
            jo.put("nomeTime", rs.getString("tim_nome"));
            jo.put("qtde", rs.getInt("qtde"));
        }

        rs.close();
        return jo;
    }

    public static JSONArray lista(TOUsuario usuario, Connection c) throws Exception {
        JSONArray ja = new JSONArray();

        String sql = "select cam_codigo, cam_nome, cam_descricao, cam_tipo "
                + " from sgc_campeonato_cam where usu_codigo = ? order by cam_nome ";

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {usuario.getCodigo()});

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("codigo", rs.getInt("cam_codigo"));
            jo.put("nome", rs.getString("cam_nome"));
            jo.put("descricao", rs.getString("cam_descricao"));
            jo.put("tipo", rs.getString("cam_tipo"));
            ja.put(jo);
        }
        rs.close();
        return ja;
    }

    public static JSONArray listaClassificacao(TOCampeonato campeonato, Connection c) throws Exception {
        JSONArray ja = new JSONArray();

        String sql = "select tim.tim_codigo, tim.tim_nome "
                        + ", sum(qtde_jogo) as qtde_jogo"
                        + ", sum(qtde_gols) as qtde_gols"
                        + ", sum(qtde_gols_contra) as qtde_gols_contra"
                        + ", (sum(qtde_gols) - sum(qtde_gols_contra)) as saldo_gols"
                        + ", sum(qtde_empate) as qtde_empate"
                        + ", sum(qtde_derrota) as qtde_derrota"
                        + ", sum(qtde_vitoria) as qtde_vitoria"
                        + ", SUM(qtde_empate + (qtde_vitoria * 3)) as total_pontos"
                    + " from"
                    + " ("
                        + " select tim.tim_codigo"
                            + ", count(jgs_codigo) as qtde_jogo"
                            + ", sum(jgs.jgs_qtde_gols_mandante) as qtde_gols"
                            + ", sum(case when jgs.jgs_qtde_gols_mandante = jgs.jgs_qtde_gols_visitante then 1"
                            + "    else 0 end) as qtde_empate"
                            + ", sum(case when jgs.jgs_qtde_gols_mandante > jgs.jgs_qtde_gols_visitante then 1"
                            + "    else 0 end) as qtde_vitoria"
                            + ", sum(case when jgs.jgs_qtde_gols_mandante < jgs.jgs_qtde_gols_visitante then 1"
                            + "    else 0 end) as qtde_derrota"
                            + ", sum(case when jgs.jgs_qtde_gols_mandante < jgs.jgs_qtde_gols_visitante then jgs.jgs_qtde_gols_visitante"
                            + "    else 0 end) as qtde_gols_contra"
                        + " from sgc_time_tim tim"
                            + " inner join sgc_jogos_jgs jgs"
                                + " on tim.tim_codigo = jgs.tim_codigo_mandante"
                        + " where tim.cam_codigo = ?"
                            + " and jgs.jgs_data_hora < CURRENT_TIMESTAMP()"
                        + " group by tim.tim_codigo"
                    + " union"
                        + " select tim.tim_codigo"
                            + ", count(jgs_codigo) as qtde_jogo"
                            + ", sum(jgs.jgs_qtde_gols_visitante) as qtde_gols"
                            + ", sum(case when jgs.jgs_qtde_gols_mandante = jgs.jgs_qtde_gols_visitante then 1"
                            + "    else 0 end) as qtde_empate"
                            + ", sum(case when jgs.jgs_qtde_gols_mandante < jgs.jgs_qtde_gols_visitante then 1"
                            + "    else 0 end) as qtde_vitoria"
                            + ", sum(case when jgs.jgs_qtde_gols_mandante > jgs.jgs_qtde_gols_visitante then 1"
                            + "    else 0 end) as qtde_derrota"
                            + ", sum(case when jgs.jgs_qtde_gols_mandante > jgs.jgs_qtde_gols_visitante then jgs.jgs_qtde_gols_mandante"
                            + "    else 0 end) as qtde_gols_contra"
                        + " from sgc_time_tim tim"
                            + " inner join sgc_jogos_jgs jgs"
                                + " on tim.tim_codigo = jgs.tim_codigo_visitante"
                        + " where tim.cam_codigo = ?"
                            + " and jgs.jgs_data_hora < CURRENT_TIMESTAMP()"
                        + " group by tim.tim_codigo"
                    + " ) as time"
                        + " inner join sgc_time_tim tim"
                            + " on tim.tim_codigo = time.tim_codigo"
                    + " group by tim.tim_codigo, tim.tim_nome"
                    + " order by total_pontos desc, saldo_gols desc, qtde_gols desc, qtde_gols_contra ";

        
        System.out.println("classificacao: "+sql);
        ResultSet rs = Data.executeQuery(c, sql, new Object[] {campeonato.getCodigo(),
                                                               campeonato.getCodigo()});

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("codigoTime", rs.getInt("tim_codigo"));
            jo.put("nomeTime", rs.getString("tim_nome"));
            jo.put("qtde_jogo", rs.getInt("qtde_jogo"));
            jo.put("qtde_gols", rs.getInt("qtde_gols"));
            jo.put("qtde_gols_contra", rs.getInt("qtde_gols_contra"));
            jo.put("saldo_gols", rs.getInt("saldo_gols"));
            jo.put("qtde_empate", rs.getInt("qtde_empate"));
            jo.put("qtde_derrota", rs.getInt("qtde_derrota"));
            jo.put("qtde_vitoria", rs.getInt("qtde_vitoria"));
            jo.put("total_pontos", rs.getInt("total_pontos"));
            ja.put(jo);
        }
        rs.close();
        return ja;
    }

    public static JSONArray obterTodos(Connection c) throws Exception {
        JSONArray ja = new JSONArray();

        String sql = "select cam.cam_codigo, cam.cam_nome, cam.cam_descricao, "
                + " cam.cam_tipo, usu.usu_nome "
                + " from sgc_campeonato_cam cam "
                + "   inner join sgc_usuario_usu usu on cam.usu_codigo = usu.usu_codigo "
                + " order by cam_nome ";

        ResultSet rs = Data.executeQuery(c, sql);

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("codigo", rs.getInt("cam_codigo"));
            jo.put("nome", rs.getString("cam_nome"));
            jo.put("descricao", rs.getString("cam_descricao"));
            jo.put("tipo", rs.getString("cam_tipo"));
            jo.put("usuarioNome", rs.getString("usu_nome"));
            ja.put(jo);
        }
        rs.close();
        return ja;
    }

    public static JSONArray listaUltimosJogosCamp(TOCampeonato campeonato, Connection c) throws Exception {
        JSONArray ja = new JSONArray();

        String sql = "select tim_mandante.tim_nome as time_nome_mandante"
                        + ", tim_visitante.tim_nome as time_nome_visitante"
                        + ", jgs.jgs_qtde_gols_mandante as gols_mandante"
                        + ", jgs.jgs_qtde_gols_visitante as gols_visitante"
                        + ", jgs.jgs_data_hora "
                        + ", case when jgs.jgs_data_hora < CURRENT_TIMESTAMP() then 1 else 0 end as jogo_aconteceu "
                    + " from sgc_jogos_jgs jgs "
                        + " inner join sgc_time_tim tim_mandante on jgs.tim_codigo_mandante = tim_mandante.tim_codigo"
                        + " inner join sgc_time_tim tim_visitante on jgs.tim_codigo_visitante = tim_visitante.tim_codigo"
                    + " where ((datediff(day, jgs.jgs_data_hora, CURRENT_TIMESTAMP()) <= 7 and datediff(day, jgs.jgs_data_hora, CURRENT_TIMESTAMP()) >= 0)"
                    + " or (datediff(day, CURRENT_TIMESTAMP(), jgs.jgs_data_hora) <= 7 and datediff(day, CURRENT_TIMESTAMP(), jgs.jgs_data_hora) >= 0))"
                    + " and jgs.cam_codigo = ?"
                    + " order by jgs.jgs_data_hora desc ";
                    

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {campeonato.getCodigo()});

        SimpleDateFormat df = new SimpleDateFormat("dd/MM HH:mm"); 
        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("timeNomeMandante", rs.getString("time_nome_mandante"));
            jo.put("timeNomeVisitante", rs.getString("time_nome_visitante"));
            jo.put("dataHora", df.format(rs.getTimestamp("jgs_data_hora")));
            jo.put("golsMandante", rs.getInt("gols_mandante"));
            jo.put("golsVisitante", rs.getString("gols_visitante"));
            jo.put("jogoAconteceu", rs.getInt("jogo_aconteceu"));
            ja.put(jo);
        }
        rs.close();
        return ja;
    }

    public static JSONArray listaUltimosJogos(Connection c) throws Exception {
        JSONArray ja = new JSONArray();

        String sql = "select top 3 tim_mandante.tim_nome as time_nome_mandante"
                        + ", tim_visitante.tim_nome as time_nome_visitante"
                        + ", jgs.jgs_qtde_gols_mandante as gols_mandante"
                        + ", jgs.jgs_qtde_gols_visitante as gols_visitante"
                        + ", jgs.jgs_data_hora"
                        + ", cam.cam_codigo"
                        + ", cam.cam_nome"
                    + " from sgc_campeonato_cam cam "
                        + " inner join sgc_jogos_jgs jgs on cam.cam_codigo = jgs.cam_codigo"
                        + " inner join sgc_time_tim tim_mandante on jgs.tim_codigo_mandante = tim_mandante.tim_codigo"
                        + " inner join sgc_time_tim tim_visitante on jgs.tim_codigo_visitante = tim_visitante.tim_codigo"
                    + " where jgs.jgs_data_hora < CURRENT_TIMESTAMP()"
                    + " order by jgs.jgs_data_hora desc, cam.cam_codigo ";


        ResultSet rs = Data.executeQuery(c, sql);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM HH:mm");
        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("timeNomeMandante", rs.getString("time_nome_mandante"));
            jo.put("timeNomeVisitante", rs.getString("time_nome_visitante"));
            jo.put("dataHora", df.format(rs.getTimestamp("jgs_data_hora")));
            jo.put("golsMandante", rs.getInt("gols_mandante"));
            jo.put("golsVisitante", rs.getInt("gols_visitante"));
            jo.put("codigoCampeonato", rs.getInt("cam_codigo"));
            jo.put("nomeCampeonato", rs.getString("cam_nome"));
            ja.put(jo);
        }
        rs.close();
        return ja;
    }

}
