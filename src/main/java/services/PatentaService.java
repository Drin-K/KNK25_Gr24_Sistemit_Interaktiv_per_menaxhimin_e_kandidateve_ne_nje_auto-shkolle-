package services;

import javafx.scene.chart.XYChart;
import models.Dto.patenta.CreatePatentaDto;
import models.Patenta;
import repository.PatentaRepository;


import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static utils.ChartData.getChartSeries;

public class PatentaService {
    private PatentaRepository patentaRepository;

    private boolean isValidStatus(String status) {
        return status.equalsIgnoreCase("aktive") ||
                status.equalsIgnoreCase("e skaduar");
    }
    public PatentaService() {
        this.patentaRepository = new PatentaRepository();
    }
    public Patenta getById(int id) throws Exception{
        if(id<=0){
            throw new Exception("Id does not exist!");
        }
        Patenta patenta = this.patentaRepository.getById(id);
        if (patenta == null) throw new Exception("The Staf was not found!");
        return patenta;
    }
    public Patenta create(CreatePatentaDto createDto) throws Exception{

        if (createDto.getIdKandidat() <= 0) {
            throw new IllegalArgumentException("The candidate ID is invalid!");
        }
        if (createDto.getIdKategori() <= 0) {
            throw new IllegalArgumentException("The category ID is invalid!");
        }
        if (createDto.getDataLeshimit() == null) {
            throw new IllegalArgumentException("The issue date is required!");
        }
        if (createDto.getStatusi() == null || createDto.getStatusi().isBlank()) {
            throw new IllegalArgumentException("Status is required!");
        }
        if (!isValidStatus(createDto.getStatusi())) {
            throw new IllegalArgumentException("The license status is invalid!");
        }
       Patenta patenta = patentaRepository.create(createDto);
        if(patenta == null){
            throw new Exception("The license was not created!");
        }
        return patenta;
    }
    public ArrayList<Patenta> getAll(){
        return patentaRepository.getAll();
    }
    public boolean delete(int id) throws Exception{
        this.getById(id); // E kontrollojm a ekziston
        return patentaRepository.delete(id);
    }

    public boolean aprovoPatenten(int kandidatId) throws SQLException{
        if (kandidatId <= 0) {
            throw new IllegalArgumentException("The candidate ID is invalid.");
        }
        return this.patentaRepository.aprovoPatenten(kandidatId);
    }

    public XYChart.Series<String, Number> getLicensesIssuedSeries() {
        try {
            HashMap<String, Integer> data = getLicensesIssuedCountPerDate();
            return getChartSeries(data, "Licenses Issued");
        } catch (Exception e) {
            System.out.println("Error loading licenses");
            return new XYChart.Series<>();
        }
    }
    public HashMap<String, Integer> getLicensesIssuedCountPerDate() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        HashMap<String, Integer> dateCountMap = new HashMap<>();

        for (Patenta patenta : patentaRepository.getLicensesIssued()) {
            String date = patenta.getDataLeshimit().format(formatter);
            if (dateCountMap.containsKey(date)) {
                int count = dateCountMap.get(date);
                dateCountMap.put(date, count + 1);
            } else {
                dateCountMap.put(date, 1);
            }
        }

        return dateCountMap;
    }


}
