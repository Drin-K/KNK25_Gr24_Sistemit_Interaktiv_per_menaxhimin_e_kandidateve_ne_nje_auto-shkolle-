package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Dto.orari.UpdateOrariDto;
import models.Orari;
import services.OrariService;

public class EditScheduleController {
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
        // 1) Parse session ID
        int sessionId1;
        try {
            sessionId1 = Integer.parseInt(sessionId.getText().trim());
        } catch (NumberFormatException e) {
            alertError("ID e sesionit duhet të jetë një numër i vlefshëm.");
            return;
        }

        try {

            Orari existing = orariService.getById(sessionId1);
            if (existing == null) {
                alertError("Nuk u gjet orari me ID " + sessionId1);
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


            alertInfo("Orari u përditësua me sukses. Statusi i ri: " + updated.getStatusi());

        } catch (Exception ex) {
            alertError("Gabim gjatë përditësimit: " + ex.getMessage());
        }
    }

    private void alertInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.setHeaderText(null);
        a.showAndWait();
    }

    private void alertError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.setHeaderText(null);
        a.showAndWait();
    }
}
