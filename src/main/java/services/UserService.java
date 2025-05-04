package services;

import models.Dto.kandidatet.CreateKandidatetDto;
import models.Dto.stafi.CreateStafiDto;
import models.Dto.user.LogInDto;
import models.Dto.user.SignUpDto;
import models.Enums.LoginStatus;
import models.Enums.SignUpStatus;
import models.User;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.HashMap;

public class UserService {
    //shiko qeta edhe njehere mire

//    private  UserRepository userRepository;
//    public UserService(){
//        this.userRepository=new UserRepository();
//    }

//kemi vendos qe me i identifiku userat ne baze te emailit si ne sems
    private static String getRoleFromEmail(String email) {
        if (email.matches(".*@stafi\\.com$")) {
            return "Staf";
        } else if (email.matches(".*@kandidat\\.com$")) {
            return "Kandidat";
        } else {
            return "Unknown";
        }
    }
    public static SignUpStatus signUp(SignUpDto userData) throws Exception {
        if (findUserByEmail(userData.getEmail()) != null) {
            return SignUpStatus.EMAIL_EXISTS;
        }

        // Validimi i passwordit dhe gjenerimi i hash-it
        String password = userData.getPassword();
        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(password, salt);

        // Detektimi i rolit nga emaili
        String detectedRole = getRoleFromEmail(userData.getEmail());
        if (detectedRole.equals("Unknown")) {
            return SignUpStatus.INVALID_ROLE;//sa ma bukur me enume :)
        }

        User createdUser = null;

        if (detectedRole.equals("Staf")) {
            CreateStafiDto stafDto = new CreateStafiDto(
                    userData.getName(),
                    userData.getSurname(),
                    userData.getEmail(),
                    userData.getPhoneNumber(),
                    userData.getDateOfBirth(),
                    passwordHash,
                    salt,
                    detectedRole,
                    userData.getAdresa(),
                    userData.getGjinia()
            );
            createdUser = new StafiService().create(stafDto);//kem mujt ma thjesht ama e kom bo per mi perdor
            //validimet e ndryshme per stafin edhe per kandidatin

        } else if (detectedRole.equals("Kandidat")) {
            CreateKandidatetDto kandidatDto = new CreateKandidatetDto(
                    userData.getName(),
                    userData.getSurname(),
                    userData.getEmail(),
                    userData.getPhoneNumber(),
                    userData.getDateOfBirth(),
                    passwordHash,
                    salt,
                    detectedRole,
                    userData.getAdresa(),
                    userData.getGjinia(),
                    LocalDate.now(),
                    "Në proces"
            );
            createdUser = new KandidateService().create(kandidatDto);
        }

        return createdUser != null ? SignUpStatus.SUCCESS : SignUpStatus.FAILURE;
    }


    public static User findUserByEmail(String email) {
        User user = new UserRepository().findByEmail(email);
        if (user != null) {
            return user; //qetu e kthejna perdorusin nese po ekziston
        }
        return null;
    }

    // Funksioni per logimin e perdorusve, qet klasen LoginResponse e kom lan per me mujt me vazhdu ma vone
    //me qat pjesen e rememberme
    public static LoginResponse login(LogInDto loginData) {
        User user = findUserByEmail(loginData.getEmail());
        if (user == null) {
            return new LoginResponse(LoginStatus.USER_NOT_FOUND, null, 0);
        }

        String password = loginData.getPassword();
        String salt = user.getSalt();
        String passwordHash = user.getHashedPassword();

        if (!PasswordHasher.compareSaltedHash(password, salt, passwordHash)) {
            return new LoginResponse(LoginStatus.INVALID_PASSWORD, null, 0);
        }

        // Marrim rolin nga emaili në vend që të përdorim atë nga objekti user
        String detectedRole = getRoleFromEmail(user.getEmail());

        // Ruajmë përdoruesin në kontekst me rolin e detektuar
        UserContext.setUser(detectedRole, user.getIdUser());

        return new LoginResponse(LoginStatus.SUCCESS, detectedRole, user.getIdUser());
    }

    public static void logout() {
        // Përdoruesi aktual largohet nga sistemi
        UserContext.clear();
    }
    public int countUsersByRole(String role) {
        return new UserRepository().countByRole(role);
    }
    public HashMap<String, Integer> getKandidatetStatusReport() {
        return new UserRepository().countKandidatetByStatusiProcesit();
    }


}