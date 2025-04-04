package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Dokumentet {
    private int id;
    private int idKandidat;
    private String llojiDokumentit;
    private String emriSkedarit;
    private LocalDate dataNgarkimit;

    public Dokumentet(int id, int idKandidat, String llojiDokumentit, String emriSkedarit, LocalDate dataNgarkimit) {
        this.id = id;
        this.idKandidat = idKandidat;
        this.llojiDokumentit = llojiDokumentit;
        this.emriSkedarit = emriSkedarit;
        this.dataNgarkimit = dataNgarkimit;
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

    public LocalDate getDataNgarkimit() {
        return dataNgarkimit;
    }

    public void setDataNgarkimit(LocalDate dataNgarkimit) {
        this.dataNgarkimit = dataNgarkimit;
    }

    public static Dokumentet getInstance(ResultSet result)throws SQLException {
        int id=result.getInt("ID_Dokument");
        int idKandidat=result.getInt("ID_Kandidat");
        String llojiDokumentit=result.getString("llojiDokumentit");
        String emriSkedarit=result.getString("Emri_Skedarit");
        LocalDate dataNgarkimit=result.getObject("Data_Ngarkimit",LocalDate.class);

        return new Dokumentet(id,idKandidat,llojiDokumentit,emriSkedarit,dataNgarkimit);
    }
}
