package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Kandidatet {
    private int id;
    private String emri;
    private String mbiemri;
    private LocalDate datelindja;
    private String gjinia;
    private String numriTelefonit;
    private String email;
    private String adresa;
    private LocalDate dataRegjistrimit;
    private String statusiProcesit;

    private Kandidatet(int id, String emri, String mbiemri, LocalDate datelindja, String gjinia, String numriTelefonit, String email, String adresa, LocalDate dataRegjistrimit, String statusiProcesit) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.numriTelefonit = numriTelefonit;
        this.email = email;
        this.adresa = adresa;
        this.dataRegjistrimit = dataRegjistrimit;
        this.statusiProcesit = statusiProcesit;
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

    public LocalDate getDatelindja() {
        return datelindja;
    }

    public String getGjinia() {
        return gjinia;
    }

    public String getNumriTelefonit() {
        return numriTelefonit;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresa() {
        return adresa;
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

    public void setDatelindja(LocalDate datelindja) {
        this.datelindja = datelindja;
    }

    public void setGjinia(String gjinia) {
        this.gjinia = gjinia;
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

    public void setDataRegjistrimit(LocalDate dataRegjistrimit) {
        this.dataRegjistrimit = dataRegjistrimit;
    }

    public void setStatusiProcesit(String statusiProcesit) {
        this.statusiProcesit = statusiProcesit;
    }

    public LocalDate getDataRegjistrimit() {
        return dataRegjistrimit;
    }

    public String getStatusiProcesit() {
        return statusiProcesit;
    }

    public static Kandidatet getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id_kandidat");
        String emri = result.getString("emri");
        String mbiemri = result.getString("mbiemri");
        LocalDate datelindja = result.getObject("datelindja", LocalDate.class);
        String gjinia = result.getString("gjinia");
        String numriTelefonit = result.getString("numri_telefonit");
        String email = result.getString("email");
        String adresa = result.getString("adresa");
        LocalDate dataRegjistrimit = result.getObject("data_e_regjistrimit", LocalDate.class);
        String statusiProcesit = result.getString("statusi_i_procesit");
        return new Kandidatet(id, emri, mbiemri, datelindja, gjinia, numriTelefonit, email, adresa, dataRegjistrimit, statusiProcesit);
    }
}
