package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class Testet {
    private int id;
    private int idkandidat;
    private int idStaf;
    private String llojiTestit;//
    private LocalDate dataTestit;//
    private String rezultati;//
    private int piket;

    private Testet(int id, int idkandidat, int idStaf, String llojiTestit, LocalDate dataTestit, String rezultati, int piket) {
        this.id = id;
        this.idkandidat = idkandidat;
        this.idStaf = idStaf;
        this.llojiTestit = llojiTestit;
        this.dataTestit = dataTestit;
        this.rezultati = rezultati;
        this.piket = piket;
    }

    public int getPiket() {
        return piket;
    }

    public String getRezultati() {
        return rezultati;
    }

    public LocalDate getDataTestit() {
        return dataTestit;
    }

    public String getLlojiTestit() {
        return llojiTestit;
    }

    public int getIdStaf() {
        return idStaf;
    }

    public int getIdkandidat() {
        return idkandidat;
    }

    public int getId() {
        return id;
    }

//    private int id;
//    private int idkandidat;
//    private int idStaf;
//    private String llojiTestit;//
//    private LocalDate dataTestit;//
//    private String rezultati;//
//    private int piket;


    public static Testet getInstance(ResultSet result)throws SQLException {
        int id=result.getInt("id_test");
        int idKandidat=result.getInt("id_kandidat");
        int idStaf=result.getInt("id_staf");
        String llojiTestit =result.getString("lloji_i_testit");
        LocalDate dataTestit=result.getObject("data_e_testit", LocalDate.class);
        String rezultati=result.getString("datelindja");
        int piket =result.getInt("piket");
        return new Testet(id,idKandidat,idStaf,llojiTestit,dataTestit,rezultati,piket);
    }

}