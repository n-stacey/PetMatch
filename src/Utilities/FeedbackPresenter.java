/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import bo.Pet;
import bo.PetMatchResult;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 *
 * @author elainemorrison
 */
public class FeedbackPresenter {
    public static void showMatchesToUser(List<PetMatchResult> results) {

        if (results == null || results.isEmpty()) {
            System.out.println("\nSorry, no pets matched your preferences.\n");
            return;
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

        System.out.println("\n===============================");
        System.out.println("       Pet Match Results       ");
        System.out.println("===============================\n");

        // show top 3 or fewer
        int limit = Math.min(3, results.size());

        for (int i = 0; i < limit; i++) {
            PetMatchResult result = results.get(i);
            Pet pet = result.getPet();

            System.out.println("Match #" + (i + 1));
            System.out.println("Match score: " + result.getScore());
            System.out.println("Pet ID:      " + pet.getPetId());
            System.out.println("Name:        " + pet.name);
            System.out.println("Species:     " + pet.species);
            System.out.println("Breed:       " + pet.breed);
            System.out.println("Color:       " + pet.color);
            System.out.println("Sex:         " + pet.sex);
            System.out.println("Age:         " + pet.age + " years");
            System.out.println("Weight:      " + pet.weight + " lbs");

            if (pet.receivedOn != null) {
                System.out.println("Received On: " + pet.receivedOn.format(fmt));
            } else {
                System.out.println("Received On: N/A");
            }

            System.out.println("----------------------------------\n");
        }
    }
}
