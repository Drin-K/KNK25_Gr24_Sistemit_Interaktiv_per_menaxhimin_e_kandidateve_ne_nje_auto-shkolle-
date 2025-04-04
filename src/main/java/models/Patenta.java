package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Patenta {
    private int id;
    private int idKandidat;
    private int idKategori;
    private LocalDate dataLeshimit;
    private String statusi;

    private Patenta(int id, int idKandidat, int idKategori, LocalDate dataLeshimit, String statusi) {
        this.id = id;
        this.idKandidat = idKandidat;
        this.idKategori = idKategori;
        this.dataLeshimit = dataLeshimit;
        this.statusi = statusi;
    }
    public int getId() {
        return id;
    }

    public int getIdKandidat() {
        return idKandidat;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public LocalDate getDataLeshimit() {
        return dataLeshimit;
    }

    public String getStatusi() {
        return statusi;
    }
    public static Patenta getInstance(ResultSet result)throws SQLException {
        int id=result.getInt("id_patente");
        int idKandidat=result.getInt("id_kandidat");
        int idKategori=result.getInt("id_kategori");
        LocalDate dataLeshimit =result.getObject("data_leshimit", LocalDate.class);
        String  statusi=result.getString(" statusi");

        return new Patenta(id,idKandidat,idKategori,dataLeshimit,statusi);
    }
}
