package com.example.gpacalc;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
        database db = new database();


        db.initialize();
        db.insertCourse("Data Structures", 4, "A");
       db.insertCourse("Operating Systems", 3, "B+");
        db.insertCourse("Calculus I", 4, "C");
        db.getAllCourses();
    }
}
