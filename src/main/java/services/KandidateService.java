package services;

import models.*;
import models.Dto.kandidatet.CreateKandidatetDto;
import repository.KandidatetRepository;
import repository.PatentaRepository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KandidateService {
    private KandidatetRepository kandidatetRepository;
    private PatentaRepository patentaRepository;
    private String emailRegEx = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; //RegEx i thjesht i emailit
    private String numriTelefonitRegEx = "\\d{8,15}"; //E kqyri a me specifiku veq per numra tKS -> qitash osht veq minimumi mi pas 8 deri 15 numra

    public KandidateService() {
        this.kandidatetRepository = new KandidatetRepository();
        this.patentaRepository=new PatentaRepository();
    }

    public Kandidatet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("Id does not exist!");
        }
        Kandidatet kandidati = this.kandidatetRepository.getById(id);
        if (kandidati == null) throw new Exception("Kandidati nuk u gjet!");
        return kandidati;
    }

    public Kandidatet create(CreateKandidatetDto createDto) throws Exception {
        int mosha = Period.between(createDto.getDateOfBirth(), LocalDate.now()).getYears(); //Llogarit moshen duke kalkuluar perioden e sotme zbritur me perioden e lindjes se kandidatit
        //Implementimi i Period eshte i nevojshum qe saktesisht ta llogarisim moshen duke mos injoruar muajin dhe diten !!!!
        //Validimi i emrit dhe mbiemrit gjate krijimit te kandidatit
        //Emri dhe mbiemri nuk duhet te jete bosh -> createDto.getEmri()/getMbiemri() == null
        //Emri dhe mbiemri duhet te kete se paku nje karakter -> createDto.getEmri()/getMbiemri().trim().isEmpty()
        if (createDto.getName() == null || createDto.getName().trim().isEmpty()) {
            throw new Exception("Emri nuk duhet të jetë bosh!");
        }
        if (createDto.getSurname() == null || createDto.getSurname().trim().isEmpty()) {
            throw new Exception("Mbiemri nuk duhet të jetë bosh!");
        }
        //Validimi i moshes per krijimin e kandidatit
        if (mosha < 18) {
            throw new Exception("Kandidati duhet të jetë mbi 18 vjeç!");
        }
        //Validimi i gjinise
        if (!createDto.getGjinia().equals("M") && !createDto.getGjinia().equals("F")) {
            throw new Exception("Gjinia duhet të jetë Mashkull ose Femer");
        }
        //Validimi i numrit te telefonit permes numriTelefonitRegEx
        if (!createDto.getPhoneNumber().matches(numriTelefonitRegEx)) {
            throw new Exception("Numri i telefonit nuk është valid");
        }
        //Validimi i emailit permes emailRegEx
        if (!createDto.getEmail().matches(emailRegEx)) {
            throw new Exception("Emaili nuk është në formatin e duhur");
        }
        //Validimi i statusit të procesit
        if (!createDto.getStatusiProcesit().equals("Në proces") && !createDto.getStatusiProcesit().equals("Përfunduar")) {
            throw new Exception("Statusi i procesit duhet të jetë Në proces ose Përfunduar");
        }
        //Data e regjistrimit duhet te jete data e regjistrimit te Kandidatit
        createDto.setDataRegjistrimit(LocalDate.now());
        //Krijimi i Kandidatit ne DB
        Kandidatet kandidati = kandidatetRepository.create(createDto);
        if (kandidati == null) {
            throw new Exception("Kandidati nuk u krijua");
        }
        return kandidati;
    }


    public boolean delete(int id) throws Exception {
        //this.getById(id); // E kontrollojm a ekziston
        return kandidatetRepository.delete(id);
    }

    public HashMap<String, Integer> getRegistrationsLast12Months() {
        try {
            return kandidatetRepository.getRegistrationsLast12Months();
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }

    }

    public boolean aprovoPatenten(int kandidatId) throws SQLException {
       return patentaRepository.aprovoPatenten(kandidatId);
    }

public ArrayList<Kandidatet> getAll() {
    ArrayList<User> users = kandidatetRepository.getAll();
    ArrayList<Kandidatet> kandidatiList = new ArrayList<>();

    for (User user : users) {
        if (user instanceof Kandidatet) {
            kandidatiList.add((Kandidatet) user);
        }
    }
    return kandidatiList;
}
    public int countKandidatet() {
        return this.kandidatetRepository.countKandidatet();
    }
    public HashMap<String, Integer> getKandidatetStatusReport() {
        return this.kandidatetRepository.countKandidatetByStatusiProcesit();
    }
    public List<Kandidatet> shfaqKandidatetMeTeDrejte(){
        return this.kandidatetRepository.shfaqKandidatetMeTeDrejte();
    }
    public List<Kandidatet> shfaqKandidatetMeTeDrejtePaPagesa(){
        return this.kandidatetRepository.shfaqKandidatetMeTeDrejtePaPagesa();
    }
}