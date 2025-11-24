/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package delete;

/**
 *
 * @author elainemorrison
 */
public interface AdminDAO {
    Admin findByUsernameAndPassword(String userNm, String password) throws Exception;
}
