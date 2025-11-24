/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import java.time.LocalDate;

/**
 *
 * @author elainemorrison
 */
public class User {
    private int userId;
    public String userName;
    public String userEmail;
    public int userPhone;
    public String userAddress;
    public String pswrd;
    public boolean isAdmin;

    public User(int userId, String userName, String userEmail, int userPhone, String userAddress, String pswrd, boolean isAdmin) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.pswrd = pswrd;
        this.isAdmin = isAdmin;
    }

    public User(int userId, String userEmail, String pswrd, boolean isAdmin) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.pswrd = pswrd;
        this.isAdmin = isAdmin;
    }


    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public int getUserPhone() { return userPhone; }
    public void setUserPhone(int userPhone) { this.userPhone = userPhone; }

    public String getUserAddress() { return userAddress; }
    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    public String getPswrd() { return userName; }
    public void setPswrd(String pswrd) { this.pswrd = pswrd; }

    public boolean getIsAdmin() { return isAdmin; }
    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
}

