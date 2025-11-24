/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.sql.*;
import java.sql.DriverManager;

/**
 *
 * @author elainemorrison
 */
public class SQLUtil {
    private Connection con;
    private Statement stm;
    private static final String url = "jdbc:mysql://localhost:3306/PetAdoption";
    private static final String username = "root";
    private static final String password = "root";

    public SQLUtil() {
        String url = "jdbc:mysql://localhost:3306/PetAdoption";
        String username = "root";
        String password = "root";
        
        try {
            con = DriverManager.getConnection(url, username, password);
            stm = con.createStatement();//Prepared Statement
        } catch (SQLException ex) {
            System.getLogger(SQLUtil.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }
    public Statement getStatement() {
        return stm;
    }
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.err.println("Error closing ResultSet: " + e.getMessage());
        }
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            System.err.println("Error closing PreparedStatement: " + e.getMessage());
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing Connection: " + e.getMessage());
        }
    }
    
    public void close() throws SQLException {
        if (stm != null && !stm.isClosed()) stm.close();
        if (con != null && !con.isClosed()) con.close();
    }
    public int executeUpdate(String cmd) {
        try {
            return this.stm.executeUpdate(cmd);
        } catch (SQLException ex) {
            System.getLogger(SQLUtil.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return -1;
    }
    public ResultSet executeQuery(String cmd) {
        try {
            return this.stm.executeQuery(cmd);
        } catch (SQLException ex) {
            System.getLogger(SQLUtil.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
}
