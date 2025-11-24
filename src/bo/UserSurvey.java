/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import delete.Survey;

import java.time.LocalDate;
/**
 *
 * @author elainemorrison
 */
public class UserSurvey {
    private int id;          
    private int userId;      
    private int surveyId;

    public UserSurvey() { }

    public UserSurvey(int id, User user1, Survey survey1 ) {
        this.id = id;
        this.userId = user1.getUserId();
        this.surveyId = survey1.getSurveyId();
    }

    public int getId() {return id; }
    public void setId(int id) { this.id = id;  }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getSurveyId() { return surveyId; }
    public void setSurveyId(int surveyId) { this.surveyId = surveyId; }

    
}
