/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bo.User;
/**
 *
 * @author elainemorrison
 */
public interface UserDAO {
    User login(String email, String password);
}
