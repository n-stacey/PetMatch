/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import java.time.LocalDate;
/**
 *
 * @author elainemorrison
 */
public class Pet {
    private int petId;
    public String name;
    public String species;
    public String breed;
    public String color;
    public String sex; 
    public int age; //in years
    public int weight; //in lbs
    public LocalDate receivedOn;
    public String image;

    public Pet(){

    }
    public Pet(int petId, String name, String species, String breed, String color, String sex, int age, int weight){
        this.petId = petId;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.color = color;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
    }

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public LocalDate getReceivedOn() { return receivedOn; }
    public void setReceivedOn(LocalDate receivedOn) { this.receivedOn = receivedOn; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
}
