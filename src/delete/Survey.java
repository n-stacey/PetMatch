/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package delete;

/**
 *
 * @author elainemorrison
 */
public class Survey {
    
    private int surveyId;
    private String prefSpecies;   // e.g., "Dog", "Cat"
    private String prefSex;       // e.g., "Male", "Female", "Any"
    private Integer minAge;            // nullable
    private Integer maxAge;            // nullable
    private Integer maxWeight;         // nullable

     public Survey(){};

     public Survey(int surveyId, String prefSpecies, String prefSex, int minAge, int maxAge, int maxWeight) {
        this.prefSpecies = prefSpecies;
        this.prefSex = prefSex;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.maxWeight = maxWeight;
    }
    

    public int getSurveyId() { return surveyId; }
    public void setSurveyId(int surveyId) { this.surveyId = surveyId; }

    public String getPrefSpecies() { return prefSpecies; }
    public void setPrefSpecies(String prefSpecies) { this.prefSpecies = prefSpecies; }

    public String getPrefSex() { return prefSex; }
    public void setPrefSex(String preferredSex) { this.prefSex = prefSex; }

    public Integer getMinAge() { return minAge; }
    public void setMinAge(Integer minAge) { this.minAge = minAge; }

    public Integer getMaxAge() { return maxAge; }
    public void setMaxAge(Integer maxAge) { this.maxAge = maxAge; }

    public Integer getMaxWeight() { return maxWeight; }
    public void setMaxWeight(Integer maxWeight) { this.maxWeight = maxWeight; }
}
