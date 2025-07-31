package com.tp.models;

import com.tp.models.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccessDB {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Enregistrer un étudiant
    public void enregistrerEtudiant(Student student) throws SQLException {
        String query = "INSERT INTO Student (Matricule, Name, Surname, Sex, DateOfBirth) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getMatricule());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getSurname());
            stmt.setString(4, String.valueOf(student.getSex()));
            stmt.setDate(5, Date.valueOf(student.getDateOfBirth()));

            stmt.executeUpdate();
        }
    }

    // Rechercher un étudiant par matricule
    public Student rechercherParMatricule(String matricule) {
        String query = "SELECT * FROM Student WHERE Matricule = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, matricule);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setMatricule(rs.getString("Matricule"));
                student.setName(rs.getString("Name"));
                student.setSurname(rs.getString("Surname"));
                student.setSex(rs.getString("Sex").charAt(0));
                student.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
                student.setDateRegister(rs.getTimestamp("DateRegister").toLocalDateTime());
                return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Rechercher les étudiants par nom
    public List<Student> rechercherParNom(String nom) {
        List<Student> liste = new ArrayList<>();
        String query = "SELECT * FROM Student WHERE Name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setMatricule(rs.getString("Matricule"));
                student.setName(rs.getString("Name"));
                student.setSurname(rs.getString("Surname"));
                student.setSex(rs.getString("Sex").charAt(0));
                student.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
                student.setDateRegister(rs.getTimestamp("DateRegister").toLocalDateTime());
                liste.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    // Supprimer un étudiant
    public boolean supprimerParMatricule(String matricule) {
        String query = "DELETE FROM Student WHERE Matricule = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, matricule);
            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Modifier un étudiant
    public boolean modifierEtudiant(Student student) {
        String query = "UPDATE Student SET Name = ?, Surname = ?, Sex = ?, DateOfBirth = ? WHERE Matricule = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getSurname());
            stmt.setString(3, String.valueOf(student.getSex()));
            stmt.setDate(4, Date.valueOf(student.getDateOfBirth()));
            stmt.setString(5, student.getMatricule());

            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lister tous les étudiants
    public List<Student> listerEtudiants() {
        List<Student> liste = new ArrayList<>();
        String query = "SELECT * FROM Student";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student student = new Student();
                student.setMatricule(rs.getString("Matricule"));
                student.setName(rs.getString("Name"));
                student.setSurname(rs.getString("Surname"));
                student.setSex(rs.getString("Sex").charAt(0));
                student.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
                student.setDateRegister(rs.getTimestamp("DateRegister").toLocalDateTime());
                liste.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }
}
