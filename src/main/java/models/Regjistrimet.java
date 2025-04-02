package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Regjistrimet {
    private int id_regjistrim;
    private int id_kandidat;
    private int id_kategori;
    private String statusi;

   private Regjistrimet(int id_regjistrim, int id_kandidat, int id_kategori, String statusi) {
        this.id_regjistrim = id_regjistrim;
        this.id_kandidat = id_kandidat;
        this.id_kategori = id_kategori;
        this.statusi = statusi;
    }

    public int getId_regjistrim() {
        return id_regjistrim;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public String getStatusi() {
        return statusi;
    }

//    private int id_regjistrim;
//    private int id_kandidat;
//    private int id_kategori;
//    private String statusi;

    public static Regjistrimet getInstance(ResultSet result)throws SQLException {
        int id_regjistrim=result.getInt("id_regjistrim");
        int id_kandidat=result.getInt("id_kandidat");
        int id_kategori=result.getInt("id_kategori");
        String statusi=result.getString("statusi");

        return new Regjistrimet(id_regjistrim, id_kandidat, id_kategori, statusi);
    }
}
