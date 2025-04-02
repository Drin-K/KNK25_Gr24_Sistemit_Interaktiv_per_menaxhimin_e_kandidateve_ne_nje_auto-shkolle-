package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Orari {
    private int id_sesioni;
    private int id_kandidat;
    private int id_staf;
    private String data_sesionit;
    private String ora_e_fillimit;
    private String ora_e_perfundimit;
    private String lloji_i_mesimit;
    private String statusi;
    private int id_automjet;

    private Orari(int id_sesioni, int id_kandidatet, int id_staf, String data_sesionit, String ora_e_fillimit, String ora_e_perfundimit, String lloji_i_mesimit, String statusi, int id_automjet) {
        this.id_sesioni = id_sesioni;
        this.id_kandidat = id_kandidatet;
        this.id_staf = id_staf;
        this.data_sesionit = data_sesionit;
        this.ora_e_fillimit = ora_e_fillimit;
        this.ora_e_perfundimit = ora_e_perfundimit;
        this.lloji_i_mesimit = lloji_i_mesimit;
        this.statusi = statusi;
        this.id_automjet = id_automjet;
    }
    public static Orari getInstance(ResultSet resultSet)throws SQLException{
        int id_sesioni= resultSet.getInt("id_sesioni");
        int id_kanditat=resultSet.getInt("id_kandidat");
        int id_staf=resultSet.getInt("id_staf");
        String data_sesionit=resultSet.getString("data_sesionit");
        String ora_e_fillimit=resultSet.getString("ora_e_fillimit");
        String ora_e_perfundimit=resultSet.getString("ora_e_perfundimit");
        String lloji_i_mesimit=resultSet.getString("lloji_i_mesimit");
        String statusi=resultSet.getString("statusi");
        int id_automjet=resultSet.getInt("id_automjet");
        return new Orari(id_sesioni,id_kanditat,id_staf,data_sesionit,ora_e_fillimit,ora_e_perfundimit,lloji_i_mesimit,statusi,id_automjet);
    }

    public int getId_sesioni() {
        return id_sesioni;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public int getId_staf() {
        return id_staf;
    }

    public String getData_sesionit() {
        return data_sesionit;
    }

    public String getOra_e_fillimit() {
        return ora_e_fillimit;
    }

    public String getOra_e_perfundimit() {
        return ora_e_perfundimit;
    }

    public String getLloji_i_mesimit() {
        return lloji_i_mesimit;
    }

    public String getStatusi() {
        return statusi;
    }

    public int getId_automjet() {
        return id_automjet;
    }
}
