package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KategoritePatentes {
    private int id;
    private String kategoria;
    private String pershkrimi;


    public int getId() {
        return id;
    }

    public String getKategoria() {
        return kategoria;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public static KategoritePatentes getInstance(ResultSet result) throws SQLException{
        int id = result.getInt("id_kategori");
        String kategoria = result.getString("kategoria");
        String pershkrimi = result.getString("pershkrimi");
        return new KategoritePatentes(id,kategoria,pershkrimi);
    }

    private KategoritePatentes(int id, String kategoria, String pershkrimi) {
        this.id = id;
        this.kategoria = kategoria;
        this.pershkrimi = pershkrimi;
    }

}
