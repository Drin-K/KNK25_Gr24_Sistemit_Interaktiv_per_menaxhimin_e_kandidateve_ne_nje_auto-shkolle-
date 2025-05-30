package services;

import models.Dto.regjistrimet.CreateRegjistrimetDto;
import models.Regjistrimet;
import repository.DokumentetRepository;
import repository.KandidatetRepository;
import repository.KategoritePatentesRepository;
import repository.RegjistrimetRepository;

public class RegjistrimiService {
    private KandidatetRepository kandidatRepository;
    private KategoritePatentesRepository kategoritePatentesRepository;
    private RegjistrimetRepository regjistrimiRepository;
    private DokumentetRepository dokumentetRepository;

    public RegjistrimiService() {
        this.kandidatRepository = new KandidatetRepository();
        this.kategoritePatentesRepository = new KategoritePatentesRepository();
        this.regjistrimiRepository = new RegjistrimetRepository();
        this.dokumentetRepository = new DokumentetRepository();
    }

    public boolean mirato(int kandidatiId) {
        int dokumenteCount = dokumentetRepository.numeroDokumentet(kandidatiId);
        if (dokumenteCount == 4) {
            regjistrimiRepository.mirato(kandidatiId);
            return true;
        } else {
            return false;
        }
    }

    public Regjistrimet create(CreateRegjistrimetDto createDto) throws Exception {
        // Validimi i ID_Kandidat
        if (createDto.getIdKandidat() <= 0 || kandidatRepository.getById(createDto.getIdKandidat()) == null) {
            throw new Exception("The candidate does not exist!");
        }

        // Validimi i ID_Kategori
        if (createDto.getIdKategori() <= 0 || kategoritePatentesRepository.getById(createDto.getIdKategori()) == null) {
            throw new Exception("The license category does not exist!");
        }

        // Validimi i Statusit
        if (!createDto.getStatusi().equals("Në proces") && !createDto.getStatusi().equals("Përfunduar")) {
            throw new Exception("Status must be 'Në proces' or 'Përfunduar'!");
        }

        // Krijimi në DB
        Regjistrimet regjistrimi = regjistrimiRepository.create(createDto);
        if (regjistrimi == null) {
            throw new Exception("The registration was not created!");
        }

        return regjistrimi;
    }
}
