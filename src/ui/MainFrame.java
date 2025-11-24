package ui;
import Utilities.SQLUtil;
import bo.Pet;
import bo.User;
import dao.PetDAO;
import dao.PetDAOImpl;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/*
 This is the main application window displayed after successful login.
 It contains tabs for different application functionalities, including the Pet CRUD tab.
 The Pet Management tab now interacts with PetDAOImpl, which uses JDBC.
 */
public class MainFrame extends JFrame {
    private final User loggedInUser;
    private JTabbedPane tabbedPane;
    private final PetDAO petDAO;
    private JTable petTable;
    private PetTableModel petTableModel;
    private JTextField searchField;

    public MainFrame(User user) throws Exception {
        super("Pet Matcher - Welcome " + user.getUserEmail() + (user.getIsAdmin() ? " (ADMIN)" : ""));
        this.loggedInUser = user;
        this.petDAO = new PetDAOImpl(SQLUtil.getConnection());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
    }

    private void initComponents() throws Exception {
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Match Pets", createMatchingPanel());
        tabbedPane.addTab("Take Survey", createSurveyPanel());

        if (loggedInUser.getIsAdmin()) {
            tabbedPane.addTab("Pets (CRUD)", createPetManagementPanel()); // Requirement 4
            //tabbedPane.addTab("Survey Builder", new JPanel()); // Requirement 5 Placeholder
            tabbedPane.addTab("Applications", new JPanel()); // Requirement 8 Placeholder
        } else {
            // User specific tab
            tabbedPane.addTab("My Applications", new JPanel()); // Requirement 8 User View Placeholder
        }
    }

    private void layoutComponents() {
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    // Tab Panel Implementations

    private JPanel createMatchingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea resultsArea = new JTextArea("Match results will appear here after taking the survey.\n(Logic from utilities.PetMatcher and utilities.FeedbackPresenter goes here)", 15, 60);
        resultsArea.setEditable(false);
        panel.add(new JScrollPane(resultsArea), BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return panel;
    }

    private JPanel createSurveyPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("<html><h2>Pet Matcher Survey</h2><p>This tab will load the active survey questions from the database and allow the user to submit their responses.</p><p>Responses will be saved via UserSurveyDAO and ResponseDAO (Required for full matching).</p></html>", SwingConstants.CENTER), BorderLayout.NORTH);
        return panel;
    }

    private JPanel createPetManagementPanel() throws Exception {
        // Initializes the pet table
        List<Pet> initialPets = petDAO.getAllPets();
        petTableModel = new PetTableModel(initialPets);
        petTable = new JTable(petTableModel);
        petTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        petTable.setAutoCreateRowSorter(true); // Enable sorting

        // Controls Panel (Buttons and Search)
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Add Pet");
        JButton editButton = new JButton("Edit Selected");
        JButton deleteButton = new JButton("Delete Selected");
        JButton refreshButton = new JButton("Refresh");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(refreshButton);
        controlPanel.add(new JLabel(" | Search:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);

        // Main Panel Layout
        JPanel petManagementPanel = new JPanel(new BorderLayout(10, 10));
        petManagementPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        petManagementPanel.add(controlPanel, BorderLayout.NORTH);
        petManagementPanel.add(new JScrollPane(petTable), BorderLayout.CENTER);

        // Action Listeners
        refreshButton.addActionListener(e -> {
            try {
                loadPets();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        searchButton.addActionListener(e -> {
            try {
                searchPets();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        searchField.addActionListener(e -> {
            try {
                searchPets();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }); // Search on Enter key

        addButton.addActionListener(e -> showPetFormDialog(null));
        editButton.addActionListener(e -> {
            int selectedRow = petTable.getSelectedRow();
            if (selectedRow != -1) {
                // Convert view index to model index if sorting is enabled
                int modelRow = petTable.convertRowIndexToModel(selectedRow);
                Pet pet = petTableModel.getPetAt(modelRow);
                showPetFormDialog(pet);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a pet to edit.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            }
        });
        deleteButton.addActionListener(e -> {
            int selectedRow = petTable.getSelectedRow();
            if (selectedRow != -1 && JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this pet?", "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int modelRow = petTable.convertRowIndexToModel(selectedRow);
                Pet petToDelete = petTableModel.getPetAt(modelRow);
                try {
                    petDAO.deletePet(petToDelete.getPetId());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    loadPets(); // Refresh table after delete
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return petManagementPanel;
    }

    private void loadPets() throws Exception {
        List<Pet> allPets = petDAO.getAllPets();
        petTableModel.setPets(allPets);
        searchField.setText("");
    }

    private void searchPets() throws Exception {
        String term = searchField.getText();
        List<Pet> foundPets = petDAO.searchPetsByNameOrSpecies(term);
        petTableModel.setPets(foundPets);
    }

    /*
     Shows a modal dialog for adding a new pet or editing an existing one.
     @param pet If non-null, the dialog is for editing this pet. If null, it's for adding.
     */
    private void showPetFormDialog(Pet pet) {
        JDialog dialog = new JDialog(this, (pet == null ? "Add New Pet" : "Edit Pet: " + pet.getName()), true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(450, 450);
        dialog.setLocationRelativeTo(this);

        // Form Fields
        JTextField nameField = new JTextField(pet != null ? pet.getName() : "");
        JTextField speciesField = new JTextField(pet != null ? pet.getSpecies() : "");
        JTextField breedField = new JTextField(pet != null ? pet.getBreed() : "");
        JTextField sexField = new JTextField(pet != null ? pet.getSex() : "");
        JTextField colorField = new JTextField(pet != null ? pet.getColor() : "");
        JTextField ageField = new JTextField(pet != null ? String.valueOf(pet.getAge()) : "");
        JTextField weightField = new JTextField(pet != null ? String.valueOf(pet.getWeight()) : "");
        //JTextArea descriptionArea = new JTextArea(pet != null ? pet.getDescription() : "", 3, 30);

        JPanel formPanel = new JPanel(new SpringLayout());
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Name:")); formPanel.add(nameField);
        formPanel.add(new JLabel("Species:")); formPanel.add(speciesField);
        formPanel.add(new JLabel("Breed:")); formPanel.add(breedField);
        formPanel.add(new JLabel("Color")); formPanel.add(colorField);
        formPanel.add(new JLabel("Sex:")); formPanel.add(sexField);
        formPanel.add(new JLabel("Age (yrs):")); formPanel.add(ageField);
        formPanel.add(new JLabel("Weight (lbs):")); formPanel.add(weightField);
        //formPanel.add(new JLabel("Description:")); formPanel.add(new JScrollPane(descriptionArea));

        SpringUtilities.makeCompactGrid(formPanel, 7, 2, 6, 6, 6, 6); // Use the utility class from LoginFrame

        // Save Button
        JButton saveButton = new JButton(pet == null ? "Add Pet" : "Save Changes");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Input validation (basic)
                    String name = nameField.getText().trim();
                    String species = speciesField.getText().trim();
                    String breed = breedField.getText().trim();
                    String sex = sexField.getText().trim();
                    String color = colorField.getText().trim();
                    int age = Integer.parseInt(ageField.getText().trim());
                    int weight = Integer.parseInt(weightField.getText().trim());

                    if (name.isEmpty() || species.isEmpty() || sex.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog, "Name, Species, and Sex are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Creates or updates Pet object
                    Pet newOrUpdatedPet;
                    if (pet == null) {
                        // ID 0 indicates a new pet for the DAO to assign a real ID
                        // The database handles ID generation for new entries
                        newOrUpdatedPet = new Pet(0, name, species, breed, color, sex, age, weight);
                        petDAO.addPet(newOrUpdatedPet);
                    } else {
                        // Uses existing ID for update
                        newOrUpdatedPet = new Pet(pet.getPetId(), name, species, breed, color, sex, age, weight);
                        petDAO.updatePet(newOrUpdatedPet);
                    }

                    loadPets(); // Refreshes the table
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Age and Weight must be valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}

// Author: Andrew Witten