package services;

import javafx.scene.text.Text;
import models.Dto.stafi.CreateStafiDto;
import models.Kandidatet;
import models.Stafi;
import models.User;
import repository.StafiRepository;

import java.util.ArrayList;


public class StafiService {
    public static final int MAX_STAF=50;
    private StafiRepository stafiRepository;
    private Stafi instruktori;
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
        if (stafi == null) throw new Exception("The staf was not found!");
        return stafi;
    }
    public Stafi create(CreateStafiDto createDto) throws Exception{
        //Implementimi i Period eshte i nevojshum qe saktesisht ta llogarisim moshen duke mos injoruar muajin dhe diten !!!!
        //Validimi i emrit dhe mbiemrit gjate krijimit te kandidatit
        //Emri dhe mbiemri nuk duhet te jete bosh -> createDto.getEmri()/getMbiemri() == null
        //Emri dhe mbiemri duhet te kete se paku nje karakter -> createDto.getEmri()/getMbiemri().trim().isEmpty()
        if(createDto.getName()== null || createDto.getName().trim().isEmpty()){
            throw new Exception("The name must not be empty!");
        }
        if(createDto.getSurname() == null || createDto.getSurname().trim().isEmpty()){
            throw new Exception("The surname must not be empty!");
        }

        //Validimi i numrit te telefonit permes numriTelefonitRegEx
        if(!createDto.getPhoneNumber().matches(numriTelefonitRegEx)){
            throw new Exception("The phonenumber is not valid!");
        }
        //Validimi i emailit permes emailRegEx
        if(!createDto.getEmail().matches(emailRegEx)){
            throw new Exception("The email is not in the correct format.");
        }
        //Krijimi i Kandidatit ne DB
        Stafi stafi = (Stafi)stafiRepository.create(createDto);
        if(stafi == null){
            throw new Exception("The staf was not created.");
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
        int count=this.stafiRepository.countStafi();
        if (count>MAX_STAF) {
            throw new IllegalStateException("The maximum staff limit has been reached!");
        }
        return count;
    }

    public String getMostRatedInstructorName() {
        String name = stafiRepository.getInstructorNameByRatingExtremum("MAX");
        return (name != null && !name.isBlank()) ? name : "No instructor";
    }

    public String getLeastRatedInstructorName() {
        String name = stafiRepository.getInstructorNameByRatingExtremum("MIN");
        return (name != null && !name.isBlank()) ? name : "No instructor";
    }

    public void getStafiInfo(Text name,Text surname,Text email,Text number,Text data,Text gjinia){
        this.instruktori = stafiRepository.getById(UserContext.getUserId());
        name.setText(instruktori.getName());
        surname.setText(instruktori.getSurname());
        email.setText(instruktori.getEmail());
        number.setText(instruktori.getPhoneNumber());
        data.setText(instruktori.getDateOfBirth().toString());
        gjinia.setText(instruktori.getGjinia());
    }
    public boolean ekzistonStafiMeId(int idQeKerkohet) {
        ArrayList<Stafi> listaStafit = this.getAll();

        for (Stafi stafi : listaStafit) {
            if (stafi.getIdUser() == idQeKerkohet) {
                return true; // E gjetëm
            }
        }

        return false; // Nuk u gjet
    }
}
