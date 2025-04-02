package models.Dto.Automjetet;

public class UpdateAutomjetetDto {
    private int id_automjet;
    private String statusi;
    private int id_staf;

    public int getId_automjet() {
        return id_automjet;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setId_automjet(int id_automjet) {
        this.id_automjet = id_automjet;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public void setId_staf(int id_staf) {
        this.id_staf = id_staf;
    }

    public int getId_staf() {
        return id_staf;
    }
}
