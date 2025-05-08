package services;

import models.Dto.raporti_progresit.CreateRaportiProgresitDto;
import models.RaportiProgresit;
import repository.KandidatetRepository;
import repository.RaportiProgresitRepository;
import repository.StafiRepository;

import java.util.List;

public class RaportiProgresitService {

    private final RaportiProgresitRepository raportiProgresitRepository;
    private final KandidatetRepository kandidatetRepository;
    private final StafiRepository stafiRepository;

    public RaportiProgresitService() {
        this.raportiProgresitRepository = new RaportiProgresitRepository();
        this.kandidatetRepository = new KandidatetRepository();
        this.stafiRepository = new StafiRepository();
    }

    public RaportiProgresit create(CreateRaportiProgresitDto dto) throws Exception {
        if (dto.getIdKandidat() <= 0 || kandidatetRepository.getById(dto.getIdKandidat()) == null) {
            throw new Exception("ID i kandidatit është i pavlefshëm.");
        }
        if (dto.getIdStaf() <= 0 || stafiRepository.getByIDstaf(dto.getIdStaf()) == null) {
            throw new Exception("ID i stafit është i pavlefshëm.");
        }
        if (dto.getPiketTeorike() < 0 || dto.getPiketTeorike() > 100) {
            throw new Exception("Piket teorike duhet të jenë mes 0 dhe 100.");
        }
        if (dto.getPiketPraktike() < 0 || dto.getPiketPraktike() > 100) {
            throw new Exception("Piket praktike duhet të jenë mes 0 dhe 100.");
        }
        if (dto.getKomentet() != null && dto.getKomentet().length() > 500) {
            throw new Exception("Komentet nuk mund të kalojnë 500 karaktere.");
        }
        String[] validPerformanca = {"Shkëlqyeshëm", "Mirë", "Mesatar", "Dobët"};
        boolean isPerformancaValid = false;
        for (String performanca : validPerformanca) {
            if (dto.getPerformancaGjenerale().equals(performanca)) {
                isPerformancaValid = true;
                break;
            }
        }
        if (!isPerformancaValid) {
            throw new Exception("Performanca duhet të jetë një nga vlerat: Shkëlqyeshëm, Mirë, Mesatar, Dobët.");
        }

        return raportiProgresitRepository.create(dto);
    }
    public List<RaportiProgresit> getRaportetById(int idStaf,int idKategori){
        return raportiProgresitRepository.getRaportetByStaf(idStaf,idKategori);
    }

}
