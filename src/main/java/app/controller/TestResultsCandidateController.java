package app.controller;


import app.controller.base.BaseController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Testet;
import services.TestiService;
import services.UserContext;

import java.time.LocalDate;
import java.util.List;

public class TestResultsCandidateController extends BaseController {
    private final TestiService testiService = new TestiService();


    @FXML private TableView<Testet> overallResults;
    @FXML private TableColumn<Testet, Integer> pointsView;
    @FXML private TableColumn<Testet, String> examType;
    @FXML private TableColumn<Testet, LocalDate> dateHeld;
    @FXML private TableColumn<Testet, String> resultStatus;

    @FXML
    private void initialize() {
        pointsView.setCellValueFactory(new PropertyValueFactory<>("piket"));
        examType.setCellValueFactory(new PropertyValueFactory<>("llojiTestit"));
        dateHeld.setCellValueFactory(new PropertyValueFactory<>("dataTestit"));
        resultStatus.setCellValueFactory(new PropertyValueFactory<>("rezultati"));
    }
    @FXML
    private void resultClick() throws Exception {
        try {
            List<Testet> testResults = testiService.getTestetByKandidatId(UserContext.getUserId());
            overallResults.setItems(FXCollections.observableArrayList(testResults));
        }
        catch (Exception e){
            showAlert(Alert.AlertType.WARNING,"Notification",e.getMessage());
        }
    }
}
