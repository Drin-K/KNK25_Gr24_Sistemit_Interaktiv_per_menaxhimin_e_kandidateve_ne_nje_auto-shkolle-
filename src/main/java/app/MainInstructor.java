package app;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.SceneManager;
import utils.SceneLocator;

import java.io.IOException;

public class MainInstructor extends Application {
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = SceneManager.getInstance(SceneLocator.INSTRUCTOR_FRONT_PAGE);
        stage.setScene(sceneManager.getScene());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Tiger-Logo.jpg")));
        stage.show();
        stage.show();
    }
}
