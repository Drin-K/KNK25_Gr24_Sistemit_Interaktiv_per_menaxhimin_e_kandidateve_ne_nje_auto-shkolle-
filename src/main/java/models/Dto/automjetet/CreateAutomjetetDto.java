package models.Dto.automjetet;

public class CreateAutomjetetDto {
    private String llojiAutomjetit;
    private String statusi;
    private int idStaf;
    private int idKategori;

    public CreateAutomjetetDto(String llojiAutomjetit, String statusi, int idStaf, int idKategori) {
        this.llojiAutomjetit = llojiAutomjetit;
        this.statusi = statusi;
        this.idStaf = idStaf;
        this.idKategori = idKategori;
    }

    public void setLlojiAutomjetit(String llojiAutomjetit) {
        this.llojiAutomjetit = llojiAutomjetit;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public void setIdStaf(int idStaf) {
        this.idStaf = idStaf;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public String getLlojiAutomjetit() {
        return llojiAutomjetit;
    }

    public String getStatusi() {
        return statusi;
    }

    public int getIdStaf() {
        return idStaf;
    }

    public int getIdKategori() {
        return idKategori;
    }
}
