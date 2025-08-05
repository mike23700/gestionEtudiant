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
                       <a href="modifier" onclick="ouvrirModal('${s.matricule}'); return false;">Modifier</a> |
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
