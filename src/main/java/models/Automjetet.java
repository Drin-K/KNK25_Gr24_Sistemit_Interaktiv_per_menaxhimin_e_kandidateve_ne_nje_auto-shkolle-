package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Automjetet {
    private int id_automjet;
    private int lloji_i_automjetit;
    private String statusi;
    private int id_staf;
    private int id_kategori;

    private Automjetet(int id_automjet, int lloji_i_automjetit, String statusi, int id_staf, int id_kategori) {
        this.id_automjet = id_automjet;
        this.lloji_i_automjetit = lloji_i_automjetit;
        this.statusi = statusi;
        this.id_staf = id_staf;
        this.id_kategori = id_kategori;
    }

    public int getId_automjet() {
        return id_automjet;
    }

    public int getLloji_i_automjetit() {
        return lloji_i_automjetit;
    }

    public String getStatusi() {
        return statusi;
    }

    public int getId_staf() {
        return id_staf;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public static Automjetet getInstance(ResultSet result) throws SQLException{
        int id_automjet = result.getInt("id_automjet");
        int lloji_i_automjetit = result.getInt("lloji_i_automjetit");
        String statusi = result.getString("statusi");
        int id_staf = result.getInt("id_staf");
        int id_kategori = result.getInt("id_kategori");
        return new Automjetet(id_automjet,lloji_i_automjetit,statusi,id_staf,id_kategori);
    }
}
