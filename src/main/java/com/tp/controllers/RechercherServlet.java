package com.tp.controllers;

import com.tp.models.AccessDB;
import com.tp.models.Student;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/rechercher")
public class RechercherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/resultat.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        String valeur = request.getParameter("valeur");

        AccessDB db = new AccessDB();
        List<Student> resultats = new ArrayList<>();

        if ("matricule".equals(type)) {
            Student student = db.rechercherParMatricule(valeur);
            if (student != null) resultats.add(student);
        } else if ("nom".equals(type)) {
            resultats = db.rechercherParNom(valeur);
        }

        request.setAttribute("resultats", resultats);
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/resultat.jsp")
                .forward(request, response);
    }
}
