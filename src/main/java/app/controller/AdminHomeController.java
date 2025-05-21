package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import services.*;

import java.util.HashMap;
import java.util.Map;

import static utils.ChartData.getChartSeries;

public class AdminHomeController extends BaseController {

    @FXML
    private Label totalKandidat, totalStafi;
    @FXML
    private PieChart piechart;
    @FXML
    private LineChart<String, Number> patentatEleshuara;
    @FXML
    private BarChart<String, Number> pagesaTePapaguara, nrRegjistrimeve, PagesaTeKryera;
    @FXML
    private ListView<String> sesionetSot;

    private final PagesaService pagesaService;
    private final OrariService sesioniService;
    private final KandidateService kandidateService;
    private final StafiService stafiService;
    private final PatentaService patentaService;

    public AdminHomeController() {
        this.pagesaService = new PagesaService();
        this.sesioniService = new OrariService();
        this.kandidateService = new KandidateService();
        this.stafiService = new StafiService();
        this.patentaService = new PatentaService();
    }

    @FXML
    public void initialize() {
        loadDashboardData();
    }

    private void loadDashboardData() {
        updateTotalKandidat();
        updateTotalStaf();
        updatePieChart(this.kandidateService.countKandidatetByStatusiProcesit());
        updateLineChart(patentatEleshuara, patentaService.getLicensesIssuedSeries());
        updateBarChart(pagesaService.getUnpaidPaymentsChartData(), pagesaTePapaguara);
        updateBarChart(getChartSeries(kandidateService.getAllRegistrationsGroupedByMonth(), "Registrations"), nrRegjistrimeve);
        updateBarChart(getChartSeries(pagesaService.getPayments(), "Number Of Payments"), PagesaTeKryera);
        updateSessionsToday();
    }

    public void updateTotalKandidat() {
        try {
            totalKandidat.setText(String.valueOf(kandidateService.countKandidatet()));
        } catch (IllegalStateException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    public void updateTotalStaf() {
        try {
            totalStafi.setText(String.valueOf(stafiService.countStafi()));
        } catch (IllegalStateException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void updatePieChart(HashMap<String, Integer> data) {
        try {
            piechart.getData().clear();
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                piechart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }
        } catch (IllegalStateException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void updateLineChart(LineChart<String, Number> chart, XYChart.Series<String, Number> series) {
        chart.getData().clear();
        chart.getData().add(series);
    }

    private void updateBarChart(XYChart.Series<String, Number> series, BarChart<String, Number> chart) {
        chart.getData().clear();
        chart.getData().add(series);
    }

    private void updateSessionsToday() {
        sesionetSot.getItems().setAll(sesioniService.getPershkrimetESesioneveTeSotme());
    }

}
