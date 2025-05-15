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

    @FXML private DatePicker dateField;
    @FXML private TableView<FeedBack> feedback;
    @FXML private TableColumn<FeedBack, String> commentArea;
    @FXML private TableColumn<FeedBack, Integer> pointsArea;
    @FXML private Button filterBttn;
    @FXML
    private void initialize() {
        // Configure table columns using the utility method
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
            showAlert("Please select a date first.");
            return;
        }

        try {
            int staffId = userContext.getUserId();
            System.out.println("UserContext returned staffId: " + staffId);
            System.out.println("Filtering feedback for date: " + selectedDate);

            List<FeedBack> feedbacks = feedBackService.getFeedbacks(staffId, selectedDate);

            if (feedbacks.isEmpty()) {
                showAlert("No feedbacks found for selected date.");
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
            showAlert("Error loading feedbacks: " + e.getMessage());
            e.printStackTrace();
        }
//        System.out.println("Selected Date (raw): " + selectedDate);
//        System.out.println("Staff ID from UserContext: " + staffId); // per debugim i kom lan si komente


    }

    private void showAlert(String message) {// mu ka nevojit nje alert customized
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
