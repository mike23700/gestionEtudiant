<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tp.models.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Résultat de recherche</title>
</head>
<body>
    <h2>Recherche d'étudiant</h2>

    <form method="post" action="rechercher">
        <label>Type de recherche :</label>
        <select name="type">
            <option value="matricule">Matricule</option>
            <option value="nom">Nom</option>
        </select><br><br>
        <input type="text" name="valeur" required>
        <input type="submit" value="Rechercher">
    </form>

    <%
        List<Student> resultats = (List<Student>) request.getAttribute("resultats");
        if (resultats != null && !resultats.isEmpty()) {
    %>
        <h3>Résultats :</h3>
        <table border="1">
            <tr>
                <th>Matricule</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Sexe</th>
                <th>Date de naissance</th>
            </tr>
            <% for (Student s : resultats) { %>
                <tr>
                    <td><%= s.getMatricule() %></td>
                    <td><%= s.getName() %></td>
                    <td><%= s.getSurname() %></td>
                    <td><%= s.getSex() %></td>
                    <td><%= s.getFormattedDateOfBirth() %></td>
                </tr>
            <% } %>
        </table>
    <% } else if (resultats != null) { %>
        <p>Aucun étudiant trouvé.</p>
    <% } %>

    <p><a href="<%= request.getContextPath() %>/">Retour à l'accueil</a></p>
</body>
</html>
