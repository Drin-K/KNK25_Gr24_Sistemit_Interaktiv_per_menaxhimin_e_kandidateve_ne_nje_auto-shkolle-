package models.Dto.automjetet;

public class UpdateAutomjetetDto {
    private int id;
    private String statusi;
    private int idStaf;

    public UpdateAutomjetetDto(int id, String statusi, int idStaf) {
        this.id = id;
        this.statusi = statusi;
        this.idStaf = idStaf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public int getIdStaf() {
        return idStaf;
    }

    public void setIdStaf(int idStaf) {
        this.idStaf = idStaf;
    }
}
