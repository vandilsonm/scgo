/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.peladinha.to.TOLocal;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class DAOLocal {

    public static void inserir (TOLocal toLocal, Connection connection) throws Exception {

        String sql = " insert into sgc_local (nome, logradouro, numero, complemento, bairro, cidade, "
                + " usu_codigo, estado) values (?, ?, ?, ?, ?, ?, ?, ?)";

        Data.executeUpdate(connection, sql, new Object[] {
                            toLocal.getNome(),
                            toLocal.getLogradouro(),
                            toLocal.getNumero(),
                            toLocal.getComplemento(),
                            toLocal.getBairro(),
                            toLocal.getCidade(),
                            toLocal.getIdUsuario(),
                            toLocal.getEstado()});
    }

    public static void alterar (TOLocal toLocal, Connection connection) throws Exception {
        String sql = " update sgc_local set nome = ?, logradouro = ?, numero = ?, complemento = ?, bairro = ?, "
                + "cidade = ?, estado = ? WHERE id = ? AND USU_CODIGO = ? ";

        Data.executeUpdate(connection, sql, new Object[] {
                            toLocal.getNome(),
                            toLocal.getLogradouro(),
                            toLocal.getNumero(),
                            toLocal.getComplemento(),
                            toLocal.getBairro(),
                            toLocal.getCidade(),
                            toLocal.getEstado(),
                            toLocal.getId(),
                            toLocal.getIdUsuario()});
    }

    public static void excluir (TOLocal toLocal, Connection connection) throws Exception {
        String sql = " delete from sgc_local where id = ? and USU_CODIGO = ?";

        Data.executeUpdate(connection, sql, new Object[] {toLocal.getId(),toLocal.getIdUsuario()});

    }

    public static JSONObject get(TOLocal toLocal, Connection connection) throws Exception {

       String sql = " select id, nome, logradouro, numero, complemento, bairro, cidade, estado "
               + " from sgc_local where id = ? and usu_codigo = ?";

        JSONObject jo = new JSONObject();

        ResultSet rs = Data.executeQuery(connection, sql, new Object[] {toLocal.getId(), toLocal.getIdUsuario()});

        if (rs.next()) {
            jo.put("id", rs.getInt("id"));
            jo.put("nome", rs.getString("nome"));
            jo.put("logradouro", rs.getString("logradouro"));
            jo.put("numero", rs.getString("numero"));
            jo.put("complemento", rs.getString("complemento"));
            jo.put("bairro", rs.getString("bairro"));
            jo.put("cidade", rs.getString("cidade"));
            jo.put("estado", rs.getString("estado"));
        }

        rs.close();

        return jo;
    }

    public static JSONArray lista(TOLocal toLocal, Connection connection) throws Exception {

       String sql = "SELECT id, nome, logradouro, numero, complemento, bairro, cidade, estado"
               + " FROM sgc_local WHERE USU_CODIGO = ? ORDER BY nome";

       JSONArray jsonArray = new JSONArray();

       ResultSet rs = Data.executeQuery(connection, sql, new Object[]{toLocal.getIdUsuario()});

       while (rs.next()) {
            JSONObject jsonObejct = new JSONObject();
            jsonObejct.put("id", rs.getInt("id"));
            jsonObejct.put("nome", rs.getString("nome"));
            jsonObejct.put("logradouro", rs.getString("logradouro"));
            jsonObejct.put("numero", rs.getString("numero"));
            jsonObejct.put("complemento", rs.getString("complemento"));
            jsonObejct.put("bairro", rs.getString("bairro"));
            jsonObejct.put("cidade", rs.getString("cidade"));
            jsonObejct.put("estado", rs.getString("estado"));
            
            jsonArray.put(jsonObejct);
        }
        rs.close();
        return jsonArray;
    }
}
