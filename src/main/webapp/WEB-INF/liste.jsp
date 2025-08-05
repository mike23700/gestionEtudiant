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
                            <a href="modifier" onclick="ouvrirModal('${s.matricule}'); return false;">Modifier</a> |
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

    <!-- Modal -->
    <div id="modal" class="modal">
      <div class="modal-content">
        <span class="close" onclick="fermerModal()">&times;</span>
        <div id="modal-body">
          <!-- Le formulaire sera chargé ici par AJAX -->
        </div>
      </div>
    </div>

    <script>
    function ouvrirModal(matricule) {
        document.getElementById('modal').style.display = 'block';

        fetch('/gestion/modifier?matricule=' + matricule)
            .then(response => response.text())
            .then(html => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(html, 'text/html');
                const form = doc.querySelector('form');
                if (form) {
                    document.getElementById('modal-body').innerHTML = '';
                    document.getElementById('modal-body').appendChild(form);
                } else {
                    document.getElementById('modal-body').innerHTML = "<p>Erreur : étudiant non trouvé.</p>";
                }
            });
    }

    function fermerModal() {
        document.getElementById('modal').style.display = 'none';
    }
    </script>

</body>
</html>
