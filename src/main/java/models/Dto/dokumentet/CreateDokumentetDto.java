package models.Dto.dokumentet;

import java.time.LocalDate;

public class CreateDokumentetDto {
    private int idKandidat;
    private String llojiDokumentit;
    private String emriSkedarit;
    private LocalDate dataNgarkimit;

    public CreateDokumentetDto(int idKandidat, String llojiDokumentit, String emriSkedarit, LocalDate dataNgarkimit) {
        this.idKandidat = idKandidat;
        this.llojiDokumentit = llojiDokumentit;
        this.emriSkedarit = emriSkedarit;
        this.dataNgarkimit = dataNgarkimit;
    }

    public int getIdKandidat() {
        return idKandidat;
    }

    public void setIdKandidat(int idKandidat) {
        this.idKandidat = idKandidat;
    }

    public String getLlojiDokumentit() {
        return llojiDokumentit;
    }

    public void setLlojiDokumentit(String llojiDokumentit) {
        this.llojiDokumentit = llojiDokumentit;
    }

    public String getEmriSkedarit() {
        return emriSkedarit;
    }

    public void setEmriSkedarit(String emriSkedarit) {
        this.emriSkedarit = emriSkedarit;
    }

    public LocalDate getDataNgarkimit() {
        return dataNgarkimit;
    }

    public void setDataNgarkimit(LocalDate dataNgarkimit) {
        this.dataNgarkimit = dataNgarkimit;
    }
}
