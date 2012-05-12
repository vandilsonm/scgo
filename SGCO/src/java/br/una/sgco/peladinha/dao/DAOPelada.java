/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.to.TOJogador;
import br.una.sgco.peladinha.to.TOPelada;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class DAOPelada {
    
    public static void inserir (TOPelada toPelada, Connection connection) throws Exception {

        String sql = " insert into sgc_pelada (nome, descricao, horario, idLocal, criador) "
                    + " values (?, ?, ?, ?, ?)";

        Data.executeUpdate(connection, sql, new Object[] {
                            toPelada.getNome(), toPelada.getDescricao(),
                            toPelada.getHorario(), toPelada.getIdLocal().getId(),
                            toPelada.getCriador()});
        
        String sqlId = "SELECT ID FROM `sgc_pelada` ORDER BY ID DESC LIMIT 0 , 1";
        ResultSet set = Data.executeQuery(connection, sqlId);
        set.next();
        Integer idPelada = set.getInt("ID");
        toPelada.setId(idPelada);       
        
        excluirRelacional(toPelada,connection);
        inserirRelacional(toPelada, connection);
    }
    
    private static void excluirRelacional(TOPelada toPelada, Connection connection)throws Exception{
          String sql = " DELETE FROM sgc_jogadorpelada where idPelada=?";
        
        Data.executeUpdate(connection, sql, new Object[]{toPelada.getId()});
    }

    private static void inserirRelacional(TOPelada toPelada, Connection connection)throws Exception{
          String sql = " insert into sgc_jogadorpelada (idPelada,idJogador) VALUES";
        
        Object[] dados = new Object[toPelada.listaJogadores.size()*2];
        
        for(int i = 0; i<toPelada.listaJogadores.size();i++){
            TOJogador jogador = toPelada.listaJogadores.get(i);
            
            int indice = i*2;
            dados[indice] = toPelada.getId();
            dados[indice+1] = jogador.getId();
            
            sql +="(?,?)";
            
            if((i+1)!= toPelada.listaJogadores.size()){
                sql+=",";
            }
            
        }
        Data.executeUpdate(connection, sql, dados);
    }

    public static void alterar (TOPelada toPelada, Connection connection) throws Exception {
        String sql = " update sgc_pelada set nome = ?, descricao = ?, "
                + " horario = ?, idLocal = ? where id = ? and criador = ?";

        Data.executeUpdate(connection, sql, new Object[] {
                            toPelada.getNome(), toPelada.getDescricao(),
                            toPelada.getHorario(), toPelada.getIdLocal().getId(), 
                            toPelada.getId(), toPelada.getCriador()});
        
        System.out.println("-------");
        
        excluirRelacional(toPelada,connection);
        inserirRelacional(toPelada, connection);
        
    }

    public static void excluir (TOPelada toPelada, Connection connection) throws Exception {
        String sql = " delete from sgc_pelada where id = ?";

        Data.executeUpdate(connection, sql, new Object[] {toPelada.getId()});

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
        
        HashMap<Integer,String> listaJogadoresMarcados = new HashMap<Integer,String>();
        
        sql = " select * FROM sgc_jogadorpelada where idPelada=?";
        rs = Data.executeQuery(connection, sql, new Object[] {toPelada.getId()});
        while (rs.next()) {
            listaJogadoresMarcados.put(rs.getInt("idJogador"), "");
        }

        
        JSONArray listaJogadores = new JSONArray();
        sql = " select * FROM sgc_jogador where criador=?";
        rs = Data.executeQuery(connection, sql, new Object[] {toPelada.getCriador()});
        while (rs.next()) {
            JSONObject obj = new JSONObject();
            Integer id = rs.getInt("id");
            obj.put("id", id);
            obj.put("nome", rs.getString("nome"));
            if(listaJogadoresMarcados.containsKey(id)){
                obj.put("selecionado", true);
            }else{
                obj.put("selecionado", false);
            }
            listaJogadores.put(obj);
        }
        jo.put("jogadores", listaJogadores);

        
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
