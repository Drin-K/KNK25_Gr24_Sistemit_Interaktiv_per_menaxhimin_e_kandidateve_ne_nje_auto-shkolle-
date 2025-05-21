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


    public int getIdKandidat() {
        return idKandidat;
    }


    public String getLlojiDokumentit() {
        return llojiDokumentit;
    }


    public String getEmriSkedarit() {
        return emriSkedarit;
    }


    public LocalDate getDataNgarkimit() {
        return dataNgarkimit;
    }


    public static Dokumentet getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int idKandidat = result.getInt("ID_Kandidat");
        String llojiDokumentit = result.getString("Lloji_Dokumentit");
        String emriSkedarit = result.getString("Emri_Skedari");
        LocalDate dataNgarkimit = result.getObject("Data_Ngarkimit", LocalDate.class);

        return new Dokumentet(id, idKandidat, llojiDokumentit, emriSkedarit, dataNgarkimit);
    }
}
