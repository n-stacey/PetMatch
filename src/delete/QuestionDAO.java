/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package delete;

import java.util.List;
/**
 *
 * @author elainemorrison
 */
public interface QuestionDAO {
        List<Question> getQuestionsForSurvey(int surveyId) throws Exception;
}
