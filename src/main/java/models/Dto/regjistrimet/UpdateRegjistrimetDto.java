package models.Dto.regjistrimet;

public class UpdateRegjistrimetDto {
    private int id_regjistrim;
    private int id_kategori;
    private String statusi;

    public UpdateRegjistrimetDto(int id_regjistrim, int id_kategori, String statusi) {
        this.id_regjistrim = id_regjistrim;
        this.id_kategori = id_kategori;
        this.statusi = statusi;
    }

    public int getId_regjistrim() {
        return id_regjistrim;
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
