package app.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.user.CreateUserDto;
import services.UserContext;
import services.UserService;

public class LogInController{
@FXML
private TextField emailField;

@FXML
private PasswordField passwordField;

@FXML
private Button loginButton;

@FXML
private Button signupButton;



@FXML
private void handleLogin() {
    String email = emailField.getText().toLowerCase();
    String password = passwordField.getText();
    boolean isLoggedIn = UserService.login(email, password);

    if (isLoggedIn) {
        showAlert("Login Successful", "You have logged in successfully!", Alert.AlertType.INFORMATION);
        System.out.println(UserContext.getUserId());
    } else {
        showAlert("Login Failed", "Invalid username or password!", Alert.AlertType.ERROR);
    }
}
@FXML
private void handleSignup() {
    //later
}

private void showAlert(String title, String message, Alert.AlertType type) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}}
