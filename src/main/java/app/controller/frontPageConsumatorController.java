package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.Kandidatet;
import repository.KandidatetRepository;
import services.LanguageManager;
import services.SceneManager;
import services.UserContext;
import utils.SceneLocator;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class frontPageConsumatorController {

    private final LanguageManager languageManager = LanguageManager.getInstance();
    private KandidatetRepository user=new KandidatetRepository();
    private Kandidatet kandidatet;
    @FXML
    private Text kandidatiName;
    @FXML
    private Text kandidatiSurname;
    @FXML
    private AnchorPane rightPage;

    @FXML
    private void categoryClick() throws Exception {

        SceneManager.load(SceneLocator.CATEGORY_PAGE, this.rightPage);

    }
    @FXML
    public void initialize(){
        this.kandidatet = user.getById(UserContext.getUserId());
        this.kandidatiName.setText(kandidatet.getName());
        this.kandidatiSurname.setText(kandidatet.getSurname());
    }
    @FXML
    private void homeClick() throws Exception {
        SceneManager.load(SceneLocator.HOME_PAGE, this.rightPage);
    }

    @FXML
    private void schedulerClick() throws Exception {
        SceneManager.load(SceneLocator.SCHEDULER_PAGE, this.rightPage);
    }

    @FXML
    private void testClick() throws Exception {
        SceneManager.load(SceneLocator.TESTS_PAGE, this.rightPage);
    }

    @FXML
    private void paymentsClick() throws Exception {
        SceneManager.load(SceneLocator.PAYMENTS_PAGE, this.rightPage);
    }

    @FXML
    private void progressClick() throws Exception {
        SceneManager.load(SceneLocator.PROGRESS_PAGE, this.rightPage);
    }

    @FXML
    private void feedbackClick() throws Exception {
        SceneManager.load(SceneLocator.FEEDBACK_PAGE, this.rightPage);
    }

    @FXML
    private void profileClick() throws Exception {
        SceneManager.load(SceneLocator.PROFILE_PAGE, this.rightPage);
    }

    @FXML
    private void languageClick() throws Exception {
        languageManager.toggleLanguage();
        SceneManager.reload();
    }
    @FXML
    private void handleLogOut()throws Exception{
        SceneManager.load(SceneLocator.LOGIN_PAGE);
        UserContext.clear();
    }
}
