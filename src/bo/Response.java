/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import delete.Options;
import delete.Question;
import delete.Survey;

import java.time.LocalDate;

/**
 *
 * @author elainemorrison
 */
public class Response {
    private int respId;
    public String species;
    public String breed;
    public String color;
    public String sex;
    public int age; //in years
    public int weight; //in lbs

    public Response(int respId, String species, String breed, String color, String sex, int age, int weight) {
        this.respId = respId;
        this.species = species;
        this.breed = breed;
        this.color = color;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
    }

    public int getRespId() { return respId; }
    public void setRespId(int respId) { this.respId = respId; }

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
}
