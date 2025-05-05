package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import services.KandidateService;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

public class AdminPatentaController {
    @FXML
    private CheckBox checkStatusiProcesit, checkPagesa, checkTestet, checkRegjistrimi;

    @FXML
    private Button btnShfaqKandidatet;

    @FXML
    private TableView<Kandidatet> tableKandidatet;

    @FXML
    private TableColumn<Kandidatet, String> colEmri, colMbiemri, colEmail, colTelefoni, colDataRegjistrimit,  colStatusi;
    private final KandidateService kandidatService = new KandidateService();


    @FXML
    private void initialize()throws SQLException {
        colEmri.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMbiemri.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefoni.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colDataRegjistrimit.setCellValueFactory(new PropertyValueFactory<>("dataRegjistrimit"));
        colStatusi.setCellValueFactory(new PropertyValueFactory<>("statusiProcesit"));
        btnShfaqKandidatet.setOnAction(e -> shfaqKandidatetMeTeDrejte());

    }

    private void shfaqKandidatetMeTeDrejte() {
        boolean statusiProcesit = checkStatusiProcesit.isSelected();
        boolean pagesa = checkPagesa.isSelected();
        boolean testet = checkTestet.isSelected();
        boolean regjistrimi = checkRegjistrimi.isSelected();

        try {
            List<Kandidatet> listaKandidateve = kandidatService.gjejKandidatetMeTeDrejte(
                    statusiProcesit, pagesa, testet, regjistrimi
            );

            tableKandidatet.getItems().clear(); // Fshin të dhënat ekzistuese
            tableKandidatet.getItems().addAll(listaKandidateve); // Shton listën e re

        } catch (Exception ex) {
            ex.printStackTrace();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Gabim gjatë ngarkimit");
            alert.setHeaderText(null);
            alert.setContentText("Nuk mund të shfaqen kandidatët. Ju lutem provoni përsëri.");
            alert.showAndWait();
        }
    }
    @FXML
    private void aprovoPatenten() {
        Kandidatet kandidat = tableKandidatet.getSelectionModel().getSelectedItem();

        boolean statusiProcesit = checkStatusiProcesit.isSelected();
        boolean pagesa = checkPagesa.isSelected();
        boolean testet = checkTestet.isSelected();
        boolean regjistrimi = checkRegjistrimi.isSelected();

        if (statusiProcesit && pagesa && testet && regjistrimi) {
            if (kandidat != null) {
                try {
                    // Aprovoni patentën dhe shfaqni një mesazh
                        kandidatService.aprovoPatenten(kandidat.getIdUser());
                        tableKandidatet.getItems().remove(kandidat);
                        shfaqKandidatetMeTeDrejte();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Suksesi");
                        alert.setHeaderText(null);
                        alert.setContentText("Patenta është aprovuar me sukses.");
                        alert.showAndWait();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Gabim");
                    alert.setHeaderText(null);
                    alert.setContentText("Nuk mund të aprovohet patenta. Provoni përsëri.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Kujdes");
                alert.setHeaderText(null);
                alert.setContentText("Zgjidhni një kandidat për të aprovuar patentën.");
                alert.showAndWait();
            }
        } else {
            // Nëse një nga checkbox-ët nuk është i kontrolluar
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kujdes");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem kontrolloni të gjitha kutitë e kontrollit për të aprovuar patentën.");
            alert.showAndWait();
        }
    }

}


