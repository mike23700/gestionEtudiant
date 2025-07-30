package com.tp.controllers;

import com.tp.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/formulaire")
public class AjouterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = new Student();

        student.setMatricule(request.getParameter("matricule"));
        student.setName(request.getParameter("name"));
        student.setSurname(request.getParameter("surname"));
        String sexString = request.getParameter("sex");
        char sexChar = sexString.charAt(0);
        student.setSex(sexChar);
        String birthdateString = request.getParameter("dateofbirth");
        LocalDate birthdate = LocalDate.parse(birthdateString);
        student.setDateOfBirth(birthdate);
        this.getServletContext().getRequestDispatcher("/WEB-INF/resultat.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/formulaire.jsp");
    }
}
