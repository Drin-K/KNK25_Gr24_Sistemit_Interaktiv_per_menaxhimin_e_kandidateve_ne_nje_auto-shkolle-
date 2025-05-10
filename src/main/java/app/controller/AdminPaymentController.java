package app.controller;

import app.controller.base.BaseController;
import app.controller.components.CommonTables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import models.Pagesat;
import repository.PagesatRepository;
import services.PagesaService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public class AdminPaymentController extends BaseController {
    @FXML private Text todayText, monthText, yearText;
    @FXML private ComboBox<String> statusiIRiComboBox, combobox1, comboBox2;
    @FXML private LineChart<String, Number> PPM;
    @FXML private TextField searchByName;
    @FXML private DatePicker from, to;
    @FXML private Circle today, month, year;
    @FXML private TableView<Pagesat> pagesatTable;
    @FXML private TableColumn<Pagesat, Integer> IdCol, idCandidatCol;
    @FXML private TableColumn<Pagesat, Double> ShumaCol;
    @FXML private TableColumn<Pagesat, String> DataEPagesesCol, MetodaPagesesCol, StatusiPagese;


    private final ObservableList<Pagesat> pagesatList;
    private final PagesatRepository pagesatRepository;
    private final XYChart.Series<String, Number> paguarSeries;
    private final XYChart.Series<String, Number> pjeserishtSeries;
    private final XYChart.Series<String, Number> mbeturSeries;
public AdminPaymentController(){
    this.pagesatList= FXCollections.observableArrayList();
    this.pagesatRepository=new PagesatRepository();
    this.paguarSeries=new XYChart.Series<>();
    this.pjeserishtSeries=new XYChart.Series<>();
    this.mbeturSeries=new XYChart.Series<>();
}
    @FXML
    public void initialize() {
        configurePagesatTable();
        initializeComboBoxes();
        loadTableDatat();
        initializeChartsAndIndicators();
    }

    private void configurePagesatTable() {
        configureTable(
                pagesatTable,
                List.of(IdCol, idCandidatCol, ShumaCol, DataEPagesesCol, MetodaPagesesCol, StatusiPagese),
                new String[]{"id", "idKandidat", "shuma", "dataPageses", "metodaPageses", "statusiPageses"}
        );
    }

    private void initializeComboBoxes() {
        combobox1.getItems().setAll("Cash", "Online");
        comboBox2.getItems().setAll("Paguar", "Pjesërisht", "Mbetur");
        statusiIRiComboBox.getItems().setAll("Paguar", "Pjeserisht", "Mbetur");
    }

    private void initializeChartsAndIndicators() {
        try {
            updateCircleIndicators();
            updateLineChartData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadTableDatat() {
        pagesatList.clear();
        pagesatList.addAll(pagesatRepository.getAll());
        pagesatTable.setItems(pagesatList);
    }

    @FXML
    private void updateLineChartData() throws SQLException {
        paguarSeries.getData().clear();
        pjeserishtSeries.getData().clear();
        mbeturSeries.getData().clear();

        Map<String, Integer> pagesatCount = pagesatRepository.getPagesatCountByStatus();

        if (pagesatCount.containsKey("Paguar")) {
            paguarSeries.getData().add(new XYChart.Data<>("Paguar", pagesatCount.get("Paguar")));
        }
        if (pagesatCount.containsKey("Pjeserisht")) {
            pjeserishtSeries.getData().add(new XYChart.Data<>("Pjeserisht", pagesatCount.get("Pjeserisht")));
        }
        if (pagesatCount.containsKey("Mbetur")) {
            mbeturSeries.getData().add(new XYChart.Data<>("Mbetur", pagesatCount.get("Mbetur")));
        }
        PPM.getData().clear();
        PPM.getData().addAll(paguarSeries, pjeserishtSeries, mbeturSeries);
    }


    @FXML
    public void filterPagesatIfValid() throws SQLException {
        String name = searchByName.getText();
        String fromDate = from.getValue() != null ? from.getValue().toString() : null;
        String toDate = to.getValue() != null ? to.getValue().toString() : null;
        String metodaPageses = combobox1.getValue();
        String statusiPageses = comboBox2.getValue();

        if (name.isEmpty() || fromDate == null || toDate == null || metodaPageses == null || statusiPageses == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "All fields must be filled!");
            return;
        }

        List<Pagesat> filteredPagesat = pagesatRepository.filterPagesat(name, fromDate, toDate, metodaPageses, statusiPageses);
        pagesatList.clear();
        pagesatList.addAll(filteredPagesat);
        pagesatTable.setItems(pagesatList);
    }

    @FXML
    private void updateCircleIndicators() throws SQLException {
        LocalDate todayDate = LocalDate.now();
        YearMonth currentMonth = YearMonth.now();

        int todayCount = pagesatRepository.countPagesatOnDate(todayDate);
        int monthCount = pagesatRepository.countPagesatInMonth(currentMonth);
        int yearCount = pagesatRepository.countPagesatInYear(todayDate.getYear());

        today.setFill(todayCount > 0 ? Color.GREEN : Color.LIGHTGRAY);
        month.setFill(monthCount > 0 ? Color.BLUE : Color.LIGHTGRAY);
        year.setFill(yearCount > 0 ? Color.GOLD : Color.LIGHTGRAY);

        todayText.setText(String.valueOf(todayCount));
        monthText.setText(String.valueOf(monthCount));
        yearText.setText(String.valueOf(yearCount));
    }

    @FXML
    private void ndryshoStatusin() {
        Pagesat selectedPagesa = pagesatTable.getSelectionModel().getSelectedItem();
        String statusiRi = statusiIRiComboBox.getValue();
        if (selectedPagesa == null || statusiRi == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a payment and a new status.");
            return;
        }
        try {
            pagesatRepository.updateStatusiPageses(selectedPagesa.getId(), statusiRi);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Payment status was successfully updated!");
            loadTableDatat();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while updating the payment status.");
        }
    }

}
