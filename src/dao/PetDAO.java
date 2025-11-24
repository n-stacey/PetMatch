/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bo.Pet;
import java.util.List;
/**
 *
 * @author elainemorrison
 */
public interface PetDAO {
        List<Pet> getAllPets() throws Exception;
        List<Pet> searchPetsByNameOrSpecies(String term) throws Exception;
        Pet getPetById(int petId) throws Exception;
        void addPet(Pet pet) throws Exception;
        void updatePet(Pet pet) throws Exception;
        void deletePet(int petId) throws Exception;
        int getLastPetId() throws Exception;

}
