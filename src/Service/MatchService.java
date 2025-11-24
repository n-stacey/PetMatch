/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import bo.Pet;
import bo.PetMatchResult;
import delete.Survey;
import dao.PetDAO;
import Utilities.PetMatcher;
import java.util.List;
/**
 *
 * @author elainemorrison
 */
public class MatchService {
    private final PetDAO petDao;

    public MatchService(PetDAO petDao) {
        this.petDao = petDao;
    }

    public List<PetMatchResult> matchPets(Survey survey) throws Exception {
        List<Pet> pets = petDao.getAllPets();
        return PetMatcher.matchPets(survey, pets);
    }
}
