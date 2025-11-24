/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

/**
 *
 * @author elainemorrison
 */
public class PetMatchResult {
     private Pet pet;
     private int score;

     public PetMatchResult(Pet pet, int score) {
        this.pet = pet;
        this.score = score;
    }

     public Pet getPet() {
        return pet;
    }

     public int getScore() {
        return score;
    }
}
