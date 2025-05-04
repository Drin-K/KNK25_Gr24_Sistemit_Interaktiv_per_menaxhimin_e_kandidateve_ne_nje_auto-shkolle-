package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.SceneManager;
import utils.SceneLocator;

import java.io.IOException;

public class Main extends Application {
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = SceneManager.getInstance();
        stage.setScene(sceneManager.getScene());
        stage.show();
    }

}
