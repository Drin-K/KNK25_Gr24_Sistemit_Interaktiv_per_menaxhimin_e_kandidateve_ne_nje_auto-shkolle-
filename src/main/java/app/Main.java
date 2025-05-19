package app;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.SceneManager;
import utils.SceneLocator;

public class  Main extends Application {
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Tiger-Logo.jpg")));
        SceneManager.mainLoad(SceneLocator.LOGIN_PAGE,stage);
    }
}
