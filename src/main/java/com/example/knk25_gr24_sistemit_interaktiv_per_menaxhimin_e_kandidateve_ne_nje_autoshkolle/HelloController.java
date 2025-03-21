package com.example.knk25_gr24_sistemit_interaktiv_per_menaxhimin_e_kandidateve_ne_nje_autoshkolle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}