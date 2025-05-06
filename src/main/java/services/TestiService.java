package services;

import models.Dto.testet.CreateTestetDto;
import models.Testet;
import repository.TestetRepository;
import java.time.LocalDate;
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
    //qet pjesen posht pe ruj per kontroller
//    public void shfaqInformacioniPerKandidatin(CreateTestetDto dto) {
//        System.out.println("Informacioni për Testin:");
//        System.out.println("Lloji i testit: " + dto.getLlojiTestit());
//        System.out.println("Data e testit: " + dto.getDataTestit());
//        System.out.println("Rezultati: " + dto.getRezultati());
//        System.out.println("Piket: " + dto.getPiket());
//    }

    //QETU OSHT IDE SHTESE ME E INTEGRU NI FUNKSION PER PIK MESATARE
    public void updateRezultatetKandidat(String idKandidatStr, String rezultati) {
        try {
            int idKandidat = Integer.parseInt(idKandidatStr);
            testetRepository.updateRezultatiKandidatit(idKandidat, rezultati);
        } catch (NumberFormatException e) {
            System.err.println("ID e kandidatit nuk është numër i vlefshëm.");
        } catch (Exception e) {
            System.err.println("Gabim gjatë përditësimit të rezultatit.");
            e.printStackTrace();
        }
    }
    public List<Testet> getTestetByKandidatId(int idKandidat) {
        return testetRepository.getTestetByKandidatId(idKandidat);
    }


}
