package models.Dto.patenta;

import java.time.LocalDate;

public class UpdatePatentaDto {
    private int id;
    private int idKandidat;//// ka mundesi duhet me hjek
    private int idKategori;
    private LocalDate dataLeshimit; //
    private String statusi;

    public UpdatePatentaDto(int id, int idKandidat, int idKategori, LocalDate dataLeshimit, String statusi) {
        this.id = id;
        this.idKandidat = idKandidat;
        this.idKategori = idKategori;
        this.dataLeshimit = dataLeshimit;
        this.statusi = statusi;
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

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public LocalDate getDataLeshimit() {
        return dataLeshimit;
    }

    public void setDataLeshimit(LocalDate dataLeshimit) {
        this.dataLeshimit = dataLeshimit;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}
