package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import models.Pagesat;
import services.PagesaService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class AdminPaymentController {
    @FXML private Text todayText;
    @FXML private Text monthText;
    @FXML private Text yearText;
    @FXML
    private ComboBox<String> statusiIRiComboBox;

    @FXML
    private LineChart<String, Number> PPM;  // LineChart



    @FXML
    private PieChart statistikatKandidateveChart;
    @FXML
    private TextField searchByName;
    @FXML
    private DatePicker from;
    @FXML
    private DatePicker to;
    @FXML
    private ComboBox<String> combobox1;
    @FXML
    private ComboBox<String> comboBox2;
    @FXML
    private Circle today;
    @FXML
    private Circle month;
    @FXML
    private Circle year;
    @FXML
    private Label paguar;
    @FXML
    private Label Pjeserisht;
    @FXML
    private Label Mbetur;
    @FXML
    private TableView<Pagesat> pagesatTable;
    @FXML
    private TableColumn<Pagesat, Integer> IdCol;
    @FXML
    private TableColumn<Pagesat, Integer> idCandidatCol;
    @FXML
    private TableColumn<Pagesat, Double> ShumaCol;
    @FXML
    private TableColumn<Pagesat, String> DataEPagesesCol;
    @FXML
    private TableColumn<Pagesat, String> MetodaPagesesCol;
    @FXML
    private TableColumn<Pagesat, String> StatusiPagese;

    private ObservableList<Pagesat> pagesatList = FXCollections.observableArrayList();
    private PagesaService pagesatService = new PagesaService();
    private XYChart.Series<String, Number> paguarSeries = new XYChart.Series<>();
    private XYChart.Series<String, Number> pjeserishtSeries = new XYChart.Series<>();
    private XYChart.Series<String, Number> mbeturSeries = new XYChart.Series<>();

    public void loadTableDatat() {
        pagesatList.clear();
        pagesatList.addAll(pagesatService.getAllPagesat());
        IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCandidatCol.setCellValueFactory(new PropertyValueFactory<>("idKandidat"));
        ShumaCol.setCellValueFactory(new PropertyValueFactory<>("shuma"));
        DataEPagesesCol.setCellValueFactory(new PropertyValueFactory<>("dataPageses"));
        MetodaPagesesCol.setCellValueFactory(new PropertyValueFactory<>("metodaPageses"));
        StatusiPagese.setCellValueFactory(new PropertyValueFactory<>("statusiPageses"));
        pagesatTable.setItems(pagesatList);
    }
    @FXML
    private void updateLineChartData() throws SQLException {
        paguarSeries.getData().clear();
        pjeserishtSeries.getData().clear();
        mbeturSeries.getData().clear();

        List<Integer> paguarData = pagesatService.getPagesatDataByStatus("Paguar");
        List<Integer> pjeserishtData = pagesatService.getPagesatDataByStatus("Pjeserisht");
        List<Integer> mbeturData = pagesatService.getPagesatDataByStatus("Mbetur");
        System.out.println("Paguar Data: " + paguarData);
        System.out.println("Pjeserisht Data: " + pjeserishtData);
        System.out.println("Mbetur Data: " + mbeturData);

        for (int i = 0; i < paguarData.size(); i++) {
            paguarSeries.getData().add(new XYChart.Data<>("Data" + (i + 1), paguarData.get(i)));
            pjeserishtSeries.getData().add(new XYChart.Data<>("Data" + (i + 1), pjeserishtData.get(i)));
            mbeturSeries.getData().add(new XYChart.Data<>("Data" + (i + 1), mbeturData.get(i)));
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vërejtje");
            alert.setHeaderText(null);
            alert.setContentText("Të gjitha fushat duhet të plotësohen!");
            alert.showAndWait();
            return;
        }

        List<Pagesat> filteredPagesat = pagesatService.getFilteredPagesat(name, fromDate, toDate, metodaPageses, statusiPageses);

        pagesatList.clear();
        pagesatList.addAll(filteredPagesat);
        pagesatTable.setItems(pagesatList);
    }
    @FXML
    private void updateCircleIndicators() throws SQLException {
        LocalDate todayDate = LocalDate.now();
        YearMonth currentMonth = YearMonth.now();

        int todayCount = pagesatService.countPagesatOnDate(todayDate);
        int monthCount = pagesatService.countPagesatInMonth(currentMonth);
        int yearCount = pagesatService.countPagesatInYear(todayDate.getYear());

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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vërejtje");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem zgjidhni një pagesë dhe një status të ri.");
            alert.showAndWait();
            return;
        }

        try {
            pagesatService.updateStatusiPageses(selectedPagesa.getId(), statusiRi);
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Sukses");
            success.setHeaderText(null);
            success.setContentText("Statusi i pagesës u përditësua me sukses!");
            success.showAndWait();
            loadTableDatat();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Gabim");
            error.setHeaderText(null);
            error.setContentText("Ndodhi një gabim gjatë përditësimit të statusit.");
            error.showAndWait();
        }
    }

    @FXML
    public void initialize() {
        this.loadTableDatat();
        combobox1.setItems(FXCollections.observableArrayList("Cash", "Kartë", "Online"));
        comboBox2.setItems(FXCollections.observableArrayList("Paguar", "Pjesërisht", "Mbetur"));
        statusiIRiComboBox.setItems(FXCollections.observableArrayList("Paguar", "Pjeserisht", "Mbetur"));
        try {
            updateCircleIndicators();
            updateLineChartData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
