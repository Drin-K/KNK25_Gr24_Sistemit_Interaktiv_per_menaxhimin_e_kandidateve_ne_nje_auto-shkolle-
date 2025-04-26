package models.Dto.testet;

import models.Enums.LlojiTestit;
import models.Enums.RezultatiTestit;

import java.time.LocalDate;

public class UpdateTestetDto {

    private int id;
    private LlojiTestit llojiTestit;
    private LocalDate dataTestit;
    private RezultatiTestit rezultati;
    private int piket;

    public UpdateTestetDto(int id, LlojiTestit llojiTestit, LocalDate dataTestit, RezultatiTestit rezultati, int piket) {
        this.id = id;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LlojiTestit getLlojiTestit() {
        return llojiTestit;
    }

    public void setLlojiTestit(LlojiTestit llojiTestit) {
        this.llojiTestit = llojiTestit;
    }

    public LocalDate getDataTestit() {
        return dataTestit;
    }

    public void setDataTestit(LocalDate dataTestit) {
        this.dataTestit = dataTestit;
    }

    public RezultatiTestit getRezultati() {
        return rezultati;
    }

    public void setRezultati(RezultatiTestit rezultati) {
        this.rezultati = rezultati;
    }

    public int getPiket() {
        return piket;
    }

    public void setPiket(int piket) {
        this.piket = piket;
    }
}
