package models.Dto.Patenta;

public class UpdatePatentaDto {
    private int id_patente;
    private int id_kandidat;
    private int id_kategori;
    private String data_leshimit;
    private String statusi;

    public UpdatePatentaDto(String statusi, String data_leshimit, int id_kategori, int id_kandidat, int id_patente) {
        this.statusi = statusi;
        this.data_leshimit = data_leshimit;
        this.id_kategori = id_kategori;
        this.id_kandidat = id_kandidat;
        this.id_patente = id_patente;
    }

    public int getId_patente() {
        return id_patente;
    }

    public int getId_kandidat() {
        return id_kandidat;
    }

    public void setId_kandidat(int id_kandidat) {
        this.id_kandidat = id_kandidat;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getData_leshimit() {
        return data_leshimit;
    }

    public void setData_leshimit(String data_leshimit) {
        this.data_leshimit = data_leshimit;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}
