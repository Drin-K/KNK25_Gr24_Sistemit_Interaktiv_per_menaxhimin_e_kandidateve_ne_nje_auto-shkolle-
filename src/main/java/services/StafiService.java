package services;

import models.Dto.stafi.CreateStafiDto;
import models.Stafi;
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
        Stafi stafi = this.stafiRepository.getById(id);
        if (stafi == null) throw new Exception("Stafi nuk u gjet!");
        return stafi;
    }
    public Stafi create(CreateStafiDto createDto) throws Exception{
        //Implementimi i Period eshte i nevojshum qe saktesisht ta llogarisim moshen duke mos injoruar muajin dhe diten !!!!
        //Validimi i emrit dhe mbiemrit gjate krijimit te kandidatit
        //Emri dhe mbiemri nuk duhet te jete bosh -> createDto.getEmri()/getMbiemri() == null
        //Emri dhe mbiemri duhet te kete se paku nje karakter -> createDto.getEmri()/getMbiemri().trim().isEmpty()
        if(createDto.getEmri() == null || createDto.getEmri().trim().isEmpty()){
            throw new Exception("Emri nuk duhet të jetë bosh!");
        }
        if(createDto.getMbiemri() == null || createDto.getMbiemri().trim().isEmpty()){
            throw new Exception("Mbiemri nuk duhet të jetë bosh!");
        }
        //Validimi i gjinise
        if(!createDto.getRoli().equals("Instruktor") && !createDto.getRoli().equals("Ekzaminer")){
            throw new Exception("Roli duhet të jetë *instruktor* ose *ekzaminer* ");
        }
        //Validimi i numrit te telefonit permes numriTelefonitRegEx
        if(!createDto.getNumriTelefonit().matches(numriTelefonitRegEx)){
            throw new Exception("Numri i telefonit nuk është valid");
        }
        //Validimi i emailit permes emailRegEx
        if(!createDto.getEmail().matches(emailRegEx)){
            throw new Exception("Emaili nuk është në formatin e duhur");
        }
        //Kontrollimi i emailit a ekziston ne databazen tone apo jo
        if(stafiRepository.existsByEmail(createDto.getEmail())){
            throw new Exception("Emaili tashmë ekziston!");
        }
        //Krijimi i Kandidatit ne DB
        Stafi stafi = stafiRepository.create(createDto);
        if(stafi == null){
            throw new Exception("Stafi nuk u krijua");
        }
        return stafi;
    }
    public ArrayList<Stafi> getAll(){
        return stafiRepository.getAll(); //Kthen te gjithe stafin e nevojshum per listime, grafe, tabela etj....
    }
    public boolean delete(int id) throws Exception{
        this.getById(id); // E kontrollojm a ekziston
        return stafiRepository.delete(id);
    }
}
