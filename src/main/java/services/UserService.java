package services;

import models.Dto.user.CreateUserDto;
import models.Kandidatet;
import models.User;
import repository.KandidatetRepository;
import repository.StafiRepository;
import repository.UserRepository;

public class UserService {

    private static final UserRepository kandidatetRepo = new KandidatetRepository();
    private static final UserRepository stafiRepo = new StafiRepository();

    public static boolean signup(CreateUserDto dto) {
        try {
            String email = dto.getEmail().toLowerCase();

            if (email.endsWith("@kandidat.com")) {
                dto.setRole("Kandidat");
            } else if (email.endsWith("@staf.com")) {
                dto.setRole("Staf");
            } else {
                System.out.println("The email must end with @kandidat.com or @staf.com.");
                return false;
            }

            UserRepository repo = dto.getRole().equals("Kandidat") ? kandidatetRepo : stafiRepo;
            if (repo.findByEmail(email) != null) {
                System.out.println("This email already exists in the system.");
                return false;
            }

            String salt = PasswordHasher.generateSalt();
            String hashedPassword = PasswordHasher.generateSaltedHash(dto.getPassword(), salt);

            dto.setSalt(salt);
            dto.setPassword(hashedPassword);
            if (dto.getRole().equalsIgnoreCase("Kandidat")) {
                kandidatetRepo.create(dto);
            } else {
                stafiRepo.create(dto);
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean login(String email, String password) {
        try {
            User user;
            String role;
            if (email.toLowerCase().endsWith("@kandidat.com")) {
                role="Kandidat";
                user = kandidatetRepo.findByEmail(email);
            } else if (email.toLowerCase().endsWith("@staf.com")) {
                user = stafiRepo.findByEmail(email);
                role="Staf";
            } else {
                System.out.println("The email does not match any role.");
                return false;
            }
            if (user == null) {
                System.out.println("The user was not found.");
                return false;
            }
            UserContext.setUser(role,user.getIdUser());
            return PasswordHasher.compareSaltedHash(password, user.getSalt(), user.getHashedPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
