package services;

import models.Dto.stafi.CreateStafiDto;
import models.Kandidatet;
import models.Stafi;
import models.User;
import repository.StafiRepository;

import java.util.ArrayList;


public class StafiService {
    private StafiRepository stafiRepository;
    private String emailRegEx = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; //RegEx i thjesht i emailit
    private String numriTelefonitRegEx = "\\d{8,15}";// nga bardhi idea. Shikoni KandidatetService per me shum info :)
    public StafiService() {
        this.stafiRepository = new StafiRepository();
    }
    public Stafi getById(int id) throws Exception{
        if(id<=0){
            throw new Exception("Id does not exist!");
        }

        Stafi stafi =(Stafi)this.stafiRepository.getById(id);
        if (stafi == null) throw new Exception("Stafi nuk u gjet!");
        return stafi;
    }
    public Stafi create(CreateStafiDto createDto) throws Exception{
        //Implementimi i Period eshte i nevojshum qe saktesisht ta llogarisim moshen duke mos injoruar muajin dhe diten !!!!
        //Validimi i emrit dhe mbiemrit gjate krijimit te kandidatit
        //Emri dhe mbiemri nuk duhet te jete bosh -> createDto.getEmri()/getMbiemri() == null
        //Emri dhe mbiemri duhet te kete se paku nje karakter -> createDto.getEmri()/getMbiemri().trim().isEmpty()
        if(createDto.getName()== null || createDto.getName().trim().isEmpty()){
            throw new Exception("Emri nuk duhet të jetë bosh!");
        }
        if(createDto.getSurname() == null || createDto.getSurname().trim().isEmpty()){
            throw new Exception("Mbiemri nuk duhet të jetë bosh!");
        }

        //Validimi i numrit te telefonit permes numriTelefonitRegEx
        if(!createDto.getPhoneNumber().matches(numriTelefonitRegEx)){
            throw new Exception("Numri i telefonit nuk është valid");
        }
        //Validimi i emailit permes emailRegEx
        if(!createDto.getEmail().matches(emailRegEx)){
            throw new Exception("Emaili nuk është në formatin e duhur");
        }
        //Krijimi i Kandidatit ne DB
        Stafi stafi = (Stafi)stafiRepository.create(createDto);
        if(stafi == null){
            throw new Exception("Stafi nuk u krijua");
        }
        return stafi;
    }
    public ArrayList<Stafi> getAll() {
        ArrayList<User> users = stafiRepository.getAll();
        ArrayList<Stafi> stafiList = new ArrayList<>();

        for (User user : users) {
            if (user instanceof Stafi) {
                stafiList.add((Stafi) user);
            }
        }
        return stafiList;
    }
    public boolean delete(int id) throws Exception{
        this.getById(id);
        return stafiRepository.delete(id);
    }
    public int countStafi() {
        return this.stafiRepository.countStafi();
    }


}
