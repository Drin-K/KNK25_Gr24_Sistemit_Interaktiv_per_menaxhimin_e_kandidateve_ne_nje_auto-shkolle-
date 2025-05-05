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
    public KategoritePatentes getById(int id) throws Exception{
        if(id<=0){
            throw new Exception("Id does not exist!");
        }
        KategoritePatentes kategoritePatentes = this.kategoriteRepository.getById(id);
        if (kategoritePatentes == null) throw new Exception("Stafi nuk u gjet!");
        return kategoritePatentes;
    }

    public KategoritePatentes create(CreateKategoritePatentesDto createDto) throws Exception{

        if (createDto.getKategoria()== null || createDto.getKategoria().isBlank()) {
            throw new IllegalArgumentException("Emri i kategorisë është i nevojshëm!");
        }

        if (!createDto.getKategoria().matches("^[A-Za-z0-9]+$")) {
            throw new IllegalArgumentException("Emri i kategorisë duhet të ketë vetëm shkronja dhe numra!");
        }


        KategoritePatentes kategoritePatentes = kategoriteRepository.create(createDto);
        if(kategoritePatentes == null){
            throw new Exception("Kategoria nuk u krijua");
        }
        return kategoritePatentes;
    }
    public ArrayList<KategoritePatentes> getAll(){
        return kategoriteRepository.getAll();
    }
    public boolean delete(int id) throws Exception{
        this.getById(id); // E kontrollojm a ekziston
        return kategoriteRepository.delete(id);
    }
}