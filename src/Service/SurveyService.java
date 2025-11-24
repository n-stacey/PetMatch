/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import delete.Options;
import delete.Question;
import delete.Survey;
import bo.Response;
import bo.UserSurvey;
import delete.OptionsDAO;
import delete.QuestionDAO;
import delete.SurveyDAO;
import dao.UserSurveyDAO;
import dao.ResponseDAO;
import java.util.List;
/**
 *
 * @author elainemorrison
 */
public class SurveyService {
    private final SurveyDAO surveyDao;
    private final QuestionDAO questionDao;
    private final OptionsDAO optionsDao;
    private final UserSurveyDAO userSurveyDao;
    private final ResponseDAO responseDao;

    public SurveyService(SurveyDAO surveyDao, QuestionDAO questionDao,
                         OptionsDAO optionsDao, UserSurveyDAO userSurveyDao,
                         ResponseDAO responseDao) {
        this.surveyDao = surveyDao;
        this.questionDao = questionDao;
        this.optionsDao = optionsDao;
        this.userSurveyDao = userSurveyDao;
        this.responseDao = responseDao;
    }

    public Survey getActiveSurvey() throws Exception {
        return surveyDao.getActiveSurvey();
    }

    public List<Question> getQuestions(int surveyId) throws Exception {
        return questionDao.getQuestionsForSurvey(surveyId);
    }

    public List<Options> getOptions(int qId) throws Exception {
        return optionsDao.getOptionsForQuestion(qId);
    }

    public UserSurvey saveUserSurveyWithResponses(int userId, Survey survey,
        List<Response> responses) throws Exception {
        UserSurvey us = userSurveyDao.create(userId, survey.getSurveyId());
        responseDao.saveResponses(us.getId(), responses);
        return us;
    }
}
