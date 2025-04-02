package models.Dto.Testet;

public class UpdateTestetDto {
    private int id_test;
    private String lloji_i_testit;//
    private String data_e_testit;//
    private String rezultati;//
    private int piket;

    public UpdateTestetDto(int id_test, String lloji_i_testit, String data_e_testit, String rezultati, int piket) {
        this.id_test = id_test;
        this.lloji_i_testit = lloji_i_testit;
        this.data_e_testit = data_e_testit;
        this.rezultati = rezultati;
        this.piket = piket;
    }

    public int getId_test() {
        return id_test;
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
