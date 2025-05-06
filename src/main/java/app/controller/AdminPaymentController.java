package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
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
    public void filterPagesatIfValid() throws SQLException {
        // Merrni vlerat nga fushat e filtrimit
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

        // Ndrysho ngjyrat sipas kushteve (opsionale)
        today.setFill(todayCount > 0 ? Color.GREEN : Color.LIGHTGRAY);
        month.setFill(monthCount > 0 ? Color.BLUE : Color.LIGHTGRAY);
        year.setFill(yearCount > 0 ? Color.GOLD : Color.LIGHTGRAY);

        todayText.setText(String.valueOf(todayCount));
        monthText.setText(String.valueOf(monthCount));
        yearText.setText(String.valueOf(yearCount));

    }

    @FXML
    public void initialize() {
        this.loadTableDatat();
        combobox1.setItems(FXCollections.observableArrayList("Cash", "Kartë", "Online"));
        comboBox2.setItems(FXCollections.observableArrayList("Paguar", "Pjesërisht", "Mbetur"));
        try {
            updateCircleIndicators();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
