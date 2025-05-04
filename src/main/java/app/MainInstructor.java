package app;

import javafx.application.Application;
import javafx.stage.Stage;
import services.SceneManager;
import utils.SceneLocator;

import java.io.IOException;

public class MainInstructor extends Application {
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = SceneManager.getInstance(SceneLocator.INSTRUCTOR_FRONT_PAGE);
        stage.setScene(sceneManager.getScene());
        stage.show();
    }
}
