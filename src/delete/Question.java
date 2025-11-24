/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package delete;

/**
 *
 * @author elainemorrison
 */
public class Question {
    public int qId;
    public String prompt;
    public String type;
    public boolean is_required;
    public int order_index; 
    public int surveyId;

    public Question(int qId, String prompt, String type, boolean is_required, int order_index, Survey survey1) {
        this.qId = qId;
        this.prompt = prompt;
        this.type = type;
        this.is_required = is_required;
        this.order_index = order_index;
        this.surveyId = survey1.getSurveyId();
    }

    
    public int getQId() { return qId; }
    public void setQId(int qId) { this.qId = qId; }
    
    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isIs_required() { return is_required; }
    public void setIs_required(boolean is_required) { this.is_required = is_required; }

    public int getOrder_index() { return order_index; }
    public void setOrder_index(int order_index) { this.order_index = order_index; }

    public int getSurveyId() { return surveyId; }
    public void setSurveyId(Survey survey1, int surveyId) { this.surveyId = surveyId; }
                                            // survey1.setSurveyId = surveyId}
    
}
