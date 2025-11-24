/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import bo.User;
import dao.UserDAO;

/**
 *
 * @author elainemorrison
 */
public class AuthService {
    private final UserDAO userDao;

    public AuthService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public User loginUser(String email, String password) throws Exception {
        return userDao.login(email, password);
    }
}
