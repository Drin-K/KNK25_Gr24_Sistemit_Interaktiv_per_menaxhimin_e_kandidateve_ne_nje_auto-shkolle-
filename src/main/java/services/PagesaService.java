package services;

import javafx.scene.chart.XYChart;
import models.Dto.pagesat.CreatePagesatDto;
import models.Pagesat;
import repository.PagesatRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            throw new Exception("Pagesa me ID " + id + " nuk ekziston!");
        }
        return pagesa;
    }
    public Pagesat create(CreatePagesatDto createDto) throws Exception{
        //Validimi i shumes
        if(createDto.getShuma() <= 0){
            throw new Exception("Shuma duhet të jetë më e madhe se 0!");
        }
        //Validimi i dates se pageses
        if(createDto.getDataPageses() == null){
            createDto.setDataPageses(LocalDate.now()); //Nese mungon data default osht data sot
        }
        // Validimi i metodës së pagesës
        if (!createDto.getMetodaPageses().equals("Cash") &&
                !createDto.getMetodaPageses().equals("Kartë") &&
                !createDto.getMetodaPageses().equals("Online")) {
            throw new Exception("Metoda e pagesës duhet të jetë Cash, Kartë ose Online!");
        }

        // Validimi i statusit të pagesës
        if (!createDto.getStatusiPageses().equals("Paguar") &&
                !createDto.getStatusiPageses().equals("Pjesërisht") &&
                !createDto.getStatusiPageses().equals("Mbetur")) {
            throw new Exception("Statusi i pagesës duhet të jetë Paguar, Pjesërisht ose Mbetur!");
        }

        // Krijimi i pagesës në databazë
        Pagesat pagesa = pagesatRepository.create(createDto);
        if (pagesa == null) {
            throw new Exception("Pagesa nuk u krijua!");
        }
        return pagesa;
    }
    public ArrayList<Pagesat> getAll(){
        return pagesatRepository.getAll();
    }
    public ArrayList<Pagesat> findPagesaByStatus(String statusi) {
        return pagesatRepository.findByStatus(statusi);
    }
    public XYChart.Series<String, Number> getUnpaidPaymentsChartData() {
        List<Pagesat> pagesatList = pagesatRepository.getUnpaidPayments();

        // Krijoni një seri të re për grafikun
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("UnpaidPayments");

        // Për çdo pagesë të papaguar, shto të dhënat në seri
        for (Pagesat pagesa : pagesatList) {
            series.getData().add(new XYChart.Data<>("Kandidat " + pagesa.getIdKandidat(), pagesa.getShuma()));
        }

        return series;
    }
    public HashMap<String, Integer> getPaymentsLast12Months() {
        return pagesatRepository.getPaymentsLast12Months();
    }
    public void updateStatusiPageses(Pagesat pagesa, String statusiRi) {
        try {
            pagesatRepository.updateStatusiPageses(pagesa.getId(), statusiRi);
        } catch (Exception e) {
            System.err.println("Gabim gjatë përditësimit të statusit të pagesës.");
            e.printStackTrace();
        }
    }
    public List<Pagesat> getAllPagesat() {
        return pagesatRepository.loadPagesatData();  }
    public List<Pagesat> getFilteredPagesat(String name, String fromDate, String toDate, String metodaPageses, String statusiPageses) throws SQLException, SQLException {
        return pagesatRepository.filterPagesat(name, fromDate, toDate, metodaPageses, statusiPageses);
    }
    public int countPagesatOnDate(LocalDate date) throws SQLException{
        return pagesatRepository.countPagesatOnDate(date);
    }
    public int countPagesatInMonth(YearMonth month) throws SQLException{
        return pagesatRepository.countPagesatInMonth(month);
    }
    public int countPagesatInYear(int year) throws SQLException {
        return pagesatRepository.countPagesatInYear(year);
    }
}
