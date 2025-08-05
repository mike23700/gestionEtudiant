<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.tp.models.Student" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Liste des étudiants</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/liste.css">
</head>
<body>
    <h2>Liste de tous les étudiants</h2>
    <p><a href="/gestion/rechercher">Rechercher</a></p>
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
                    <th>Statut</th>
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
        </c:when>
        <c:otherwise>
            <p>Aucun étudiant enregistré.</p>
        </c:otherwise>
    </c:choose>

    <p><a href="/gestion/home">Retour à l'accueil</a></p>
</body>
</html>
