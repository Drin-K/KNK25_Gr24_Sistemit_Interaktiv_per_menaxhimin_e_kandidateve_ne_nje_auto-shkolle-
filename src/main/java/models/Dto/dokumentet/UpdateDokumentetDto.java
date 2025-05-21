package models.Dto.dokumentet;


public class UpdateDokumentetDto {
    private int id;
    private int idKandidat;
    private String llojiDokumentit;
    private String emriSkedarit;
    public UpdateDokumentetDto(int id, int idKandidat, String llojiDokumentit, String emriSkedarit) {
        this.id = id;
        this.idKandidat = idKandidat;
        this.llojiDokumentit = llojiDokumentit;
        this.emriSkedarit = emriSkedarit;
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
