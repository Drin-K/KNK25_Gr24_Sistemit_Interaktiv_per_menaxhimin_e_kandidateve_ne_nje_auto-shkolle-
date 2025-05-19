package services;

import models.Dokumentet;
import models.Dto.testet.CreateTestetDto;
import models.Kandidatet;
import models.Testet;
import repository.TestetRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestiService {

    private final TestetRepository testetRepository;

    public TestiService() {
        this.testetRepository = new TestetRepository();
    }

    public Testet regjistroTestin(CreateTestetDto dto) throws Exception {

        if (dto.getIdKandidat() <= 0) {
            throw new Exception("The candidate ID must be positive and valid.");
        }
        if (dto.getIdStaf() <= 0) {
            throw new Exception("The staff ID must be positive and valid.");
        }
        if (dto.getLlojiTestit() == null) {
            throw new Exception("The test type cannot be null.");
        }
        if(!dto.getLlojiTestit().equals("Teori") && !dto.getLlojiTestit().equals("Praktikë")){
            throw new Exception("The test type must be 'Theory' or 'Practical'.");
        }
        if (dto.getDataTestit() == null || dto.getDataTestit().isAfter(LocalDate.now().plusYears(1))) {
            throw new Exception("The test date must be a valid date and not too far in the future.");
        }
        if (dto.getRezultati() == null) {
            throw new Exception("The result cannot be null.");
        }
        if(!dto.getRezultati().equals("Kaluar") && !dto.getRezultati().equals("Dështuar")){
            throw new Exception("The result must be 'Kaluar' or 'Dështuar'.");
        }
        if (dto.getPiket() < 0 || dto.getPiket() > 100) {
            throw new Exception("The points must be between 0 and 100.");
        }
        return testetRepository.create(dto);
    }

    public List<Testet> getTestetByKandidatId(int idKandidat) throws Exception{
        List<Testet> testet;
        testet = testetRepository.getTestetByKandidatId(idKandidat);
        if(testet.isEmpty()){
            throw new Exception("No test results !");
        }
        return testet;
    }

    public void delete(int Id) throws Exception {
       Testet ekzistues = testetRepository.getById(Id);
        if (ekzistues == null) {
            throw new Exception("The exam with id " + Id + " does not exist.");
        }
        boolean fshirje =testetRepository.delete(Id);
        if (!fshirje) {
            throw new Exception("Error occurred while deleting the test with ID " + Id);
        }
    }
    public List<Testet> getAll() {
        List<Testet> lista = testetRepository.getAll();
        List<Testet> validList = new ArrayList<>();

        for (Testet t : lista) {
            if (t.getId() > 0
                    && t.getIdKandidat() > 0
                    && t.getIdStaf() > 0
                    && t.getDataTestit() != null
                    && t.getPiket() >= 0 && t.getPiket() <= 100) {
                validList.add(t);
            }
        }

        return validList;
    }

}
