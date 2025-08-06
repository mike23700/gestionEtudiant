<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <form method="post" action="/gestion/modifier">
    <div class="container">
      <input type="hidden" name="matricule" value="${etudiant.matricule}">

      <h2>Modifier l'étudiant ${etudiant.name}</h2>

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
        <input type="submit" value="Modifier" onclick="return confirm('Voulez-vous vraiment modifier les informations de ${etudiant.name}?');">
      </div>

    </div>
  </form>

