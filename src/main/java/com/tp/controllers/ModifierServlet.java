package com.tp.controllers;


import com.tp.DAO.AccessDB;
import com.tp.models.Student;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/modifier")
public class ModifierServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String matricule = request.getParameter("matricule");
        AccessDB db = new AccessDB();
        Student student = db.rechercherParMatricule(matricule);

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
        student.setSex(request.getParameter("sex").charAt(0));
        student.setDateOfBirth(LocalDate.parse(request.getParameter("dob")));

        AccessDB db = new AccessDB();
        boolean success = db.modifierEtudiant(student);

        if (success) {
            response.sendRedirect("lister");
        } else {
            request.setAttribute("message", "Erreur lors de la modification.");
            request.setAttribute("etudiant", student);
            this.getServletContext().getRequestDispatcher("/WEB-INF/modifier.jsp")
                    .forward(request, response);
        }
    }
}

