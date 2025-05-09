package app.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import models.Kandidatet;

import repository.DokumentetRepository;
import repository.KandidatetRepository;
import services.SceneManager;
import services.UserContext;
import utils.SceneLocator;

import java.io.File;
import java.net.URL;

public class CandidateProfileController {
    DokumentetRepository dokumentetRepository = new DokumentetRepository();
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView profilePicture;

    private KandidatetRepository user=new KandidatetRepository();
    private Kandidatet kandidatet;
    @FXML
    private Text nameField,surnameField,emailField,numberField,birthDateField,registerDateField,statusField,genderField;
    @FXML
    public void initialize(){
        this.getKandidati();
        loadProfilePicture();
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
    private void loadProfilePicture() {
        String filename = dokumentetRepository.getFotoFileNameForCurrentUser();

        if (filename != null && !filename.isBlank()) {
            File imageFile = new File("src/main/java/utils/uploads", filename);

            if (imageFile.exists()) {
                Image profileImage = new Image(imageFile.toURI().toString());
                profilePicture.setImage(profileImage);
            } else {
                System.err.println("File not found: " + imageFile.getAbsolutePath());
            }
        } else {
            File defaultProfilePicture = new File("src/main/resources/images/Default Profile Picture.jpg");
            Image defaultPicture = new Image(defaultProfilePicture.toURI().toString());
            profilePicture.setImage(defaultPicture);
            System.err.println("No filename found for profile picture.");
        }
        Circle clip = new Circle(
                profilePicture.getFitWidth() / 2,
                profilePicture.getFitHeight() / 2,
                Math.min(profilePicture.getFitWidth(), profilePicture.getFitHeight()) / 2
        ); // Qe ta bejme foton e profilit ne form rrethore
        profilePicture.setClip(clip);
    }
}