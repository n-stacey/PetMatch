/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Utilities.SQLUtil;
import bo.Pet;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author elainemorrison
 */
public class PetDAOImpl implements PetDAO{

    private Connection conn;

    public PetDAOImpl(Connection conn) {
        this.conn = conn;
    }

    private Pet mapRowToPet(ResultSet rs) throws SQLException {
        int id = rs.getInt("PetID");
        String name = rs.getString("Name");
        String species = rs.getString("Species");
        String breed = rs.getString("Breed");
        String color = rs.getString("Color");
        String sex = rs.getString("sex");
        int age = rs.getInt("Age");
        int weight = rs.getInt("weight");

        return new Pet(id, name, species, breed, color, sex, age, weight);
    }

    @Override
    public List<Pet> getAllPets() {
        System.out.println("DAO: Retrieving all pet records from database.");
        List<Pet> pets = new ArrayList<>();

        String sql = "SELECT PetID, Name, Species, Breed, Color, sex, Age, weight, Received_on, image FROM Pet";

        try (Connection conn = SQLUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                pets.add(mapRowToPet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching pets from database: " + e.getMessage());
            e.printStackTrace();
        }

        return pets;
    }

    @Override
    public Pet getPetById(int petId) {
        String sql = "SELECT PetID, Name, Species, Breed, Color, sex, Age, weight, Received_on, image " +
                "FROM Pet WHERE PetID = ?";

        try (Connection conn = SQLUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, petId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching pet by ID from database: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addPet(Pet pet) throws SQLException {
        // Assuming PetID is NOT auto-increment and you pass a valid ID in the Pet object.
        // If you change the table to auto-increment PetID, adjust this method accordingly.
        String sql = "INSERT INTO Pet (PetID, Name, Species, Breed, Color, sex, Age, weight, Received_on, image) " +
         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURDATE(), ?)";
        try (Connection conn = SQLUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pet.getPetId());
            ps.setString(2, pet.getName());
            ps.setString(3, pet.getSpecies());
            ps.setString(4, pet.getBreed());
            ps.setString(5, pet.getColor());
            ps.setString(6, pet.getSex());
            ps.setInt(7, pet.getAge());
            ps.setDouble(8, pet.getWeight());
            ps.setString(9, ""); // image placeholder; adjust if you have an image in Pet

            int rows = ps.executeUpdate();
            System.out.println("DAO: Inserted pet into database, rows affected = " + rows);
        } catch (SQLException e) {
            System.err.println("Error inserting pet into database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updatePet(Pet pet) {
        String sql = "UPDATE Pet SET Name = ?, Species = ?, Breed = ?, Color = ?, sex = ?, Age = ?, weight = ? " +
                "WHERE PetID = ?";

        try (Connection conn = SQLUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pet.getName());
            ps.setString(2, pet.getSpecies());
            ps.setString(3, pet.getBreed());
            ps.setString(4, pet.getColor());  // again, mapping description to Color
            ps.setString(5, pet.getSex());
            ps.setInt(6, pet.getAge());
            ps.setDouble(7, pet.getWeight());
            ps.setInt(8, pet.getPetId());

            int rows = ps.executeUpdate();
            System.out.println("DAO: Updated pet in database, rows affected = " + rows);
        } catch (SQLException e) {
            System.err.println("Error updating pet in database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deletePet(int petId) {
        String sql = "DELETE FROM Pet WHERE PetID = ?";

        try (Connection conn = SQLUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, petId);
            int rows = ps.executeUpdate();
            System.out.println("DAO: Deleted pet from database, rows affected = " + rows);
        } catch (SQLException e) {
            System.err.println("Error deleting pet from database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public List<Pet> searchPetsByNameOrSpecies(String term) {
        if (term == null || term.trim().isEmpty()) {
            return getAllPets();
        }

        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT PetID, Name, Species, Breed, Color, sex, Age, weight, Received_on, image " +
                "FROM Pet " +
                "WHERE LOWER(Name) LIKE ? OR LOWER(Species) LIKE ?";

        String like = "%" + term.trim().toLowerCase() + "%";

        try (Connection conn = SQLUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, like);
            ps.setString(2, like);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pets.add(mapRowToPet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching pets in database: " + e.getMessage());
            e.printStackTrace();
        }

        return pets;
    }

    public int getLastPetId() throws SQLException {
        String sql = "SELECT MAX(PetID) FROM Pet;";
        int petId = 0;
        Connection conn = SQLUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(0, petId); // Comparing against stored 'pass'
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SQLUtil.close(rs, ps, conn);
        }
        return petId;
    }
    }


