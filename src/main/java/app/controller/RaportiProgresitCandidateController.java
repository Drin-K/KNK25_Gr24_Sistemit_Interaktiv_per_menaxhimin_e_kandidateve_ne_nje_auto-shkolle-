package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import models.RaportiProgresit;
import services.RaportiProgresitService;
import services.UserContext;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class RaportiProgresitCandidateController {

    private final RaportiProgresitService raportiService = new RaportiProgresitService();
    @FXML
    private TextField stafField;
    @FXML
    private TableView<RaportiProgresit> raportiprogresit;
    @FXML
    private TableColumn<RaportiProgresit,Date> dateArea;
    @FXML
    private TableColumn<RaportiProgresit, Integer> theoryPointsArea;
    @FXML
    private TableColumn<RaportiProgresit, Integer> practicalPointsArea;
    @FXML
    private TableColumn<RaportiProgresit, Text> commentArea;
    @FXML
    private TableColumn<RaportiProgresit, Text> generalPerformanceArea;
    @FXML
    private Button findBtn;

    @FXML
    private void initialize() {
        dateArea.setCellValueFactory(new PropertyValueFactory<>("dataRaportit"));
        theoryPointsArea.setCellValueFactory(new PropertyValueFactory<>("piketTeorike"));
        practicalPointsArea.setCellValueFactory(new PropertyValueFactory<>("piketPraktike"));
        commentArea.setCellValueFactory(new PropertyValueFactory<>("komentet"));
        generalPerformanceArea.setCellValueFactory(new PropertyValueFactory<>("performancaGjenerale"));

        findBtn.setOnAction(e -> handleFind());
    }

    @FXML
    private void handleFind() {
        int candidateId = UserContext.getUserId();
        int staffID = Integer.parseInt(stafField.getText());
        System.out.println("Finding progress report for candidate ID: " + staffID);

        try {
            List<RaportiProgresit> raportet = raportiService.getRaportetById(staffID,candidateId);

            if (raportet.isEmpty()) {
                showAlert("No progress reports found for your ID.");
            } else {
                raportet.forEach(r -> System.out.println(
                        "Staf ID: " + r.getIdStaf() +
                                " | Date: " + r.getDataRaportit() +
                                " | Theory: " + r.getPiketTeorike() +
                                " | Practical: " + r.getPiketPraktike() +
                                " | Comment: " + r.getKomentet() +
                                " | Performance: " + r.getPerformancaGjenerale()
                ));
            }

            raportiprogresit.getItems().setAll(raportet);

        } catch (Exception e) {
            showAlert("Error loading progress reports: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
