package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        getAllStudents();
        deleteStudent(2);
        getAllStudents();
    }

    // Method to fetch all students from the database
    // It establishes a connection to the database, executes a SQL query to fetch all students,
    // and then prints the details of each student.
    public static void getAllStudents() {
        System.out.println("printing all students");
        try (Connection connection = DbUtil.connect();) {
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.print(resultSet.getString("student_id") + "\t");
                System.out.print(resultSet.getString("first_name") + "\t");
                System.out.print(resultSet.getString("last_name") + "\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.println(resultSet.getDate("enrollment_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Method to add a new student to the database
    // It establishes a connection to the database, executes a SQL query to add a student,
    public static void addStudent(String first_name, String last_name, String email, Date enrollment_date) {
        System.out.println("adding student");
        try (Connection connection = DbUtil.connect();) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, enrollment_date);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Method to update a student's email in the database
    // It establishes a connection to the database, executes a SQL query to update a student's
    // email based on the student_id parameter
    public static void updateStudentEmail(int student_id, String email) {
        System.out.println("updating student email");
        try (Connection connection = DbUtil.connect();) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE students SET email = ? WHERE student_id = ?;");
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, student_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Method to delete a student from the database
    // It establishes a connection to the database, executes a SQL query to delete a student
    // email based on the student_id parameter
    public static void deleteStudent(int student_id) {
        System.out.println("deleting student");
        try (Connection connection = DbUtil.connect();) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students WHERE student_id = ?;");
            preparedStatement.setInt(1, student_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

