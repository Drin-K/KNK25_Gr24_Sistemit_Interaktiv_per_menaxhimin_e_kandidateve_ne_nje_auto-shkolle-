package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import models.FeedBack;

import models.Dto.feedBack.CreateFeedBackDto;
import models.Dto.feedBack.UpdateFeedBackDto;
import models.Dto.regjistrimet.UpdateRegjistrimetDto;
import services.FeedBackService;
import services.UserContext;

import java.time.LocalDate;

public class FeedbackInstructorController {
    private UserContext userContext;

    private final FeedBackService feedBackService;
    @FXML
    private TextField txtcandId;
    @FXML
    private DatePicker dateField;
    @FXML
    private MenuButton filterbyBttn;
    @FXML
    private MenuItem candidateOption;
    @FXML
    private MenuItem dateOption;

    @FXML
    private TableView<FeedBack> feedbackTable;
    @FXML
    private TableColumn<FeedBack, String> commentColumn;
    @FXML
    private TableColumn<FeedBack, Integer> pointsColumn;


    public FeedbackInstructorController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }
//    private UpdateFeedBackDto getFeedbackInput(){
//        int kandId=Integer.parseInt(this.txtcandId.getText());
//        LocalDate date=this.dateField.getValue();
//        return  new UpdateFeedBackDto(kandId,userContext.getUserId(), date,)
//    }

    @FXML
    public void initialize() {
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("koment"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("vlersimi"));
    }

    @FXML
    private void filterClick() {
        try {
            Integer candidateId = txtcandId.getText().isEmpty() ? null : Integer.parseInt(txtcandId.getText());
            LocalDate date = dateField.getValue();

            int instructorId = userContext.getUserId();
            var feedbacks = feedBackService.getFilteredFeedbacks(candidateId, instructorId, date);

            feedbackTable.getItems().clear();
            feedbackTable.getItems().addAll(feedbacks);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
