package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Kategorite_Patentes {
    private int id_kategori;
    private String kategoria;
    private String pershkrimi;

    private Kategorite_Patentes(int id_kategori, String kategoria, String pershkrimi) {
        this.id_kategori = id_kategori;
        this.kategoria = kategoria;
        this.pershkrimi = pershkrimi;
    }
    public static Kategorite_Patentes getInstance(ResultSet result) throws SQLException{
        int id_kategori = result.getInt("id_kategori");
        String kategoria = result.getString("kategoria");
        String pershkrimi = result.getString("pershkrimi");
        return new Kategorite_Patentes(id_kategori,kategoria,pershkrimi);
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public String getKategoria() {
        return kategoria;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }
}
