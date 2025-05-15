package app.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Dto.testet.CreateTestetDto;
import models.Testet;
import services.SceneManager;
import services.TestiService;
import services.UserContext;
import utils.SceneLocator;

import java.time.LocalDate;
//SELECT setval(
//  pg_get_serial_sequence('Testet','id'),
//  (SELECT MAX(id) FROM Testet) + 1
//);
public class TestManagerController {
    private TestiService testiService;
    private String llojiTestit;
   @FXML
    private TextField txtCandidateId, txtPiket;
   @FXML
   private DatePicker setDate;
   public TestManagerController(){
       this.testiService=new TestiService();
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
            Testet saved = testiService.regjistroTestin(dto);

            // Success alert
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Test Result");
            info.setHeaderText("Sukses");
            info.setContentText("Testi u regjistrua me ID: " + saved.getId());
            info.showAndWait();

            cleanFields();
        } catch (Exception ex) {
            // Error alert
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("Gabim");
            err.setHeaderText("Nuk mund të regjistrohet testi");
            err.setContentText(ex.getMessage());
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
