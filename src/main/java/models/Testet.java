package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Testet {
    private int id_test;
    private int id_kandidat;
    private int id_staf;
    private String lloji_i_testit;//
    private String data_e_testit;//
    private String rezultati;//
    private int piket;


    public Testet(int id_test, int id_kandidat, int id_staf, String lloji_i_testit, String data_e_testit, String rezultati, int piket) {
        this.id_test = id_test;
        this.id_kandidat = id_kandidat;
        this.id_staf = id_staf;
        this.lloji_i_testit = lloji_i_testit;
        this.data_e_testit = data_e_testit;
        this.rezultati = rezultati;
        this.piket = piket;
    }

    public int getId_test() {
        return id_test;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public int getId_staf() {
        return id_staf;
    }

    public String getLloji_i_testit() {
        return lloji_i_testit;
    }

    public String getData_e_testit() {
        return data_e_testit;
    }

    public String getRezultati() {
        return rezultati;
    }

    public int getPiket() {
        return piket;
    }

    //    private int id_test;
//    private int id_kandidat;
//    private int id_staf;
//    private String lloji_i_testit;//
//    private String data_e_testit;//
//    private String rezultati;//
//    private int piket;

    public static Testet getInstance(ResultSet result)throws SQLException {
        int id_test=result.getInt("id_test");
        int id_kandidat=result.getInt("id_kandidat");
        int id_staf=result.getInt("id_staf");
        String lloji_i_testit =result.getString("lloji_i_testit");
        String data_e_testit=result.getString("data_e_testit");
        String rezultati=result.getString("datelindja");
        int piket =result.getInt("piket");
        return new Testet(id_test,id_kandidat,id_staf,lloji_i_testit,data_e_testit,rezultati,piket);
    }

}