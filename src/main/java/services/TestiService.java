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
            throw new Exception("ID e kandidatit duhet të jetë pozitive dhe të vlefshme.");
        }

        // Validimi i idStaf
        if (dto.getIdStaf() <= 0) {
            throw new Exception("ID e stafit duhet të jetë pozitive dhe të vlefshme.");
        }

        // Validimi i llojit të testit
        if (dto.getLlojiTestit() == null) {
            throw new Exception("Lloji i testit nuk mund të jetë null.");
        }
        if(!dto.getLlojiTestit().equals("Teori") && !dto.getLlojiTestit().equals("Praktikë")){
            throw new Exception("Lloji i testit duhet të jetë 'Teori' ose 'Praktikë'.");
        }

        // Validimi i datës së testit
        if (dto.getDataTestit() == null || dto.getDataTestit().isAfter(LocalDate.now().plusYears(1))) {
            throw new Exception("Data e testit duhet të jetë një datë valide dhe jo shumë larg në të ardhmen.");
        }

        // Validimi i rezultatit të testit
        if (dto.getRezultati() == null) {
            throw new Exception("Rezultati nuk mund të jetë null.");
        }

        if(!dto.getRezultati().equals("Kaluar") && !dto.getRezultati().equals("Dështuar")){
            throw new Exception("Rezultati duhet të jetë 'Kaluar' ose 'Dështuar'.");
        }

        // Validimi i pikëve
        if (dto.getPiket() < 0 || dto.getPiket() > 100) {
            throw new Exception("Piket duhet të jetë mes 0 dhe 100.");
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
            throw new Exception("Testi me ID " + Id + " nuk ekziston.");
        }
        boolean fshirje =testetRepository.delete(Id);
        if (!fshirje) {
            throw new Exception("Gabim gjatë fshirjes së testit me ID " + Id);
        }
    }

}
