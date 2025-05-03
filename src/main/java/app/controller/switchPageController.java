package app.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import services.SceneManager;
import utils.SceneLocator;

public class switchPageController {
    @FXML
    private AnchorPane rightPage;
    @FXML
    private void categoryClick()throws Exception{

        SceneManager.load(SceneLocator.CATEGORY_PAGE,this.rightPage);

    }
}
