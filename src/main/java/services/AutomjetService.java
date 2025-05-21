package services;

import models.Automjetet;
import models.Dto.automjetet.CreateAutomjetetDto;
import models.Dto.automjetet.UpdateAutomjetetDto;
import repository.AutomjetetRepository;
import repository.KategoritePatentesRepository;
import repository.StafiRepository;

import java.util.ArrayList;
import java.util.List;

public class AutomjetService {
    private AutomjetetRepository automjetetRepository;
    private KategoritePatentesRepository kategoritePatentesRepository;
    private StafiRepository stafiRepository;

    public AutomjetService() {
        this.automjetetRepository = new AutomjetetRepository();
        this.kategoritePatentesRepository = new KategoritePatentesRepository();
        this.stafiRepository = new StafiRepository();
    }

    public Automjetet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("Id does not exist!");
        }
        Automjetet automjeti = this.automjetetRepository.getById(id);
        if (automjeti == null) {
            throw new Exception("The vehicle does not exist!");
        }
        return automjeti;
    }

    public Automjetet create(CreateAutomjetetDto createDto) throws Exception {

        if (createDto.getIdStaf() <= 0 || stafiRepository.getById(createDto.getIdStaf()) == null) {
            throw new Exception("The staff does not exist!");
        }

        if (createDto.getIdKategori() <= 0 || kategoritePatentesRepository.getById(createDto.getIdKategori()) == null) {
            throw new Exception("The category does not exist!");
        }

        if (!createDto.getLlojiAutomjetit().equals("Vetura") && !createDto.getLlojiAutomjetit().equals("Motoçikletë") && !createDto.getLlojiAutomjetit().equals("Kamion")) {
            throw new Exception("This type of vehicle does not exist.");
        }

        if (!createDto.getStatusi().equals("Në përdorim")) {
            throw new Exception("The vehicle must be in 'Maintenance' status to be created!");
        }

        Automjetet automjeti = automjetetRepository.create(createDto);
        if (automjeti == null) {
            throw new Exception("The vehicle was not created!");
        }
        return automjeti;
    }

    public boolean delete(int id) throws Exception {
        this.getById(id);
        return automjetetRepository.delete(id);
    }

    public Integer getFirstAvailableVehicleIdByLloji(String llojiAutomjetit) throws Exception {
        if (!llojiAutomjetit.equals("Vetura") && !llojiAutomjetit.equals("Motoçikletë") && !llojiAutomjetit.equals("Kamion")) {
            throw new Exception("The vehicle type is not valid!");
        }

        ArrayList<Automjetet> automjetet = automjetetRepository.getByStatus(llojiAutomjetit);
        for (Automjetet a : automjetet) {
            if (a.getStatusi().equals("Në përdorim")) {
                return a.getId();
            }
        }

        throw new Exception("No available vehicle is free for the type:" + llojiAutomjetit);
    }

    public Automjetet updateStatusByIdLlojiAndKategori(int id, String llojiAutomjetit, int idKategori, String newStatus, int idStaf) throws Exception {

        if (id <= 0) {
            throw new Exception("The vehicle ID is not valid.");
        }
        if (llojiAutomjetit == null || llojiAutomjetit.isEmpty()) {
            throw new Exception("The vehicle type is not specified.");
        }
        if (idKategori <= 0) {
            throw new Exception("The category is not valid.");
        }
        if (newStatus == null || newStatus.isEmpty()) {
            throw new Exception("The new status is not specified.");
        }
        if (idStaf <= 0 || stafiRepository.getById(idStaf) == null) {
            throw new Exception("The staff does not exist.");
        }

        Automjetet automjeti = automjetetRepository.getById(id);
        if (automjeti == null) {
            throw new Exception("The vehicle was not found");
        }
        if (!automjeti.getLlojiAutomjetit().equals(llojiAutomjetit) || automjeti.getIdKategori() != idKategori) {
            throw new Exception("The vehicle does not belong to the specified type or category.");
        }

        UpdateAutomjetetDto dto = new UpdateAutomjetetDto(id, newStatus, idStaf);
        return automjetetRepository.update(dto);
    }

    public List<Automjetet> findByStatusiAndStafi(int id, String statusi) throws Exception {
        if (automjetetRepository.getByLlojiAndStafi(statusi, id).isEmpty()) {
            throw new Exception("No active Vehicle found !");
        }
        return automjetetRepository.getByLlojiAndStafi(statusi, id);
    }
}
