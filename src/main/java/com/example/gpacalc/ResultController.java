package com.example.gpacalc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.List;

public class ResultController {

    @FXML
    private GridPane resultsGrid;

    @FXML
    private Label gpaResultLabel;


    public void setResults(List<addcontroller.Course> courses, double calculatedGpa) {

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
}