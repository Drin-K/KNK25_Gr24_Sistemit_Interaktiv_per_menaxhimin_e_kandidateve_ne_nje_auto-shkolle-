package models.Dto.regjistrimet;

public class UpdateRegjistrimetDto {
    private int id;
    private int idKategori;
    private String statusi;

    public UpdateRegjistrimetDto(int idRegjistrim, int idKategori, String statusi) {
        this.id = idRegjistrim;
        this.idKategori = idKategori;
        this.statusi = statusi;
    }

    public int getIdRegjistrim() {
        return id;
    }

    public void setIdRegjistrim(int idRegjistrim) {
        this.id = idRegjistrim;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}
