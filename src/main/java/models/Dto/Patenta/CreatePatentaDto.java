package models.Dto.Patenta;

public class CreatePatentaDto {

    private int id_kandidat;
    private int id_kategori;
    private String data_leshimit;
    private String statusi;

    public CreatePatentaDto(int id_patente, int id_kandidat, int id_kategori, String data_leshimit, String statusi) {

        this.id_kandidat = id_kandidat;
        this.id_kategori = id_kategori;
        this.data_leshimit = data_leshimit;
        this.statusi = statusi;
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

