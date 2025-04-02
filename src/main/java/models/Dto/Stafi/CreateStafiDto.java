package models.Dto.Stafi;

public class CreateStafiDto{
    private String emri;
    private String mbiemri;
    private String numri_telefonit;
    private String email;
    private String adresa;
    private String roli;

    public CreateStafiDto(String emri, String mbiemri, String numri_telefonit, String email, String adresa, String roli) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.numri_telefonit = numri_telefonit;
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

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
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

    public void setRoli(String roli) {
        this.roli = roli;
    }

}
