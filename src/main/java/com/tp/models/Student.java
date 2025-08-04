package com.tp.models;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Student {
    private String Matricule;
    private String Name;
    private String Surname;
    private char Sex;
    private LocalDate DateOfBirth;
    private LocalDateTime DateRegister;

    public  Student(){
    }

    public String getMatricule() {
        return Matricule;
    }

    public void setMatricule(String matricule) {
        this.Matricule = matricule;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public char getSex() {
        return Sex;
    }

    public void setSex(char sex) {
        this.Sex = sex;
    }

    public String getSexAsString() {
        return String.valueOf(this.Sex);
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }

    public String getFormattedDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(DateOfBirth);
    }

    public LocalDateTime getDateRegister() {
        return DateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.DateRegister = dateRegister;
    }

    public String getFormattedDateRegister() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return DateRegister.format(formatter);
    }

}
