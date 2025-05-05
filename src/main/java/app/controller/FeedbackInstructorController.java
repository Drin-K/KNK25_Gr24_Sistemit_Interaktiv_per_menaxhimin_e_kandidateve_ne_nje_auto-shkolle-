// ✅ FIXED FeedbackInstructorController.java
package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.FeedBack;
import services.FeedBackService;
import services.UserContext;

import java.time.LocalDate;

public class FeedbackInstructorController {

    private UserContext userContext = new UserContext(); // Ensure this is properly set
    private final FeedBackService feedBackService;

    @FXML private TextField candField;
    @FXML private DatePicker dateField;
    @FXML private TableView<FeedBack> feedback;
    @FXML private TableColumn<FeedBack, String> commentArea;
    @FXML private TableColumn<FeedBack, Integer> pointsArea;
    @FXML private MenuItem candidateOption;
    @FXML private MenuItem dateOption;
    @FXML private MenuButton filterbyBttn;
    @FXML private Button filterBttn;

    public FeedbackInstructorController() {
        this.feedBackService = new FeedBackService();
    }

    private enum FilterType {
        CANDIDATE, DATE, BOTH, NONE
    }
    private FilterType currentFilter = FilterType.NONE;

    @FXML
    public void initialize() {
        commentArea.setCellValueFactory(new PropertyValueFactory<>("koment"));
        pointsArea.setCellValueFactory(new PropertyValueFactory<>("vlersimi"));
        filterBttn.setOnAction(e -> filterClick());
        candField.setDisable(true);
        dateField.setDisable(true);
    }

    @FXML
    private void candidateClick() {
        currentFilter = FilterType.CANDIDATE;
        filterbyBttn.setText("Candidate");
        candField.setDisable(false);
        dateField.setDisable(true);
    }

    @FXML
    private void dateClick() {
        currentFilter = FilterType.DATE;
        filterbyBttn.setText("Date");
        candField.setDisable(true);
        dateField.setDisable(false);
    }

    private void filterClick() {
        try {
            int instructorId = userContext.getUserId();
            Integer candidateId = null;
            LocalDate date = null;

            switch (currentFilter) {
                case CANDIDATE:
                    if (!candField.getText().isEmpty()) {
                        candidateId = Integer.parseInt(candField.getText());
                    }
                    break;
                case DATE:
                    date = dateField.getValue();
                    break;
                default:
                    return;
            }

            var feedbacks = feedBackService.getFilteredFeedbacks(candidateId, instructorId, date);
            feedback.getItems().setAll(feedbacks);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to filter feedback.").show();
        }
    }
}
