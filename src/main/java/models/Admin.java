package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Admin extends User {

    private Admin(int idUser, String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String hashedPassword, String salt, String role, String adresa, String gjinia) {
        super(idUser,name,surname,email,phoneNumber,dateOfBirth,hashedPassword,salt,role,adresa,gjinia);

    }


    public static Admin getInstance(ResultSet result) throws SQLException {
        User user = User.getInstance(result);
        return new Admin(user.getIdUser(),user.getName(),user.getSurname(),user.getEmail(),user.getPhoneNumber(),user.getDateOfBirth(),user.getHashedPassword(),user.getSalt(),"Admin", user.getAdresa(), user.getGjinia());
    }

}
