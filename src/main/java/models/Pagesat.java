package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Pagesat {
    private int id;
    private int idKandidat;
    private double shuma;
    private LocalDate dataPageses;
    private String metodaPageses;
    private String statusiPageses;


    private Pagesat(int id, int idKandidat, double shuma, LocalDate dataPageses, String metodaPageses, String statusiPageses) {
        this.id = id;
        this.idKandidat = idKandidat;
        this.shuma = shuma;
        this.dataPageses = dataPageses;
        this.metodaPageses = metodaPageses;
        this.statusiPageses = statusiPageses;
    }

    public static Pagesat getInstance(ResultSet resultSet) throws SQLException {
int id=resultSet.getInt("id");
int idKandidat=resultSet.getInt("ID_Kandidat");
double shuma=resultSet.getDouble("Shuma");
LocalDate dataPageses=resultSet.getObject("Data_e_Pageses",LocalDate.class);
String metodaPageses=resultSet.getString("Metoda_e_Pageses");
String statusiPageses=resultSet.getString("Statusi_i_Pageses");
return new Pagesat(id,idKandidat,shuma,dataPageses,metodaPageses,statusiPageses);
    }

    public int getId() {
        return id;
    }

    public int getIdKandidat() {
        return idKandidat;
    }

    public double getShuma() {
        return shuma;
    }

    public LocalDate getDataPageses() {
        return dataPageses;
    }

    public String getMetodaPageses() {
        return metodaPageses;
    }

    public String getStatusiPageses() {
        return statusiPageses;
    }
}
