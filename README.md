# Gestion Étudiants - Java EE

Ce projet est une application web développée en **Java EE (JSP/Servlet)** permettant la gestion basique des étudiants à travers une interface utilisateur simple. Le projet comprend les fonctionnalités suivantes :

- Ajout d’un étudiant
- Modification d’un étudiant
- Suppression d’un étudiant
- Consultation de la liste des étudiants
- Recherche d'étudiant ( par matricule ou nom)

## 🌐 Technologies utilisées

- Java 8+
- Java EE (Servlets, JSP, JSTL)
- Apache Tomcat 8.5+
- JDBC (Connexion MySQL)
- HTML/CSS
- JSTL (JavaServer Pages Standard Tag Library)
```

## ⚙️ Configuration requise

- **JDK 8+**
- **Apache Tomcat 9**
- **MySQL Server** (ou équivalent compatible JDBC)
- **IDE** (Eclipse, IntelliJ)

## 🧠 Base de données

Base de données MySQL :

```sql
CREATE DATABASE student_db;

USE student_db;

CREATE TABLE IF NOT EXISTS Student (
    Matricule VARCHAR(20) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Surname VARCHAR(100) NOT NULL,
    Sex VARCHAR(10) NOT NULL,
    DateOfBirth DATE NOT NULL,
    DateRegister TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
);
```

## ▶️ Déploiement

1. Cloner le projet :
   ```bash
   git clone https://github.com/mike23700/gestionEtudiant.git
   ```

2. Importer dans votre IDE en tant que **Dynamic Web Project** ou projet Maven selon votre configuration.

3. Configurer votre base de données (nom, utilisateur, mot de passe) dans le fichier Java qui crée la connexion JDBC.

4. Déployer sur un serveur **Tomcat**.

5. Accéder à l’application via :
   ```
   http://localhost:8080/gestion/home
   ```

##Bonus

```
La prochaine version est en cours de developement avec ajout de fonctionalite d'atribuer les matricule generer automatiquement.

```

