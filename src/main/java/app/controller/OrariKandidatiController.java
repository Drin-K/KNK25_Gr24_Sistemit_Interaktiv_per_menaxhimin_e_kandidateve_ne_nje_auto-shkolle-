package app.controller;
import app.controller.base.BaseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Orari;
import repository.OrariRepository;
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
    public final OrariRepository orariRepository;
    public OrariKandidatiController(){
        this.orariService=new OrariService();
        this.kandidatId=UserContext.getUserId();
        this.orariRepository=new OrariRepository();
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
        llojiColumn.setCellValueFactory(new PropertyValueFactory<>("llojiMesimit"));
        statusiColumn.setCellValueFactory(new PropertyValueFactory<>("statusi"));
    }

    @FXML
    private void kerkoOraretPerDate() {
        LocalDate dataZgjedhur = dataPicker.getValue();
        if (dataZgjedhur == null) {
            showAlert(Alert.AlertType.ERROR,"Gabim", "Zgjidhni një datë.");
            return;
        }
        List<Orari> oraretEFiltruara = orariService.gjejOraretPerKandidat(dataZgjedhur, kandidatId);
        shfaqOraret(oraretEFiltruara);
    }


    private void shfaqOraret(List<Orari> oraret) {
        orariTable.getItems().setAll(oraret);
        if (oraret.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION,"Informacion", "Nuk u gjetën orare.");
        }
    }

    private void updateProgresiChart() {
        try {
            int teori = orariService.numeroSesione(kandidatId, "Teori", "Përfunduar");
            int praktike = orariService.numeroSesione(kandidatId, "Praktikë", "Përfunduar");

            progresiChart.getData().setAll(
                    new PieChart.Data("Teori (" + teori + "/15)", teori),
                    new PieChart.Data("Praktikë (" + praktike + "/20)", praktike)
            );

            String status = (teori >= 15 && praktike >= 20) ? "GATI PËR PROVIM" : "NUK JENI GATI PËR PROVIM";
            progresiChart.setTitle("Progresi i Mësimeve\nStatus: " + status);

        } catch (Exception e) {
            showAlert(Alert.AlertType.INFORMATION,"Gabim", "Gabim gjatë ngarkimit të progresit.");
        }
    }


}
