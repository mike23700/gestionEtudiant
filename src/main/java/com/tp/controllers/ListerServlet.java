package com.tp.controllers;

import com.tp.models.AccessDB;
import com.tp.models.Student;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/lister")
public class ListerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccessDB db = new AccessDB();
        List<Student> liste = db.listerEtudiants();

        request.setAttribute("listeEtudiants", liste);
        this.getServletContext().getRequestDispatcher("/WEB-INF/liste.jsp")
                .forward(request, response);
    }
}
