package com.tp.controllers;

import com.tp.DAO.AccessDB;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/supprimer")
public class SupprimerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String matricule = request.getParameter("matricule");
        AccessDB db = new AccessDB();
        db.supprimerParMatricule(matricule);

        response.sendRedirect("lister");
    }
}
