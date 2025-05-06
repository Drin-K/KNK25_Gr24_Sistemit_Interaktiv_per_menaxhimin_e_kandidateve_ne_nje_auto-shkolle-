package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.feedBack.CreateFeedBackDto;
import models.FeedBack;
import services.FeedBackService;
import services.UserContext;

import java.time.LocalDate;

public class FeedbackConsumatorController {
    private int idKandidat = UserContext.getUserId();
    private final FeedBackService feedBackService;
    private LocalDate date = LocalDate.now();
    public FeedbackConsumatorController(){
        this.feedBackService = new FeedBackService();
    }
    private int selectedRating = 0; //Ruan rating nga kandidati
    @FXML
    private TextArea commentField;
    @FXML
    private ToggleGroup ratingInstructor;
    @FXML
    private TextField instructorId;
    @FXML
    private void handleRatingSelected(){
        RadioButton selected = (RadioButton) ratingInstructor.getSelectedToggle();
        if (selected != null) {
            String rating = selected.getText();
            this.selectedRating = Integer.parseInt(rating);
            System.out.println("Selected rating: " + rating);
        } else {
            System.out.println("Asnjë rating nuk është selektuar.");
        }
    }
    @FXML
    private void submitbtn(){
        int idinstructor = Integer.parseInt(instructorId.getText().trim());
        try{
            CreateFeedBackDto dto = new CreateFeedBackDto(idKandidat,idinstructor, date,selectedRating,commentField.getText());
            FeedBack result = feedBackService.create(dto);
            System.out.println("U krijua me sukses " + result.getId());
            showAlert("Notification","Feedback u ruajt me sukses !");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
