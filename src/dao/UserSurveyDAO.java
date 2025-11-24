/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bo.UserSurvey;
/**
 *
 * @author elainemorrison
 */
public interface UserSurveyDAO {
    UserSurvey create(int userId, int surveyId) throws Exception;
    UserSurvey getLatestForUser(int userId) throws Exception;
}
