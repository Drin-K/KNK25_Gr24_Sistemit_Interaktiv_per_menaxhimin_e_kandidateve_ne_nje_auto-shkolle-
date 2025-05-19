package services;

import models.*;
import models.Dto.orari.UpdateOrariDto;
import models.Dto.orari.CreateOrariDto;
import repository.OrariRepository;
import repository.KandidatetRepository;
import repository.StafiRepository;
import repository.AutomjetetRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OrariService {
    private final OrariRepository orariRepository;
    private final AutomjetetRepository automjetRepository;
    public OrariService() {
        this.orariRepository = new OrariRepository();
        this.automjetRepository = new AutomjetetRepository();
    }
    public Orari getById(int idSesioni) {
        return orariRepository.getById(idSesioni);
    }

    public List<Orari> shikoOraretPerId(int id) {
        return orariRepository.gjejOraretPerId(id);
    }
    public List<Orari> gjejOraretPerKandidat(LocalDate dataZgjedhur, int kandidatId) {
        List<Orari> teGjithaOraret = orariRepository.gjejOraretPerDate(dataZgjedhur);
        List<Orari> oraretEFiltruara = new ArrayList<>();

        for (Orari o : teGjithaOraret) {
            if (o.getIdKandidat() == kandidatId) {
                oraretEFiltruara.add(o);
            }
        }
        return oraretEFiltruara;
    }
    public void delete(int orariId) throws Exception {
        Orari ekzistues = orariRepository.getById(orariId);
        if (ekzistues == null) {
            throw new Exception("The schedule with ID " + orariId + " does not exist.");
        }
        boolean fshirje = orariRepository.delete(orariId);
        if (!fshirje) {
            throw new Exception("Error deleting the schedule with ID " + orariId);
        }
    }
    public Orari update(int orariId, UpdateOrariDto dto) throws Exception {
        Orari ekzistues = orariRepository.getById(orariId);
        if (ekzistues == null) {
            throw new Exception("The schedule with ID " + orariId + " does not exist.");
        }

        if (dto.getIdKandidat() <= 0)
            throw new Exception("The candidate ID is invalid.");

        if (dto.getIdStaf() <= 0)
            throw new Exception("The staff ID is invalid.");

        if (dto.getDataSesionit() == null)
            throw new Exception("The session date cannot be null.");

        if (dto.getOraFillimit() == null || dto.getOraPerfundimit() == null)
            throw new Exception("The start and end times are mandatory.");

        if (dto.getOraFillimit().isAfter(dto.getOraPerfundimit()))
            throw new Exception("The start time cannot be after the end time.");

        if (dto.getLlojiMesimit() == null || dto.getLlojiMesimit().isBlank())
            throw new Exception("The lesson type is required.");

        if (dto.getStatusi() == null || dto.getStatusi().isBlank())
            throw new Exception("The status is required.");

        if (dto.getIdAutomjet() <= 0)
            throw new Exception("The vehicle ID is invalid.");

        dto.setIdSesioni(orariId);

        return orariRepository.update(dto);
    }

    public Orari create(CreateOrariDto dto) throws Exception {

        if (dto.getDataSesionit() == null) {
            throw new Exception("The session date cannot be empty.");
        }
        if (dto.getDataSesionit().isBefore(LocalDate.now())) {
            throw new Exception("The session date must be today or in the future.");
        }
        if (dto.getOraFillimit() == null || dto.getOraPerfundimit() == null) {
            throw new Exception("The start and end schedule times cannot be empty.");
        }
        if (!dto.getOraPerfundimit().isAfter(dto.getOraFillimit())) {
            throw new Exception("The end time must be after the start time.");
        }
        if (dto.getOraFillimit().isBefore(LocalTime.of(9, 0)) || dto.getOraPerfundimit().isAfter(LocalTime.of(17, 0))) {
            throw new Exception("The schedule must be within working hours, from 9:00 to 17:00.");
        }
        if (dto.getLlojiMesimit() == null ||
                !(dto.getLlojiMesimit().equalsIgnoreCase("Teori") ||
                        dto.getLlojiMesimit().equalsIgnoreCase("Praktikë"))) {
            throw new Exception("The lesson type must be 'Teori' or 'Praktikë'.");
        }

        if (dto.getStatusi() == null ||
                !(dto.getStatusi().equalsIgnoreCase("Planifikuar") ||
                        dto.getStatusi().equalsIgnoreCase("Përfunduar") ||
                        dto.getStatusi().equalsIgnoreCase("Anuluar"))) {
            throw new Exception("The status must be 'Planifikuar', 'Përfunduar' or 'Anuluar'.");
        }

        Automjetet automjeti = automjetRepository.getById(dto.getIdAutomjet());
        if (automjeti == null) {
            throw new Exception("The vehicle with this ID does not exist.");
        }
        List<Orari> ekzistuesKandidat = shikoOraretPerId(dto.getIdKandidat());
        List<Orari> ekzistuesStaf = shikoOraretPerId(dto.getIdStaf());
        List<Orari> ekzistuesAutomjet = orariRepository.gjejOraretPerId(dto.getIdAutomjet());
        StringBuilder errorMessage = new StringBuilder();
        for (Orari orar : ekzistuesKandidat) {
            if (orar.getDataSesionit().equals(dto.getDataSesionit()) &&
                    (orar.getOraFillimit().isBefore(dto.getOraPerfundimit()) && orar.getOraPerfundimit().isAfter(dto.getOraFillimit()))) {
                errorMessage.append("The candidate has another session at this time.");
            }
        }
        for (Orari orar : ekzistuesStaf) {
            if (orar.getDataSesionit().equals(dto.getDataSesionit()) &&
                    (orar.getOraFillimit().isBefore(dto.getOraPerfundimit()) && orar.getOraPerfundimit().isAfter(dto.getOraFillimit()))) {
                errorMessage.append("The staff has another session at this time.");
            }
        }
        for (Orari orar : ekzistuesAutomjet) {
            if (orar.getDataSesionit().equals(dto.getDataSesionit()) &&
                    (orar.getOraFillimit().isBefore(dto.getOraPerfundimit()) && orar.getOraPerfundimit().isAfter(dto.getOraFillimit()))) {
                errorMessage.append("The vehicle is occupied at this time.");
            }
        }

        if (!errorMessage.isEmpty()) {
            throw new Exception(errorMessage.toString());
        }
        Orari orari=orariRepository.create(dto);
        return orari;
    }



    public int numeroSesione(int idKandidat, String llojiMesimit, String statusi){
     int numriSesioneve= this.orariRepository.numeroSesione(idKandidat,llojiMesimit,statusi);
        if (numriSesioneve > 20) {
            throw new IllegalStateException("The candidate has exceeded the maximum limit of sessions for this type of lesson and status!");
        }
        return numriSesioneve;

    }
    public List<Orari> getSessionsToday() {
        LocalDate currentDate = LocalDate.now();
        return orariRepository.gjejOraretPerDate(currentDate);
    }
    public String getPershkrimiSesionit(Orari orari) {
        return orari.getLlojiMesimit()+ ": " + orari.getOraFillimit() + "-" + orari.getOraPerfundimit();
    }
    public List<String> getPershkrimetESesioneveTeSotme() {
        List<String> pershkrimet = new ArrayList<>();
        for (Orari orari : getSessionsToday()) {
            pershkrimet.add(getPershkrimiSesionit(orari));
        }
        return pershkrimet;
    }
    public List<Orari> getAll() {
        List<Orari> lista = orariRepository.getAll();
        List<Orari> validList = new ArrayList<>();

        for (Orari o : lista) {
            if (o.getIdSesioni() > 0
                    && o.getIdKandidat() > 0
                    && o.getOraFillimit() != null
                    && o.getOraPerfundimit() != null
                    && o.getOraFillimit().isBefore(o.getOraPerfundimit())) {
                validList.add(o);
            }
        }
        return validList;
    }


}
