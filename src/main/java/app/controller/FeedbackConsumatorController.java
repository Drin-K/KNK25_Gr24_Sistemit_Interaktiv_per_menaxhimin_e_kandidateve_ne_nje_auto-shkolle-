package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.feedBack.CreateFeedBackDto;
import models.FeedBack;
import models.Stafi;
import services.FeedBackService;
import services.StafiService;
import services.UserContext;

import java.time.LocalDate;
import java.util.ArrayList;

public class FeedbackConsumatorController extends BaseController {
    private int idKandidat = UserContext.getUserId();
    private final FeedBackService feedBackService;
    private final StafiService stafiService;
    private LocalDate date = LocalDate.now();
    public FeedbackConsumatorController(){
        this.feedBackService = new FeedBackService();
        this.stafiService=new StafiService();
    }
    private int selectedRating = 0;
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
        try{
            int idinstructor = Integer.parseInt(instructorId.getText().trim());
            if (!stafiService.ekzistonStafiMeId(idinstructor)){
                showAlert(Alert.AlertType.ERROR,"Error","The staf with this ID doesn't exist!");
            }else {
            CreateFeedBackDto dto = new CreateFeedBackDto(idKandidat,idinstructor, date,selectedRating,commentField.getText());
            FeedBack result = feedBackService.create(dto);
            System.out.println("U krijua me sukses " + result.getId());
            showAlert(Alert.AlertType.INFORMATION,"Notification","FeedBack created successfully");
            clearFields();}
        }
        catch (Exception e){
            showAlert(Alert.AlertType.WARNING,"Warning","U must fill all the fields!");
        }
    }
    private void clearFields(){
        instructorId.clear();
        commentField.clear();
        ratingInstructor.selectToggle(null);
        selectedRating = 0;
    }


}
