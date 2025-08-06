package com.tp.controllers;

import com.tp.dao.DaoFactory;
import com.tp.interfaces.StudentDao;
import com.tp.models.Student;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/modifier")
public class ModifierServlet extends HttpServlet {

    private StudentDao studentDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.studentDao = daoFactory.getStudentDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        Student student = null;

        try {
            student = studentDao.rechercherParMatricule(matricule);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur lors de la recherche de l'étudiant.");
        }

        if (student != null) {
            request.setAttribute("etudiant", student);
            this.getServletContext().getRequestDispatcher("/WEB-INF/modifier.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("lister");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = new Student();
        student.setMatricule(request.getParameter("matricule"));
        student.setName(request.getParameter("name"));
        student.setSurname(request.getParameter("surname"));
        student.setStatut(request.getParameter("statut"));

        String sexString = request.getParameter("sex");
        if (sexString != null && !sexString.isEmpty()) {
            student.setSex(sexString.charAt(0));
        }

        String birthdateString = request.getParameter("dob");
        try {
            if (birthdateString != null && !birthdateString.isEmpty()) {
                student.setDateOfBirth(LocalDate.parse(birthdateString));
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        boolean success = false;
        try {
            success = studentDao.modifierEtudiant(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (success) {
            response.sendRedirect(request.getContextPath() + "/lister");
        } else {
            request.setAttribute("message", "Erreur lors de la modification de l'étudiant.");
            request.setAttribute("etudiant", student);
            this.getServletContext().getRequestDispatcher("/WEB-INF/modifier.jsp")
                    .forward(request, response);
        }
    }
}