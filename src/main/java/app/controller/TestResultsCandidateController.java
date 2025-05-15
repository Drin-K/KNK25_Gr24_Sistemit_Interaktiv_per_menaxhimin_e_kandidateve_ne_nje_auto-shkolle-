package app.controller;


import app.controller.base.BaseController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Testet;
import services.TestiService;
import services.UserContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestResultsCandidateController extends BaseController {
    public  UserContext userContext;
    private final TestiService testiService = new TestiService();


    @FXML private TableView<Testet> overallResults;
    @FXML private TableColumn<Testet, Integer> pointsView;
    @FXML private TableColumn<Testet, String> examType;
    @FXML private TableColumn<Testet, LocalDate> dateHeld;
    @FXML private TableColumn<Testet, String> resultStatus;

    @FXML
    private void initialize() {
        configureTable(
               overallResults, // assuming your TableView is named `examTable`
                Arrays.asList(pointsView, examType, dateHeld, resultStatus),
                new String[] { "piket", "llojiTestit", "dataTestit", "rezultati" }
        );
    }

    @FXML
    private void resultClick() {
        userContext.getUserId(); // Replace with dynamic ID logic if needed
        List<Testet> testResults = testiService.getTestetByKandidatId(userContext.getUserId());
        overallResults.setItems(FXCollections.observableArrayList(testResults));
    }
}
