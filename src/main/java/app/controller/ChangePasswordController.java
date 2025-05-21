package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import services.UserService;

public class ChangePasswordController extends BaseController {
    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;

    @FXML
    public void onClickChangePassword() {
        String current = currentPassword.getText();
        String newPass = newPassword.getText();
        String confirm = confirmPassword.getText();
        try {
            UserService.changePassword(current, newPass, confirm);
            showAlert(Alert.AlertType.INFORMATION, "Message", "Password changed successfully");
        } catch (Exception e) {
            showAlert(Alert.AlertType.WARNING, "Warning", e.getLocalizedMessage());
        }
    }
}
