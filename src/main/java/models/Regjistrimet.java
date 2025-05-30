package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Regjistrimet {
    private int id;
    private int idkandidat;
    private int idKategori;
    private String statusi;

    private Regjistrimet(String statusi, int idKategori, int idkandidat, int id) {
        this.statusi = statusi;
        this.idKategori = idKategori;
        this.idkandidat = idkandidat;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getIdkandidat() {
        return idkandidat;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public String getStatusi() {
        return statusi;
    }


    public static Regjistrimet getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int idKandidat = result.getInt("ID_Kandidat");
        int idKategori = result.getInt("ID_Kategori");
        String statusi = result.getString("Statusi");

        return new Regjistrimet(statusi, idKandidat, idKategori, id);
    }
}
