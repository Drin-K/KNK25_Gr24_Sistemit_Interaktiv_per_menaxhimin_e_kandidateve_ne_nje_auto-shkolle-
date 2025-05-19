package app.controller;

import app.controller.base.BaseController;
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

public class RaportiProgresitCandidateController extends BaseController {

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
        configureTable(raportiprogresit,List.of(dateArea,theoryPointsArea,practicalPointsArea,commentArea,generalPerformanceArea),new String[]{"dataRaportit","piketTeorike","piketPraktike","komentet","performancaGjenerale"});
        findBtn.setOnAction(e -> handleFind());
    }

    @FXML
    private void handleFind() {

        try {
            int candidateId = UserContext.getUserId();
            int staffID = Integer.parseInt(stafField.getText());
            List<RaportiProgresit> raportet = raportiService.getRaportetById(staffID,candidateId);

            if (raportet.isEmpty()) {
                showAlert(Alert.AlertType.WARNING,"Warning","No progress reports found for your ID.");
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
            showAlert(Alert.AlertType.WARNING,"Warning",e.getMessage());
            e.printStackTrace();
        }
    }
}
