package models.Dto.Stafi;

public class UpdateStafiDto {
    private String numri_telefonit;
    private String email;
    private String adresa;
    private String roli;

    public UpdateStafiDto(String numri_telefonit, String email, String adresa, String roli) {
        this.numri_telefonit = numri_telefonit;
        this.email = email;
        this.adresa = adresa;
        this.roli = roli;
    }

    public String getNumri_telefonit(){
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
