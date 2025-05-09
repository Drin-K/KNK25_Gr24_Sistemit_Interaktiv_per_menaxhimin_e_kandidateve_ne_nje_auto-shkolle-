package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.Stafi;
import models.User;
import repository.StafiRepository;
import repository.UserRepository;
import services.SceneManager;
import services.UserContext;
import services.UserService;
import utils.SceneLocator;



public class InstructorProfileController {
    @FXML
    private AnchorPane pane;
    @FXML
    private Text nameField,surnameField,emailField,numberField,dataField,gjiniaField;
    private StafiRepository user=new StafiRepository();
    private  Stafi instruktori;
    @FXML
    public void initialize() {
        this.getStafi();
    }
    private void getStafi(){
        this.instruktori = user.getById(UserContext.getUserId());
        this.nameField.setText(instruktori.getName());
        this.surnameField.setText(instruktori.getSurname());
        this.emailField.setText(instruktori.getEmail());
        this.numberField.setText(instruktori.getPhoneNumber());
        this.dataField.setText(instruktori.getDateOfBirth().toString());
        this.gjiniaField.setText(instruktori.getGjinia());
    }
    @FXML
    private void changePassClick()throws Exception{
        SceneManager.load(SceneLocator.CHANGE_PASSWORD, this.pane);
    }
}
