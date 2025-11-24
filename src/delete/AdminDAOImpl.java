/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package delete;

import java.sql.Connection;
/**
 *
 * @author elainemorrison
 */
public class AdminDAOImpl implements AdminDAO{
    private final Connection conn;

    public AdminDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Admin findByUsernameAndPassword(String userNm, String password) throws Exception {
        // TODO: SELECT * FROM admin WHERE user_nm=? AND pswrd=? 
        return null;
    }
}
