/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bo.Response;
import java.util.List;
/**
 *
 * @author elainemorrison
 */
public interface ResponseDAO {
    void saveResponses(int userSurveyId, List<Response> responses) throws Exception;
    List<Response> getResponsesForUserSurvey(int userSurveyId) throws Exception;
}
