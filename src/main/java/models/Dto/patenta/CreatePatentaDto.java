package models.Dto.patenta;

public class CreatePatentaDto {
    private int idKandidat;
    private int idKategori;
    private String dataLeshimit;
    private String statusi;

    public CreatePatentaDto(int idKandidat, int idKategori, String dataLeshimit, String statusi) {
        this.idKandidat = idKandidat;
        this.idKategori = idKategori;
        this.dataLeshimit = dataLeshimit;
        this.statusi = statusi;
    }

    public int getIdKandidat() {
        return idKandidat;
    }

    public void setIdKandidat(int idKandidat) {
        this.idKandidat = idKandidat;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public String getDataLeshimit() {
        return dataLeshimit;
    }

    public void setDataLeshimit(String dataLeshimit) {
        this.dataLeshimit = dataLeshimit;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}

