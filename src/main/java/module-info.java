module com.example.knk25_gr24_sistemit_interaktiv_per_menaxhimin_e_kandidateve_ne_nje_autoshkolle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens models to javafx.base, javafx.fxml;


    opens app to javafx.fxml;
    exports app;
    exports app.controller;
    opens app.controller to javafx.fxml;
}