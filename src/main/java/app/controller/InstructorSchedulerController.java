package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Dto.orari.CreateOrariDto;
import models.Orari;
import services.AutomjetService;
import services.OrariService;
import services.SceneManager;
import services.UserContext;
import utils.SceneLocator;

import java.time.LocalDate;
import java.time.LocalTime;

public class InstructorSchedulerController {
    AutomjetService automjetService;
    UserContext context;
    private final OrariService orariService;

    public InstructorSchedulerController() throws Exception {
        this.orariService=new OrariService();
        this.context=new UserContext();
    }
    String llojiMesimit;
    @FXML
     private AnchorPane rightPane;
    @FXML
    private TextField candidateId;
    @FXML
    private DatePicker dateForLesson;
    @FXML
    private TextField txtStart;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtSpecify;
    Integer vehicleId = automjetService.getFirstAvailableVehicleIdByLloji("vetura");

    @FXML
    private void scheduleClick ()throws Exception{
       try{
           Orari orari= this.orariService.create(this.getScheduleInputData());
           System.out.println("Schedule is inserted successfully");
           System.out.println("Candidate ID"+orari.getIdKandidat());
           this.cleanFields();
       }
       catch (Exception e){
           System.out.println("---Error Scheduling---"+e.getMessage());
       }
    }
    private CreateOrariDto getScheduleInputData(){
        int candidateId=Integer.parseInt(this.candidateId.getText());
        String textSpecify=this.txtSpecify.getText();
        LocalDate dateForLesson= this.dateForLesson.getValue();
        LocalTime startTime=LocalTime.parse(txtStart.getText());
        LocalTime endTime=LocalTime.parse(txtEnd.getText());
        return new CreateOrariDto(candidateId, UserContext.getUserId(), dateForLesson,startTime,endTime,this.llojiMesimit,"Planifikuar",vehicleId);

    }
    private void cleanFields(){
        this.candidateId.setText("");
        this.txtSpecify.setText("");
        this.txtEnd.setText("");
        this.txtStart.setText("");
        this.dateForLesson.setValue(null);
    }
    @FXML
    private void  removeScheduleClick()throws Exception{
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, this.rightPane);
    }

    @FXML
    private void teoriClick()throws Exception{
        this.llojiMesimit="Teori";
    }

    @FXML
    private  void praktikClick()throws Exception{
       this.llojiMesimit="Praktik";
    }

    @FXML
    private  void golf4Click()throws Exception{
        this.txtSpecify.setText("Golf 4");
    }

    @FXML
    private  void bmwClick()throws Exception{
        this.txtSpecify.setText("BMW");
    }

    @FXML
    private  void mercedesClick()throws Exception{
        this.txtSpecify.setText("Mercedes");
    }

    @FXML
    private  void glof7Click()throws Exception{
        this.txtSpecify.setText("Golf 7");
    }
    @FXML
    private void editClick()throws Exception{
        SceneManager.load("/app/stafiFXML/edit_scheduler.fxml", rightPane);
    }
}
