package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class FeedBack {
    private int id;
    private int idKandidat;
    private int idStaf;
    private LocalDate dataFeedback;
    private int vlersimi;
    private String koment;

    private FeedBack(int id, int idKandidat, int idStaf, LocalDate dataFeedback, int vlersimi, String koment) {
        this.id = id;
        this.idKandidat = idKandidat;
        this.idStaf = idStaf;
        this.dataFeedback = dataFeedback;
        this.vlersimi = vlersimi;
        this.koment = koment;
    }

    public static FeedBack getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int idKandidat = resultSet.getInt("ID_Kandidat");
        int idStaf = resultSet.getInt("ID_Staf");
        LocalDate dataFeedback = resultSet.getObject("Data_Feedback", LocalDate.class);
        int vlersimi = resultSet.getInt("Vleresimi");
        String koment = resultSet.getString("Koment");
        return new FeedBack(id, idKandidat, idStaf, dataFeedback, vlersimi, koment);
    }

    public int getId() {
        return id;
    }

    public int getIdKandidat() {
        return idKandidat;
    }

    public int getIdStaf() {
        return idStaf;
    }

    public LocalDate getDataFeedback() {
        return dataFeedback;
    }

    public int getVlersimi() {
        return vlersimi;
    }

    public String getKoment() {
        return koment;
    }
}
