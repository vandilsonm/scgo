/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.dao;

import br.una.sgco.framework.Data;
import br.una.sgco.to.TOUsuario;
import br.una.sgco.util.Criptografia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nathalia
 */
public class DAOUsuario {

    public static void inserir (TOUsuario usuario, Connection c) throws Exception {

        String sql = " insert into sgc_usuario_usu (usu_nome, usu_status, usu_celular, usu_email,  "
                + " usu_login, usu_senha) values (?, ?, ?, ?, ?, ?)";

        Data.executeUpdate(c, sql, new Object[] {usuario.getNome(), usuario.getStatus(),
                                usuario.getCelular(), usuario.getEmail(), usuario.getLogin(),
                                usuario.getSenha()});
    }

    public static void alterar (TOUsuario usuario, Connection c) throws Exception {
        String sql = " update sgc_usuario_usu set usu_nome = ?, usu_status = ?, "
                + " usu_email = ?, usu_login = ? where usu_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {usuario.getNome(), usuario.getStatus(),
                                usuario.getEmail(), usuario.getLogin(), usuario.getCodigo()});
        
    }

    public static void alterarSenha (TOUsuario usuario, Connection c) throws Exception {
        String sql = " update sgc_usuario_usu set usu_senha = ?"
                + " where usu_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {Criptografia.md5(usuario.getSenha()),
                                                    usuario.getCodigo()});
        
    }

    public static void inativar (TOUsuario usuario, Connection c) throws Exception {
        String sql = " update sgc_usuario_usu set usu_status = 'I' where usu_codigo = ?";

        Data.executeUpdate(c, sql, new Object[] {usuario.getCodigo()});

    }

    public static ArrayList<TOUsuario> obterTodos(Connection c) throws Exception {
        String sql = " select usu_codigo, usu_nome, usu_status, usu_email, usu_login"
                   + " from sgc_usuario_usu";

        ArrayList<TOUsuario> lista = new ArrayList<TOUsuario>();

        ResultSet rs = Data.executeQuery(c, sql);

        while (rs.next()) {
            TOUsuario item = new TOUsuario(rs.getInt("usu_codigo"), rs.getString("usu_nome"),
                rs.getString("usu_status"), rs.getString("usu_email"), rs.getString("usu_login"));

            lista.add(item);
        }

        return lista;
    }

    public static TOUsuario obterUm(TOUsuario usuario, Connection c) throws Exception {

        String sql = " select usu_codigo, usu_nome, usu_status, usu_email, usu_login"
                   + " from sgc_usuario_usu where usu_codigo = ?";

        TOUsuario registro = new TOUsuario();

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {usuario.getCodigo()});

        if (rs.next()) {
            registro.setCodigo(rs.getInt("usu_codigo"));
            registro.setNome(rs.getString("usu_nome"));
            registro.setStatus(rs.getString("usu_status"));
            registro.setEmail(rs.getString("usu_email"));
            registro.setLogin(rs.getString("usu_login"));
        }
        
        return registro;
    }

    public static boolean verificaSeLoginExiste (TOUsuario usuario, Connection c) throws Exception {

        String sql = " select count(usu_codigo) as qtde from sgc_usuario_usu "
                + " where usu_login = ? ";

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {usuario.getLogin()});

        if (rs.next()){
            if (rs.getInt("qtde") > 0)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public static TOUsuario retornaUsuarioLogin(TOUsuario usuario, Connection c) throws Exception {
        TOUsuario retorno = null;

        String sql = "select usu_codigo, usu_nome, usu_status, usu_login, usu_senha, usu_email"
                + " from sgc_usuario_usu where usu_login = ?";

        ResultSet rs = Data.executeQuery(c, sql, new Object[] {usuario.getLogin()});

        if (rs.next()) {
            retorno = new TOUsuario();
            retorno.setCodigo(rs.getInt("usu_codigo"));
            retorno.setNome(rs.getString("usu_nome"));
            retorno.setEmail(rs.getString("usu_email"));
            retorno.setLogin(rs.getString("usu_login"));
            retorno.setSenha(rs.getString("usu_senha"));
            retorno.setStatus(rs.getString("usu_status"));
        }

        return retorno;
    }
}
