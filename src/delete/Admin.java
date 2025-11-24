/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package delete;

/**
 *
 * @author elainemorrison
 */
public class Admin {
    public int adminId;
    public String userName;
    public String role;
    private String pswrd;
    public int userId;

    public Admin(int adminId, String userName, String role, String pswrd, int userId) {
        this.adminId = adminId;
        this.userName = userName;
        this.role = role;
        this.pswrd = pswrd;
        this.userId = userId;
    }
    
    
    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPswrd() { return pswrd; }
    public void setPswrd(String pswrd) { this.pswrd = pswrd; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }


}
