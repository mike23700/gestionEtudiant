package com.tp.controllers;

import com.tp.dao.DaoFactory;
import com.tp.interfaces.StudentDao;
import com.tp.models.Student;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/rechercher")
public class RechercherServlet extends HttpServlet {

    private StudentDao studentDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.studentDao = daoFactory.getStudentDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/resultat.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        String valeur = request.getParameter("valeur");

        List<Student> resultats = new ArrayList<>();

        try {
            if ("matricule".equals(type)) {
                Student student = studentDao.rechercherParMatricule(valeur);
                if (student != null) {
                    resultats.add(student);
                }
            } else if ("nom".equals(type)) {
                resultats = studentDao.rechercherParNom(valeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("resultats", resultats);
        this.getServletContext().getRequestDispatcher("/WEB-INF/resultat.jsp")
                .forward(request, response);
    }
}