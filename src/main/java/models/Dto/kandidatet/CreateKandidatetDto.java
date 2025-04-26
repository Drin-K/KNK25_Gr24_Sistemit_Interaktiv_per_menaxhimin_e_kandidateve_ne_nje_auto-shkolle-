package models.Dto.kandidatet;

import java.time.LocalDate;

public class CreateKandidatetDto {
    private String emri;
    private String mbiemri;
    private LocalDate datelindja;
    private String gjinia;
    private String numriTelefonit;
    private String email;
    private String adresa;
    private LocalDate dataRegjistrimit;
    private String statusiProcesit;

    public CreateKandidatetDto(String emri, String mbiemri, LocalDate datelindja, String gjinia, String numriTelefonit, String email, String adresa, LocalDate dataRegjistrimit, String statusiProcesit) {
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

    public LocalDate getDataRegjistrimit() {
        return dataRegjistrimit;
    }

    public String getStatusiProcesit() {
        return statusiProcesit;
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
}
