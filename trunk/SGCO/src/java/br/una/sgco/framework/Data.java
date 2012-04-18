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
 * @author Leandro Marcos
 */
public class Data {

    public static Connection openConnection() throws Exception  {
        Connection conn = null;

        String server = "localhost";
        String database = "sgc_online";
        String user = "root";
        String password = "";

        Class.forName("org.gjt.mm.mysql.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://"+ server +":3306/"+ database, user, password);
        return conn;
    }

    public static void closeConnection(Connection con) throws Exception {
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
            System.out.println("sql: "+query);
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
        System.out.println("sql: "+pstmt.toString());
        return pstmt.executeUpdate();
    }

    public static int executeUpdate(Connection conn, String query) throws SQLException {
        Statement stm = conn.createStatement();
        System.out.println("sql: "+stm.toString());
        return stm.executeUpdate(query);
    }

    public static ResultSet executeQuery(Connection conn, String query, Object[] parametros) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        for (int i = 1; i <= parametros.length; i++) {
            pstmt.setObject(i, parametros[i - 1]);
        }
        System.out.println("executeQuery: "+pstmt);
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
