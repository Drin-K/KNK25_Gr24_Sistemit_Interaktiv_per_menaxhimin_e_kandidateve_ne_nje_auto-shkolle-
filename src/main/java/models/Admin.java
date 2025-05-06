package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Admin extends User {

    protected Admin(int idUser, String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String hashedPassword, String salt, String role, String adresa, String gjinia) {
        super(idUser,name,surname,email,phoneNumber,dateOfBirth,hashedPassword,salt,role,adresa,gjinia);

    }


    public static Admin getInstance(ResultSet result) throws SQLException {
        Admin admin = Admin.getInstance(result);
        return new Admin(admin.getIdUser(),admin.getName(),admin.getSurname(),admin.getEmail(),admin.getPhoneNumber(),admin.getDateOfBirth(),admin.getHashedPassword(),admin.getSalt(),"Admin", admin.getAdresa(), admin.getGjinia());
    }

}
