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
            throw new Exception("The candidate's ID is invalid.");
        }
        if (dto.getIdStaf() <= 0 || stafiRepository.getById(dto.getIdStaf()) == null) {
            throw new Exception("The staf's ID is invalid.");
        }
        if (dto.getPiketTeorike() < 0 || dto.getPiketTeorike() > 100) {
            throw new Exception("Theoritical points must be between 0 and 100.");
        }
        if (dto.getPiketPraktike() < 0 || dto.getPiketPraktike() > 100) {
            throw new Exception("Practical points must be between 0 and 100.");
        }
        if (dto.getKomentet() != null && dto.getKomentet().length() > 500) {
            throw new Exception("Comments cannot exceed 500 characters.");
        }
        String[] validPerformanca = {"Shkelqyeshem", "Mire", "Mesatar", "Dobet"};
        boolean isPerformancaValid = false;
        for (String performanca : validPerformanca) {
            if (dto.getPerformancaGjenerale().equals(performanca)) {
                isPerformancaValid = true;
                break;
            }
        }
        if (!isPerformancaValid) {
            throw new Exception("The performance should be one from these values: Shkelqyeshem, Mire, Mesatar, Dobet.");
        }

        return raportiProgresitRepository.create(dto);
    }
    public List<RaportiProgresit> getRaportetById(int idStaf,int idKategori){
        return raportiProgresitRepository.getRaportetByStaf(idStaf,idKategori);
    }

}
