<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un étudiant</title>
</head>
<body>
    <h2>Ajouter un étudiant</h2>
    <form method="post" action="ajouter">
        <label>Matricule :</label><br>
        <input type="text" name="matricule" required><br><br>

        <label>Nom :</label><br>
        <input type="text" name="name" required><br><br>

        <label>Prénom :</label><br>
        <input type="text" name="surname" required><br><br>

        <label>Sexe :</label><br>
        <select name="sex" required>
            <option value="M">Masculin</option>
            <option value="F">Féminin</option>
        </select><br><br>

        <label>Date de naissance :</label><br>
        <input type="date" name="dob" required><br><br>

        <input type="submit" value="Enregistrer">
    </form>

    <p><a href="<%= request.getContextPath() %>/">Retour à l'accueil</a></p>
</body>
</html>
