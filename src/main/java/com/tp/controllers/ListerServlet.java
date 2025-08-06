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

@WebServlet("/lister")
public class ListerServlet extends HttpServlet {

    private StudentDao studentDao;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.studentDao = daoFactory.getStudentDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Student> liste = new ArrayList<>();
        try {
            liste = studentDao.listerEtudiants();
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Erreur lors de la récupération de la liste des étudiants : " + e.getMessage());
        }

        request.setAttribute("listeEtudiants", liste);
        this.getServletContext().getRequestDispatcher("/WEB-INF/liste.jsp")
                .forward(request, response);
    }
}