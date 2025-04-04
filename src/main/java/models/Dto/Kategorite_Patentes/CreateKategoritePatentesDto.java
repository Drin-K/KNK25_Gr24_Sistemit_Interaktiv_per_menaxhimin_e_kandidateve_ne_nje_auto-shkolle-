package models.Dto.Kategorite_Patentes;

public class CreateKategoritePatentesDto {
    private String kategoria;
    private String pershkrimi;

    public CreateKategoritePatentesDto(String kategoria, String pershkrimi) {
        this.kategoria = kategoria;
        this.pershkrimi = pershkrimi;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }
}
