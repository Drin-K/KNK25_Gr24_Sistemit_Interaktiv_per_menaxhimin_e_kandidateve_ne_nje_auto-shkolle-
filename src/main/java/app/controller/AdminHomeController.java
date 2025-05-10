package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import repository.KandidatetRepository;
import services.*;

import java.util.HashMap;
import java.util.Map;

public class AdminHomeController extends BaseController {

    @FXML private Label totalKandidat, totalStafi;
    @FXML private PieChart piechart;
    @FXML private LineChart<String, Number> patentatEleshuara;
    @FXML private BarChart<String, Number> pagesaTePapaguara, nrRegjistrimeve, PagesaTeKryera;
    @FXML private ListView<String> sesionetSot;

    private final PagesaService pagesaService = new PagesaService();
    private final OrariService sesioniService = new OrariService();
    private final KandidateService kandidateService = new KandidateService();
    private final StafiService stafiService = new StafiService();
    private final KandidatetRepository kandidatetRepository = new KandidatetRepository();
    private final PatentaService patentaService=new PatentaService();

    @FXML
    public void initialize() {
        loadDashboardData();
    }

    private void loadDashboardData() {
        totalKandidat.setText(String.valueOf(kandidateService.countKandidatet()));
        totalStafi.setText(String.valueOf(stafiService.countStafi()));
        updatePieChart(kandidatetRepository.countKandidatetByStatusiProcesit());
        updateLineChart(patentatEleshuara, getLicensesIssuedSeries());
        updateBarChart(pagesaService.getUnpaidPaymentsChartData(), pagesaTePapaguara);
        updateBarChart(getChartSeries(kandidateService.getRegistrationsLast12Months(), "Registrations"), nrRegjistrimeve);
        updateBarChart(getChartSeries(pagesaService.getPaymentsLast12Months(), "Number Of Payments"), PagesaTeKryera);
        updateSessionsToday();
    }

    private XYChart.Series<String, Number> getLicensesIssuedSeries() {
        try {
            Map<String, Integer> data = patentaService.getLicensesIssuedCountPerDate();
            return getChartSeries(new HashMap<>(data), "Licenses Issued");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Error loading licenses");
            return new XYChart.Series<>();
        }
    }

    private void updatePieChart(HashMap<String, Integer> data) {
        piechart.getData().clear();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            piechart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
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
        sesionetSot.getItems().clear();
        for (var orari : sesioniService.getSessionsToday()) {
            String item = orari.getLlojiMesimit() + ": " + orari.getOraFillimit() + "-" + orari.getOraPerfundimit();
            sesionetSot.getItems().add(item);
        }
    }
}
