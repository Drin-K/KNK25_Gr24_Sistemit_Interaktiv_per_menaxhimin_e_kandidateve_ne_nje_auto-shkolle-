package models.Dto.user;

import services.UserContext;

import java.time.LocalDate;

public class UpdateUserDto {
    private int idUser;
    private String email;
    private String phoneNumber;
    private String password;
    private String salt;
    private String adresa;

    public UpdateUserDto() {
        email = UserContext.getEmail(); // Na sherben per ta dalluar a eshte kandidat apo staf
    } //Shtimi i nje konstruktori pa parametra na sherben per te ndrruar passwordin

    public UpdateUserDto(int id, String email, String phoneNumber, String password, String adresa) {
        this.idUser = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.adresa = adresa;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
