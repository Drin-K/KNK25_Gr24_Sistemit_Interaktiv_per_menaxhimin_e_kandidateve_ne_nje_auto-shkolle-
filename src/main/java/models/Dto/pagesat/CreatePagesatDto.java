package models.Dto.pagesat;

public class CreatePagesatDto {
    private int id_kandidat;
    private double shuma;
    private String data_e_pageses;
    private String metoda_e_pageses;
    private String statusi_i_pageses;

    public CreatePagesatDto(int id_kandidat, double shuma, String data_e_pageses, String metoda_e_pageses, String statusi_i_pageses) {
        this.id_kandidat = id_kandidat;
        this.shuma = shuma;
        this.data_e_pageses = data_e_pageses;
        this.metoda_e_pageses = metoda_e_pageses;
        this.statusi_i_pageses = statusi_i_pageses;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public void setId_kandidat(int id_kandidat) {
        this.id_kandidat = id_kandidat;
    }

    public double getShuma() {
        return shuma;
    }

    public void setShuma(double shuma) {
        this.shuma = shuma;
    }

    public String getData_e_pageses() {
        return data_e_pageses;
    }

    public void setData_e_pageses(String data_e_pageses) {
        this.data_e_pageses = data_e_pageses;
    }

    public String getMetoda_e_pageses() {
        return metoda_e_pageses;
    }

    public void setMetoda_e_pageses(String metoda_e_pageses) {
        this.metoda_e_pageses = metoda_e_pageses;
    }

    public String getStatusi_i_pageses() {
        return statusi_i_pageses;
    }

    public void setStatusi_i_pageses(String statusi_i_pageses) {
        this.statusi_i_pageses = statusi_i_pageses;
    }
}
