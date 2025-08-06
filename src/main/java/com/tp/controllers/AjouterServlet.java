package com.tp.controllers;

import com.tp.dao.DaoFactory;
import com.tp.interfaces.StudentDao;
import com.tp.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet("/formulaire")
public class AjouterServlet extends HttpServlet {

    private StudentDao studentDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.studentDao = daoFactory.getStudentDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = new Student();

        student.setName(request.getParameter("name"));
        student.setSurname(request.getParameter("surname"));

        String sexString = request.getParameter("sex");
        student.setSex(sexString.charAt(0));

        String birthdateString = request.getParameter("dateofbirth");
        LocalDate birthdate = LocalDate.parse(birthdateString);
        student.setDateOfBirth(birthdate);

        student.setStatut(request.getParameter("statut"));
        student.setDateRegister(LocalDateTime.now());

        try {
            String matricule = studentDao.genererMatricule();
            student.setMatricule(matricule);

            studentDao.enregistrerEtudiant(student);
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