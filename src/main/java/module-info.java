module com.example.knk25_gr24_sistemit_interaktiv_per_menaxhimin_e_kandidateve_ne_nje_autoshkolle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.knk25_gr24_sistemit_interaktiv_per_menaxhimin_e_kandidateve_ne_nje_autoshkolle to javafx.fxml;
    exports com.example.knk25_gr24_sistemit_interaktiv_per_menaxhimin_e_kandidateve_ne_nje_autoshkolle;
}