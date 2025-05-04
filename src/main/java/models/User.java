package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//ME VONE DO TA SHQYRTOSH TA BESH ABSTRAKTE
public class User {
    private int idUser;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String hashedPassword;
    private String salt;
    private String role; //  'Kandidat', 'Staf', 'Admin'> adminin do ta bejme vetem nje dhe ai do vendoset direkt
    //nga databaza
    private String adresa;
    private String gjinia; //('M', 'F')

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
        this.adresa=adresa;
        this.gjinia=gjinia;
    }
    public String getGjinia(){
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
    public String getAdresa(){
        return adresa;
    }
    public static User getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("idUser");
        String name=result.getString("name");
        String surname=result.getString("surname");

        String email=result.getString("email");
        String phoneNumber=result.getString("phoneNumber");

        LocalDate dateOfBirth = result.getObject("dateOfBirth", LocalDate.class);

        String hashedPassword = result.getString("hashedPassword");
        String salt=result.getString("salt");
        String role=result.getString("role");
        String adresa=result.getString("adresa");
        String gjinia=result.getString("gjinia");
        return new User(id, name, surname, email, phoneNumber, dateOfBirth, hashedPassword, salt, role, adresa,gjinia);
    }

}
