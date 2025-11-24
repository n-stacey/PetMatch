/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import bo.AdoptApp;
import dao.AdoptAppDAO;     
import java.util.List;
/**
 *
 * @author elainemorrison
 */
public class AdoptionService {
    private final AdoptAppDAO adoptAppDao;

    public AdoptionService(AdoptAppDAO dao) {
        this.adoptAppDao = dao;
    }

    public void submitApplication(AdoptApp app) throws Exception {
        adoptAppDao.create(app);
    }

    public List<AdoptApp> getUserApplications(int userId) throws Exception {
        return adoptAppDao.getApplicationsForUser(userId);
    }

    public List<AdoptApp> getAllApplications() throws Exception {
        return adoptAppDao.getAllApplications();
    }

    public void updateStatus(int appId, String status) throws Exception {
        adoptAppDao.updateStatus(appId, status);
    }
}
