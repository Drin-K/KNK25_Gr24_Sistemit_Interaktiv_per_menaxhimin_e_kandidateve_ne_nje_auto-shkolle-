package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stafi{
    private int id;
    private String emri;
    private String mbiemri;
    private String numriTelefonit;
    private String email;
    private String adresa;
    private String roli;
    private Stafi(int id, String emri, String mbiemri, String numriTelefonit, String email, String adresa, String roli){
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.numriTelefonit = numriTelefonit;
        this.email = email;
        this.adresa = adresa;
        this.roli = roli;
    }

    public int getId_staf() {
        return id;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getNumri_telefonit() {
        return numriTelefonit;
    }

    public void setId_staf(int id_staf) {
        this.id = id_staf;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public void setNumri_telefonit(String numri_telefonit) {
        this.numriTelefonit = numri_telefonit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setRoli(String roli) {
        this.roli = roli;
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
