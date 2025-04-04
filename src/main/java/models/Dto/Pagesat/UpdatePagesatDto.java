package models.Dto.Pagesat;

import java.time.LocalDate;

public class UpdatePagesatDto {
    private int idPagesat;
    private int idKandidat;
    private double shuma;
    private LocalDate dataPageses;
    private String metodaPageses;
    private String statusiPageses;

    public UpdatePagesatDto(int idPagesat, int idKandidat, double shuma, LocalDate dataPageses, String metodaPageses, String statusiPageses) {
        this.idPagesat = idPagesat;
        this.idKandidat = idKandidat;
        this.shuma = shuma;
        this.dataPageses = dataPageses;
        this.metodaPageses = metodaPageses;
        this.statusiPageses = statusiPageses;
    }

    public int getIdPagesat() {
        return idPagesat;
    }

    public void setIdPagesat(int idPagesat) {
        this.idPagesat = idPagesat;
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
