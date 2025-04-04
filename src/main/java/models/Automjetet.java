package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Automjetet {
    private int id;
    private int llojiAutomjetit;
    private String statusi;
    private int idStaf;
    private int idKategori;

    private Automjetet(int id, int llojiAutomjetit, String statusi, int idStaf, int idKategori) {
        this.id = id;
        this.llojiAutomjetit = llojiAutomjetit;
        this.statusi = statusi;
        this.idStaf = idStaf;
        this.idKategori = idKategori;
    }

    public int getId() {
        return id;
    }

    public int getLlojiAutomjetit() {
        return llojiAutomjetit;
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
        int llojiAutomjetit = result.getInt("lloji_i_automjetit");
        String statusi = result.getString("statusi");
        int idStaf = result.getInt("id_staf");
        int idKategori = result.getInt("id_kategori");
        return new Automjetet(idAutomjet,llojiAutomjetit,statusi,idStaf,idKategori);
    }
}
