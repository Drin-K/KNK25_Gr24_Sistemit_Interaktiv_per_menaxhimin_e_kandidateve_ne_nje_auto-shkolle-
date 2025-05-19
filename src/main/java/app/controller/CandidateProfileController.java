package app.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.DokumentService;
import services.KandidateService;
import services.SceneManager;
import utils.SceneLocator;


public class CandidateProfileController {
    DokumentService dokumentService = new DokumentService();
    private final KandidateService kandidateService = new KandidateService();
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Text nameField,surnameField,emailField,numberField,birthDateField,registerDateField,statusField,genderField;
    @FXML
    public void initialize(){
        this.getKandidati();
        loadProfilePicture();
    }
    private void getKandidati(){
        this.kandidateService.getKandidatiInfo(nameField,surnameField,emailField,numberField,birthDateField,registerDateField,statusField,genderField);
    }
    @FXML
    private void changePassClick()throws Exception{
        SceneManager.load(SceneLocator.CHANGE_PASSWORD, this.pane);
    }
    private void loadProfilePicture() {
        this.dokumentService.getProfilePicture(this.profilePicture);
    }
}