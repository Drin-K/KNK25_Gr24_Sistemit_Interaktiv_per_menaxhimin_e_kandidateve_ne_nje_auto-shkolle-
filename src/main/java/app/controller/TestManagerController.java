package app.controller;


import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Dto.testet.CreateTestetDto;
import models.Kandidatet;
import models.Testet;
import services.KandidateService;
import services.SceneManager;
import services.TestiService;
import services.UserContext;
import utils.SceneLocator;

import java.time.LocalDate;
import java.util.ArrayList;

//SELECT setval(
//  pg_get_serial_sequence('Testet','id'),
//  (SELECT MAX(id) FROM Testet) + 1
//);
public class TestManagerController extends BaseController {
    private TestiService testiService;
    private String llojiTestit;
    private  final KandidateService kandidateService;
   @FXML
    private TextField txtCandidateId, txtPiket;
   @FXML
   private DatePicker setDate;
   public TestManagerController(){
       this.testiService=new TestiService();
       this.kandidateService=new KandidateService();
   }
   @FXML
    private void teoriClick(){
     this.llojiTestit="Teori";
   }
   @FXML
    private void praktikClick(){
     this.llojiTestit="Praktikë";
   }
    @FXML
    private void setResultClick() {
        try {
            CreateTestetDto dto = getInput();
            if(!kandidateService.ekzistonKandidatMeId(dto.getIdKandidat())) {
                showAlert(Alert.AlertType.ERROR, "Error", "The candidate with this id doesn't exist.");
            }else{
            Testet saved = testiService.regjistroTestin(dto);


            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Exam Result");
            info.setHeaderText("Success");
            info.setContentText("Exam was registed with ID: " + saved.getId());
            info.showAndWait();

            cleanFields();}
        } catch (Exception ex) {

            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("Error");
            err.setHeaderText("The exam could not be registered.");
            err.setContentText("All fields must be filled!");
            err.showAndWait();
        }
    }
   private CreateTestetDto getInput(){
       int kandId = Integer.parseInt(this.txtCandidateId.getText().trim());
       int stafiId= UserContext.getUserId();
       String lloji=this.llojiTestit;
       LocalDate date = this.setDate.getValue();
       String rezultati=null;
       int piket=Integer.parseInt(this.txtPiket.getText().trim());
       if (piket>=85){
           rezultati="Kaluar";
       } else if (piket<85) {
           rezultati="Dështuar";
       }
       return new CreateTestetDto(kandId,stafiId,lloji,date,rezultati,piket);
   }
    private void cleanFields() {
        this.txtCandidateId.clear();
        this.txtPiket.clear();
        this.setDate.setValue(null);
        this.llojiTestit = null;
    }


}
