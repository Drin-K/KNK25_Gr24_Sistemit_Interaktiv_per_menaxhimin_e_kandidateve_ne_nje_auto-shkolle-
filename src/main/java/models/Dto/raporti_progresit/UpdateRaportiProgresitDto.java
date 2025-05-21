package models.Dto.raporti_progresit;


public class UpdateRaportiProgresitDto {
    private int id;
    private int piketTeorike;
    private int piketPraktike;
    private String komentet;
    private String performancaGjenerale;

    public UpdateRaportiProgresitDto(int id, int piketTeorike, int piketPraktike, String komentet, String performancaGjenerale) {
        this.id = id;
        this.piketTeorike = piketTeorike;
        this.piketPraktike = piketPraktike;
        this.komentet = komentet;
        this.performancaGjenerale = performancaGjenerale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
