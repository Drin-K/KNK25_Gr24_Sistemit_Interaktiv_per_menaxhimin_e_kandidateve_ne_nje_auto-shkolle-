package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Testet {
    private int id;
    private int idKandidat;
    private int idStaf;
    private String llojiTestit;
    private LocalDate dataTestit;
    private String rezultati;
    private int piket;

    private Testet(int id, int idKandidat, int idStaf, String llojiTestit, LocalDate dataTestit, String rezultati, int piket) {
        this.id = id;
        this.idKandidat = idKandidat;
        this.idStaf = idStaf;
        this.llojiTestit = llojiTestit;
        this.dataTestit = dataTestit;
        this.rezultati = rezultati;
        this.piket = piket;
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

    public String getLlojiTestit() {
        return llojiTestit;
    }

    public LocalDate getDataTestit() {
        return dataTestit;
    }

    public String getRezultati() {
        return rezultati;
    }

    public int getPiket() {
        return piket;
    }

    public static Testet getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int idKandidat = result.getInt("ID_Kandidat");
        int idStaf = result.getInt("ID_Staf");
        String llojiTestit = result.getString("Lloji_i_Testit");
        LocalDate dataTestit = result.getObject("Data_e_testit", LocalDate.class);
        String rezultati = result.getString("Rezultati");
        int piket = result.getInt("Piket");
        return new Testet(id, idKandidat, idStaf, llojiTestit, dataTestit, rezultati, piket);
    }
}
