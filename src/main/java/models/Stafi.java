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

    public int getId() {
        return id;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getNumriTelefonit() {
        return numriTelefonit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public void setNumriTelefonit(String numriTelefonit) {
        this.numriTelefonit = numriTelefonit;
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
        int id=result.getInt("id_staf");
        String emri=result.getString("emri");
        String mbiemri=result.getString("mbiemri");
        String numriTelefonit=result.getString("numri_telefonit");
        String email=result.getString("email");
        String adresa=result.getString("adresa");
        String roli=result.getString("roli");
        return new Stafi(id,emri,mbiemri,numriTelefonit,email,adresa,roli);
    }
}
