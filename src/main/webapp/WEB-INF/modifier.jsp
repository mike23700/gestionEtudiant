<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier un étudiant</title>
</head>
<body>
    <h2>Modifier un étudiant</h2>

    <c:choose>
        <c:when test="${not empty etudiant}">
            <form method="post" action="modifier">
                <input type="hidden" name="matricule" value="${etudiant.matricule}" />

                <label>Nom :</label><br>
                <input type="text" name="name" value="${etudiant.name}" required><br><br>

                <label>Prénom :</label><br>
                <input type="text" name="surname" value="${etudiant.surname}" required><br><br>

                <label>Sexe :</label>
                <select name="sex" required>
                   <option value="M" ${'M'.equals(etudiant.sex) ? 'selected' : ''}>M</option>
                   <option value="F" ${'F'.equals(etudiant.sex) ? 'selected' : ''}>F</option>
                </select><br><br>

                <label>Date de naissance :</label><br>
                <input type="date" name="dob" value="${etudiant.dateOfBirth}" required><br><br>

                <input type="submit" value="Modifier">
            </form>
        </c:when>
        <c:otherwise>
            <p>Étudiant non trouvé.</p>
        </c:otherwise>
    </c:choose>

    <p><a href="${pageContext.request.contextPath}/">Retour à l'accueil</a></p>
</body>
</html>
