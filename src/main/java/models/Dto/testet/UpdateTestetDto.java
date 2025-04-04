package models.Dto.testet;

public class UpdateTestetDto {
    private int id;
    private String llojiTestit;//
    private String dataTestit;//
    private String rezultati;//
    private int piket;

    public UpdateTestetDto(int idTest, String llojiTestit, String dataTestit, String rezultati, int piket) {
        this.id = idTest;
        this.llojiTestit = llojiTestit;
        this.dataTestit = dataTestit;
        this.rezultati = rezultati;
        this.piket = piket;
    }

    public int getIdTest() {
        return id;
    }

    public void setIdTest(int idTest) {
        this.id = idTest;
    }

    public String getLlojiTestit() {
        return llojiTestit;
    }

    public void setLlojiTestit(String llojiTestit) {
        this.llojiTestit = llojiTestit;
    }

    public String getDataTestit() {
        return dataTestit;
    }

    public void setDataTestit(String dataTestit) {
        this.dataTestit = dataTestit;
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
