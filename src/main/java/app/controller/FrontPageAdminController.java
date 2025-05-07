package app.controller;

import javafx.fxml.FXML;import javafx.scene.layout.AnchorPane;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

import java.util.Locale;

public class FrontPageAdminController {

    private final LanguageManager languageManager = LanguageManager.getInstance();

    @FXML
    private AnchorPane rightPage;

    @FXML
    private void licensesClick()throws Exception{

        SceneManager.load(SceneLocator.LICENSES_PAGE,this.rightPage);

    }
    @FXML
    private void homeAdminClick()throws Exception{
        SceneManager.load(SceneLocator.ADMIN_HOME_PAGE,this.rightPage);
    }
    @FXML
    private void manageUsersClick()throws Exception{
        SceneManager.load(SceneLocator.CANDIDATES_PAGE,this.rightPage);
    }
    @FXML
    private void manageStafClick()throws Exception{
        SceneManager.load(SceneLocator.STAF_PAGE,this.rightPage);
    }
    @FXML
    private void managePaymentsClick()throws Exception{
        SceneManager.load(SceneLocator.PAYMENTS_MANAGE_PAGE,this.rightPage);
    }

    @FXML
    private void profileClick()throws Exception{
        SceneManager.load(SceneLocator.PROFILE_PAGE,this.rightPage);
    }
    @FXML
    private void languageClick()throws Exception{
        languageManager.toggleLanguage();
        SceneManager.reload();

    }
}
