package models.Dto.testet;

import java.time.LocalDate;

public class CreateTestetDto {

    private int idKandidat;
    private int idStaf;
    private String llojiTestit;//
    private LocalDate dataTestit;//
    private String rezultati;//
    private int piket;

    public CreateTestetDto(int idKandidat, int idStaf, String llojiTestit, LocalDate dataTestit, String rezultati, int piket) {
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
