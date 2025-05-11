package app;

import javafx.application.Application;
import javafx.stage.Stage;
import services.SceneManager;
import utils.SceneLocator;

public class Test extends Application{
    public void start(Stage stage){
    SceneManager sceneManager = SceneManager.getInstance(SceneLocator.CHANGE_PASSWORD);
        stage.setScene(sceneManager.getScene());
        stage.show();}
}
