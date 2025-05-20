package services;

import javafx.scene.control.Alert;
import models.Dto.kandidatet.CreateKandidatetDto;
import models.Dto.user.UpdateUserDto;
import models.Kandidatet;
import models.User;
import repository.AdminRepository;
import repository.KandidatetRepository;
import repository.StafiRepository;
import repository.UserRepository;


public class UserService {

    private static final KandidatetRepository kandidatetRepo = new KandidatetRepository();
    private static final KandidateService kandidatService=new KandidateService();
    private static final UserRepository stafiRepo = new StafiRepository();
    private static final UserRepository adminRepo=new AdminRepository();

    public static boolean signup(CreateKandidatetDto dto)throws Exception {
        try {
            String email = dto.getEmail().toLowerCase();
            if (kandidatetRepo.findByEmail(email) != null) {
                System.out.println("This email already exists in the system.");
                return false;
            }
            String salt = dto.getSalt();
            String hashedPassword = PasswordHasher.generateSaltedHash(dto.getPassword(), salt);
            dto.setPassword(hashedPassword);
            Kandidatet created = kandidatService.create(dto);
            if (created == null) {
                System.out.println("Failed to create candidate.");
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
    public static void changePassword(String current,String newPass,String confirm){
        int userId = UserContext.getUserId();
        String role = UserContext.getRole();
        if(role.equals("Kandidat")){
            try {
                User user = kandidatetRepo.getById(userId);
                if (user == null) {
                    throw new Exception("User does not exists!");
                }
                if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
                    throw new Exception("All fields must be filled.");
                }
                if (!newPass.equals(confirm)) {
                    throw new Exception("New password and confirmation do not match.");
                }
                if (!PasswordHasher.compareSaltedHash(current, user.getSalt(), user.getHashedPassword())) {
                    throw new Exception("Current password is incorrect !");
                }
                String newSalt = PasswordHasher.generateSalt();
                String newHashedPassword = PasswordHasher.generateSaltedHash(newPass, newSalt);

                UpdateUserDto updateDto = new UpdateUserDto();
                updateDto.setIdUser(userId);
                updateDto.setPassword(newHashedPassword);
                updateDto.setSalt(newSalt);
                kandidatetRepo.update(updateDto);
                System.out.println("Password successfully changed.");
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                User user = stafiRepo.getById(userId);
                if(user == null){
                    throw new Exception("User does not exists!");
                }
                if (!PasswordHasher.compareSaltedHash(current, user.getSalt(), user.getHashedPassword())) {
                    throw new Exception("Current password is incorrect !");
                }
                String newSalt = PasswordHasher.generateSalt();
                String newHashedPassword = PasswordHasher.generateSaltedHash(newPass, newSalt);

                UpdateUserDto updateDto = new UpdateUserDto();
                updateDto.setIdUser(userId);
                updateDto.setPassword(newHashedPassword);
                updateDto.setSalt(newSalt);
                stafiRepo.update(updateDto);
                System.out.println("Password successfully changed.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
