package app;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.SceneManager;
import utils.SceneLocator;

import java.io.IOException;

public class MainConsumator extends Application {
    public void start(Stage stage) throws Exception {
        SceneManager.mainLoad(SceneLocator.FRONT_PAGE,stage);
    }

}
