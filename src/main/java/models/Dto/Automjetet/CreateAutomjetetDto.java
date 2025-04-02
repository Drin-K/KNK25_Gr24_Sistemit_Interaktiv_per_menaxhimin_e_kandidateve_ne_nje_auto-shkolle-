package models.Dto.Automjetet;

public class CreateAutomjetetDto {
    private String lloji_i_automjetit;
    private String statusi;
    private int id_staf;
    private int id_kategori;

    public CreateAutomjetetDto(String lloji_i_automjetit, String statusi, int id_staf, int id_kategori) {
        this.lloji_i_automjetit = lloji_i_automjetit;
        this.statusi = statusi;
        this.id_staf = id_staf;
        this.id_kategori = id_kategori;
    }

    public String getLloji_i_automjetit() {
        return lloji_i_automjetit;
    }

    public void setLloji_i_automjetit(String lloji_i_automjetit) {
        this.lloji_i_automjetit = lloji_i_automjetit;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public int getId_staf() {
        return id_staf;
    }

    public void setId_staf(int id_staf) {
        this.id_staf = id_staf;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
    }
}
