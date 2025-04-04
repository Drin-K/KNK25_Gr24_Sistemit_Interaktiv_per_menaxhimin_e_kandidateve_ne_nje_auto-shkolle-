package models.Dto.kandidatet;

public class UpdateKandidatetDto{
    private int id_kandidat;
    private String numri_telefonit;
    private String email;
    private String adresa;
    private String statusi_i_procesit;

    public UpdateKandidatetDto(int id_kandidatet,String numri_telefonit, String email, String adresa, String statusi_i_procesit) {
       this.id_kandidat=id_kandidatet;
        this.numri_telefonit = numri_telefonit;
        this.email = email;
        this.adresa = adresa;
        this.statusi_i_procesit = statusi_i_procesit;
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

    public String getStatusi_i_procesit() {
        return statusi_i_procesit;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public void setId_kandidat(int id_kandidat) {
        this.id_kandidat = id_kandidat;
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

    public void setStatusi_i_procesit(String statusi_i_procesit) {
        this.statusi_i_procesit = statusi_i_procesit;
    }
}

