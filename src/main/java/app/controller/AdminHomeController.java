package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Map;

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
    private final UserService userService = new UserService();
    private final PatentaService patentaService=new PatentaService();
    private final PagesaService pagesaService=new PagesaService();
    private final OrariService sesioniService=new OrariService();
    private final KandidateService kandidateService=new KandidateService();


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
        int total = userService.countUsersByRole("Kandidat");
        totalKandidat.setText(String.valueOf(total));
    }

    private void loadTotalStaff() {
        int total = userService.countUsersByRole("Staf");
        totalStafi.setText(String.valueOf(total));
    }
    private void loadInProgressCompleted() {
        HashMap<String, Integer> statusReport = userService.getKandidatetStatusReport();

        ArrayList<PieChart.Data> pieChartData = new ArrayList<>();

        for (String status : statusReport.keySet()) {
            pieChartData.add(new PieChart.Data(status, statusReport.get(status)));
        }

        piechart.getData().clear();
        piechart.getData().addAll(pieChartData);
    }

        private void loadLicensesIssued() {
            try {
                // Merrni patentat e lëshuara
                List<Patenta> patentat = patentaService.getLicensesIssued();

                // Krijoni një serie për të shfaqur në grafik
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("Licenses Issued");

                // Përdorni një DateTimeFormatter për të shfaqur datën si String
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                for (Patenta patenta : patentat) {
                    // Shtoni të dhënat në serinë e grafikut
                    series.getData().add(new XYChart.Data<>(patenta.getDataLeshimit().format(formatter), 1)); // Përdoreni 1 për të treguar një patentë për datën specifike
                }

                // Vendosni serinë në grafikonin tuaj
                patentatEleshuara.getData().add(series);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    private void loadUnpaidPayments() {
        XYChart.Series<String, Number> chartData = pagesaService.getUnpaidPaymentsChartData();

        // Shto të dhënat në BarChart
        pagesaTePapaguara.getData().clear();  // Fshi çdo të dhënë të mëparshme
        pagesaTePapaguara.getData().add(chartData);  // Shto serinë e re
    }
    private void loadSessionsToday() {
        List<Orari> sessionsToday = sesioniService.getSessionsToday();

        // Krijoni një List<String> për t'i shfaqur në ListView
        for (Orari orari : sessionsToday) {
            sesionetSot.getItems().add(orari.getLlojiMesimit() + ": " + orari.getOraFillimit()+"-"+orari.getOraPerfundimit());
        }
    }
    private void loadRegistrationsTopPayments(){
        // Regjistrimet
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
