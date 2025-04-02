package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Patenta {
    private int id_patente;
    private int id_kandidat;
    private int id_kategori;
    private String data_leshimit;
    private String statusi;

    private Patenta(int id_patente, int id_kandidat, int id_kategori, String data_leshimit, String statusi) {
        this.id_patente = id_patente;
        this.id_kandidat = id_kandidat;
        this.id_kategori = id_kategori;
        this.data_leshimit = data_leshimit;
        this.statusi = statusi;
    }

    public int getId_patente() {
        return id_patente;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public String getData_leshimit() {
        return data_leshimit;
    }

    public String getStatusi() {
        return statusi;
    }


//    private int id_patente;
//    private int id_kandidat;
//    private int id_kategori;
//    private String data_leshimit;
//    private String statusi;
    public static Patenta getInstance(ResultSet result)throws SQLException {
        int id_patente=result.getInt("id_patente");
        int id_kandidat=result.getInt("id_kandidat");
        int id_kategori=result.getInt("id_kategori");
        String data_leshimit =result.getString("data_leshimit");
        String  statusi=result.getString(" statusi");

        return new Patenta(id_patente, id_kandidat,id_kategori,data_leshimit,statusi);
    }
}
