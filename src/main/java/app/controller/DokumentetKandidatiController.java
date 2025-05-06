package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Dto.dokumentet.CreateDokumentetDto;
import repository.DokumentetRepository;
import services.DokumentService;
import services.SceneManager;
import services.UserContext;
import utils.SceneLocator;

import java.io.File;
import java.time.LocalDate;

public class DokumentetKandidatiController {
    Stage stage = (Stage) SceneManager.getInstance(SceneLocator.getCurrentRightPage()).getScene().getWindow();
    private final DokumentService dokumentService;
    public DokumentetKandidatiController(){
        this.dokumentService = new DokumentService();
    }
    @FXML
    private void handleUploadDokument(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage); // your JavaFX window

        if (selectedFile != null) {
            String llojiDokumentit = "Leternjoftim"; // or fetch from user input
            int kandidatId = UserContext.getUserId();

            CreateDokumentetDto dto = new CreateDokumentetDto(
                    kandidatId,
                    llojiDokumentit,
                    selectedFile.getName(),
                    LocalDate.now()
            );

            try {
                dokumentService.uploadDokument(dto, selectedFile);
                showAlert("Notification","File u ngarkua me sukses");
                System.out.println("Dokumenti u ngarkua me sukses!");
            } catch (Exception e) {
                System.err.println("Gabim gjatë ngarkimit: " + e.getMessage());
            }
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

}
