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

    public static Kandidatet getInstance(ResultSet result) throws SQLException {
        // Krijo instancën e User nga getInstance e User
        User user = User.getInstance(result);

        // Merr të dhënat shtesë për Kandidat
        LocalDate dataRegjistrimi = result.getObject("dataRegjistrimi", LocalDate.class);
        String statusiProcesit = result.getString("statusiProcesit");

        // Krijo dhe kthe instancën e Kandidatet
        return new Kandidatet(user.getIdUser(), user.getName(), user.getSurname(), user.getEmail(),
                user.getPhoneNumber(), user.getDateOfBirth(), user.getHashedPassword(),
                user.getSalt(), user.getAdresa(), user.getGjinia(), dataRegjistrimi, statusiProcesit);
    }
}

