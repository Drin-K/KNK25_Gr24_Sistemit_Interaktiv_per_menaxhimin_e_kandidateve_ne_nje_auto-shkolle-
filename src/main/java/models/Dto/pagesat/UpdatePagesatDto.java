package models.Dto.pagesat;

import java.time.LocalDate;

public class UpdatePagesatDto {
    private int id;
    private int idKandidat;
    private String numriXhirollogarise;
    private double shuma;
    private String metodaPageses;
    private String statusiPageses;

    public UpdatePagesatDto(int id, String numriXhirollogarise, int idKandidat, double shuma, String metodaPageses, String statusiPageses) {
        this.id = id;
        this.numriXhirollogarise = numriXhirollogarise;
        this.idKandidat = idKandidat;
        this.shuma = shuma;
        this.metodaPageses = metodaPageses;
        this.statusiPageses = statusiPageses;
    }

    public String getNumriXhirollogarise() {
        return numriXhirollogarise;
    }

    public void setNumriXhirollogarise(String numriXhirollogarise) {
        this.numriXhirollogarise = numriXhirollogarise;
    }

    public int getId() {
        return id;
    }

    public void setIdPagesat(int idPagesat) {
        this.id = idPagesat;
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
