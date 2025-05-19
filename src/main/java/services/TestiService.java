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

        // Validimi i idKandidat
        if (dto.getIdKandidat() <= 0) {
            throw new Exception("The candidate ID must be positive and valid.");
        }

        // Validimi i idStaf
        if (dto.getIdStaf() <= 0) {
            throw new Exception("The staff ID must be positive and valid.");
        }

        // Validimi i llojit të testit
        if (dto.getLlojiTestit() == null) {
            throw new Exception("The test type cannot be null.");
        }
        if(!dto.getLlojiTestit().equals("Teori") && !dto.getLlojiTestit().equals("Praktikë")){
            throw new Exception("The test type must be 'Theory' or 'Practical'.");
        }

        // Validimi i datës së testit
        if (dto.getDataTestit() == null || dto.getDataTestit().isAfter(LocalDate.now().plusYears(1))) {
            throw new Exception("The test date must be a valid date and not too far in the future.");
        }

        // Validimi i rezultatit të testit
        if (dto.getRezultati() == null) {
            throw new Exception("The result cannot be null.");
        }

        if(!dto.getRezultati().equals("Kaluar") && !dto.getRezultati().equals("Dështuar")){
            throw new Exception("The result must be 'Kaluar' or 'Dështuar'.");
        }

        // Validimi i pikëve
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

}
