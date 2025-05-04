package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Stafi extends User{
    private Stafi(int idUser, String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String hashedPassword, String salt, String adresa, String gjinia) {
        super(idUser,name,surname,email,phoneNumber,dateOfBirth,hashedPassword,salt,"Staf",adresa, gjinia);

    }
    public static Stafi getInstance(ResultSet result) throws SQLException {
        User user = User.getInstance(result);
        return new Stafi(user.getIdUser(),user.getName(),user.getSurname(),user.getEmail(),user.getPhoneNumber(),user.getDateOfBirth(),user.getHashedPassword(),user.getSalt(), user.getAdresa(), user.getGjinia());
    }
}
