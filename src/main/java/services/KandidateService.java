package services;

import javafx.scene.text.Text;
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
    private static final int MAX_KANDIDATE = 500;
    private KandidatetRepository kandidatetRepository;
    private Kandidatet kandidatet;
    private PatentaRepository patentaRepository;
    private String emailRegEx = "^[a-zA-Z0-9._%+-]+@kandidat\\.com$"; //RegEx i thjesht i emailit
    private String numriTelefonitRegEx = "\\d{8,15}"; //E kqyri a me specifiku veq per numra tKS -> qitash osht veq minimumi mi pas 8 deri 15 numra

    public KandidateService() {
        this.kandidatetRepository = new KandidatetRepository();
        this.patentaRepository = new PatentaRepository();
    }

    public Kandidatet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("Id does not exist!");
        }
        Kandidatet kandidati = this.kandidatetRepository.getById(id);
        if (kandidati == null) throw new Exception("The candidate was not found!");
        return kandidati;
    }

    public Kandidatet create(CreateKandidatetDto createDto) throws Exception {
        int mosha = Period.between(createDto.getDateOfBirth(), LocalDate.now()).getYears();

        if (createDto.getName() == null || createDto.getName().trim().isEmpty()) {
            throw new Exception("The name must not be empty!");
        }
        if (createDto.getSurname() == null || createDto.getSurname().trim().isEmpty()) {
            throw new Exception("The surname must not be empty!");
        }
        //Validimi i moshes per krijimin e kandidatit
        if (mosha < 18) {
            throw new Exception("The candidate must be over 18 years old!");
        }
        //Validimi i gjinise
        if (!createDto.getGjinia().equals("M") && !createDto.getGjinia().equals("F")) {
            throw new Exception("Gender must be Male or Female.");
        }
        //Validimi i numrit te telefonit permes numriTelefonitRegEx
        if (!createDto.getPhoneNumber().matches(numriTelefonitRegEx)) {
            throw new Exception("The phonenumber is not valid");
        }
        //Validimi i emailit permes emailRegEx
        if (!createDto.getEmail().matches(emailRegEx)) {
            throw new Exception("The email is not in the correct format.");
        }
        //Krijimi i Kandidatit ne DB
        Kandidatet kandidati = kandidatetRepository.create(createDto);
        if (kandidati == null) {
            throw new Exception("The candidate was not created.");
        }
        return kandidati;
    }


    public boolean delete(int id) throws Exception {
        this.getById(id);
        return kandidatetRepository.delete(id);
    }

    public HashMap<String, Integer> getAllRegistrationsGroupedByMonth() {
        HashMap<String, Integer> data =new HashMap<>();
        try {
            data = kandidatetRepository.getAllRegistrationsGroupedByMonth();
            if (data.isEmpty()) {
                throw new IllegalStateException("There are no registrations to be shown.");
            }
        } catch (SQLException e) {
            System.out.println("Gabim gjatë marrjes së regjistrimeve: " + e.getMessage());
            throw new RuntimeException("Couldn't read registrations from database", e);
        }
        return data;
    }


    public void getKandidatiInfo(Text name, Text surname, Text email, Text number, Text birth, Text registerDate, Text status, Text genderField) {
        this.kandidatet = kandidatetRepository.getById(UserContext.getUserId());
        name.setText(kandidatet.getName());
        surname.setText(kandidatet.getSurname());
        email.setText(kandidatet.getEmail());
        number.setText(kandidatet.getPhoneNumber());
        birth.setText(kandidatet.getDateOfBirth().toString());
        registerDate.setText(kandidatet.getDataRegjistrimit().toString());
        status.setText(kandidatet.getStatusiProcesit());
        genderField.setText(kandidatet.getGjinia());
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
        int count = this.kandidatetRepository.countKandidatet();
        if (count > MAX_KANDIDATE) {
            throw new IllegalStateException("The maximum limit of candidates has been reached!");
        }
        return count;
    }

    public HashMap<String, Integer> countKandidatetByStatusiProcesit() {
        HashMap<String, Integer> result = this.kandidatetRepository.countKandidatetByStatusiProcesit();

        if (result == null || result.isEmpty()) {
            throw new IllegalStateException("No data was found for candidate statuses!");
        }

        return result;
    }

    public List<Kandidatet> shfaqKandidatetMeTeDrejte() {
        List<Kandidatet> lista = this.kandidatetRepository.shfaqKandidatetMeKushtPagesa("'Paguar'");
        if (lista == null || lista.isEmpty()) {
            throw new IllegalStateException("No candidate meeting the license criteria was found.");
        }
        return lista;
    }

    public List<Kandidatet> shfaqKandidatetMeTeDrejtePaPagesa() {
        List<Kandidatet> lista = this.kandidatetRepository.shfaqKandidatetMeKushtPagesa("'Mbetur', 'Pjesërisht'");
        if (lista == null || lista.isEmpty()) {
            throw new IllegalStateException("No candidate meeting the license criteria was found without completed payment.");
        }
        return lista;
    }
    public boolean ekzistonKandidatMeId(int idQeKerkohet) {
        ArrayList<Kandidatet> listaKandidat = this.getAll();

        for (Kandidatet kandidatet : listaKandidat) {
            if (kandidatet.getIdUser() == idQeKerkohet) {
                return true;
            }
        }

        return false;
    }
}