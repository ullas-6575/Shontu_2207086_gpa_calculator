package com.example.gpacalc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class database {


    private static final String URL = "jdbc:sqlite:gpacalc.db";


    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            //System.out.println("Connected to SQLite.");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return conn;
    }

    public static void initialize() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Courses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    course_name TEXT NOT NULL,
                    credit REAL NOT NULL,
                    grade TEXT NOT NULL
                );
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Initialization failed!");
            e.printStackTrace();
        }
    }
    public static void insertCourse(String name, double credit, String grade) {
        String sql = "INSERT INTO Courses(course_name, credit, grade) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, credit);
            pstmt.setString(3, grade);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Insert failed for course " + name + ". Error: " + e.getMessage());
        }
    }
    public void getAllCourses() {
        String sql = "SELECT id, course_name, credit, grade FROM Courses";

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("course_name");
                int credit = rs.getInt("credit");
                String grade = rs.getString("grade");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
