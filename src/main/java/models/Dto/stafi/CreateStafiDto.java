package models.Dto.stafi;

public class CreateStafiDto {
    private String emri;
    private String mbiemri;
    private String numriTelefonit;
    private String email;
    private String adresa;
    private String roli;

    public CreateStafiDto(String emri, String mbiemri, String numriTelefonit, String email, String adresa, String roli) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.numriTelefonit = numriTelefonit;
        this.email = email;
        this.adresa = adresa;
        this.roli = roli;
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

    public String getEmail() {
        return email;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getRoli() {
        return roli;
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

}
