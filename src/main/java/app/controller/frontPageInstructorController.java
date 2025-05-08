package app.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import services.LanguageManager;
import services.SceneManager;
import services.UserContext;
import utils.SceneLocator;

import java.util.Locale;

public class frontPageInstructorController {
    private final LanguageManager languageManager = LanguageManager.getInstance();

    @FXML
    private AnchorPane rightPage;
    @FXML
    private void homeClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_HOME,this.rightPage);
    }
    @FXML
    private void scheduleClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_SCHEDULER,this.rightPage);
    }
    @FXML
    private void mngTestClick()throws Exception{
        SceneManager.load(SceneLocator.MANAGE_TESTS,this.rightPage);
    }
    @FXML
    private void mngVehiclesClick()throws Exception{
        SceneManager.load(SceneLocator.MANAGE_VEHICLES,this.rightPage);
    }
    @FXML
    private void raportsClick()throws Exception{
        SceneManager.load(SceneLocator.REPORTS,this.rightPage);
    }
    @FXML
    private void feedbackClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_FEEDBACK,this.rightPage);
    }
    @FXML
    private void profileClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_PROFILE,this.rightPage);
    }
    @FXML
    private void languageClick()throws Exception{
        languageManager.toggleLanguage();
        SceneManager.reload();

    }
    @FXML
    private void handleLogOut()throws Exception{
        SceneManager.load(SceneLocator.LOGIN_PAGE);
        UserContext.clear();
    }


}
