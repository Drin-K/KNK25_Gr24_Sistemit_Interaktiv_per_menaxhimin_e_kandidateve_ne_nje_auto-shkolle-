package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import utils.SceneLocator;

public class SceneManager {
    private static SceneManager sceneManager;
    private Scene scene;
    private LanguageManager languageManager;
    private String currentPath;

    private SceneManager(String currentpath){
        this.languageManager = LanguageManager.getInstance();
        this.currentPath = currentpath;
        this.scene = this.initScene();
    }

    public static SceneManager getInstance(String currentPath){
        if(sceneManager == null)
            sceneManager = new SceneManager(currentPath);
        return sceneManager;
    }

    private Scene initScene(){
        try{
            return new Scene(this.getParent(currentPath));
        }catch (Exception e){
            return null;
        }
    }


    public static void load(String path) throws Exception{
        if(sceneManager == null){
            throw new Exception("Scene manager is not initialized yet!");
        }
        sceneManager.loadParent(path);
    }

    public static void load(String path, Pane pane) throws Exception{
        if(sceneManager == null){
            throw new Exception("Scene manager is not initialized yet!");
        }
        SceneLocator.setCurrentRightPage(path);
        sceneManager.loadParent(path, pane);
    }

    private void loadParent(String path) throws Exception{
        Parent parent = getParent(path);
        this.currentPath = path;
        scene.setRoot(parent);
    }

    private void loadParent(String path, Pane pane) throws Exception{
        pane.getChildren().clear();

        Parent parent = getParent(path);
        pane.getChildren().add(parent);
    }

    private Parent getParent(String path) throws Exception{
        FXMLLoader loader = new FXMLLoader(
                this.getClass().getResource(path)
        );
        loader.setResources(this.languageManager.getResourceBundle());
        return loader.load();
    }

    public static void reload() throws Exception{
        load(sceneManager.currentPath);
    }

//    public void reloadPaneInstance(String path, Pane pane) throws Exception {
//        pane.getChildren().clear();
//        pane.getChildren().add(getParent(path));
//    }
//
//
//    public static void reloadPane(String path, Pane pane) throws Exception {
//        if (sceneManager == null) {
//            throw new Exception("Scene manager not initialized!");
//        }
//        sceneManager.reloadPaneInstance(path, pane);
//    }

    public Scene getScene() {
        return scene;
    }}
