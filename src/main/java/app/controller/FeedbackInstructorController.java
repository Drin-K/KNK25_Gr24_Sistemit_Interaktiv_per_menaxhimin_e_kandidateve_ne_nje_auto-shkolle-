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

    private final UserContext userContext = new UserContext();
    private final FeedBackService feedBackService = new FeedBackService();

    @FXML private DatePicker dateField;
    @FXML private TableView<FeedBack> feedback;
    @FXML private TableColumn<FeedBack, String> commentArea;
    @FXML private TableColumn<FeedBack, Integer> pointsArea;
    @FXML private Button filterBttn;
    @FXML
    public void initialize() {
        commentArea.setCellValueFactory(new PropertyValueFactory<>("koment"));
        pointsArea.setCellValueFactory(new PropertyValueFactory<>("vlersimi"));
        filterBttn.setOnAction(e -> handleFilter());
    }
   @FXML
    private void handleFilter() {
        LocalDate selectedDate = dateField.getValue();

        if (selectedDate == null) {
            showAlert("Please select a date first.");
            return;
        }

        try {
            int staffId = userContext.getUserId();
            System.out.println("UserContext returned staffId: " + staffId);
            System.out.println("Filtering feedback for date: " + selectedDate);

            List<FeedBack> feedbacks = feedBackService.getFeedbacksByDate(staffId, selectedDate);

            if (feedbacks.isEmpty()) {
                showAlert("No feedbacks found for selected date.");
            } else {
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
            showAlert("Error loading feedbacks: " + e.getMessage());
            e.printStackTrace();
        }
//        System.out.println("Selected Date (raw): " + selectedDate);
//        System.out.println("Staff ID from UserContext: " + staffId);

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
