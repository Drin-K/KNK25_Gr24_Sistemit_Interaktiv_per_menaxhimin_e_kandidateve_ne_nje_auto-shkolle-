package services;

import models.Dto.kategorite_patentes.CreateKategoritePatentesDto;
import models.KategoritePatentes;
import repository.KategoritePatentesRepository;

import java.util.ArrayList;

public class KategoritePatentesService {
    private KategoritePatentesRepository kategoriteRepository;

    public KategoritePatentesService() {
        this.kategoriteRepository = new KategoritePatentesRepository();
    }

    public KategoritePatentes getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("Id does not exist!");
        }
        KategoritePatentes kategoritePatentes = this.kategoriteRepository.getById(id);
        if (kategoritePatentes == null) throw new Exception("The staf was not found!");
        return kategoritePatentes;
    }

    public KategoritePatentes create(CreateKategoritePatentesDto createDto) throws Exception {

        if (createDto.getKategoria() == null || createDto.getKategoria().isBlank()) {
            throw new IllegalArgumentException("The category name is required!");
        }

        if (!createDto.getKategoria().matches("^[A-Za-z0-9]+$")) {
            throw new IllegalArgumentException("The category name must contain only letters and numbers");
        }


        KategoritePatentes kategoritePatentes = kategoriteRepository.create(createDto);
        if (kategoritePatentes == null) {
            throw new Exception("The category failed to be created");
        }
        return kategoritePatentes;
    }

    public ArrayList<KategoritePatentes> getAll() {
        return kategoriteRepository.getAll();
    }

    public boolean delete(int id) throws Exception {
        this.getById(id); // E kontrollojm a ekziston
        return kategoriteRepository.delete(id);
    }
}