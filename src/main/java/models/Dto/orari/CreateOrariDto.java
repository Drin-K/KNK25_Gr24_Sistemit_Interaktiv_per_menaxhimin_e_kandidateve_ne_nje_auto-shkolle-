package models.Dto.orari;

public class CreateOrariDto {
    private int id_kandidat;
    private int id_staf;
    private String data_sesionit;
    private String ora_e_fillimit;
    private String ora_e_perfundimit;
    private String lloji_i_mesimit;
    private String statusi;
    private int id_automjet;

    public CreateOrariDto(int id_kandidat, int id_staf, String data_sesionit, String ora_e_fillimit, String ora_e_perfundimit, String lloji_i_mesimit, String statusi, int id_automjet) {
        this.id_kandidat = id_kandidat;
        this.id_staf = id_staf;
        this.data_sesionit = data_sesionit;
        this.ora_e_fillimit = ora_e_fillimit;
        this.ora_e_perfundimit = ora_e_perfundimit;
        this.lloji_i_mesimit = lloji_i_mesimit;
        this.statusi = statusi;
        this.id_automjet = id_automjet;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public void setId_kandidat(int id_kandidat) {
        this.id_kandidat = id_kandidat;
    }

    public int getId_staf() {
        return id_staf;
    }

    public void setId_staf(int id_staf) {
        this.id_staf = id_staf;
    }

    public String getData_sesionit() {
        return data_sesionit;
    }

    public void setData_sesionit(String data_sesionit) {
        this.data_sesionit = data_sesionit;
    }

    public String getOra_e_fillimit() {
        return ora_e_fillimit;
    }

    public void setOra_e_fillimit(String ora_e_fillimit) {
        this.ora_e_fillimit = ora_e_fillimit;
    }

    public String getOra_e_perfundimit() {
        return ora_e_perfundimit;
    }

    public void setOra_e_perfundimit(String ora_e_perfundimit) {
        this.ora_e_perfundimit = ora_e_perfundimit;
    }

    public String getLloji_i_mesimit() {
        return lloji_i_mesimit;
    }

    public void setLloji_i_mesimit(String lloji_i_mesimit) {
        this.lloji_i_mesimit = lloji_i_mesimit;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public int getId_automjet() {
        return id_automjet;
    }

    public void setId_automjet(int id_automjet) {
        this.id_automjet = id_automjet;
    }
}
