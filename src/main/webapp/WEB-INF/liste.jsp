<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tp.models.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des étudiants</title>
</head>
<body>
    <h2>Liste de tous les étudiants</h2>

    <%
        List<Student> liste = (List<Student>) request.getAttribute("listeEtudiants");
        if (liste != null && !liste.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Matricule</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Sexe</th>
                <th>Date de naissance</th>
                <th>Actions</th>
            </tr>
            <% for (Student s : liste) { %>
                <tr>
                    <td><%= s.getMatricule() %></td>
                    <td><%= s.getName() %></td>
                    <td><%= s.getSurname() %></td>
                    <td><%= s.getSex() %></td>
                    <td><%= s.getFormattedDateOfBirth() %></td>
                    <td>
                        <a href="modifier?matricule=<%= s.getMatricule() %>">Modifier</a> |
                        <a href="supprimer?matricule=<%= s.getMatricule() %>">Supprimer</a>
                    </td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>Aucun étudiant enregistré.</p>
    <% } %>

    <p><a href="<%= request.getContextPath() %>/">Retour à l'accueil</a></p>
</body>
</html>
