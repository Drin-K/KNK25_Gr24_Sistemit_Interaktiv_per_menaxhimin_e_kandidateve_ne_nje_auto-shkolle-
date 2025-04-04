package models.Dto.orari;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateOrariDto {
    private int idKandidat;
    private int idStaf;
    private LocalDate dataSesionit;
    private LocalTime oraFillimit;
    private LocalTime oraPerfundimit;
    private String llojiMesimit;
    private String statusi;
    private int idAutomjet;

    public CreateOrariDto(int idKandidat, int idStaf, LocalDate dataSesionit, LocalTime oraFillimit, LocalTime oraPerfundimit, String llojiMesimit, String statusi, int idAutomjet) {
        this.idKandidat = idKandidat;
        this.idStaf = idStaf;
        this.dataSesionit = dataSesionit;
        this.oraFillimit = oraFillimit;
        this.oraPerfundimit = oraPerfundimit;
        this.llojiMesimit = llojiMesimit;
        this.statusi = statusi;
        this.idAutomjet = idAutomjet;
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

    public LocalDate getDataSesionit() {
        return dataSesionit;
    }

    public void setDataSesionit(LocalDate dataSesionit) {
        this.dataSesionit = dataSesionit;
    }

    public LocalTime getOraFillimit() {
        return oraFillimit;
    }

    public void setOraFillimit(LocalTime oraFillimit) {
        this.oraFillimit = oraFillimit;
    }

    public LocalTime getOraPerfundimit() {
        return oraPerfundimit;
    }

    public void setOraPerfundimit(LocalTime oraPerfundimit) {
        this.oraPerfundimit = oraPerfundimit;
    }

    public String getLlojiMesimit() {
        return llojiMesimit;
    }

    public void setLlojiMesimit(String llojiMesimit) {
        this.llojiMesimit = llojiMesimit;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public int getIdAutomjet() {
        return idAutomjet;
    }

    public void setIdAutomjet(int idAutomjet) {
        this.idAutomjet = idAutomjet;
    }
}
