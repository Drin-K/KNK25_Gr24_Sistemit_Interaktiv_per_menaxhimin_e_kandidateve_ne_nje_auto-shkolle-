package models;

import models.Enums.LlojiTestit;
import models.Enums.RezultatiTestit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Testet {
    private int id;
    private int idKandidat;
    private int idStaf;
    private LlojiTestit llojiTestit;
    private LocalDate dataTestit;
    private RezultatiTestit rezultati;
    private int piket;

    private Testet(int id, int idKandidat, int idStaf, LlojiTestit llojiTestit, LocalDate dataTestit, RezultatiTestit rezultati, int piket) {
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

    public LlojiTestit getLlojiTestit() {
        return llojiTestit;
    }

    public LocalDate getDataTestit() {
        return dataTestit;
    }

    public RezultatiTestit getRezultati() {
        return rezultati;
    }

    public int getPiket() {
        return piket;
    }

    public static Testet getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id_test");
        int idKandidat = result.getInt("id_kandidat");
        int idStaf = result.getInt("id_staf");

        String llojiTestitString = result.getString("lloji_i_testit");
        LlojiTestit llojiTestit = LlojiTestit.valueOf(llojiTestitString);

        LocalDate dataTestit = result.getObject("data_e_testit", LocalDate.class);

        String rezultatiString = result.getString("rezultati");
        RezultatiTestit rezultati = RezultatiTestit.valueOf(rezultatiString);

        int piket = result.getInt("piket");

        return new Testet(id, idKandidat, idStaf, llojiTestit, dataTestit, rezultati, piket);
    }
}
