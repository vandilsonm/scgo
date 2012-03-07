/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author dirceu
 */
public class Data_old {

    public static Connection openConnection() throws Exception{
        return openConnectionMssql("127.0.0.1", "una", "una", "una");
        // return openConnectionMysql("127.0.0.1", "una", "una", "una");
        // return openConnectionPostgre("127.0.0.1", "una", "una", "una");
    }

    public static Connection openConnectionMssql(String server,
        String database, String user, String password) throws Exception {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://" + server +
                "\\SQLEXPRESS:1433;databaseName=" + database + ";user=" +
                user + ";password=" + password + ";");
        return conn;
    }

    public static Connection openConnectionMysql(String server,
        String database, String user, String password) throws Exception {
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://" + server +
                "/" + database + "?user=" + user + "&password="+ password);
        return conn;
    }

    public static Connection openConnectionPostgre(String server,
        String database, String user, String password) throws Exception {
        Connection conn = null;
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://" + server +
                ":5432/" + database, user, password);
        return conn;
    }

    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection : " + e.getMessage());
            }
        }
    }

    public static ResultSet executeQuery(Connection conn, String query) throws SQLException {
        Statement sta = conn.createStatement();
        ResultSet rs = null;
        try {
            rs = sta.executeQuery(query);
        } catch (Exception err) {
            //Log.logar(err.getMessage(), Log.TYPE_INFORMATION);
        }
        return rs;
    }

    public static int executeUpdate(Connection conn, String query, Object[] parametros) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        for (int i = 1; i <= parametros.length; i++) {
            pstmt.setObject(i, parametros[i - 1]);
        }
        return pstmt.executeUpdate();
    }

    public static int executeUpdate(Connection conn, String query) throws SQLException {
        Statement stm = conn.createStatement();
        return stm.executeUpdate(query);
    }

    public static ResultSet executeQuery(Connection conn, String query, Object[] parametros) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        for (int i = 1; i <= parametros.length; i++) {
            pstmt.setObject(i, parametros[i - 1]);
        }
        return pstmt.executeQuery();
    }

    public static void closeResultSet(ResultSet rs) throws Exception {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new Exception("Error closing ResultSet : " + e.getMessage());
            }
        }
    }

}
