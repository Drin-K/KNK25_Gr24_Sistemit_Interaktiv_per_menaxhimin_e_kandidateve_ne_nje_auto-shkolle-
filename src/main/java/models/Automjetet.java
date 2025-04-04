package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Automjetet {
    private int idAutomjet;
    private int llojiIAutomjetit;
    private String statusi;
    private int idStaf;
    private int idKategori;

    public Automjetet(int idAutomjet, int llojiIAutomjetit, String statusi, int idStaf, int idKategori) {
        this.idAutomjet = idAutomjet;
        this.llojiIAutomjetit = llojiIAutomjetit;
        this.statusi = statusi;
        this.idStaf = idStaf;
        this.idKategori = idKategori;
    }

    public int getIdAutomjet() {
        return idAutomjet;
    }

    public int getLlojiIAutomjetit() {
        return llojiIAutomjetit;
    }

    public String getStatusi() {
        return statusi;
    }

    public int getIdStaf() {
        return idStaf;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public static Automjetet getInstance(ResultSet result) throws SQLException{
        int idAutomjet = result.getInt("id_automjet");
        int llojiIAutomjetit = result.getInt("lloji_i_automjetit");
        String statusi = result.getString("statusi");
        int idStaf = result.getInt("id_staf");
        int idKategori = result.getInt("id_kategori");
        return new Automjetet(idAutomjet,llojiIAutomjetit,statusi,idStaf,idKategori);
    }
}
