package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Dto.raporti_progresit.CreateRaportiProgresitDto;
import models.RaportiProgresit;
import services.RaportiProgresitService;
import services.UserContext;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class CreateRaportiController {
    private RaportiProgresitService raportiProgresitService;
    @FXML private TextField txtID, txtTeoriPoints, txtPracticePoints, txtPerformanca;
    @FXML private TextArea txtComment;
    public CreateRaportiController(){
        this.raportiProgresitService=new RaportiProgresitService();
    }
    @FXML
    private void saveClick() {
        try {
            RaportiProgresit raportiProgresit = this.raportiProgresitService.create(this.getInput());
            this.cleanFields();

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Raporti was inserted successfully!\nID: " + raportiProgresit.getId());
            successAlert.showAndWait();

        } catch (Exception e) {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Failed to insert Raporti");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();

            System.out.println("Error inserting raporti. " + e.getMessage());
        }
    }

    private CreateRaportiProgresitDto getInput(){
        int kandId = Integer.parseInt(this.txtID.getText().trim());
        int stafiId= UserContext.getUserId();
        int piketTeori=Integer.parseInt(this.txtTeoriPoints.getText().trim());
        int piketPraktik=Integer.parseInt(this.txtPracticePoints.getText().trim());
        String performancaGjenerale=txtPerformanca.getText().trim();
        String Komenti=txtComment.getText();
        LocalDate date = LocalDate.now();
        return new CreateRaportiProgresitDto(kandId,stafiId,date,piketTeori,piketPraktik,Komenti,performancaGjenerale);
    }
    private void cleanFields() {
        txtID.setText("");
        txtTeoriPoints.setText("");
        txtPracticePoints.setText("");
        txtPerformanca.setText("");
        txtComment.setText("");
    }


}
