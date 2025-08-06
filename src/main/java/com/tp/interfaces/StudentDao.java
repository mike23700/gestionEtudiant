package com.tp.interfaces;

import com.tp.models.Student;

import java.sql.SQLException;
import java.util.List;


public interface StudentDao {

    void enregistrerEtudiant(Student student) throws SQLException;
    Student rechercherParMatricule(String matricule) throws SQLException;
    List<Student> rechercherParNom(String nom) throws SQLException;
    boolean supprimerParMatricule(String matricule) throws SQLException;
    boolean modifierEtudiant(Student student) throws SQLException;
    List<Student> listerEtudiants() throws SQLException;
    String genererMatricule() throws SQLException;
}