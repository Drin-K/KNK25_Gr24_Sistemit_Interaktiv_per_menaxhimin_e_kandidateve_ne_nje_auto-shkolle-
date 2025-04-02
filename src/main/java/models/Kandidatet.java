package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Kandidatet{
    private int id_kandidat;
    private String emri;
    private String mbiemri;
    private String datelindja;
    private String gjinia;
    private String numri_telefonit;
    private String email;
    private String adresa;
    private String data_e_regjistrimit;
    private String statusi_i_procesit;

    private Kandidatet(int id_kandidat, String emri, String mbiemri, String datelindja, String gjinia, String numri_telefonit, String email, String adresa, String data_e_regjistrimit, String statusi_i_procesit) {
        this.id_kandidat = id_kandidat;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.numri_telefonit = numri_telefonit;
        this.email = email;
        this.adresa = adresa;
        this.data_e_regjistrimit = data_e_regjistrimit;
        this.statusi_i_procesit = statusi_i_procesit;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getDatelindja() {
        return datelindja;
    }

    public String getGjinia() {
        return gjinia;
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

    public void setId_kandidat(int id_kandidat) {
        this.id_kandidat = id_kandidat;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public void setDatelindja(String datelindja) {
        this.datelindja = datelindja;
    }

    public void setGjinia(String gjinia) {
        this.gjinia = gjinia;
    }

    public void setNumri_telefonit(String numri_telefonit) {
        this.numri_telefonit = numri_telefonit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setData_e_regjistrimit(String data_e_regjistrimit) {
        this.data_e_regjistrimit = data_e_regjistrimit;
    }

    public void setStatusi_i_procesit(String statusi_i_procesit) {
        this.statusi_i_procesit = statusi_i_procesit;
    }

    public String getData_e_regjistrimit() {
        return data_e_regjistrimit;
    }

    public String getStatusi_i_procesit() {
        return statusi_i_procesit;
    }

    public static Kandidatet getInstance(ResultSet result)throws SQLException {
        int id_kandidat=result.getInt("id_kandidat");
        String emri=result.getString("emri");
        String mbiemri=result.getString("mbiemri");
        String datelindja=result.getString("datelindja");
        String gjinia=result.getString("gjinia");
        String numri_telefonit=result.getString("numri_telefonit");
        String email=result.getString("email");
        String adresa=result.getString("adresa");
        String data_e_regjistrimit=result.getString("data_e_regjistrimit");
        String statusi_i_procesit=result.getString("statusi_i_procesit");
        return new Kandidatet(id_kandidat,emri,mbiemri,datelindja,gjinia,numri_telefonit,email,adresa,data_e_regjistrimit,statusi_i_procesit);
    }
}
