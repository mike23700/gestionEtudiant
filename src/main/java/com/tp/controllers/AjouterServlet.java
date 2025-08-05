package com.tp.controllers;

import com.tp.models.Student;
import com.tp.DAO.AccessDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/formulaire")
public class AjouterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = new Student();
        AccessDB db = new AccessDB();

        student.setName(request.getParameter("name"));
        student.setSurname(request.getParameter("surname"));

        String sexString = request.getParameter("sex");
        char sexChar = sexString.charAt(0);
        student.setSex(sexChar);

        String birthdateString = request.getParameter("dateofbirth");
        LocalDate birthdate = LocalDate.parse(birthdateString);
        student.setDateOfBirth(birthdate);
        student.setStatut(request.getParameter("statut"));
        String matricule = db.genererMatricule();
        student.setMatricule(matricule);

        student.setDateRegister(java.time.LocalDateTime.now());

        try {
            db.enregistrerEtudiant(student);
            request.setAttribute("message", "Étudiant ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/formulaire.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/formulaire.jsp").forward(request, response);
    }
}
