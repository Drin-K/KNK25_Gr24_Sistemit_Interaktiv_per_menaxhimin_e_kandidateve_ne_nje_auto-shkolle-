package models.Dto.Regjistrimet;

public class CreateRegjistrimetDto {
    private int id_regjistrim;
    private int id_kandidat;
    private int id_kategori;
    private String statusi;

    public CreateRegjistrimetDto(int id_regjistrim, int id_kandidat, int id_kategori, String statusi) {
        this.id_regjistrim = id_regjistrim;
        this.id_kandidat = id_kandidat;
        this.id_kategori = id_kategori;
        this.statusi = statusi;
    }

    public int getId_regjistrim() {
        return id_regjistrim;
    }

    public void setId_regjistrim(int id_regjistrim) {
        this.id_regjistrim = id_regjistrim;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public void setId_kandidat(int id_kandidat) {
        this.id_kandidat = id_kandidat;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

}
