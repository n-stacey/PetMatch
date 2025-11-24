package ui;
import bo.Pet;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

// Custom Table Model for displaying Pet objects in a JTable.
public class PetTableModel extends AbstractTableModel {
    private List<Pet> pets;
    private final String[] columnNames = {"ID", "Name", "Species", "Breed", "Sex", "Age", "Weight (kg)", "Description"};

    public PetTableModel(List<Pet> pets) {
        this.pets = new ArrayList<>(pets);
    }

    /*
     Updates the model's data and notifies the JTable to redraw.
     @param newPets is the new list of pets.
     */
    public void setPets(List<Pet> newPets) {
        this.pets = new ArrayList<>(newPets);
        fireTableDataChanged();
    }


    // Gets the Pet object at a specific row index.
    public Pet getPetAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < pets.size()) {
            return pets.get(rowIndex);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return pets.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pet pet = pets.get(rowIndex);
        switch (columnIndex) {
            case 0: return pet.getPetId();
            case 1: return pet.getName();
            case 2: return pet.getSpecies();
            case 3: return pet.getBreed();
            case 4: return pet.getSex();
            case 5: return pet.getAge();
            case 6: return pet.getWeight();
            //case 7: return pet.getDescription();
            default: return null;
        }
    }

    // This'll ensure that the ID column is treated as an integer for sorting purposes
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0 || columnIndex == 5) {
            return Integer.class;
        } else if (columnIndex == 6) {
            return Double.class;
        }
        return String.class;
    }
}

// Author: Andrew Witten