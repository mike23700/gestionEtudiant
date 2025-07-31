<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.tp.models.Student" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Liste des étudiants</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h2>Liste de tous les étudiants</h2>

    <c:choose>
        <c:when test="${not empty listeEtudiants}">
            <table border="1">
                <tr>
                    <th>Matricule</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Sexe</th>
                    <th>Date de naissance</th>
                    <th>Date d'inscription</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="s" items="${listeEtudiants}">
                    <tr>
                        <td>${s.matricule}</td>
                        <td>${s.name}</td>
                        <td>${s.surname}</td>
                        <td>${s.sex}</td>
                        <td>${s.formattedDateOfBirth}</td>
                        <td>${s.formattedDateRegister}</td>
                        <td>
                            <a href="modifier?matricule=${s.matricule}">Modifier</a> |
                            <a href="supprimer?matricule=${s.matricule}">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>Aucun étudiant enregistré.</p>
        </c:otherwise>
    </c:choose>

    <p><a href="/gestion/home">Retour à l'accueil</a></p>
</body>
</html>
