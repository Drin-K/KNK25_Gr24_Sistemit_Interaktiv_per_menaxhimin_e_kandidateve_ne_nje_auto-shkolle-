package models.Dto.kategorite_patentes;

public class UpdateKategoritePatentesDto {
    private int id;
    private String pershkrimi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }
}
