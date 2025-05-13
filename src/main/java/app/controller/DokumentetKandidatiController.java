package app.controller;

import app.controller.base.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Dto.dokumentet.CreateDokumentetDto;
import services.DokumentService;
import services.SceneManager;
import services.UserContext;
import utils.SceneLocator;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DokumentetKandidatiController extends BaseController implements Initializable {
    @FXML
    private ComboBox<String> chooseDocumentType;
    Stage stage = (Stage) SceneManager.getInstance(SceneLocator.getCurrentRightPage()).getScene().getWindow();
    private final DokumentService dokumentService;
    public DokumentetKandidatiController(){
        this.dokumentService = new DokumentService();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooseDocumentType.getItems().setAll("Leternjoftim", "Aplikim", "Certifikate Mjekësore", "Foto");
    }
    @FXML
    private void handleUploadDokument(ActionEvent event) {
        String llojiDokumentit = chooseDocumentType.getValue();
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            int kandidatId = UserContext.getUserId();

            CreateDokumentetDto dto = new CreateDokumentetDto(
                    kandidatId,
                    llojiDokumentit,
                    selectedFile.getName(),
                    LocalDate.now()
            );

            try {
                dokumentService.uploadDokument(dto, selectedFile);
                showAlert(Alert.AlertType.INFORMATION,"Notification","File uploaded successfully");
                System.out.println("Dokumenti u ngarkua me sukses!");
            } catch (Exception e) {
                System.err.println("Gabim gjatë ngarkimit: " + e.getMessage());
                showAlert(Alert.AlertType.ERROR,"Error","Error while uploading the files !");
            }
        }
    }
}
