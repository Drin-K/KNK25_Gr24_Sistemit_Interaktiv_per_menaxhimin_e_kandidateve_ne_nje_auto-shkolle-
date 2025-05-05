package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.FeedBack;
import services.FeedBackService;
import services.UserContext;

import java.time.LocalDate;
import java.util.List;

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

            // Validate inputs before processing
            if (currentFilter == FilterType.CANDIDATE && !candField.getText().isEmpty()) {
                try {
                    candidateId = Integer.parseInt(candField.getText());
                } catch (NumberFormatException e) {
                    new Alert(Alert.AlertType.ERROR, "Invalid candidate ID format").show();
                    return;
                }
            } else if (currentFilter == FilterType.DATE) {
                date = dateField.getValue();
                if (date == null) {
                    new Alert(Alert.AlertType.ERROR, "Please select a valid date").show();
                    return;
                }
            }

            List<FeedBack> feedbacks = feedBackService.getFilteredFeedbacks(candidateId, instructorId, date);

            if (feedbacks.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "No feedbacks found with these filters").show();
            }

            feedback.getItems().setAll(feedbacks);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to filter feedback: " + e.getMessage()).show();
        }
    }
}

