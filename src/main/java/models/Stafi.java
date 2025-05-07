package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Stafi extends User{
    protected Stafi(int idUser, String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String hashedPassword, String salt, String adresa, String gjinia) {
        super(idUser,name,surname,email,phoneNumber,dateOfBirth,hashedPassword,salt,"Staf",adresa, gjinia);

    }
    public static Stafi getInstance(ResultSet result) throws SQLException {
        User stafi = User.getInstance(result);
        return new Stafi(stafi.getIdUser(),stafi.getName(),stafi.getSurname(),stafi.getEmail(),stafi.getPhoneNumber(),stafi.getDateOfBirth(),stafi.getHashedPassword(),stafi.getSalt(), stafi.getAdresa(), stafi.getGjinia());
    }
}
