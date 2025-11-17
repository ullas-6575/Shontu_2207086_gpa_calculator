package com.example.gpacalc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    protected void onStartGpaCalculatorClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-page.fxml"));
            Parent addCourseRoot = loader.load();

            Stage stage = (Stage) rootPane.getScene().getWindow();

            Scene scene = new Scene(addCourseRoot);
            stage.setScene(scene);
            stage.setTitle("Add Course");
            stage.show();

        } catch (IOException e) {
            System.err.println("Failed to load add-page.fxml");
            e.printStackTrace();
        }
    }
}