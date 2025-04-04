package models.Dto.kandidatet;

public class UpdateKandidatetDto{
    private int id;
    private String numriTelefonit;
    private String email;
    private String adresa;
    private String statusiProcesit;

    public UpdateKandidatetDto(int id,String numriTelefonit, String email, String adresa, String statusiProcesit) {
       this.id=id;
        this.numriTelefonit = numriTelefonit;
        this.email = email;
        this.adresa = adresa;
        this.statusiProcesit = statusiProcesit;
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

    public String getStatusiProcesit() {
        return statusiProcesit;
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

    public void setStatusiProcesit(String statusiProcesit) {
        this.statusiProcesit = statusiProcesit;
    }
}

