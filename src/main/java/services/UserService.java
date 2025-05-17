package services;

import models.Dto.kandidatet.CreateKandidatetDto;
import models.Dto.user.CreateUserDto;
import models.Kandidatet;
import models.User;
import repository.AdminRepository;
import repository.KandidatetRepository;
import repository.StafiRepository;
import repository.UserRepository;

public class UserService {

    private static final KandidatetRepository kandidatetRepo = new KandidatetRepository();
    private static final UserRepository stafiRepo = new StafiRepository();
    private static final UserRepository adminRepo=new AdminRepository();

    public static boolean signup(CreateKandidatetDto dto) {
        try {
            String email = dto.getEmail().toLowerCase();
            if (kandidatetRepo.findByEmail(email) != null) {
                System.out.println("This email already exists in the system.");
                return false;
            }
            String salt = dto.getSalt();
            String hashedPassword = PasswordHasher.generateSaltedHash(dto.getPassword(), salt);
            dto.setPassword(hashedPassword);
            Kandidatet created = kandidatetRepo.create(dto);
            if (created == null) {
                System.out.println("Failed to create candidate.");
                return false;
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
            } else if (email.toLowerCase().endsWith("@admin.com")) {
                user=adminRepo.findByEmail(email);
                role="Admin";
            }else {
                System.out.println("The email does not match any role.");
                return false;
            }
            if (user == null) {
                System.out.println("The user was not found.");
                return false;
            }
            UserContext.setUser(role,user.getIdUser(),user.getEmail());
            return PasswordHasher.compareSaltedHash(password, user.getSalt(), user.getHashedPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
