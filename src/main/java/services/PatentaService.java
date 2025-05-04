package services;

import models.Dto.patenta.CreatePatentaDto;
import models.Patenta;
import models.Stafi;
import repository.PatentaRepository;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        if (patenta == null) throw new Exception("Stafi nuk u gjet!");
        return patenta;
    }
    public Patenta create(CreatePatentaDto createDto) throws Exception{

        if (createDto.getIdKandidat() <= 0) {
            throw new IllegalArgumentException("ID e kandidatit është e pavlefshme!");
        }
        if (createDto.getIdKategori() <= 0) {
            throw new IllegalArgumentException("ID e kategorisë është e pavlefshme!");
        }
        if (createDto.getDataLeshimit() == null) {
            throw new IllegalArgumentException("Data e lëshimit është e detyrueshme!");
        }
        if (createDto.getStatusi() == null || createDto.getStatusi().isBlank()) {
            throw new IllegalArgumentException("Statusi është i detyrueshëm!");
        }
        if (!isValidStatus(createDto.getStatusi())) {
            throw new IllegalArgumentException("Statusi i patentës nuk është i vlefshëm!");
        }
       Patenta patenta = patentaRepository.create(createDto);
        if(patenta == null){
            throw new Exception("Patenta nuk u krijua");
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
    public List<Patenta> getLicensesIssued() throws SQLException {
        return patentaRepository.getLicensesIssued();
    }
}
