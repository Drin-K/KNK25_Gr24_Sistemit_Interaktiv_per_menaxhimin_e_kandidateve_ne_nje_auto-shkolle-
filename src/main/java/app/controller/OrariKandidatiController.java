package app.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Orari;
import services.OrariService;
import services.UserContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrariKandidatiController {

    // Elementet FXML
    @FXML private TableView<Orari> orariTable;
    @FXML private TableColumn<Orari, String> dataColumn;
    @FXML private TableColumn<Orari, String> oraFillimitColumn;
    @FXML private TableColumn<Orari, String> oraPerfundimitColumn;
    @FXML private TableColumn<Orari, String> llojiColumn;
    @FXML private TableColumn<Orari, String> statusiColumn;

    @FXML private DatePicker dataPicker;
    @FXML private Button kerkoButton;
    @FXML private PieChart progresiChart;


    private OrariService orariService;
    private int kandidatId = UserContext.getUserId();
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @FXML
    public void initialize() {
        orariService = new OrariService();
        configureTableColumns();
        dataPicker.setValue(LocalDate.now());
        refreshOraret();
        updateProgresiChart();
    }

    private void configureTableColumns() {
        dataColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                formatDate(cellData.getValue().getDataSesionit())
        ));
        oraFillimitColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                formatTime(cellData.getValue().getOraFillimit())
        ));
        oraPerfundimitColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                formatTime(cellData.getValue().getOraPerfundimit())
        ));
        llojiColumn.setCellValueFactory(new PropertyValueFactory<>("llojiMesimit"));
        statusiColumn.setCellValueFactory(new PropertyValueFactory<>("statusi"));
    }

    private String formatDate(LocalDate date) {
        return date != null ? date.format(dateFormatter) : "";
    }

    private String formatTime(LocalTime time) {
        return time != null ? time.format(timeFormatter) : "";
    }

    private void refreshOraret() {
        try {
            List<Orari> oraret = orariService.shikoOraretPerId(kandidatId);
            ObservableList<Orari> observableList = FXCollections.observableArrayList(oraret);
            orariTable.setItems(observableList);
            if (oraret.isEmpty()) {
                showAlert("Informacion", "Nuk u gjetën orare për këtë kandidat");
            }
        } catch (Exception e) {
            showAlert("Gabim", "Ndodhi një gabim gjatë ngarkimit të orareve: " + e.getMessage());
        }
    }

    @FXML
    private void kerkoOraretPerDate() {
        LocalDate selectedDate = dataPicker.getValue();
        if (selectedDate == null) {
            showAlert("Gabim", "Ju lutem zgjidhni një datë");
            return;
        }
        try {
            List<Orari> filteredOrari = orariService.gjejOraretPerDate(selectedDate);
            ObservableList<Orari> observableList = FXCollections.observableArrayList(
                    filterOraret(filteredOrari)
            );
            orariTable.setItems(observableList);
            if (filteredOrari.isEmpty()) {
                showAlert("Informacion", "Nuk u gjetën orare për datën e zgjedhur");
            }
        } catch (Exception e) {
            showAlert("Gabim", "Ndodhi një gabim gjatë kërkimit: " + e.getMessage());
        }
    }

    private List<Orari> filterOraret(List<Orari> filteredOrari) {
        return filteredOrari.stream()
                .filter(o -> o.getIdKandidat() == kandidatId)
                .toList();
    }

    private void updateProgresiChart() {
        try {
            long teori = orariService.numeroSesione(kandidatId, "Teori", "Përfunduar");
            long praktike = orariService.numeroSesione(kandidatId, "Praktikë", "Përfunduar");

            PieChart.Data teoriData = new PieChart.Data("Teori (" + teori + "/15)", teori);
            PieChart.Data praktikeData = new PieChart.Data("Praktikë (" + praktike + "/20)", praktike);

            progresiChart.getData().clear();
            progresiChart.getData().addAll(teoriData, praktikeData);

            String status = (teori >= 15 && praktike >= 20) ? "GATI PËR PROVIM" : "NUK JENI GATI PËR PROVIM";
            progresiChart.setTitle("Progresi i Mësimeve\nStatus: " + status);

        } catch (Exception e) {
            showAlert("Gabim", "Ndodhi një gabim gjatë ngarkimit të progresit: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
