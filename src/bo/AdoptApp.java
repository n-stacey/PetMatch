/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

/**
 *
 * @author elainemorrison
 */

public class AdoptApp {
    public int appId;
    public String status;
    public int userId;
    public int petId;

    public AdoptApp(int appId, String status, User user1, Pet pet1) {
        this.appId = appId;
        this.status = status;
        this.userId = user1.getUserId();
        this.petId = pet1.getPetId();
    }

    public int getAppId() { return appId; }
    public void setAppId(int appId) { this.appId = appId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }
    
}
