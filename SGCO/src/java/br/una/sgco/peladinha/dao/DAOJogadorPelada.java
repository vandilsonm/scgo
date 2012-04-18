/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.to.TOJogadorPelada;
import br.una.sgco.peladinha.to.TOPelada;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class DAOJogadorPelada {
    
    public static void inserir (List<TOJogadorPelada> tOJogadorPelada, Connection connection) throws Exception {

        String sql = " insert into sgc_jogadorpelada (idPelada,idJogador) VALUE"
                    + " values (?, ?)";
        
        Object[] dados = new Object[tOJogadorPelada.size()];
        
        for(int i = 0; i<tOJogadorPelada.size();i++){
            TOJogadorPelada jogador = tOJogadorPelada.get(i);
            
            int indice = i*2;
            dados[indice] = jogador.getIdPelada();
            dados[indice+1] = jogador.getIdPelada();
            
            sql +="(?,?)";
            
            if((i+1)!=tOJogadorPelada.size()){
                sql+=",";
            }
            
        }
        System.out.println(tOJogadorPelada.getClass().getSimpleName()+"   ->   "+sql);
        Data.executeUpdate(connection, sql, dados);
    }


    public static void excluir (String id, Connection connection) throws Exception {
        String sql = "DELETE FROM sgc_jogadorpelada where idPelada = ?";
        Data.executeUpdate(connection, sql, new Object[] {id});
    }
    public static JSONObject get(TOPelada toPelada, Connection connection) throws Exception {

       String sql = " select id, nome, descricao, horario, idLocal, criador from sgc_pelada where id = ?"
               + " and criador = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(connection, sql, new Object[] {toPelada.getId(), toPelada.getCriador()});

        if (rs.next()) {
            jo.put("id", rs.getInt("id"));
            jo.put("nome", rs.getString("nome"));
            jo.put("descricao", rs.getString("descricao"));
            jo.put("horario", rs.getTime("horario"));
            jo.put("idLocal", rs.getInt("idLocal"));
            jo.put("criador", rs.getInt("criador"));
        }

        rs.close();

        return jo;
    }

    public static JSONArray lista(TOPelada toPelada, Connection connection) throws Exception {

       String sql = " select pel.id, pel.nome, pel.descricao, pel.horario, pel.idLocal, "
               + "pel.criador, loc.nome as descricaoLocal "
               + "from sgc_pelada pel left outer join sgc_local loc on loc.id = pel.idLocal "
               + "order by pel.nome";

       JSONArray jsonArray = new JSONArray();

       ResultSet rs = Data.executeQuery(connection, sql);

       while (rs.next()) {
            JSONObject jsonObejct = new JSONObject();
            jsonObejct.put("id", rs.getInt("id"));
            jsonObejct.put("nome", rs.getString("nome"));
            jsonObejct.put("descricao", rs.getString("descricao"));
            jsonObejct.put("horario", rs.getTime("horario"));
            jsonObejct.put("idLocal", rs.getInt("idLocal"));   
            jsonObejct.put("criador", rs.getInt("criador"));
            jsonObejct.put("descricaoLocal", rs.getString("descricaoLocal"));
            jsonArray.put(jsonObejct);
        }

        rs.close();
        return jsonArray;
    }
    
    public static JSONArray getJogadores(TOPelada toPelada, Connection connection) throws Exception {

       String sql = " select id, nome, descricao, horario, idLocal, criador "
               + "from sgc_pelada where criador = ? order by nome";
       JSONArray jsonArray = new JSONArray();

       ResultSet rs = Data.executeQuery(connection, sql, new Object[]{toPelada.getCriador()});

       while (rs.next()) {
            JSONObject jsonObejct = new JSONObject();
            jsonObejct.put("id", rs.getInt("id"));
            jsonObejct.put("nome", rs.getString("nome"));
            jsonObejct.put("descricao", rs.getString("descricao"));
            jsonObejct.put("horario", rs.getTime("horario"));
            jsonObejct.put("idLocal", rs.getInt("idLocal"));   
            jsonObejct.put("criador", rs.getInt("criador"));
            jsonArray.put(jsonObejct);
        }

        rs.close();
        return jsonArray;
    }
}
