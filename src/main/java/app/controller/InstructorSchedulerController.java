package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.SceneManager;
import utils.SceneLocator;

public class InstructorSchedulerController {
    @FXML
     private AnchorPane rightPane;
    @FXML
    private TextField candidateId;
    @FXML
    private DatePicker dateForLesson;
    @FXML
    private TextField txtStart;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtSpecify;

    @FXML
    private void scheduleClick ()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, this.rightPane);
    }
    @FXML
    private void  removeScheduleClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, this.rightPane);
    }

    @FXML
    private void teoriClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, this.rightPane);
    }

    @FXML
    private  void praktikClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, this.rightPane);
    }

    @FXML
    private  void golf4Click()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, this.rightPane);
    }

    @FXML
    private  void bmwClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, this.rightPane);
    }

    @FXML
    private  void mercedesClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, this.rightPane);
    }

    @FXML
    private  void glof7Click()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, this.rightPane);
    }
    @FXML
    private void editClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, this.rightPane);
    }
}
