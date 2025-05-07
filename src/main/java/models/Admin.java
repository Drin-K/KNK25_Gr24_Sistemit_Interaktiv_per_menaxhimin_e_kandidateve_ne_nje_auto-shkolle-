package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Admin extends User {

    protected Admin(int idUser, String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String hashedPassword, String salt, String adresa, String gjinia) {
        super(idUser,name,surname,email,phoneNumber,dateOfBirth,hashedPassword,salt,"Admin",adresa,gjinia);

    }


    public static Admin getInstance(ResultSet result) throws SQLException {
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
        return new Admin(id, name, surname, email,
                phoneNumber, dateOfBirth, hashedPassword,
                salt, adresa, gjinia);
    }

}