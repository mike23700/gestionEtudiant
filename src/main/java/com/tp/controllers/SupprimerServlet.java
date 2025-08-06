package com.tp.controllers;

import com.tp.dao.DaoFactory;
import com.tp.interfaces.StudentDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/supprimer")
public class SupprimerServlet extends HttpServlet {

    private StudentDao studentDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.studentDao = daoFactory.getStudentDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String matricule = request.getParameter("matricule");
        boolean success = false;

        if (matricule != null && !matricule.isEmpty()) {
            try {
                success = studentDao.supprimerParMatricule(matricule);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Erreur lors de la suppression de l'étudiant : " + e.getMessage());
            }
        }

        if (success) {
            request.getSession().setAttribute("infoMessage", "Étudiant supprimé avec succès !");
        } else {
            request.getSession().setAttribute("errorMessage", "Erreur lors de la suppression ou matricule invalide.");
        }
        response.sendRedirect("lister");
    }
}