package models.Dto.regjistrimet;

public class UpdateRegjistrimetDto {
    private int id;
    private String statusi;

    public UpdateRegjistrimetDto(int idRegjistrim, String statusi) {
        this.id = idRegjistrim;
        this.statusi = statusi;
    }

    public int getIdRegjistrim() {
        return id;
    }

    public void setIdRegjistrim(int idRegjistrim) {
        this.id = idRegjistrim;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}
