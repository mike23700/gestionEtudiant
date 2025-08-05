<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Modifier un étudiant</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel="stylesheet" type="text/css" href="css/form.css">
</head>
<body>
<h2>Modifier un étudiant</h2>

<c:if test="${not empty etudiant}">
  <form method="post" action="/gestion/modifier">
    <div class="container">
      <input type="hidden" name="matricule" value="${etudiant.matricule}">

      <div>
        <label>Matricule :</label>
        <input type="text" value="${etudiant.matricule}" disabled>
        <input type="hidden" name="matricule" value="${etudiant.matricule}">
      </div>

      <div>
        <label>Nom :</label>
        <input type="text" name="name" value="${etudiant.name}" required>
      </div>

      <div>
        <label>Prénom :</label>
        <input type="text" name="surname" value="${etudiant.surname}" required>
      </div>

      <div>
        <label>Sexe :</label><br>
        <div class="radio-group">
          <input type="radio" name="sex" id="homme" value="M"
                 ${etudiant.sexAsString == 'M' ? 'checked' : ''} required>
          <label for="homme">Homme</label>

          <input type="radio" name="sex" id="femme" value="F"
                 ${etudiant.sexAsString == 'F' ? 'checked' : ''} required>
          <label for="femme">Femme</label>
        </div>
      </div>

      <div>
        <label>Date de Naissance :</label>
        <input type="date" name="dob" value="${etudiant.dateOfBirth}" required>
      </div>

      <div>
        <label>Statut :</label><br>
        <div class="radio-group">
          <input type="radio" name="statut" id="pre" value="Pre-inscrit"
                 ${etudiant.statut == 'Pre-inscrit' ? 'checked' : ''} required>
          <label for="pre">Pre-inscrit</label>
          <input type="radio" name="statut" id="ins" value="Inscrit"
                 ${etudiant.statut == 'Inscrit' ? 'checked' : ''} required>
          <label for="ins">Inscrit</label>
          <input type="radio" name="statut" id="des" value="Desinscrit"
                 ${etudiant.statut == 'Desinscrit' ? 'checked' : ''} required>
          <label for="des">Desinscrit</label>
        </div>
      </div>

      <div>
        <input type="submit" value="Modifier">
      </div>

      <c:if test="${not empty message}">
        <p style="color: green; font-weight: bold;">${message}</p>
      </c:if>
    </div>
  </form>
</c:if>

<c:if test="${empty etudiant}">
  <p>Étudiant non trouvé.</p>
</c:if>

<p><a href="/gestion/home">Retour à l'accueil</a></p>
</body>
</html>