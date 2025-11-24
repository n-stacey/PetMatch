/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Utilities.SQLUtil;
/**
 *
 * @author elainemorrison
 */
public class UserDAOImpl implements dao.UserDAO{
    @Override
    public User login(String email, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        /*
         NOTE: In a real app, we would retrieve the password_hash and use a library (like BCrypt)
         to compare the hashed password with the provided plaintext password.
         For this demo, we check for a direct match against the unhashed 'password_hash' column.
        */
        String sql = "SELECT UserID, Email, pswrd, isAdmin FROM user WHERE email = ? AND pswrd = ?";
        try {
            conn = SQLUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password); // Comparing against stored 'pass'
            rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User(
                        rs.getInt("UserID"),
                        rs.getString("Email"),
                        rs.getString("pswrd"),
                        rs.getBoolean("isAdmin")
                );
                System.out.println("DAO: Successfully authenticated user: " + email);
                return user;
            }
        } catch (SQLException e) {
            System.err.println("DAO Error during login for " + email + ": " + e.getMessage());
        } finally {
            SQLUtil.close(rs, ps, conn);
        }
        return null;
    }
}
