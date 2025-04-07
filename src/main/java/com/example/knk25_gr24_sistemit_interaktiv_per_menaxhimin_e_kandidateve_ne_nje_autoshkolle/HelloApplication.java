package com.example.knk25_gr24_sistemit_interaktiv_per_menaxhimin_e_kandidateve_ne_nje_autoshkolle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign_up.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Auto Shkolla Tigri");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}