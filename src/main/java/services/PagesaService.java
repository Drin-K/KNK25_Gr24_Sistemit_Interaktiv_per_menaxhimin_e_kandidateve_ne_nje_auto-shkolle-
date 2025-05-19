package services;

import javafx.scene.chart.XYChart;
import models.Dto.pagesat.CreatePagesatDto;
import models.Pagesat;
import repository.PagesatRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.ChartData.getChartSeries;

public class PagesaService {
    private PagesatRepository pagesatRepository;
    public PagesaService(){
        pagesatRepository = new PagesatRepository();
    }
    public Pagesat getById(int id) throws Exception {
        if(id <=0){
            throw new Exception("Id does not exist!");
        }
        Pagesat pagesa = this.pagesatRepository.getById(id);
        if (pagesa == null) {
            throw new Exception("The payment with ID " + id + " does not exist!");
        }
        return pagesa;
    }
    public Pagesat create(CreatePagesatDto createDto) throws Exception{
        //Validimi i shumes
        if(createDto.getShuma() <= 0 && createDto.getShuma()>500){
            throw new Exception("The amount must be greater than 0.!");
        }
        if(createDto.getNumriXhirollogarise().length() <= 0 && createDto.getNumriXhirollogarise().length()>10 ){
            throw new Exception("The bank account number must be 10 digits long!");
        }
        //Validimi i dates se pageses
        if(createDto.getDataPageses() == null){
            createDto.setDataPageses(LocalDate.now()); //Nese mungon data default osht data sot
        }
        // Validimi i metodës së pagesës
        if (!createDto.getMetodaPageses().equals("Cash") &&
                !createDto.getMetodaPageses().equals("Online")) {
            throw new Exception("The payment method must be Cash or Online!");
        }

        // Validimi i statusit të pagesës
        if (!createDto.getStatusiPageses().equals("Paguar") &&
                !createDto.getStatusiPageses().equals("Pjesërisht") &&
                !createDto.getStatusiPageses().equals("Mbetur")) {
            throw new Exception("The payment status must be Paid, Partial, or Remaining!");
        }

        // Krijimi i pagesës në databazë
        Pagesat pagesa = pagesatRepository.create(createDto);
        if (pagesa == null) {
            throw new Exception("The payment was not created!");
        }
        return pagesa;
    }

    public XYChart.Series<String, Number> getUnpaidPaymentsChartData() {
        List<Pagesat> pagesatList = pagesatRepository.getUnpaidPayments();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("UnpaidPayments");
        for (Pagesat pagesa : pagesatList) {
            series.getData().add(new XYChart.Data<>("Candidate " + pagesa.getIdKandidat(), pagesa.getShuma()));
        }
        return series;
    }
    public HashMap<String, Integer> getPayments() {
        HashMap<String, Integer> data = pagesatRepository.getPayments();
        if (data == null) {
            throw new IllegalStateException("The payment data is null!");
        }
        if (data.isEmpty()) {
            throw new IllegalStateException("No payments were found in the system!");
        }
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            if (entry.getValue() < 0) {
                throw new IllegalStateException("Negative value for month: " + entry.getKey());
            }
        }

        return data;
    }

    public void delete(int pagesaId) throws Exception {
        Pagesat ekzistues = pagesatRepository.getById(pagesaId);
        if (ekzistues == null) {
            throw new Exception("The payment with ID " + pagesaId + " does not exist.");
        }
        boolean fshirje = pagesatRepository.delete(pagesaId);
        if (!fshirje) {
            throw new Exception("Error while deleting the payment with ID " + pagesaId);
        }
    }
    public void updateLineChartData(XYChart<String, Number> chart) throws SQLException {
        Map<String, Integer> pagesatCount = this.pagesatRepository.getPagesatCountByStatus();
        chart.getData().clear();
        for (String status : List.of("Paguar", "Pjeserisht", "Mbetur")) {
            Integer count = pagesatCount.getOrDefault(status, 0);
            XYChart.Series<String, Number> series = getChartSeries(Map.of(status, count), status);
            chart.getData().add(series);
        }
    }
    public ArrayList<Pagesat> getAll() {
        ArrayList<Pagesat> all = pagesatRepository.getAll();
        ArrayList<Pagesat> valid = new ArrayList<>();
        for (Pagesat p : all) {
            if (p.getShuma() > 0) {
                valid.add(p);
            } else {
                System.out.println("Invalid amount for the payment with ID:" + p.getId());
            }
        }
        return valid;
    }
    public List<Pagesat> filterPagesat(String name, LocalDate fromDate, LocalDate toDate, String metodaPageses, String statusiPageses) throws SQLException {
        if (name == null || name.trim().length() < 2) {
            throw new IllegalArgumentException("Name must be at least 2 characters long.");
        }
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Both fromDate and toDate must be provided.");
        }
        if (toDate.isBefore(fromDate)) {
            throw new IllegalArgumentException("toDate cannot be before fromDate.");
        }
        List<String> metodaValide = List.of("Cash", "Online");
        if (!metodaValide.contains(metodaPageses)) {
            throw new IllegalArgumentException("Invalid payment method: " + metodaPageses);
        }
        List<String> statusetValide = List.of("Paguar", "Pjesërisht", "Mbetur");
        if (!statusetValide.contains(statusiPageses)) {
            throw new IllegalArgumentException("Invalid payment status: " + statusiPageses);
        }
        return pagesatRepository.filterPagesat(name, fromDate, toDate, metodaPageses, statusiPageses);
    }
    public int countPagesatOnDate(LocalDate date) throws SQLException {
        if (date == null)
            throw new IllegalArgumentException("Date cannot be null");

        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future.");
        }
        return pagesatRepository.countPagesatOnDate(date);
    }

    public int countPagesatInMonth(YearMonth month) throws SQLException {
        if (month == null)
            throw new IllegalArgumentException("Month cannot be null");

        if (month.isAfter(YearMonth.now())) {
            throw new IllegalArgumentException("Month cannot be in the future.");
        }
        return pagesatRepository.countPagesatInMonth(month);
    }

    public int countPagesatInYear(int year) throws SQLException {
        if (year > Year.now().getValue()) {
            throw new IllegalArgumentException("Year cannot be in the future.");
        }
        return pagesatRepository.countPagesatInYear(year);
    }
    public void updateStatusiPageses(int pagesaId, String statusiRi) throws SQLException {
        if (pagesaId <= 0) {
            throw new IllegalArgumentException("The payment ID must be a positive number.");
        }
        if (statusiRi == null || statusiRi.trim().isEmpty()) {
            throw new IllegalArgumentException("The new payment status cannot be empty.");
        }
        List<String> validStatuset = List.of("Paguar", "Pjesërisht", "Mbetur");
        if (!validStatuset.contains(statusiRi)) {
            throw new IllegalArgumentException("The new status is not valid. Accepted value: " + validStatuset);
        }
        pagesatRepository.updateStatusiPageses(pagesaId, statusiRi);
    }
}
