package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Patenta;
import services.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class AdminHomeController extends BaseController {

    @FXML private Label totalKandidat, totalStafi;
    @FXML private PieChart piechart;
    @FXML private LineChart<String, Number> patentatEleshuara;
    @FXML private BarChart<String, Number> pagesaTePapaguara, nrRegjistrimeve, PagesaTeKryera;
    @FXML private ListView<String> sesionetSot;

    private PatentaService patentaService;
    private PagesaService pagesaService;
    private OrariService sesioniService;
    private KandidateService kandidateService;
    private StafiService stafiService;

    public AdminHomeController() {
        this.patentaService = new PatentaService();
        this.pagesaService = new PagesaService();
        this.sesioniService = new OrariService();
        this.kandidateService = new KandidateService();
        this.stafiService = new StafiService();
    }

    @FXML
    public void initialize() {
        loadDashboardData();
    }

    private void loadDashboardData() {
        loadTotalCandidates();
        loadTotalStaff();
        loadChartsData();
        loadSessionsToday();
    }

    private void loadTotalCandidates() {
        totalKandidat.setText(String.valueOf(kandidateService.countKandidatet()));
    }

    private void loadTotalStaff() {
        totalStafi.setText(String.valueOf(stafiService.countStafi()));
    }

    private void loadChartsData() {
        loadInProgressCompleted();
        loadLicensesIssued();
        loadUnpaidPayments();
        loadRegistrationsTopPayments();
    }

    private void loadInProgressCompleted() {
        updatePieChart(kandidateService.getKandidatetStatusReport());
    }

    private void loadLicensesIssued() {
        try {
            XYChart.Series<String, Number> series = createSeries("Licenses Issued");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            List<Patenta> licenses = patentaService.getLicensesIssued();
            for (Patenta patenta : licenses) {
                String dateFormatted = patenta.getDataLeshimit().format(formatter);
                series.getData().add(new XYChart.Data<>(dateFormatted, 1));
            }
            updateLineChart(patentaService, patentatEleshuara, series);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Error loading licenses");
        }
    }


    private void loadUnpaidPayments() {
        updateBarChart(pagesaService.getUnpaidPaymentsChartData(), pagesaTePapaguara);
    }

    private void loadSessionsToday() {
        sesionetSot.getItems().clear();
        sesioniService.getSessionsToday().forEach(orari ->
                sesionetSot.getItems().add(String.format("%s: %s-%s",
                        orari.getLlojiMesimit(), orari.getOraFillimit(), orari.getOraPerfundimit())
                )
        );
    }

    private void loadRegistrationsTopPayments() {
        loadChartData(kandidateService.getRegistrationsLast12Months(), nrRegjistrimeve, "Registrations");
        loadChartData(pagesaService.getPaymentsLast12Months(), PagesaTeKryera, "Number Of Payments");
    }

    private void loadChartData(HashMap<String, Integer> data, BarChart<String, Number> chart, String seriesName) {
        XYChart.Series<String, Number> series = createSeries(seriesName);
        data.forEach((month, value) ->
                series.getData().add(new XYChart.Data<>(month, value))
        );
        updateBarChart(series, chart);
    }

    private XYChart.Series<String, Number> createSeries(String seriesName) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);
        return series;
    }

    private void updatePieChart(HashMap<String, Integer> data) {
        piechart.getData().clear();
        data.forEach((status, count) ->
                piechart.getData().add(new PieChart.Data(status, count))
        );
    }

    private void updateLineChart(PatentaService service, LineChart<String, Number> chart, XYChart.Series<String, Number> series) {
        chart.getData().clear();
        chart.getData().add(series);
    }

    private void updateBarChart(XYChart.Series<String, Number> series, BarChart<String, Number> chart) {
        chart.getData().clear();
        chart.getData().add(series);
    }

}
