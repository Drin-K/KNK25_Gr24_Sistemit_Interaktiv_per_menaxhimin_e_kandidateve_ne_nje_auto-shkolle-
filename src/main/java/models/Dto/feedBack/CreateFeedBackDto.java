package models.Dto.feedBack;

import java.time.LocalDate;

public class CreateFeedBackDto {
    private  int idKandidat;
    private int idStaf;
    private LocalDate dataFeedback;
    private int vlersimi;
    private String koment;

    public CreateFeedBackDto(int idKandidat, int idStaf, LocalDate dataFeedback, int vlersimi, String koment) {
        this.idKandidat = idKandidat;
        this.idStaf = idStaf;
        this.dataFeedback = dataFeedback;
        this.vlersimi = vlersimi;
        this.koment = koment;
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
