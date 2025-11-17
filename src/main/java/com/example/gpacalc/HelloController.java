//package com.example.gpacalc;
//
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.Label;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class HelloController {
//    @FXML
//    private Label welcomeText;
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
//}
package com.example.gpacalc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    // This links to the fx:id="rootPane" in your hello-view.fxml
    @FXML
    private AnchorPane rootPane;

    /**
     * This method is called when the "Start GPA Calculator" button is clicked.
     * It handles loading the new FXML and switching the scene.
     */
    @FXML
    protected void onStartGpaCalculatorClick() {
        try {
            // 1. Load the FXML file for the "Add Course" scene
            //    *** This line is now updated to "add-page.fxml" ***
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-page.fxml"));
            Parent addCourseRoot = loader.load();

            // 2. Get the current stage (window) from the root pane
            Stage stage = (Stage) rootPane.getScene().getWindow();

            // 3. Set the new scene on the current stage
            Scene scene = new Scene(addCourseRoot);
            stage.setScene(scene);
            stage.setTitle("Add Course"); // Optional: set a new title
            stage.show();

        } catch (IOException e) {
            // Updated error message to be correct
            System.err.println("Failed to load add-page.fxml");
            e.printStackTrace();
        }
    }
}