package models.Dto.raporti_progresit;

import java.time.LocalDate;

public class CreateRaportiProgresitDto {
    private int idKandidat;
    private int idStaf;
    private LocalDate dataRaportit;
    private int piketTeorike;
    private int piketPraktike;
    private String komentet;
    private String performancaGjenerale;

    public CreateRaportiProgresitDto(int idKandidat, int idStaf, LocalDate dataRaportit, int piketTeorike, int piketPraktike, String komentet, String performancaGjenerale) {
        this.idKandidat = idKandidat;
        this.idStaf = idStaf;
        this.dataRaportit = dataRaportit;
        this.piketTeorike = piketTeorike;
        this.piketPraktike = piketPraktike;
        this.komentet = komentet;
        this.performancaGjenerale = performancaGjenerale;
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

    public LocalDate getDataRaportit() {
        return dataRaportit;
    }

    public void setDataRaportit(LocalDate dataRaportit) {
        this.dataRaportit = dataRaportit;
    }

    public int getPiketTeorike() {
        return piketTeorike;
    }

    public void setPiketTeorike(int piketTeorike) {
        this.piketTeorike = piketTeorike;
    }

    public int getPiketPraktike() {
        return piketPraktike;
    }

    public void setPiketPraktike(int piketPraktike) {
        this.piketPraktike = piketPraktike;
    }

    public String getKomentet() {
        return komentet;
    }

    public void setKomentet(String komentet) {
        this.komentet = komentet;
    }

    public String getPerformancaGjenerale() {
        return performancaGjenerale;
    }

    public void setPerformancaGjenerale(String performancaGjenerale) {
        this.performancaGjenerale = performancaGjenerale;
    }
}
