package com.example.gpacalc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class addcontroller implements Initializable {


    @FXML
    private ChoiceBox<String> gradechoice;

    @FXML
    private TextField courseNameField;

    @FXML
    private TextField courseCodeField;

    @FXML
    private TextField courseCreditField;

    @FXML
    private TextField teacher1Field;

    @FXML
    private TextField teacher2Field;

    @FXML
    private Button addButton;

    @FXML
    private Button addMoreButton;

    @FXML
    private Button calculateButton;


    private final String[] grade = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (gradechoice != null) {
            gradechoice.getItems().addAll(grade);
        }
    }
//     * You would add logic here to get data from the fields and store it.
    @FXML
    protected void onAddButtonClick() {
        System.out.println("Add Course button clicked!");

        // Example: How to get data from the fields
        String courseName = courseNameField.getText();
        String courseCode = courseCodeField.getText();
        String credits = courseCreditField.getText();
        String selectedGrade = gradechoice.getValue();

//        System.out.println("Adding course: " + courseName + " (" + courseCode + ")");
//        System.out.println("Credits: " + credits + ", Grade: " + selectedGrade);

        // TODO: Add logic to store this course data (e.g., in a list)
    }
    @FXML
    protected void onAddMoreClick() {
        courseNameField.clear();
        courseCodeField.clear();
        courseCreditField.clear();
        teacher1Field.clear();
        teacher2Field.clear();
        gradechoice.setValue(null);
    }
    @FXML
    protected void onCalculateGpaClick() {
//        System.out.println("Calculate GPA button clicked!");

        // TODO: Add logic to calculate GPA from all the courses you've stored
    }
}