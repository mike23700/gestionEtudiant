<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.tp.models.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier un étudiant</title>
</head>
<body>
    <h2>Modifier un étudiant</h2>

    <%
        Student s = (Student) request.getAttribute("etudiant");
        if (s != null) {
    %>
    <form method="post" action="modifier">
        <input type="hidden" name="matricule" value="<%= s.getMatricule() %>">

        <label>Nom :</label><br>
        <input type="text" name="name" value="<%= s.getName() %>" required><br><br>

        <label>Prénom :</label><br>
        <input type="text" name="surname" value="<%= s.getSurname() %>" required><br><br>

        <label>Sexe :</label><br>
        <select name="sex" required>
            <option value="M" <%= s.getSex() == 'M' ? "selected" : "" %>>Masculin</option>
            <option value="F" <%= s.getSex() == 'F' ? "selected" : "" %>>Féminin</option>
        </select><br><br>

        <label>Date de naissance :</label><br>
        <input type="date" name="dob" value="<%= s.getDateOfBirth() %>" required><br><br>

        <input type="submit" value="Modifier">
    </form>
    <% } else { %>
        <p>Étudiant non trouvé.</p>
    <% } %>

    <p><a href="<%= request.getContextPath() %>/">Retour à l'accueil</a></p>
</body>
</html>
