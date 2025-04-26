package services;

import models.Dto.raporti_progresit.CreateRaportiProgresitDto;
import models.RaportiProgresit;
import repository.KandidatetRepository;
import repository.RaportiProgresitRepository;
import repository.StafiRepository;

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
        // Validimi i ID_KANDIDAT
        if (dto.getIdKandidat() <= 0 || kandidatetRepository.getById(dto.getIdKandidat()) == null) {
            throw new Exception("ID i kandidatit është i pavlefshëm.");
        }

        // Validimi i ID_STAF
        if (dto.getIdStaf() <= 0 || stafiRepository.getById(dto.getIdStaf()) == null) {
            throw new Exception("ID i stafit është i pavlefshëm.");
        }
        // Validimi i pikëve teorike dhe praktike
        if (dto.getPiketTeorike() < 0 || dto.getPiketTeorike() > 100) {
            throw new Exception("Piket teorike duhet të jenë mes 0 dhe 100.");
        }
        if (dto.getPiketPraktike() < 0 || dto.getPiketPraktike() > 100) {
            throw new Exception("Piket praktike duhet të jenë mes 0 dhe 100.");
        }

        // Validimi i komenteve
        if (dto.getKomentet() != null && dto.getKomentet().length() > 500) {
            throw new Exception("Komentet nuk mund të kalojnë 500 karaktere.");
        }

        // Validimi i performancës së përgjithshme
        //MA VONE DUHET ME PROVU ME BOO ME ENUM SE MA PROFESIONALE
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
    //ide tjera per ma vone:
    //1. Metodë: Me i llogarit pikët totale të një kandidati në raport progresi
    //2. Metodë: Me i marrë të gjithë raportet për një kandidat të caktuar
    //3. Metodë: Me llogarit mesataren e performancës së një kandidati
    //Nëse më vonë e konverton PerformancaGjenerale në Enum me vlerë numerike (p.sh. Shkëlqyeshëm = 4, Mirë = 3...), atëherë mundesh me bo diçka të tillë:

}
