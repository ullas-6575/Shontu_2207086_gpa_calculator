package com.example.gpacalc;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.List;

public class ResultController {

    @FXML
    private GridPane resultsGrid;

    @FXML
    private Label gpaResultLabel;

    // Field to store course data locally
    private List<addcontroller.Course> currentCourses;

    public void setResults(List<addcontroller.Course> courses, double calculatedGpa) {
        // Save the list so we can access it when clicking the Save Button
        this.currentCourses = courses;

        int row = 1;
        for (addcontroller.Course course : courses) {
            Label nameLabel = new Label(course.getName());
            Label creditLabel = new Label(String.valueOf(course.getCredits()));
            Label gradeLabel = new Label(course.getGrade());

            nameLabel.setFont(new Font(14));
            creditLabel.setFont(new Font(14));
            gradeLabel.setFont(new Font(14));

            resultsGrid.add(nameLabel, 0, row);
            resultsGrid.add(creditLabel, 1, row);
            resultsGrid.add(gradeLabel, 2, row);

            row++;
        }

        gpaResultLabel.setText(String.format("Overall GPA: %.2f", calculatedGpa));
    }

    @FXML
    protected void onSaveDataClick() {
        if (currentCourses == null || currentCourses.isEmpty()) {
            return;
        }

        try {
            for (addcontroller.Course course : currentCourses) {
                database.insertCourse(course.getName(), course.getCredits(), course.getGrade());
            }

            // Show Success Message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Courses saved successfully to the database!");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to save data.");
            alert.showAndWait();
        }
    }
}