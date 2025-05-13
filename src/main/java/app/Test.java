package app;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.SceneManager;
import utils.SceneLocator;

public class Test extends Application{
    public void start(Stage stage){
    SceneManager sceneManager = SceneManager.getInstance(SceneLocator.LOGIN_PAGE);
        stage.setScene(sceneManager.getScene());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Tiger-Logo.jpg")));
        stage.show();
        stage.show();}
}
