package models.Dto.testet;

import models.Enums.LlojiTestit;
import models.Enums.RezultatiTestit;

import java.time.LocalDate;

public class CreateTestetDto {

    private int idKandidat;
    private int idStaf;
    private LlojiTestit llojiTestit; // ENUM në vend të String
    private LocalDate dataTestit;
    private RezultatiTestit rezultati; // ENUM në vend të String
    private int piket;

    public CreateTestetDto(int idKandidat, int idStaf, LlojiTestit llojiTestit, LocalDate dataTestit, RezultatiTestit rezultati, int piket) {
        this.idKandidat = idKandidat;
        this.idStaf = idStaf;
        this.llojiTestit = llojiTestit;
        this.dataTestit = dataTestit;
        this.rezultati = rezultati;
        this.piket = piket;
    }

    public int getIdKandidat() {
        return idKandidat;
    }

    public void setIdKandidat(int idKandidat) {
        this.idKandidat = idKandidat;
    }

    public int getIdStaf() {
        return idStaf;
    }

    public void setIdStaf(int idStaf) {
        this.idStaf = idStaf;
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
