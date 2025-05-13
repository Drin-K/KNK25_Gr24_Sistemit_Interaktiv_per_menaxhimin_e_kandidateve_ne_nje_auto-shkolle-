package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import models.Dto.user.UpdateUserDto;
import models.User;
import repository.KandidatetRepository;
import repository.StafiRepository;
import repository.UserRepository;
import services.PasswordHasher;
import services.UserContext;

public class ChangePasswordController extends BaseController {
    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    public void onClickChangePassword(){
        String current = currentPassword.getText();
        String newPass = newPassword.getText();
        String confirm = confirmPassword.getText();

        if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
            System.out.println("All fields must be filled.");
            showAlert(Alert.AlertType.WARNING,"Notification","All fields must be filled.");
            return;
        }

        if (!newPass.equals(confirm)) {
            showAlert(Alert.AlertType.WARNING,"Notification","Password is not matching !");
            System.out.println("New password and confirmation do not match.");
            return;
        }

        int userId = UserContext.getUserId();
        String role = UserContext.getRole();
        UserRepository userRepository;

        if(role.equals("Kandidat")){
             userRepository = new KandidatetRepository();
        }
        else{
             userRepository = new StafiRepository();
        }

        User user = userRepository.getById(userId);
        if (user == null) {
            System.out.println("User not found.");
            showAlert(Alert.AlertType.ERROR,"Notification","User not found !");
            return;
        }

        if (!PasswordHasher.compareSaltedHash(current, user.getSalt(), user.getHashedPassword())) {
            showAlert(Alert.AlertType.WARNING,"Notification","Current password is incorrect !");
            System.out.println("Current password is incorrect.");
            return;
        }

        try {
            String newSalt = PasswordHasher.generateSalt();
            String newHashedPassword = PasswordHasher.generateSaltedHash(newPass, newSalt);

            UpdateUserDto updateDto = new UpdateUserDto();
            updateDto.setIdUser(userId);
            updateDto.setPassword(newHashedPassword);
            userRepository.update(updateDto);

            System.out.println("Password successfully changed.");
            showAlert(Alert.AlertType.CONFIRMATION,"Notfication","Changed password succesfuly !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to change password.");
            showAlert(Alert.AlertType.ERROR,"Error","Failed to change password !");
        }
    }
}
