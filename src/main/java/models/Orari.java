package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Orari {
    private int idSesioni;
    private int idKandidat;
    private int idStaf;
    private String dataSesionit;
    private String oraFillimit;
    private String oraPerfundimit;
    private String llojiMesimit;
    private String statusi;
    private int idAutomjet;

    public static Orari getInstance(ResultSet resultSet)throws SQLException{
        int idSesioni= resultSet.getInt("id_sesioni");
        int idKanditat=resultSet.getInt("id_kandidat");
        int idStaf =resultSet.getInt("id_staf");
        String dataSesionit=resultSet.getString("data_sesionit");
        String oraFillimit=resultSet.getString("ora_e_fillimit");
        String oraPerfundimit=resultSet.getString("ora_e_perfundimit");
        String llojiMesimit=resultSet.getString("lloji_i_mesimit");
        String statusi=resultSet.getString("statusi");
        int idAutomjet=resultSet.getInt("id_automjet");
        return new Orari(idSesioni,idKanditat, idStaf,dataSesionit,oraFillimit,oraPerfundimit,llojiMesimit,statusi,idAutomjet);
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

    public String getDataSesionit() {
        return dataSesionit;
    }

    public String getOraFillimit() {
        return oraFillimit;
    }

    public String getOraPerfundimit() {
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

    private Orari(int idSesioni, int idKandidat, int idStaf, String dataSesionit, String oraFillimit, String oraPerfundimit, String llojiMesimit, String statusi, int idAutomjet) {
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
