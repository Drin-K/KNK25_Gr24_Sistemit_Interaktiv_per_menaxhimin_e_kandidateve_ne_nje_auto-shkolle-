package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Automjetet {
    private int id;
    private String llojiAutomjetit;
    private String statusi;
    private int idStaf;
    private int idKategori;

    private Automjetet(int id, String llojiAutomjetit, String statusi, int idStaf, int idKategori) {
        this.id = id;
        this.llojiAutomjetit = llojiAutomjetit;
        this.statusi = statusi;
        this.idStaf = idStaf;
        this.idKategori = idKategori;
    }

    public int getId() {
        return id;
    }

    public String getLlojiAutomjetit() {
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

    public static Automjetet getInstance(ResultSet result) throws SQLException {
        int idAutomjet = result.getInt("id");
        String llojiAutomjetit = result.getString("Lloji_i_Automjetit");
        String statusi = result.getString("Statusi");
        int idStaf = result.getInt("ID_Staf");
        int idKategori = result.getInt("ID_Kategori");
        return new Automjetet(idAutomjet, llojiAutomjetit, statusi, idStaf, idKategori);
    }
}
