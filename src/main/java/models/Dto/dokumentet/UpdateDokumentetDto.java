package models.Dto.dokumentet;

import java.time.LocalDate;

public class UpdateDokumentetDto {
    private int id;
    private int idKandidat;
    private String llojiDokumentit;//
    private String emriSkedarit;
    // sjom e sigurt!  private LocalDate dataNgarkimit;

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
}
