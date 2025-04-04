package models.Dto.regjistrimet;

public class CreateRegjistrimetDto {

    private int idKandidat;
    private int idKategori;
    private String statusi;

    public CreateRegjistrimetDto(int idKandidat, int idKategori, String statusi) {
        this.idKandidat = idKandidat;
        this.idKategori = idKategori;
        this.statusi = statusi;
    }

    public int getIdKandidat() {
        return idKandidat;
    }

    public void setIdKandidat(int idKandidat) {
        this.idKandidat = idKandidat;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }
}
