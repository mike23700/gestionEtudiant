<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Formulaire</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel="stylesheet" type="text/css" href="css/form.css">
</head>
<body>
<h2>Entrez les informations de l'étudiant</h2>
  <form method="post" action="/gestion/formulaire">
    <div class="container">
      <div>
        <label>Nom : </label>
        <input type="text" name="name" id="name" required>
      </div>
      <div>
        <label>Prénom : </label>
        <input type="text" name="surname" id="surname" required>
      </div>

      <div>
         <label>Sexe :</label><br>
         <div class="radio-group">
           <input type="radio" name="sex" id="homme" value="M" required>
           <label for="homme">Homme</label>
           <input type="radio" name="sex" id="femme" value="F" required>
           <label for="femme">Femme</label>
         </div>
      </div>

      <div>
        <label>Date de Naissance : </label>
        <input type="date" name="dateofbirth" id="dateofbirth" required>
      </div>

      <div>
         <label>Statut :</label><br>
         <div class="radio-group">
           <input type="radio" name="statut" id="pre" value="Pre-inscrit" required checked>
           <label for="pre">Pre-inscrit</label>
           <input type="radio" name="statut" id="ins" value="Inscrit" required>
           <label for="ins">Inscrit</label>
           <input type="radio" name="statut" id="des" value="Desinscrit" required>
           <label for="des">Desinscrit</label>
         </div>
      </div>

      <div>
        <input type="submit" value="Envoyer">
      </div>
    </div>
  </form>

  <c:if test="${not empty message}">
      <p style="color : green; font-weight: bold;">${message}</p>
  </c:if>

   <p><a href="/gestion/home">Retour à l'accueil</a></p>
</body>
</html>