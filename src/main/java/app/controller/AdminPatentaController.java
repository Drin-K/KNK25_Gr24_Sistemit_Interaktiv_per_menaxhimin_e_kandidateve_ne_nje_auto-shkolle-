package app.controller;

import app.controller.base.BaseController;
import app.controller.components.CommonTables;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Kandidatet;
import services.KandidateService;

import java.util.List;

public class AdminPatentaController extends BaseController {

    @FXML private TableView<Kandidatet> tableKandidatet, tableKandidatet1;
    @FXML private TableColumn<Kandidatet, String> colEmri, colMbiemri, colEmail, colTelefoni, colDataRegjistrimit, colStatusi;
    @FXML private TableColumn<Kandidatet, String> colEmri1, colMbiemri1, colEmail1, colTelefoni1, colDataRegjistrimit1, colStatusi1;

    private final KandidateService kandidatService = new KandidateService();

    @FXML
    public void initialize() {
        configureTables(tableKandidatet, List.of(colEmri, colMbiemri, colEmail, colTelefoni, colDataRegjistrimit, colStatusi),
                new String[]{"name", "surname", "email", "phoneNumber", "dataRegjistrimit", "statusiProcesit"});

        configureTables(tableKandidatet1, List.of(colEmri1, colMbiemri1, colEmail1, colTelefoni1, colDataRegjistrimit1, colStatusi1),
                new String[]{"name", "surname", "email", "phoneNumber", "dataRegjistrimit", "statusiProcesit"});

        loadCandidateData();
    }

    private void configureTables(TableView<Kandidatet> table, List<TableColumn<Kandidatet, ?>> columns, String[] propertyNames) {
        CommonTables.configureTable(table, columns, propertyNames);
    }

    private void loadCandidateData() {
        tableKandidatet.setItems(FXCollections.observableArrayList(kandidatService.shfaqKandidatetMeTeDrejte()));
        tableKandidatet1.setItems(FXCollections.observableArrayList(kandidatService.shfaqKandidatetMeTeDrejtePaPagesa()));
    }

    @FXML
    private void aprovoPatentenClick() {
        Kandidatet kandidat = tableKandidatet.getSelectionModel().getSelectedItem();

        if (kandidat == null) {
            showAlert(Alert.AlertType.WARNING, "Attention", "Please select a candidate");
            return;
        }

        try {
            if (kandidatService.aprovoPatenten(kandidat.getIdUser())) {
                loadCandidateData();
                showAlert(Alert.AlertType.INFORMATION, "Success!", "The license has been aproved");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "The patent cannot be approved");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Database-related error");
        }
    }
}
