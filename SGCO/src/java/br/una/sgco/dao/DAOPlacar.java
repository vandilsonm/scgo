/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOCampeonato;
import br.una.sgco.to.TOPlacar;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class DAOPlacar {

    public static void inserir (TOPlacar placar, Connection c) throws Exception {

        String sql = " insert into sgc_placar_plc (jgs_codigo, tim_codigo, jog_codigo, plc_qtde_gols)"
                    + " values (?, ?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[] {placar.getJogo().getCodigo(),
                                                    placar.getTime().getCodigo(),
                                                    placar.getJogador().getCodigo(),
                                                    placar.getQtdeGols()});
        alterarJogo(placar, c);
    }
    
    public static void alterarJogo (TOPlacar toPlacar, Connection c) throws Exception {        
        int codJogo = toPlacar.getJogo().getCodigo();
        int codTime = toPlacar.getTime().getCodigo();
        int qtdeGols = toPlacar.getQtdeGols();
        
        int time = 0;
        String sqlTime = "";
        String sqlGols = "";
        String sql1 = "select jgs_codigo, tim_codigo_mandante, tim_codigo_visitante"
                + " from sgc_jogos_jgs where jgs_codigo = ?";
        
        ResultSet rs = Data.executeQuery(c, sql1, new Object[] {codJogo});
        rs.next();
        if (rs.getInt("tim_codigo_mandante") == codTime){
            time = rs.getInt("tim_codigo_mandante");
            sqlTime = "tim_codigo_mandante = ?";
            sqlGols = "jgs_qtde_gols_mandante = ? ";
        }else if (rs.getInt("tim_codigo_visitante") == codTime){
            time = rs.getInt("tim_codigo_visitante");
            sqlTime = "tim_codigo_visitante = ?";
            sqlGols = "jgs_qtde_gols_visitante = ? ";
        }
        
        String sql = " update sgc_jogos_jgs set "
                + sqlGols
                + " where jgs_codigo = ? and "
                + sqlTime;

        Data.executeUpdate(c, sql, new Object[] {
                qtdeGols, codJogo, time
        });
    }
    
    public static JSONObject get (TOPlacar placar, Connection c) throws Exception {

        String sql = "select tim_codigo, plc_qtde_gols, jgs_codigo, jog_codigo from sgc_placar_plc"
                + " where jgs_codigo = ? and jog_codigo = ?";

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {placar.getJogo().getCodigo(),
                                                    placar.getJogador().getCodigo()});
        
        JSONObject jo = new JSONObject();
        
        if (rs.next()) {
            jo.put("idJogador", rs.getInt("jgs_codigo"));
            jo.put("idJogo", rs.getInt("jog_codigo"));          
        }
       
        rs.close();
        return jo;        
    }    

    public static void excluir (TOPlacar placar, Connection c) throws Exception {
        String sql = " delete from sgc_placar_plc where jog_codigo = ? "
                    + " and tim_codigo = ? and jgs_codigo = ?";

        try {
            Data.executeUpdate(c, sql, new Object[] {placar.getJogador().getCodigo(),
                                                    placar.getTime().getCodigo(),
                                                    placar.getJogo().getCodigo()});

        } finally {
            if (c != null) c.close();
        }
    }

    public static JSONArray obterTodos(TOCampeonato campeonato, Connection c) throws Exception {
        String sql = " select plc.jog_codigo, plc.tim_codigo, plc.jgs_codigo, plc.plc_qtde_gols, "
                    + " jog.jog_nome, tim1.tim_nome as time_mandante, tim2.tim_nome as time_visitante "
                    + " from sgc_placar_plc plc "
                    + "   inner join sgc_jogos_jgs jgs on plc.jgs_codigo = jgs.jgs_codigo "
                    + "   inner join sgc_jogador_jog jog "
                    + "     on jog.jog_codigo = plc.jog_codigo "
                    + "   inner join sgc_time_tim tim1 "
                    + "     on tim1.tim_codigo = jgs.tim_codigo_mandante "
                    + "   inner join sgc_time_tim tim2 "
                    + "     on tim2.tim_codigo = jgs.tim_codigo_visitante "
                    + " where jgs.cam_codigo = ? "
                    + " order by jgs.jgs_data_hora desc ";

        JSONArray ja = new JSONArray();

        ResultSet rs = Data.executeQuery(c, sql, new Object[]{campeonato.getCodigo()});

        while (rs.next()) {
            JSONObject jo = new JSONObject();
            jo.put("jogoCodigo", rs.getInt("jgs_codigo"));
            jo.put("jogadorCodigo", rs.getInt("jog_codigo"));
            jo.put("timeCodigo", rs.getInt("tim_codigo"));
            jo.put("qtdeGols", rs.getInt("plc_qtde_gols"));
            jo.put("jogadorNome", rs.getString("jog_nome"));
            jo.put("timeMandante", rs.getString("time_mandante"));
            jo.put("timeVisitante", rs.getString("time_visitante"));
            ja.put(jo);
        }

        rs.close();
        return ja;
    }

//    public static ArrayList<TOPlacar> obterGolsJogo(TOJogo jogo, Connection c) throws Exception {
//        String sql = " select jog_codigo, jgs_codigo, plc_qtde_gols "
//                    + " from sgc_placar_plc where jgs_codigo = ?";
//
//        ArrayList<TOPlacar> lista = new ArrayList<TOPlacar>();
//
//        try {
//            ResultSet rs = Data.executeQuery(c, sql, new Object[]{jogo.getCodigo()});
//
//            while (rs.next()) {
//                TOPlacar item = new TOPlacar();
//                TOJogador jogador = new TOJogador();
//                jogador.setCodigo(rs.getInt("jog_codigo"));
//
//                //item.setJogador(DAOJogador.obterUm(jogador, c));
//                item.setJogo(DAOJogo.obterUm(jogo, c));
//                item.setQtdeGols(rs.getInt("plc_qtde_gols"));
//
//                lista.add(item);
//            }
//        } finally {
//            if (c != null) c.close();
//        }
//
//        return lista;
//    }
//
//    public static ArrayList<TOPlacar> obterGolsJogador(TOJogador jogador, Connection c) throws Exception {
//        String sql = " select jog_codigo, jgs_codigo, plc_qtde_gols "
//                    + " from sgc_placar_plc where jog_codigo = ?";
//
//        ArrayList<TOPlacar> lista = new ArrayList<TOPlacar>();
//
//        try {
//            ResultSet rs = Data.executeQuery(c, sql, new Object[]{jogador.getCodigo()});
//
//            while (rs.next()) {
//                TOPlacar item = new TOPlacar();
//                TOJogo jogo = new TOJogo();
//                jogo.setCodigo(rs.getInt("jgs_codigo"));
//
//                //item.setJogador(DAOJogador.obterUm(jogador, c));
//                item.setJogo(DAOJogo.obterUm(jogo, c));
//                item.setQtdeGols(rs.getInt("plc_qtde_gols"));
//
//                lista.add(item);
//            }
//        } finally {
//            if (c != null) c.close();
//        }
//
//        return lista;
//    }


}
