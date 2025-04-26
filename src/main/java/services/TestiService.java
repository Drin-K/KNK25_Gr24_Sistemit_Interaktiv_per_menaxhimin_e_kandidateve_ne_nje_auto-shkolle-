package services;

import models.Enums.LlojiTestit;
import models.Enums.RezultatiTestit;
import models.Dto.testet.CreateTestetDto;
import models.Testet;
import repository.TestetRepository;

import java.time.LocalDate;

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
        LlojiTestit llojiTestit;
        try {
            llojiTestit = LlojiTestit.valueOf(dto.getLlojiTestit().toString());
        } catch (Exception e) {
            throw new Exception("Lloji i testit duhet të jetë 'TEORI' ose 'PRAKTIKE'.");
        }

        // Validimi i datës së testit
        if (dto.getDataTestit() == null || dto.getDataTestit().isAfter(LocalDate.now().plusYears(1))) {
            throw new Exception("Data e testit duhet të jetë një datë valide dhe jo shumë larg në të ardhmen.");
        }

        // Validimi i rezultatit të testit
        if (dto.getRezultati() == null) {
            throw new Exception("Rezultati nuk mund të jetë null.");
        }
        RezultatiTestit rezultatiTestit;
        try {
            rezultatiTestit = RezultatiTestit.valueOf(dto.getRezultati().toString());
        } catch (Exception e) {
            throw new Exception("Rezultati duhet të jetë 'KALUAR' ose 'DESHTUAR'.");
        }

        // Validimi i pikëve
        if (dto.getPiket() < 0 || dto.getPiket() > 100) {
            throw new Exception("Piket duhet të jetë mes 0 dhe 100.");
        }

        return testetRepository.create(dto);
    }
    //qet pjesen posht pe ruj per kontroller
//    public void shfaqInformacioniPerKandidatin(CreateTestetDto dto) {
//        System.out.println("Informacioni për Testin:");
//        System.out.println("Lloji i testit: " + dto.getLlojiTestit());
//        System.out.println("Data e testit: " + dto.getDataTestit());
//        System.out.println("Rezultati: " + dto.getRezultati());
//        System.out.println("Piket: " + dto.getPiket());
//    }
}
