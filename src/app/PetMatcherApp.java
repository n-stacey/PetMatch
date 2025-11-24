/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import delete.Survey;
import bo.Pet;
import bo.PetMatchResult;
import dao.PetDAO;
import dao.PetDAOImpl;
import Utilities.SurveyPrompter;
import Utilities.PetMatcher;
import Utilities.FeedbackPresenter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
/**
 *
 * @author elainemorrison
 */
public class PetMatcherApp {
    public static void main(String[] args) {

        try {
            // 1. Ask user for survey answers
            Survey survey = SurveyPrompter.askUserForSurvey();

            // 2. Connect to database
            // TODO: change URL, username, password to match your setup
            String url = "jdbc:mysql://localhost:3306/Pet Adoption Database";
            String user = "root";
            String password = "root";

            Connection conn = DriverManager.getConnection(url, user, password);

            // 3. Load pets from DB
            PetDAO petDao = new PetDAOImpl(conn);
            List<Pet> allPets = petDao.getAllPets();

            // 4. Run matcher
            List<PetMatchResult> results = PetMatcher.matchPets(survey, allPets);

            // 5. Show feedback to user
            FeedbackPresenter.showMatchesToUser(results);

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
