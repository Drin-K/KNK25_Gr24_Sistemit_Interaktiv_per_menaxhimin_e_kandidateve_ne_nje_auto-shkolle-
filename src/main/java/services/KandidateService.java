package services;

import models.Dto.kandidatet.CreateKandidatetDto;
import models.Kandidatet;
import repository.KandidatetRepository;

import java.time.LocalDate;

public class KandidateService {
    private KandidatetRepository kandidatetRepository;
    private String emailRegEx = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; //RegEx i thjesht i emailit
    private String numriTelefonitRegEx = "\\d{8,15}"; //E kqyri a me specifiku veq per numra tKS -> qitash osht veq minimumi mi pas 8 deri 15 numra
    public KandidateService(){
        this.kandidatetRepository = new KandidatetRepository();
    }
    public Kandidatet create(CreateKandidatetDto createDto) throws Exception{
        //Validimi i emrit dhe mbiemrit gjate krijimit te kandidatit
        //Emri dhe mbiemri nuk duhet te jete bosh -> createDto.getEmri()/getMbiemri() == null
        //Emri dhe mbiemri duhet te kete se paku nje karakter -> createDto.getEmri()/getMbiemri().trim().isEmpty()
        if(createDto.getEmri() == null || createDto.getEmri().trim().isEmpty()){
            throw new Exception("Emri nuk duhet të jetë bosh!");
        }
        if(createDto.getMbiemri() == null || createDto.getMbiemri().trim().isEmpty()){
            throw new Exception("Mbiemri nuk duhet të jetë bosh!");
        }
        //Validimi i moshes per krijimin e kandidatit
        if(createDto.getDatelindja().getYear() < 18){
            throw new Exception("Kandidati duhet të jetë mbi 18 vjeç!");
        }
        //Validimi i gjinise
        if(!createDto.getGjinia().equals("Mashkull") && !createDto.getGjinia().equals("Femer")){
            throw new Exception("Gjinia duhet të jetë Mashkull ose Femer");
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
        if(kandidatetRepository.existsByEmail(createDto.getEmail())){
            throw new Exception("Emaili tashmë ekziston!");
        }
        //Validimi i statusit të procesit
        if(!createDto.getStatusiProcesit().equals("Në proces") && !createDto.getStatusiProcesit().equals("Përfunduar")){
            throw new Exception("Statusi i procesit duhet të jetë Në proces ose Përfunduar");
        }
        //Data e regjistrimit duhet te jete data e regjistrimit te Kandidatit
        createDto.setDataRegjistrimit(LocalDate.now());
        //Krijimi i Kandidatit ne DB
        Kandidatet kandidati = kandidatetRepository.create(createDto);
        if(kandidati == null){
            throw new Exception("Kandidati nuk u krijua");
        }
        return kandidati;
    }
}
