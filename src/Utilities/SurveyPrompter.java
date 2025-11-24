/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import delete.Survey;
import java.util.Scanner;
/**
 *
 * @author elainemorrison
 */
    public class SurveyPrompter {

    public static Survey askUserForSurvey() {
        Scanner scanner = new Scanner(System.in);
        Survey survey = new Survey();

        System.out.println("Welcome to the Pet Matcher!");
        System.out.println("Please answer a few questions.\n");

        System.out.print("Preferred species (Dog/Cat/Any): ");
        String species = scanner.nextLine().trim();
        if (species.equalsIgnoreCase("any") || species.isEmpty()) {
            species = null; // means no preference
        }
        survey.setPrefSpecies(species);

        System.out.print("Preferred sex (Male/Female/Any): ");
        String sex = scanner.nextLine().trim();
        if (sex.equalsIgnoreCase("any") || sex.isEmpty()) {
            sex = null;
        }
        survey.setPrefSex(sex);

        System.out.print("Minimum age (or leave blank for no minimum): ");
        String minAgeStr = scanner.nextLine().trim();
        if (!minAgeStr.isEmpty()) {
            survey.setMinAge(Integer.parseInt(minAgeStr));
        }

        System.out.print("Maximum age (or leave blank for no maximum): ");
        String maxAgeStr = scanner.nextLine().trim();
        if (!maxAgeStr.isEmpty()) {
            survey.setMaxAge(Integer.parseInt(maxAgeStr));
        }

        System.out.print("Maximum weight in lbs (or leave blank for no maximum): ");
        String maxWeightStr = scanner.nextLine().trim();
        if (!maxWeightStr.isEmpty()) {
            survey.setMaxWeight(Integer.parseInt(maxWeightStr));
        }

        System.out.println("\nThank you! Finding matches...\n");

        return survey;
    }
}
