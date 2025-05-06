package app.controller;

import app.Test;
import javafx.fxml.FXML;
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

public class TestManagerController {
    private TestiService testiService;
    private String llojiTestit;
    private String resultati;
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
    private void kaluarClick(){
      this.resultati="Kaluar";

   }
   @FXML
    private void deshtuarClick(){
      this.resultati="Dështuar";
   }
   @FXML
    private void setResultClick(){
       try{
           Testet test = this.testiService.regjistroTestin(this.getInput());
           System.out.println("Test Result inserted successfully!");
           System.out.println("Test Id: " + test.getId());
           this.cleanFields();
       }catch (Exception e){
           System.out.println("Error inserting user. " + e.getMessage());
       }
   }
   private CreateTestetDto getInput(){
       int kandId = Integer.parseInt(this.txtCandidateId.getText().trim());
       int stafiId= UserContext.getUserId();
       String lloji=this.llojiTestit;
       LocalDate date = this.setDate.getValue();
       String rezultati=this.resultati;
       int piket=Integer.parseInt(this.txtPiket.getText().trim());
       return new CreateTestetDto(kandId,stafiId,lloji,date,rezultati,piket);
   }
    private void cleanFields() {
        this.txtCandidateId.clear();
        this.txtPiket.clear();
        this.setDate.setValue(null);
        this.llojiTestit = null;
        this.resultati = null;
    }

}
