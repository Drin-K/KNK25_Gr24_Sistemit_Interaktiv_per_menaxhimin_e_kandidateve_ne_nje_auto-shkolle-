package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Dto.raporti_progresit.CreateRaportiProgresitDto;
import models.RaportiProgresit;
import services.RaportiProgresitService;
import services.UserContext;



import java.time.LocalDate;

public class CreateRaportiController extends BaseController {
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
            String mesazhi="Raporti was inserted successfully! ID:"+raportiProgresit.getId();
            this.showAlert(Alert.AlertType.INFORMATION,"Success",mesazhi);

        } catch (Exception e) {
             this.showAlert(Alert.AlertType.ERROR,"Error","Failed to insert Raporti");

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
