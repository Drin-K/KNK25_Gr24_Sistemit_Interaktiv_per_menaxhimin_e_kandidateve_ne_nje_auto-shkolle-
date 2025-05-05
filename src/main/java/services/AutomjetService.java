package services;

import models.Automjetet;
import models.Dto.automjetet.CreateAutomjetetDto;
import repository.AutomjetetRepository;
import repository.KategoritePatentesRepository;
import repository.StafiRepository;

import java.util.ArrayList;

public class AutomjetService {
    private AutomjetetRepository automjetetRepository;
    private KategoritePatentesRepository kategoritePatentesRepository;
    private StafiRepository stafiRepository;
    public AutomjetService(){
        this.automjetetRepository = new AutomjetetRepository();
        this.kategoritePatentesRepository = new KategoritePatentesRepository();
        this.stafiRepository = new StafiRepository();
    }
    public Automjetet getById(int id) throws Exception{
        if (id <= 0){
            throw new Exception("Id does not exist!");
        }
        Automjetet automjeti = this.automjetetRepository.getById(id);
        if (automjeti == null)
        {
            throw new Exception("Automjeti nuk ekziston!");
        }
        return automjeti;
    }
    public Automjetet create(CreateAutomjetetDto createDto) throws Exception{
        //Validimi i ID_Stafi
        if(createDto.getIdStaf() <= 0 || stafiRepository.getById(createDto.getIdStaf()) == null){
            throw new Exception("Stafi nuk ekziston!");
        }
        //Validimi i ID_Kategori
        if(createDto.getIdKategori() <= 0 || kategoritePatentesRepository.getById(createDto.getIdKategori()) == null){
            throw new Exception("Kategoria nuk ekziston!");
        }
        //Validimi i llojit te automjetit
        if(!createDto.getLlojiAutomjetit().equals("Vetura") && !createDto.getLlojiAutomjetit().equals("Motoçikletë") && !createDto.getLlojiAutomjetit().equals("Kamion")){
            throw new Exception("Ky lloj i automjetit nuk ekziston");
        }
        //Validimi i statusit te automjetit
        if(!createDto.getStatusi().equals("Mirëmbajtje")){
            throw new Exception("Automjeti duhet të jetë në statusin 'Mirëmbajtje' për tu krijuar!");
        }
        //Krijimi ne DB
        Automjetet automjeti = automjetetRepository.create(createDto);
        if(automjeti == null){
            throw new Exception("Automjeti nuk u krijua!");
        }
        return automjeti;
    }
    public boolean delete(int id) throws Exception{
        this.getById(id);
        return automjetetRepository.delete(id);
    }
    public Integer getFirstAvailableVehicleIdByLloji(String llojiAutomjetit) throws Exception {
        if (!llojiAutomjetit.equals("Vetura") && !llojiAutomjetit.equals("Motoçikletë") && !llojiAutomjetit.equals("Kamion")) {
            throw new Exception("Lloji i automjetit nuk është valid!");
        }

        ArrayList<Automjetet> automjetet = automjetetRepository.getByStatus(llojiAutomjetit);
        for (Automjetet a : automjetet) {
            if (a.getStatusi().equals("Në përdorim")) {
                return a.getId();
            }
        }

        throw new Exception("Asnjë automjet i lirë nuk është i disponueshëm për llojin: " + llojiAutomjetit);
    }
    public ArrayList<Automjetet> findByLloji(String lloji){
        return automjetetRepository.getByLloji(lloji);
    }
}
