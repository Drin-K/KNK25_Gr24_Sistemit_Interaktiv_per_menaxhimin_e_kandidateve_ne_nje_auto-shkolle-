package app.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import models.Pagesat;

public class AdminPaymentController {
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

}
