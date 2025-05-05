package app.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Orari;
import models.Patenta;
import services.*;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminHomeController {
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
    private final PatentaService patentaService=new PatentaService();
    private final PagesaService pagesaService=new PagesaService();
    private final OrariService sesioniService=new OrariService();
    private final KandidateService kandidateService=new KandidateService();
    private final StafiService stafiService=new StafiService();


    @FXML
    public void initialize() {
        loadTotalCandidates();
        loadTotalStaff();
        loadInProgressCompleted();
        loadLicensesIssued();
        loadUnpaidPayments();
        loadSessionsToday();
        loadRegistrationsTopPayments();
    }
    private void loadTotalCandidates() {
        int total = kandidateService.countKandidatet();
        totalKandidat.setText(String.valueOf(total));
    }

    private void loadTotalStaff() {
        int total = stafiService.countStafi();
        totalStafi.setText(String.valueOf(total));
    }
    private void loadInProgressCompleted() {
        HashMap<String, Integer> statusReport = kandidateService.getKandidatetStatusReport();

        ArrayList<PieChart.Data> pieChartData = new ArrayList<>();

        for (String status : statusReport.keySet()) {
            pieChartData.add(new PieChart.Data(status, statusReport.get(status)));
        }

        piechart.getData().clear();
        piechart.getData().addAll(pieChartData);
    }

        private void loadLicensesIssued() {
            try {
                List<Patenta> patentat = patentaService.getLicensesIssued();

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("Licenses Issued");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                for (Patenta patenta : patentat) {
                    series.getData().add(new XYChart.Data<>(patenta.getDataLeshimit().format(formatter), 1)); // Përdoreni 1 për të treguar një patentë për datën specifike
                }

                patentatEleshuara.getData().add(series);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    private void loadUnpaidPayments() {
        XYChart.Series<String, Number> chartData = pagesaService.getUnpaidPaymentsChartData();


        pagesaTePapaguara.getData().clear();
        pagesaTePapaguara.getData().add(chartData);
    }
    private void loadSessionsToday() {
        List<Orari> sessionsToday = sesioniService.getSessionsToday();

        for (Orari orari : sessionsToday) {
            sesionetSot.getItems().add(orari.getLlojiMesimit() + ": " + orari.getOraFillimit()+"-"+orari.getOraPerfundimit());
        }
    }
    private void loadRegistrationsTopPayments(){

        HashMap<String, Integer> registrationsData = kandidateService.getRegistrationsLast12Months();
        XYChart.Series<String, Number> registrationsSeries = new XYChart.Series<>();
        registrationsSeries.setName("Registrations");

        for (String muaji : registrationsData.keySet()) {
            registrationsSeries.getData().add(new XYChart.Data<>(muaji, registrationsData.get(muaji)));
        }

        nrRegjistrimeve.getData().clear();
        nrRegjistrimeve.getData().add(registrationsSeries);

        // Pagesat
        HashMap<String, Integer> paymentsData = pagesaService.getPaymentsLast12Months();
        XYChart.Series<String, Number> paymentsSeries = new XYChart.Series<>();
        paymentsSeries.setName("Number Of Payments");

        for (String muaji : paymentsData.keySet()) {
            paymentsSeries.getData().add(new XYChart.Data<>(muaji, paymentsData.get(muaji)));
        }

        PagesaTeKryera.getData().clear();
        PagesaTeKryera.getData().add(paymentsSeries);
    }


}
