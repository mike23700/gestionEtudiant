<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Modifier un étudiant</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h2>Modifier un étudiant</h2>

<c:if test="${not empty etudiant}">
  <form method="post" action="${pageContext.request.contextPath}/modifier">
    <div class="container">
      <input type="hidden" name="matricule" value="${etudiant.matricule}">

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

        <input type="radio" name="sex" id="homme" value="M"
          <c:if test="${s.getSex()== 'M'}">checked</c:if> required>
        <label for="homme">Homme</label>

        <input type="radio" name="sex" id="femme" value="F"
          <c:if test="${s.getSex() == 'F'}">checked</c:if> required>
        <label for="femme">Femme</label>
      </div>

      <div>
        <label>Date de Naissance :</label>
        <input type="date" name="dob" value="${etudiant.dateOfBirth}" required>
      </div>

      <div>
        <input type="submit" value="Modifier">
      </div>

      <c:if test="${not empty message}">
        <p style="color:red; font-weight:bold;">${message}</p>
      </c:if>
    </div>
  </form>
</c:if>

<c:if test="${empty etudiant}">
  <p>Étudiant non trouvé.</p>
</c:if>

<p style="font-weight: bold;">${message}</p>
</body>
</html>
