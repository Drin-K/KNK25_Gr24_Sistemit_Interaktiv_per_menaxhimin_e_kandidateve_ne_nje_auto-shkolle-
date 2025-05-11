package app.controller;

import app.controller.base.BaseController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Kandidatet;
import services.KandidateService;
import services.PatentaService;

import java.util.List;

public class AdminPatentaController extends BaseController {

    @FXML
    private TableView<Kandidatet> tableKandidatet, tableKandidatet1;
    @FXML
    private TableColumn<Kandidatet, String> colEmri, colMbiemri, colEmail, colTelefoni, colDataRegjistrimit, colStatusi;
    @FXML
    private TableColumn<Kandidatet, String> colEmri1, colMbiemri1, colEmail1, colTelefoni1, colDataRegjistrimit1, colStatusi1;

    private final KandidateService kandidateService;
    private final PatentaService patentaService;

    public AdminPatentaController() {
        this.patentaService=new PatentaService();
        this.kandidateService=new KandidateService();
    }

    @FXML
    public void initialize() {
        configureTables(tableKandidatet, List.of(colEmri, colMbiemri, colEmail, colTelefoni, colDataRegjistrimit, colStatusi),
                new String[]{"name", "surname", "email", "phoneNumber", "dataRegjistrimit", "statusiProcesit"});

        configureTables(tableKandidatet1, List.of(colEmri1, colMbiemri1, colEmail1, colTelefoni1, colDataRegjistrimit1, colStatusi1),
                new String[]{"name", "surname", "email", "phoneNumber", "dataRegjistrimit", "statusiProcesit"});

        loadCandidateData();
    }

    private void configureTables(TableView<Kandidatet> table, List<TableColumn<Kandidatet, ?>> columns, String[] propertyNames) {
        configureTable(table, columns, propertyNames);
    }

    private void loadCandidateData() {
        tableKandidatet.setItems(FXCollections.observableArrayList(kandidateService.shfaqKandidatetMeTeDrejte()));
        tableKandidatet1.setItems(FXCollections.observableArrayList(kandidateService.shfaqKandidatetMeTeDrejtePaPagesa()));
    }

    @FXML
    private void aprovoPatentenClick() {
        Kandidatet kandidat = tableKandidatet.getSelectionModel().getSelectedItem();

        if (kandidat == null) {
            showAlert(Alert.AlertType.WARNING, "Attention", "Please select a candidate");
            return;
        }
        try {
            if (this.patentaService.aprovoPatenten(kandidat.getIdUser())) {
                loadCandidateData();
                showAlert(Alert.AlertType.INFORMATION, "Success!", "The license has been aproved");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "The license cannot be approved");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Database-related error");
        }
    }
}
