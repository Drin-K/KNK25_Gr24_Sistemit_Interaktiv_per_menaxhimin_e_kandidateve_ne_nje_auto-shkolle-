package app;

import javafx.application.Application;
import javafx.stage.Stage;
import services.SceneManager;
import utils.SceneLocator;

public class Main extends Application {
    public void start(Stage stage) throws Exception {
        SceneManager.mainLoad(SceneLocator.LOGIN_PAGE,stage);

    }
}
