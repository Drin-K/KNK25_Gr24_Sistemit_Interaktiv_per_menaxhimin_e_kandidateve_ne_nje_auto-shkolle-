package app.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

import java.util.Locale;

public class frontPageInstructorController {
    private final LanguageManager languageManager = LanguageManager.getInstance();
    private void loadLanguage(Locale locale) throws Exception{
        languageManager.setLocale(locale);
        SceneManager.reload();

    }
    @FXML
    private AnchorPane rightPage;
    @FXML
    private void schedulerClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_SCHEDULER,this.rightPage);
    }


}
