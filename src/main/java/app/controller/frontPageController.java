package app.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import services.SceneManager;
import utils.SceneLocator;

public class frontPageController {
    @FXML
    private AnchorPane rightPage;
    @FXML
    private void categoryClick()throws Exception{

        SceneManager.load(SceneLocator.CATEGORY_PAGE,this.rightPage);

    }
    @FXML
    private void homeClick()throws Exception{
        SceneManager.load(SceneLocator.HOME_PAGE,this.rightPage);
    }
    @FXML
    private void schedulerClick()throws Exception{
        SceneManager.load(SceneLocator.SCHEDULER_PAGE,this.rightPage);
    }
    @FXML
    private void testClick()throws Exception{
        SceneManager.load(SceneLocator.TESTS_PAGE,this.rightPage);
    }
    @FXML
    private void paymentsClick()throws Exception{
        SceneManager.load(SceneLocator.PAYMENTS_PAGE,this.rightPage);
    }
    @FXML
    private void progressClick()throws Exception{
        SceneManager.load(SceneLocator.PROGRESS_PAGE,this.rightPage);
    }
    @FXML
    private void feedbackClick()throws Exception{
        SceneManager.load(SceneLocator.FEEDBACK_PAGE,this.rightPage);
    }
    @FXML
    private void profileClick()throws Exception{
        SceneManager.load(SceneLocator.PROFILE_PAGE,this.rightPage);
    }
}
