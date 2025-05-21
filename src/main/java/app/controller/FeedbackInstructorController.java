package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.FeedBack;
import services.FeedBackService;
import services.UserContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class FeedbackInstructorController extends BaseController {

    private final UserContext userContext = new UserContext();
    private final FeedBackService feedBackService = new FeedBackService();

    @FXML
    private DatePicker dateField;
    @FXML
    private TableView<FeedBack> feedback;
    @FXML
    private TableColumn<FeedBack, String> commentArea;
    @FXML
    private TableColumn<FeedBack, Integer> pointsArea;
    @FXML
    private Button filterBttn;

    @FXML
    private void initialize() {
        configureTable(
                feedback,
                Arrays.asList(commentArea, pointsArea),
                new String[]{"koment", "vlersimi"}
        );

        filterBttn.setOnAction(e -> handleFilter());
    }

    @FXML
    private void handleFilter() {
        LocalDate selectedDate = dateField.getValue();

        if (selectedDate == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a date first.");
            return;
        }

        try {
            int staffId = userContext.getUserId();
            System.out.println("UserContext returned staffId: " + staffId);
            System.out.println("Filtering feedback for date: " + selectedDate);

            List<FeedBack> feedbacks = feedBackService.getFeedbacks(staffId, selectedDate);

            if (feedbacks.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning", "No feedbacks found for selected date.");
            } else {// a po funksionon mir
                feedbacks.forEach(fb -> System.out.println(
                        "Feedback ID: " + fb.getId() +
                                " | Staff: " + fb.getIdStaf() +
                                " | Date: " + fb.getDataFeedback() +
                                " | Comment: " + fb.getKoment() +
                                " | Points: " + fb.getVlersimi()
                ));
            }

            feedback.getItems().setAll(feedbacks);

        } catch (Exception e) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Error loading feedbacks: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
