package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import services.SceneManager;
import utils.SceneLocator;

public class InstructorChangePassController {
    @FXML
    private AnchorPane pane;
    @FXML
    private Button changePassBttn;

    @FXML
    private void changePassClick()throws Exception{
        SceneManager.load(SceneLocator.CHANGE_PASSWORD, this.pane);
    }
}
