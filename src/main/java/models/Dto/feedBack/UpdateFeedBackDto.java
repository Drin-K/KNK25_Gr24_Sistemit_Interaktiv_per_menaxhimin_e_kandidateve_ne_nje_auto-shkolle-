package models.Dto.feedBack;

import java.time.LocalDate;

public class UpdateFeedBackDto {
    private int id;
    private  int idKandidat;
    private int idStaf;
    private LocalDate dataFeedback;
    private int vlersimi;
    private String koment;

    public UpdateFeedBackDto(int id, int idKandidat, int idStaf, LocalDate dataFeedback, int vlersimi, String koment) {
        this.id = id;
        this.idKandidat = idKandidat;
        this.idStaf = idStaf;
        this.dataFeedback = dataFeedback;
        this.vlersimi = vlersimi;
        this.koment = koment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getDataFeedback() {
        return dataFeedback;
    }

    public void setDataFeedback(LocalDate dataFeedback) {
        this.dataFeedback = dataFeedback;
    }

    public int getVlersimi() {
        return vlersimi;
    }

    public void setVlersimi(int vlersimi) {
        this.vlersimi = vlersimi;
    }

    public String getKoment() {
        return koment;
    }

    public void setKoment(String koment) {
        this.koment = koment;
    }
}
