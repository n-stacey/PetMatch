/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import bo.Pet;
import delete.Survey;
import bo.PetMatchResult;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author elainemorrison
 */
public class PetMatcher {
    public static List<PetMatchResult> matchPets(Survey survey, List<Pet> allPets) {
        List<PetMatchResult> results = new ArrayList<>();

        for (Pet pet : allPets) {
            int score = calculateMatchScore(survey, pet);
            results.add(new PetMatchResult(pet, score));
        }

        // sort highest score first
        results.sort(Comparator.comparingInt(PetMatchResult::getScore).reversed());
        return results;
    }

    private static int calculateMatchScore(Survey survey, Pet pet) {
        int score = 0;

        // Species preference
        if (survey.getPrefSpecies() != null) {
            if (pet.species != null &&
                pet.species.equalsIgnoreCase(survey.getPrefSpecies())) {
                score += 30;
            } else {
                score -= 20; // penalty if they wanted a specific species
            }
        }

        // Sex preference
        if (survey.getPrefSex() != null) {
            if (pet.sex != null &&
                pet.sex.equalsIgnoreCase(survey.getPrefSex())) {
                score += 20;
            } else {
                score -= 10;
            }
        }

        // Age range
        if (survey.getMinAge() != null && pet.age < survey.getMinAge()) {
            score -= 5;
        }
        if (survey.getMaxAge() != null && pet.age > survey.getMaxAge()) {
            score -= 5;
        }
        if (survey.getMinAge() != null || survey.getMaxAge() != null) {
            // small reward if in range
            boolean inMin = survey.getMinAge() == null || pet.age >= survey.getMinAge();
            boolean inMax = survey.getMaxAge() == null || pet.age <= survey.getMaxAge();
            if (inMin && inMax) {
                score += 15;
            }
        }

        // Weight limit
        if (survey.getMaxWeight() != null) {
            if (pet.weight <= survey.getMaxWeight()) {
                score += 10;
            } else {
                score -= 10;
            }
        }

        return score;
    }
}
