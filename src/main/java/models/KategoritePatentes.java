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

    public static KategoritePatentes getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String kategoria = result.getString("Kategoria");
        String pershkrimi = result.getString("Pershkrimi");
        return new KategoritePatentes(id, kategoria, pershkrimi);
    }

    private KategoritePatentes(int id, String kategoria, String pershkrimi) {
        this.id = id;
        this.kategoria = kategoria;
        this.pershkrimi = pershkrimi;
    }

}
