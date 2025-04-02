package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pagesat {
    private int id_pagesat;
    private int id_kandidat;
    private double shuma;
    private String data_e_pageses;
    private String metoda_e_pageses;
    private String statusi_i_pageses;

    private Pagesat(int id_pagesat, int id_kandidat, double shuma, String data_e_pageses, String metoda_e_pageses, String statusi_i_pageses) {
        this.id_pagesat = id_pagesat;
        this.id_kandidat = id_kandidat;
        this.shuma = shuma;
        this.data_e_pageses = data_e_pageses;
        this.metoda_e_pageses = metoda_e_pageses;
        this.statusi_i_pageses = statusi_i_pageses;
    }
    public static Pagesat getInstance(ResultSet resultSet) throws SQLException {
int id_pagesat=resultSet.getInt("id_pagesat");
int id_kandidat=resultSet.getInt("id_kandidat");
double shuma=resultSet.getDouble("shuma");
String data_e_pageses=resultSet.getString("data_e_pageses");
String metoda_e_pageses=resultSet.getString("metoda_e_pageses");
String statusi_i_pageses=resultSet.getString("statusi_i_pageses");
return new Pagesat(id_pagesat,id_kandidat,shuma,data_e_pageses,metoda_e_pageses,statusi_i_pageses);
    }

    public int getId_pagesat() {
        return id_pagesat;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public double getShuma() {
        return shuma;
    }

    public String getData_e_pageses() {
        return data_e_pageses;
    }

    public String getMetoda_e_pageses() {
        return metoda_e_pageses;
    }

    public String getStatusi_i_pageses() {
        return statusi_i_pageses;
    }
}
