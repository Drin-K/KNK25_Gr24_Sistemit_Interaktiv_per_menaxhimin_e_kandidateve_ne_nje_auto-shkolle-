package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RaportiProgresit {
    private int id;
    private int idKandidat;
    private int idStaf;
    private LocalDate dataRaportit;
    private int piketTeorike;
    private int piketPraktike;
    private String komentet;
    private String performancaGjenerale;

    private RaportiProgresit(int id, int idKandidat, int idStaf, LocalDate dataRaportit, int piketTeorike, int piketPraktike, String komentet, String performancaGjenerale) {
        this.id = id;
        this.idKandidat = idKandidat;
        this.idStaf = idStaf;
        this.dataRaportit = dataRaportit;
        this.piketTeorike = piketTeorike;
        this.piketPraktike = piketPraktike;
        this.komentet = komentet;
        this.performancaGjenerale = performancaGjenerale;
    }

    public int getId() {
        return id;
    }

    public int getIdKandidat() {
        return idKandidat;
    }

    public int getIdStaf() {
        return idStaf;
    }

    public LocalDate getDataRaportit() {
        return dataRaportit;
    }

    public int getPiketTeorike() {
        return piketTeorike;
    }

    public int getPiketPraktike() {
        return piketPraktike;
    }

    public String getKomentet() {
        return komentet;
    }

    public String getPerformancaGjenerale() {
        return performancaGjenerale;
    }

    public static RaportiProgresit getInstance(ResultSet resultSet)throws SQLException {
        int id = resultSet.getInt("id_raport");
        int idKandidat = resultSet.getInt("id_kandidat");
        int idStaf = resultSet.getInt("id_staf");
        LocalDate dataRaportit = resultSet.getObject("data_raportit", LocalDate.class);
        int piketTeorike = resultSet.getInt("piket_teorike");
        int piketPraktike = resultSet.getInt("piket_praktike");
        String komentet = resultSet.getString("komentet");
        String performancaGjenerale = resultSet.getString("performanca_gjenerale");
        return new RaportiProgresit(id,idKandidat,idStaf,dataRaportit,piketTeorike,piketPraktike,komentet,performancaGjenerale);
    }
}
