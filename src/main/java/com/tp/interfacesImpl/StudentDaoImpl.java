package com.tp.interfacesImpl;

import com.tp.dao.DaoFactory;
import com.tp.interfaces.StudentDao;
import com.tp.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class StudentDaoImpl implements StudentDao {

    private DaoFactory daoFactory;

    public StudentDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    @Override
    public void enregistrerEtudiant(Student student) throws SQLException {
        String query = "INSERT INTO Student (Matricule, Name, Surname, Sex, Statut, DateOfBirth, DateRegister) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, student.getMatricule());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getSurname());
            stmt.setString(4, String.valueOf(student.getSex()));
            stmt.setString(5, student.getStatut());
            stmt.setDate(6, Date.valueOf(student.getDateOfBirth()));
            stmt.setTimestamp(7, Timestamp.valueOf(student.getDateRegister()));

            stmt.executeUpdate();
        }
    }


    @Override
    public Student rechercherParMatricule(String matricule) throws SQLException {
        String query = "SELECT * FROM Student WHERE Matricule = ?";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, matricule);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setMatricule(rs.getString("Matricule"));
                    student.setName(rs.getString("Name"));
                    student.setSurname(rs.getString("Surname"));
                    student.setSex(rs.getString("Sex").charAt(0));
                    student.setStatut(rs.getString("Statut"));
                    student.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
                    student.setDateRegister(rs.getTimestamp("DateRegister").toLocalDateTime());
                    return student;
                }
            }
        }
        return null;
    }

    @Override
    public List<Student> rechercherParNom(String nom) throws SQLException {
        List<Student> liste = new ArrayList<>();
        String query = "SELECT * FROM Student WHERE Name LIKE ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nom);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setMatricule(rs.getString("Matricule"));
                    student.setName(rs.getString("Name"));
                    student.setSurname(rs.getString("Surname"));
                    student.setSex(rs.getString("Sex").charAt(0));
                    student.setStatut(rs.getString("Statut"));
                    student.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
                    student.setDateRegister(rs.getTimestamp("DateRegister").toLocalDateTime());
                    liste.add(student);
                }
            }
        }
        return liste;
    }

    @Override
    public boolean supprimerParMatricule(String matricule) throws SQLException {
        String query = "DELETE FROM Student WHERE Matricule = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, matricule);
            int affected = stmt.executeUpdate();
            return affected > 0;
        }
    }

    @Override
    public boolean modifierEtudiant(Student student) throws SQLException {
        String query = "UPDATE Student SET Name = ?, Surname = ?, Sex = ?, Statut = ?, DateOfBirth = ? WHERE Matricule = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getSurname());
            stmt.setString(3, String.valueOf(student.getSex()));
            stmt.setString(4, student.getStatut());
            stmt.setDate(5, Date.valueOf(student.getDateOfBirth()));
            stmt.setString(6, student.getMatricule());

            int updated = stmt.executeUpdate();
            return updated > 0;
        }
    }

    @Override
    public List<Student> listerEtudiants() throws SQLException {
        List<Student> liste = new ArrayList<>();
        String query = "SELECT * FROM Student ORDER BY DateRegister";

        try (Connection conn = daoFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student student = new Student();
                student.setMatricule(rs.getString("Matricule"));
                student.setName(rs.getString("Name"));
                student.setSurname(rs.getString("Surname"));
                student.setSex(rs.getString("Sex").charAt(0));
                student.setStatut(rs.getString("Statut"));
                student.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
                student.setDateRegister(rs.getTimestamp("DateRegister").toLocalDateTime());
                liste.add(student);
            }
        }
        return liste;
    }

    @Override
    public String genererMatricule() {
        String matricule = null;
        String etu = "Etu";
        String unique = UUID.randomUUID().toString().substring(0, 4);
        matricule = etu + unique.toUpperCase();
        return matricule;
    }
}