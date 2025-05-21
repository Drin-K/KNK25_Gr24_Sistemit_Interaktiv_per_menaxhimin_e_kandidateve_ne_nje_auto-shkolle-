package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.SceneLocator;

import java.io.IOException;

public class SceneManager {
    private static SceneManager sceneManager;
    private Scene scene;
    private LanguageManager languageManager;
    private String currentPath;
    private String childPath;

    private SceneManager(String currentpath) {
        this.languageManager = LanguageManager.getInstance();
        this.currentPath = currentpath;
        this.childPath = null;
        this.scene = this.initScene();
    }

    public static SceneManager getInstance(String currentPath) {
        if (sceneManager == null)
            sceneManager = new SceneManager(currentPath);
        return sceneManager;
    }

    private Scene initScene() {
        try {
            return new Scene(this.getParent(currentPath));
        } catch (Exception e) {
            return null;
        }
    }


    public static void load(String path) throws Exception {
        if (sceneManager == null) {
            throw new Exception("Scene manager is not initialized yet!");
        }
        sceneManager.loadParent(path);
    }

    public static void load(String path, Pane pane) throws Exception {
        if (sceneManager == null) {
            throw new Exception("Scene manager is not initialized yet!");
        }
        sceneManager.childPath = path;
        sceneManager.loadParent(path, pane);
    }

    public static void mainLoad(String path, Stage stage) throws Exception {
        SceneManager.getInstance(path);
        stage.setScene(sceneManager.getScene());
        stage.getIcons().add(new Image(sceneManager.getClass().getResourceAsStream("/images/Tiger-Logo.jpg")));
        stage.show();
        sceneManager.loadParent(path);
    }

    private void loadParent(String path) throws Exception {
        Stage stage = (Stage) scene.getWindow();
        Parent parent = getParent(path);
        this.currentPath = path;
        scene.setRoot(parent);
        stage.sizeToScene();
        if (path.equals(SceneLocator.LOGIN_PAGE) || path.equals(SceneLocator.SIGNUP_PAGE)) {
            stage.setResizable(false);
        } else {
            stage.setResizable(true);
        }
        stage.centerOnScreen();
    }

    private void loadParent(String path, Pane pane) throws Exception {
        pane.getChildren().clear();
        Parent parent = getParent(path);
        pane.getChildren().add(parent);
    }

    private Parent getParent(String path) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                this.getClass().getResource(path)
        );
        loader.setResources(this.languageManager.getResourceBundle());
        return loader.load();
    }

    public static void reload() throws Exception {
        load(sceneManager.currentPath);
        if (sceneManager.childPath != null) {
            Pane right = (Pane) sceneManager.scene.getRoot().lookup("#rightPage");
            right.getChildren().setAll(sceneManager.getParent(sceneManager.childPath));
        }
    }

    public Scene getScene() {
        return scene;
    }
}
