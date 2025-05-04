package services;

import models.Dto.orari.UpdateOrariDto;
import models.Orari;
import models.Kandidatet;
import models.Stafi;
import models.Automjetet;
import models.Dto.orari.CreateOrariDto;
import repository.OrariRepository;
import repository.KandidatetRepository;
import repository.StafiRepository;
import repository.AutomjetetRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrariService {
    private final OrariRepository orariRepository;
    private final KandidatetRepository kandidatRepository;
    private final StafiRepository stafiRepository;
    private final AutomjetetRepository automjetRepository;

    public OrariService(OrariRepository orariRepository, KandidatetRepository kandidatRepository,
                        StafiRepository stafiRepository, AutomjetetRepository automjetRepository) {
        this.orariRepository = orariRepository;
        this.kandidatRepository = kandidatRepository;
        this.stafiRepository = stafiRepository;
        this.automjetRepository = automjetRepository;
    }
    public OrariService() {
        this.orariRepository = new OrariRepository();
        this.kandidatRepository = new KandidatetRepository();
        this.stafiRepository = new StafiRepository();
        this.automjetRepository = new AutomjetetRepository();
    }
    //METODAT
    // metode ndihmese per dy poshte: e kemi lene si mundesi nese na vie ide me vone per ndonje kolone tjeter
    public List<Orari> shikoOraretPerId(int id) {
        return orariRepository.gjejOraretPerId(id);
    }

    // ===================== METODA: Kandidati sheh oraret =====================
//    public List<Orari> shikoOraretPerKandidat(int idKandidat) {
//        return shikoOraretPerId("ID_KANDIDAT", idKandidat);
//    }
//
//    // ===================== METODA: Stafi sheh oraret =====================
//    public List<Orari> shikoOraretPerStaf(int idStaf) {
//        return shikoOraretPerId("ID_STAF", idStaf);
//    }
    public boolean eshteGatiPerProvim(int idKandidat) {
        long teori = numeroSesione(idKandidat, "Teori","");
        long praktike = numeroSesione(idKandidat, "Praktikë","");
        return teori >= 15 && praktike >= 20;
    }
    public void delete(int orariId) throws Exception {
        Orari ekzistues = orariRepository.getById(orariId);
        if (ekzistues == null) {
            throw new Exception("Orari me ID " + orariId + " nuk ekziston.");
        }
        boolean fshirje = orariRepository.delete(orariId);
        if (!fshirje) {
            throw new Exception("Gabim gjatë fshirjes së orarit me ID " + orariId);
        }
    }
    public Orari update(int orariId, UpdateOrariDto dto) throws Exception {
        Orari ekzistues = orariRepository.getById(orariId);
        if (ekzistues == null) {
            throw new Exception("Orari me ID " + orariId + " nuk ekziston.");
        }

        // VALIDIME TE ZAKONSHME
        if (dto.getIdKandidat() <= 0)
            throw new Exception("ID e kandidatit është e pavlefshme.");

        if (dto.getIdStaf() <= 0)
            throw new Exception("ID e stafit është e pavlefshme.");

        if (dto.getDataSesionit() == null)
            throw new Exception("Data e sesionit nuk mund të jetë null.");

        if (dto.getOraFillimit() == null || dto.getOraPerfundimit() == null)
            throw new Exception("Ora e fillimit dhe përfundimit janë të detyrueshme.");

        if (dto.getOraFillimit().isAfter(dto.getOraPerfundimit()))
            throw new Exception("Ora e fillimit nuk mund të jetë pas orës së përfundimit.");

        if (dto.getLlojiMesimit() == null || dto.getLlojiMesimit().isBlank())
            throw new Exception("Lloji i mësimit është i detyrueshëm.");

        if (dto.getStatusi() == null || dto.getStatusi().isBlank())
            throw new Exception("Statusi është i detyrueshëm.");

        if (dto.getIdAutomjet() <= 0)
            throw new Exception("ID e automjetit është e pavlefshme.");

        // PËRDITËSIMI NË BAZËN E TË DHËNAVE
        // Shto ID-në e sesionit në DTO
        dto.setIdSesioni(orariId);

        return orariRepository.update(dto);
    }

    // ===================== METODA: Shto sesion të ri =====================
    public Orari create(CreateOrariDto dto) throws Exception {
        // 1. Validimi i datës së sesionit
        if (dto.getDataSesionit() == null) {
            throw new Exception("Data e sesionit nuk mund të jetë bosh.");
        }
        if (dto.getDataSesionit().isBefore(LocalDate.now())) {
            throw new Exception("Data e sesionit duhet të jetë sot ose në të ardhmen.");
        }

        // 2. Validimi i orës së fillimit dhe përfundimit
        if (dto.getOraFillimit() == null || dto.getOraPerfundimit() == null) {
            throw new Exception("Orari i fillimit dhe përfundimit nuk mund të jenë bosh.");
        }
        if (!dto.getOraPerfundimit().isAfter(dto.getOraFillimit())) {
            throw new Exception("Ora e përfundimit duhet të jetë pas orës së fillimit.");
        }
        // Optional: Validimi për orar jashtë orarit të punës (p.sh., mes 9:00 dhe 17:00)
        if (dto.getOraFillimit().isBefore(LocalTime.of(9, 0)) || dto.getOraPerfundimit().isAfter(LocalTime.of(17, 0))) {
            throw new Exception("Orari duhet të jetë brenda orarit të punës, nga 9:00 deri në 17:00.");
        }

        // 3. Validimi i llojit të mësimit (me insensitive case)
        if (dto.getLlojiMesimit() == null ||
                !(dto.getLlojiMesimit().equalsIgnoreCase("Teori") ||
                        dto.getLlojiMesimit().equalsIgnoreCase("Praktikë"))) {
            throw new Exception("Lloji i mësimit duhet të jetë 'Teori' ose 'Praktikë'.");
        }

        // 4. Validimi i statusit të sesionit (me insensitive case)
        if (dto.getStatusi() == null ||
                !(dto.getStatusi().equalsIgnoreCase("Planifikuar") ||
                        dto.getStatusi().equalsIgnoreCase("Përfunduar") ||
                        dto.getStatusi().equalsIgnoreCase("Anuluar"))) {
            throw new Exception("Statusi duhet të jetë 'Planifikuar', 'Përfunduar' ose 'Anuluar'.");
        }

        // 5. Kontrollo ekzistencën e kandidatit, stafit dhe automjetit përmes BaseRepository -> getById()
        Kandidatet kandidati = (Kandidatet) kandidatRepository.getById(dto.getIdKandidat());
        if (kandidati == null) {
            throw new Exception("Kandidati me këtë ID nuk ekziston.");
        }

        Stafi stafi =(Stafi) stafiRepository.getById(dto.getIdStaf());
        if (stafi == null) {
            throw new Exception("Stafi me këtë ID nuk ekziston.");
        }

        Automjetet automjeti = automjetRepository.getById(dto.getIdAutomjet());
        if (automjeti == null) {
            throw new Exception("Automjeti me këtë ID nuk ekziston.");
        }

        // --6. Kontrollo që nuk ka orar të dyfishtë për kandidat, staf dhe automjet--
        // Për kandidat
        List<Orari> ekzistuesKandidat = shikoOraretPerId(dto.getIdKandidat());
        // Për staf
        List<Orari> ekzistuesStaf = shikoOraretPerId(dto.getIdStaf());
        //Per automjet
        List<Orari> ekzistuesAutomjet = orariRepository.gjejOraretPerId(dto.getIdAutomjet());
        StringBuilder errorMessage = new StringBuilder(); // Për të mbajtur mesazhet e gabimit
        // Kontrollo për kandidat
        for (Orari orar : ekzistuesKandidat) {
            if (orar.getDataSesionit().equals(dto.getDataSesionit()) &&
                    (orar.getOraFillimit().isBefore(dto.getOraPerfundimit()) && orar.getOraPerfundimit().isAfter(dto.getOraFillimit()))) {
                errorMessage.append("Kandidati ka një sesion tjetër në këtë orar.\n");
            }
        }
        // Kontrollo për staf
        for (Orari orar : ekzistuesStaf) {
            if (orar.getDataSesionit().equals(dto.getDataSesionit()) &&
                    (orar.getOraFillimit().isBefore(dto.getOraPerfundimit()) && orar.getOraPerfundimit().isAfter(dto.getOraFillimit()))) {
                errorMessage.append("Stafi ka një sesion tjetër në këtë orar.\n");
            }
        }
        for (Orari orar : ekzistuesAutomjet) {
            if (orar.getDataSesionit().equals(dto.getDataSesionit()) &&
                    (orar.getOraFillimit().isBefore(dto.getOraPerfundimit()) && orar.getOraPerfundimit().isAfter(dto.getOraFillimit()))) {
                errorMessage.append("Automjeti është i zënë në këtë orar.\n");
            }
        }

        // Nëse ka mesazhe gabimi për kandidat ose staf, hedh përjashtimin
        if (!errorMessage.isEmpty()) {
            throw new Exception(errorMessage.toString());
        }
        // 7. Në fund e shtojmë sesionin përmes OrariRepository
        Orari orari=orariRepository.create(dto);
        return orari;
    }

    public long numeroSesione(int idKandidat, String llojiMesimit, String statusi){
        return orariRepository.numeroSesione(idKandidat,llojiMesimit,statusi);
    }
    public List<Orari> gjejOraretPerId(String columnName, int id){
        return orariRepository.gjejOraretPerId(id);
    }
    public List<Orari> gjejOraretMidis(LocalDate dataStart, LocalDate dataEnd){
        return orariRepository.gjejOraretMidis(dataStart,dataEnd);
    }
    public List<Orari> gjejOraretPerDate(LocalDate data){
        return orariRepository.gjejOraretPerDate(data);
    }
}
