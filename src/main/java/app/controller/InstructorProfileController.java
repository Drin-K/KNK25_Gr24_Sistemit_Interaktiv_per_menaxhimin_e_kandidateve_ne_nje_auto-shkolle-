package app.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.SceneManager;
import services.StafiService;
import utils.SceneLocator;



public class InstructorProfileController {
    private final StafiService stafiService = new StafiService();
    @FXML
    private AnchorPane pane;
    @FXML
    private Text nameField,surnameField,emailField,numberField,dataField,gjiniaField;
    @FXML
    public void initialize() {
        this.getStafi();
    }
    private void getStafi(){
          this.stafiService.getStafiInfo(nameField,surnameField,emailField,numberField,dataField,gjiniaField);
    }
    @FXML
    private void changePassClick()throws Exception{
        SceneManager.load(SceneLocator.CHANGE_PASSWORD, this.pane);
    }
}
