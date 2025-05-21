package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Orari {
    private int idSesioni;
    private int idKandidat;
    private int idStaf;
    private LocalDate dataSesionit;
    private LocalTime oraFillimit;
    private LocalTime oraPerfundimit;
    private String llojiMesimit;
    private String statusi;
    private int idAutomjet;

    public static Orari getInstance(ResultSet resultSet) throws SQLException {
        int idSesioni = resultSet.getInt("id");
        int idKanditat = resultSet.getInt("ID_Kandidat");
        int idStaf = resultSet.getInt("ID_Staf");
        LocalDate dataSesionit = resultSet.getObject("Data_e_Sesionit", LocalDate.class);
        LocalTime oraFillimit = resultSet.getObject("Ora_e_Fillimit", LocalTime.class);
        LocalTime oraPerfundimit = resultSet.getObject("Ora_e_Perfundimit", LocalTime.class);
        String llojiMesimit = resultSet.getString("Lloji_i_Mesimit");
        String statusi = resultSet.getString("Statusi");
        int idAutomjet = resultSet.getInt("ID_Automjet");
        return new Orari(idSesioni, idKanditat, idStaf, dataSesionit, oraFillimit, oraPerfundimit, llojiMesimit, statusi, idAutomjet);
    }

    public int getIdSesioni() {
        return idSesioni;
    }

    public int getIdKandidat() {
        return idKandidat;
    }

    public int getIdStaf() {
        return idStaf;
    }

    public LocalDate getDataSesionit() {
        return dataSesionit;
    }

    public LocalTime getOraFillimit() {
        return oraFillimit;
    }

    public LocalTime getOraPerfundimit() {
        return oraPerfundimit;
    }

    public String getLlojiMesimit() {
        return llojiMesimit;
    }

    public String getStatusi() {
        return statusi;
    }

    public int getIdAutomjet() {
        return idAutomjet;
    }

    private Orari(int idSesioni, int idKandidat, int idStaf, LocalDate dataSesionit, LocalTime oraFillimit, LocalTime oraPerfundimit, String llojiMesimit, String statusi, int idAutomjet) {
        this.idSesioni = idSesioni;
        this.idKandidat = idKandidat;
        this.idStaf = idStaf;
        this.dataSesionit = dataSesionit;
        this.oraFillimit = oraFillimit;
        this.oraPerfundimit = oraPerfundimit;
        this.llojiMesimit = llojiMesimit;
        this.statusi = statusi;
        this.idAutomjet = idAutomjet;
    }
}
