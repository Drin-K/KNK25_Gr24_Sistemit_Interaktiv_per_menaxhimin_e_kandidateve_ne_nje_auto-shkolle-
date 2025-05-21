package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public abstract class User {
    protected int idUser;
    protected String name;
    protected String surname;
    protected String email;
    protected String phoneNumber;
    protected LocalDate dateOfBirth;
    protected String hashedPassword;
    protected String salt;
    protected String role; //  'Kandidat', 'Staf', 'Admin'> adminin do ta bejme vetem nje dhe ai do vendoset direkt
    //nga databaza
    protected String adresa;
    protected String gjinia; //('M', 'F')

    protected User(int idUser, String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String hashedPassword, String salt, String role, String adresa, String gjinia) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.role = role;
        this.adresa = adresa;
        this.gjinia = gjinia;
    }

    public String getGjinia() {
        return gjinia;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public String getRole() {
        return role;
    }

    public String getAdresa() {
        return adresa;
    }

}