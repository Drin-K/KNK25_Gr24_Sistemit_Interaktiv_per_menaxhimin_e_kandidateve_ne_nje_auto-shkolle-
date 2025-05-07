package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Kandidatet extends User{
    private LocalDate dataRegjistrimit;
    private String statusiProcesit;

    protected Kandidatet(int idUser, String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String hashedPassword, String salt, String adresa, String gjinia,LocalDate dataRegjistrimit, String statusiProcesit) {
        super(idUser,name,surname,email,phoneNumber,dateOfBirth,hashedPassword,salt,"Kandidat",adresa, gjinia);
        this.dataRegjistrimit = dataRegjistrimit;
        this.statusiProcesit = statusiProcesit;//ne proces dhe perfunduar
    }

    public LocalDate getDataRegjistrimit() {
        return dataRegjistrimit;
    }

    public String getStatusiProcesit() {
        return statusiProcesit;
    }
    //RESULTSETI PER KANDIDTATET
//    SELECT
//    u.id,
//    u.name,
//    u.surname,
//    u.email,
//    u.phoneNumber,
//    u.dateOfBirth,
//    u.hashedPassword,
//    u.salt,
//    u.role,
//    u.adresa,
//    u.gjinia,
//    k.dataRegjistrimi,
//    k.statusiProcesit
//            FROM
//    "User" u
//            JOIN
//    Kandidatet k ON u.id = k.id
//            WHERE
//    u.id = ?;

    public static Kandidatet getInstance(ResultSet result) throws SQLException {
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
        LocalDate dataRegjistrimi = result.getObject("dataRegjistrimi", LocalDate.class);
        String statusiProcesit = result.getString("statusiProcesit");
        return new Kandidatet(id, name, surname, email, phoneNumber, dateOfBirth, hashedPassword, salt,
                adresa, gjinia, dataRegjistrimi, statusiProcesit);
    }
}

