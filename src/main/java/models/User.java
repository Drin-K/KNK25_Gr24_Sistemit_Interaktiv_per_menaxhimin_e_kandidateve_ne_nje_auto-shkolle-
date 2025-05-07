package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//ME VONE DO TA SHQYRTOSH TA BESH ABSTRAKTE
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
    public User(int id, String name, String surname, String email, String phoneNumber,
                LocalDate dateOfBirth, String hashedPassword, String salt, String adresa, String gjinia) {
        this.idUser = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.adresa = adresa;
        this.gjinia = gjinia;
    }


    public static User getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        String surname = result.getString("surname");
        String email = result.getString("email");
        String phoneNumber = result.getString("phoneNumber");
        LocalDate dateOfBirth = result.getObject("dateOfBirth", LocalDate.class);
        String hashedPassword = result.getString("hashedPassword");
        String salt = result.getString("salt");
        String adresa = result.getString("adresa");
        String gjinia = result.getString("gjinia");
        String role = result.getString("role");
        if (role == null) {
            System.out.println("Role is null for user ID: " + result.getInt("id"));
        }
        if ("Kandidat".equals(role)) {
            LocalDate dataRegjistrimi = result.getObject("dataRegjistrimi", LocalDate.class);
            String statusiProcesit = result.getString("statusiProcesit");
            return new Kandidatet(id, name, surname, email, phoneNumber, dateOfBirth, hashedPassword, salt, adresa, gjinia, dataRegjistrimi, statusiProcesit);
        } else if ("Staf".equals(role)) {
            return new Stafi(id, name, surname, email, phoneNumber, dateOfBirth, hashedPassword, salt, adresa, gjinia);
        } else {
            throw new SQLException("Role i pa njohur: " + role);
        }
    }
}