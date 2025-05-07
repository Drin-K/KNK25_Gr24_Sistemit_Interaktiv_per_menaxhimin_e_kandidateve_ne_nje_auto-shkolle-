package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import services.KandidateService;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

public class AdminPatentaController {



    @FXML
    private TableView<Kandidatet> tableKandidatet;

    @FXML
    private TableColumn<Kandidatet, String> colEmri, colMbiemri, colEmail, colTelefoni, colDataRegjistrimit,  colStatusi;
    private final KandidateService kandidatService = new KandidateService();

    @FXML
    private TableView<Kandidatet> tableKandidatet1;


    @FXML
    private TableColumn<Kandidatet, String> colEmri1, colMbiemri1, colEmail1, colTelefoni1, colDataRegjistrimit1,  colStatusi1;
    @FXML
    private void initialize()throws SQLException {
        colEmri.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMbiemri.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefoni.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colDataRegjistrimit.setCellValueFactory(new PropertyValueFactory<>("dataRegjistrimit"));
        colStatusi.setCellValueFactory(new PropertyValueFactory<>("statusiProcesit"));
        colEmri1.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMbiemri1.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colEmail1.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefoni1.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colDataRegjistrimit1.setCellValueFactory(new PropertyValueFactory<>("dataRegjistrimit"));
        colStatusi1.setCellValueFactory(new PropertyValueFactory<>("statusiProcesit"));
        shfaqKandidatetMeTeDrejte();
        shfaqKandidatetMeTeDrejtePaPagesa();

    }

    private void shfaqKandidatetMeTeDrejte() {
        List<Kandidatet> kandidatet = kandidatService.shfaqKandidatetMeTeDrejte();
            ObservableList<Kandidatet> data = FXCollections.observableArrayList(kandidatet);
            tableKandidatet.setItems(data);
    }

    @FXML
    private void aprovoPatenten() {
        Kandidatet kandidat = tableKandidatet.getSelectionModel().getSelectedItem();

        if (kandidat != null) {
            int kandidatId = kandidat.getIdUser();
            try {
                boolean sukses = kandidatService.aprovoPatenten(kandidatId);
                if (sukses) {
                    tableKandidatet.refresh();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sukses");
                    alert.setHeaderText("Patenta është aprovuar");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Gabim");
                    alert.setHeaderText("Patenta nuk mund të aprovohet");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Gabim");
                alert.setHeaderText("Gabim në lidhje me bazën e të dhënave");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Përzgjedhje e pamjaftueshme");
            alert.setHeaderText("Ju lutem përzgjedhni një kandidat");
            alert.showAndWait();
        }
    }
    private void shfaqKandidatetMeTeDrejtePaPagesa() {
        List<Kandidatet> kandidatet = kandidatService.shfaqKandidatetMeTeDrejtePaPagesa();
        ObservableList<Kandidatet> data = FXCollections.observableArrayList(kandidatet);
        tableKandidatet1.setItems(data);
    }



}




