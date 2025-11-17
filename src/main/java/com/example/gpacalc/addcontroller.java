package com.example.gpacalc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addcontroller implements Initializable {
    @FXML
    private BorderPane addCourseRootPane;
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

    private final List<Course> courseList = new ArrayList<>();

    public static class Course {
        String name;
        double credits;
        String grade;
        double gradePoint;

        public Course(String name, double credits, String grade, double gradePoint) {
            this.name = name;
            this.credits = credits;
            this.grade = grade;
            this.gradePoint = gradePoint;
        }

        public String getName() { return name; }
        public double getCredits() { return credits; }
        public String getGrade() { return grade; }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (gradechoice != null) {
            gradechoice.getItems().addAll(grade);
        }
    }

    @FXML
    protected void onAddButtonClick() {

        String courseName = courseNameField.getText();
        String creditsText = courseCreditField.getText();
        String selectedGrade = gradechoice.getValue();


        try {
            double credits = Double.parseDouble(creditsText);

            double gradePoint = getGradePoint(selectedGrade);

            Course newCourse = new Course(courseName, credits, selectedGrade, gradePoint);
            courseList.add(newCourse);
            onAddMoreClick();
        } catch (NumberFormatException e) {
            System.err.println("Invalid credit number. Please enter a valid number.");
        }
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
        if (courseList.isEmpty()) {
            System.err.println("No courses added to calculate GPA.");
//            showErrorAlert("No Courses", "Please add at least one course before calculating GPA.");
            return;
        }

        double totalGradePoints = 0.0;
        double totalCredits = 0.0;
        for (Course course : courseList) {
            totalGradePoints += (course.gradePoint * course.credits);
            totalCredits += course.credits;
        }

        double finalGpa = (totalCredits == 0) ? 0.0 : (totalGradePoints / totalCredits);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result-page.fxml"));
            Parent resultRoot = loader.load();

            ResultController resultController = loader.getController();
            resultController.setResults(courseList, finalGpa);


            Stage stage = (Stage) addCourseRootPane.getScene().getWindow();
            Scene scene = new Scene(resultRoot);
            stage.setScene(scene);
            stage.setTitle("GPA Results");
            stage.show();

        } catch (IOException e) {
            System.err.println("Failed to load result-page.fxml");
            e.printStackTrace();
        }
    }
    private double getGradePoint(String grade) {
        switch (grade) {
            case "A+": return 4.0;
            case "A":  return 3.75;
            case "A-": return 3.5;
            case "B+": return 3.25;
            case "B":  return 3.0;
            case "B-": return 2.75;
            case "C+": return 2.5;
            case "C":  return 2.25;
            case "D":  return 2.0;
//            case "F":  return 0.0;
            default:   return 0.0;
        }
    }
}