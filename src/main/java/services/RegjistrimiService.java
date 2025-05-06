package services;

import models.Dto.regjistrimet.CreateRegjistrimetDto;
import models.Regjistrimet;
import repository.KandidatetRepository;
import repository.KategoritePatentesRepository;
import repository.RegjistrimetRepository;

public class RegjistrimiService {
    private KandidatetRepository kandidatRepository;
    private KategoritePatentesRepository kategoritePatentesRepository;
    private RegjistrimetRepository regjistrimiRepository;

    public RegjistrimiService() {
        this.kandidatRepository = new KandidatetRepository();
        this.kategoritePatentesRepository = new KategoritePatentesRepository();
        this.regjistrimiRepository = new RegjistrimetRepository();
    }

    public Regjistrimet create(CreateRegjistrimetDto createDto) throws Exception {
        // Validimi i ID_Kandidat
        if (createDto.getIdKandidat() <= 0 || kandidatRepository.getbyID(createDto.getIdKandidat()) == null) {
            throw new Exception("Kandidati nuk ekziston!");
        }

        // Validimi i ID_Kategori
        if (createDto.getIdKategori() <= 0 || kategoritePatentesRepository.getById(createDto.getIdKategori()) == null) {
            throw new Exception("Kategoria e patentës nuk ekziston!");
        }

        // Validimi i Statusit
        if (!createDto.getStatusi().equals("Në proces") && !createDto.getStatusi().equals("Përfunduar")) {
            throw new Exception("Statusi duhet të jetë 'Në proces' ose 'Përfunduar'!");
        }

        // Krijimi në DB
        Regjistrimet regjistrimi = regjistrimiRepository.create(createDto);
        if (regjistrimi == null) {
            throw new Exception("Regjistrimi nuk u krijua!");
        }

        return regjistrimi;
    }
}
