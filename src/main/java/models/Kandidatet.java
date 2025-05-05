package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Kandidatet extends User{
    private LocalDate dataRegjistrimit;
    private String statusiProcesit;

    private Kandidatet(int idUser, String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String hashedPassword, String salt, String adresa, String gjinia,LocalDate dataRegjistrimit, String statusiProcesit) {
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

    public static Kandidatet getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name=result.getString("name");
        String surname=result.getString("surname");

        String email=result.getString("email");
        String phoneNumber=result.getString("phoneNumber");

        LocalDate dateOfBirth = result.getObject("dateOfBirth", LocalDate.class);

        String hashedPassword = result.getString("hashedPassword");
        String salt=result.getString("salt");
        String adresa=result.getString("adresa");
        String gjinia=result.getString("gjinia");
        LocalDate dataRegjistrimit=result.getObject("dataRegjistrimi", LocalDate.class);
        String statusiProcesit=result.getString("statusiProcesit");
        return new Kandidatet(id,name,surname,email,phoneNumber,dateOfBirth,hashedPassword,salt,adresa,gjinia,dataRegjistrimit,statusiProcesit);
    }
}
