package models.Dto.testet;

import java.time.LocalDate;

public class UpdateTestetDto {

    private int id;
    private String llojiTestit;
    private LocalDate dataTestit;
    private String rezultati;
    private int piket;

    public UpdateTestetDto(int idTest, String llojiTestit, LocalDate dataTestit, String rezultati, int piket) {
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

    public LocalDate getDataTestit() {
        return dataTestit;
    }

    public void setDataTestit(LocalDate dataTestit) {
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
