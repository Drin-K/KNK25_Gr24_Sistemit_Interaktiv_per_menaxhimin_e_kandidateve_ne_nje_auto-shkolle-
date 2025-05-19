package app.controller;
import app.controller.base.BaseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import models.Orari;
import services.OrariService;
import services.UserContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class OrariKandidatiController extends BaseController {

    @FXML private TableView<Orari> orariTable;
    @FXML private TableColumn<Orari, String> dataColumn;
    @FXML private TableColumn<Orari, String> oraFillimitColumn;
    @FXML private TableColumn<Orari, String> oraPerfundimitColumn;
    @FXML private TableColumn<Orari, String> llojiColumn;
    @FXML private TableColumn<Orari, String> statusiColumn;
    @FXML private DatePicker dataPicker;
    @FXML private PieChart progresiChart;

    private final OrariService orariService;
    private final int kandidatId;
    public OrariKandidatiController(){
        this.orariService=new OrariService();
        this.kandidatId=UserContext.getUserId();
    }

    @FXML
    public void initialize() {
        configureTableColumns();
        dataPicker.setValue(LocalDate.now());
        shfaqOraret(orariService.shikoOraretPerId(kandidatId));
        updateProgresiChart();
    }
    //SimpleStringProperty thjesht i tregon JavaFX-it se kjo është një vlerë
// që mund të vendoset në qeliza të tabelës si tekst.
    private void configureTableColumns() {

        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");
        dataColumn.setCellValueFactory(cellData -> {
            String data = cellData.getValue().getDataSesionit().format(dateFmt);
            return new SimpleStringProperty(data);
        });
        oraFillimitColumn.setCellValueFactory(cellData -> {
            String ora = cellData.getValue().getOraFillimit().format(timeFmt);
            return new SimpleStringProperty(ora);
        });
        oraPerfundimitColumn.setCellValueFactory(cellData -> {
            String ora = cellData.getValue().getOraPerfundimit().format(timeFmt);
            return new SimpleStringProperty(ora);
        });
        configureTable(orariTable,List.of(llojiColumn,statusiColumn),new String[]{"llojiMesimit","statusi"});
    }

    @FXML
    private void kerkoOraretPerDate() {
        LocalDate dataZgjedhur = dataPicker.getValue();
        if (dataZgjedhur == null) {
            showAlert(Alert.AlertType.ERROR,"Error", "Please select a date.");
            return;
        }
        List<Orari> oraretEFiltruara = orariService.gjejOraretPerKandidat(dataZgjedhur, kandidatId);
        shfaqOraret(oraretEFiltruara);
    }


    private void shfaqOraret(List<Orari> oraret) {
        orariTable.getItems().setAll(oraret);
        if (oraret.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION,"Information", "No schedules were found.");
        }
    }

    private void updateProgresiChart() {
        try {
            int teori = orariService.numeroSesione(kandidatId, "Teori", "Përfunduar");
            int praktike = orariService.numeroSesione(kandidatId, "Praktikë", "Përfunduar");

            progresiChart.getData().setAll(
                    new PieChart.Data("Theory (" + teori + "/15)", teori),
                    new PieChart.Data("Practice (" + praktike + "/20)", praktike)
            );

            String status = (teori >= 15 && praktike >= 20) ? "READY FOR THE EXAM" : "YOU ARE NOT READY FOR THE EXAM";
            progresiChart.setTitle("Lesson Progress\nStatus: " + status);

        } catch (Exception e) {
            showAlert(Alert.AlertType.INFORMATION,"Error", "Error while loading progress.");
        }
    }


}
