<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tp.models.Student" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Résultat de recherche</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/recherche.css">
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

    <c:if test="${not empty resultats}">
        <h3>Résultats :</h3>
        <table border="1">
            <tr>
                <th>Matricule</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Sexe</th>
                <th>Date de naissance</th>
                <th>Date d'inscription</th>
                <th>Statut</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="s" items="${resultats}">
                <tr>
                    <td>${s.matricule}</td>
                    <td>${s.name}</td>
                    <td>${s.surname}</td>
                    <td>${s.sex}</td>
                    <td>${s.formattedDateOfBirth}</td>
                    <td>${s.formattedDateRegister}</td>
                    <td>
                       <span class="${s.statut == 'Pre-inscrit' ? 'badge badge-pre' : s.statut == 'Inscrit' ? 'badge badge-ins' : s.statut == 'Desinscrit' ? 'badge badge-des' : 'badge'}">
                          ${s.statut}
                       </span>
                    </td>
                    <td>
                       <a href="modifier?matricule=${s.matricule}">Modifier</a> |
                       <a href="supprimer?matricule=${s.matricule}" onclick="return confirm('Voulez-vous vraiment supprimer étudiant  ${s.name}?');">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </c:if>

    <c:if test="${resultats != null && resultats.isEmpty()}">
        <p>Aucun étudiant trouvé.</p>
    </c:if>

    <p><a href="/gestion/home">Retour à l'accueil</a></p>
</body>
</html>
