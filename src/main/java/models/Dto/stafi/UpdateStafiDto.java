package models.Dto.stafi;

public class UpdateStafiDto {
    private int id;
    private String numriTelefonit;
    private String email;
    private String adresa;
    private String roli;

    public UpdateStafiDto(String numriTelefonit, String email, String adresa, String roli) {
        this.numriTelefonit = numriTelefonit;
        this.email = email;
        this.adresa = adresa;
        this.roli = roli;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
