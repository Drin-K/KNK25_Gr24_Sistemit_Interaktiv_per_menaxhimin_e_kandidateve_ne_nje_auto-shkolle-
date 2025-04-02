package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stafi{
    private int id_staf;
    private String emri;
    private String mbiemri;
    private String numri_telefonit;
    private String email;
    private String adresa;
    private String roli;
    private Stafi(int id_staf, String emri, String mbiemri, String numri_telefonit, String email, String adresa, String roli){
        this.id_staf = id_staf;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.numri_telefonit = numri_telefonit;
        this.email = email;
        this.adresa = adresa;
        this.roli = roli;
    }

    public int getId_staf() {
        return id_staf;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getNumri_telefonit() {
        return numri_telefonit;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getRoli() {
        return roli;
    }
    public static Stafi getInstance(ResultSet result)throws SQLException {
        int id_staf=result.getInt("id_staf");
        String emri=result.getString("emri");
        String mbiemri=result.getString("mbiemri");
        String numri_telefonit=result.getString("numri_telefonit");
        String email=result.getString("email");
        String adresa=result.getString("adresa");
        String roli=result.getString("roli");
        return new Stafi(id_staf,emri,mbiemri,numri_telefonit,email,adresa,roli);
    }
}
