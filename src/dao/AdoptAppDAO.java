/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import bo.AdoptApp;
import java.util.List;
/**
 *
 * @author elainemorrison
 */
public interface AdoptAppDAO {
    void create(AdoptApp app) throws Exception;
    List<AdoptApp> getApplicationsForUser(int userId) throws Exception;
    List<AdoptApp> getAllApplications() throws Exception;
    void updateStatus(int appId, String newStatus) throws Exception;
}
