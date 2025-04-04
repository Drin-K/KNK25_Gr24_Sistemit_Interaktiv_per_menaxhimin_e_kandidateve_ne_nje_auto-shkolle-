package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Pagesat {
    private int idPagesat;
    private int idKandidat;
    private double shuma;
    private LocalDate dataPageses;
    private String metodaPageses;
    private String statusiPageses;


    public Pagesat(int idPagesat, int idKandidat, double shuma, LocalDate dataPageses, String metodaPageses, String statusiPageses) {
        this.idPagesat = idPagesat;
        this.idKandidat = idKandidat;
        this.shuma = shuma;
        this.dataPageses = dataPageses;
        this.metodaPageses = metodaPageses;
        this.statusiPageses = statusiPageses;
    }

    public static Pagesat getInstance(ResultSet resultSet) throws SQLException {
int idPagesat=resultSet.getInt("id_pagesat");
int idKandidat=resultSet.getInt("id_kandidat");
double shuma=resultSet.getDouble("shuma");
LocalDate dataPageses=resultSet.getObject("data_e_pageses",LocalDate.class);
String metodaPageses=resultSet.getString("metoda_e_pageses");
String statusiPageses=resultSet.getString("statusi_i_pageses");
return new Pagesat(idPagesat,idKandidat,shuma,dataPageses,metodaPageses,statusiPageses);
    }

    public int getIdPagesat() {
        return idPagesat;
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
