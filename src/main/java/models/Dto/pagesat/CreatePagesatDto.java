package models.Dto.pagesat;

import java.time.LocalDate;

public class CreatePagesatDto {
    private int idKandidat;
    private String numriXhirollogarise;
    private double shuma;
    private LocalDate dataPageses;
    private String metodaPageses;
    private String statusiPageses;

    public CreatePagesatDto(int idKandidat, String numriXhirollogarise,double shuma, LocalDate dataPageses, String metodaPageses, String statusiPageses) {
        this.idKandidat = idKandidat;
        this.numriXhirollogarise= numriXhirollogarise;
        this.shuma = shuma;
        this.dataPageses = dataPageses;
        this.metodaPageses = metodaPageses;
        this.statusiPageses = statusiPageses;
    }

    public String getNumriXhirollogarise() {
        return numriXhirollogarise;
    }

    public void setNumriXhirollogarise(String numriXhirollogarise) {
        this.numriXhirollogarise = numriXhirollogarise;
    }

    public int getIdKandidat() {
        return idKandidat;
    }

    public void setIdKandidat(int idKandidat) {
        this.idKandidat = idKandidat;
    }

    public double getShuma() {
        return shuma;
    }

    public void setShuma(double shuma) {
        this.shuma = shuma;
    }

    public LocalDate getDataPageses() {
        return dataPageses;
    }

    public void setDataPageses(LocalDate dataPageses) {
        this.dataPageses = dataPageses;
    }

    public String getMetodaPageses() {
        return metodaPageses;
    }

    public void setMetodaPageses(String metodaPageses) {
        this.metodaPageses = metodaPageses;
    }

    public String getStatusiPageses() {
        return statusiPageses;
    }

    public void setStatusiPageses(String statusiPageses) {
        this.statusiPageses = statusiPageses;
    }
}
