package app.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.Kandidatet;

import repository.KandidatetRepository;
import services.SceneManager;
import services.UserContext;
import utils.SceneLocator;

public class CandidateProfileController {
    @FXML
    private AnchorPane pane;

    private KandidatetRepository user=new KandidatetRepository();
    private Kandidatet kandidatet;
    @FXML
    private Text nameField,surnameField,emailField,numberField,birthDateField,registerDateField,statusField,genderField;
    @FXML
    public void initialize(){
        this.getKandidati();
    }
    private void getKandidati(){
        this.kandidatet=user.getById(UserContext.getUserId());
        this.nameField.setText(kandidatet.getName());
        this.surnameField.setText(kandidatet.getSurname());
        this.emailField.setText(kandidatet.getEmail());
        this.numberField.setText(kandidatet.getPhoneNumber());
        this.birthDateField.setText(kandidatet.getDateOfBirth().toString());
        this.registerDateField.setText(kandidatet.getDataRegjistrimit().toString());
        this.statusField.setText(kandidatet.getStatusiProcesit());
        this.genderField.setText(kandidatet.getGjinia());
    }
    @FXML
    private void changePassClick()throws Exception{
        SceneManager.load(SceneLocator.CHANGE_PASSWORD, this.pane);
    }
}