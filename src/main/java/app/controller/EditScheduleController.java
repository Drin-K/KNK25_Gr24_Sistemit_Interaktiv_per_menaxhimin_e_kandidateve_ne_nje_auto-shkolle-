package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Dto.orari.UpdateOrariDto;
import models.Orari;
import services.OrariService;

public class EditScheduleController extends BaseController {
    @FXML private TextField sessionId;

    private final OrariService orariService = new OrariService();

    @FXML
    private void perfundoClick() {
        changeStatus("Përfunduar");
    }

    @FXML
    private void anuluarClick() {
        changeStatus("Anuluar");
    }

    private void changeStatus(String newStatus) {

        int sessionId1;
        try {
            sessionId1 = Integer.parseInt(sessionId.getText().trim());
        } catch (NumberFormatException e) {
            this.showAlert(Alert.AlertType.ERROR,null,"ID e sesionit duhet të jetë një numër i vlefshëm.");
            return;
        }

        try {

            Orari existing = orariService.getById(sessionId1);
            if (existing == null) {
                this.showAlert(Alert.AlertType.ERROR,null,"Nuk u gjet orari me ID " + sessionId1);
                return;
            }


            UpdateOrariDto dto = new UpdateOrariDto(
                    sessionId1,
                    existing.getIdKandidat(),
                    existing.getIdStaf(),
                    existing.getDataSesionit(),
                    existing.getOraFillimit(),
                    existing.getOraPerfundimit(),
                    existing.getLlojiMesimit(),
                    newStatus,
                    existing.getIdAutomjet()
            );


            Orari updated = orariService.update(sessionId1, dto);

             this.showAlert(Alert.AlertType.INFORMATION,null,"Orari u përditësua me sukses. Statusi i ri: " + updated.getStatusi());
             this.sessionId.setText("");

        } catch (Exception ex) {
            this.showAlert(Alert.AlertType.ERROR,null,"Gabim gjatë përditësimit: " + ex.getMessage());

        }
    }


}
