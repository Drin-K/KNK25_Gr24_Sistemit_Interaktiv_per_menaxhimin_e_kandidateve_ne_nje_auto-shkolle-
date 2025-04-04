package models.Dto.testet;

public class CreateTestetDto {

    private int id_kandidat;
    private int id_staf;
    private String lloji_i_testit;//
    private String data_e_testit;//
    private String rezultati;//
    private int piket;


    public CreateTestetDto(int id_test, int id_kandidat, int id_staf, String lloji_i_testit, String data_e_testit, String rezultati, int piket) {

        this.id_kandidat = id_kandidat;
        this.id_staf = id_staf;
        this.lloji_i_testit = lloji_i_testit;
        this.data_e_testit = data_e_testit;
        this.rezultati = rezultati;
        this.piket = piket;
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

    public String getLloji_i_testit() {
        return lloji_i_testit;
    }

    public void setLloji_i_testit(String lloji_i_testit) {
        this.lloji_i_testit = lloji_i_testit;
    }

    public String getData_e_testit() {
        return data_e_testit;
    }

    public void setData_e_testit(String data_e_testit) {
        this.data_e_testit = data_e_testit;
    }

    public String getRezultati() {
        return rezultati;
    }

    public void setRezultati(String rezultati) {
        this.rezultati = rezultati;
    }

    public int getPiket() {
        return piket;
    }

    public void setPiket(int piket) {
        this.piket = piket;
    }

}
