package app.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.user.CreateUserDto;
import services.LanguageManager;
import services.SceneManager;
import services.UserContext;
import services.UserService;
import utils.SceneLocator;

public class LogInController{
    private final LanguageManager languageManager = LanguageManager.getInstance();
@FXML
private TextField emailField;

@FXML
private PasswordField passwordField;

@FXML
private Button loginButton;

@FXML
private Button signupButton;
@FXML
private Label errorLabel;


@FXML
private void handleLogin()throws Exception {
    String email = emailField.getText().toLowerCase();
    String password = passwordField.getText();
    boolean isLoggedIn = UserService.login(email, password);
if (email.isEmpty()||password.isEmpty()){
    errorLabel.setText("All fields must be filled!");
    errorLabel.setVisible(true);
    return;
}
    String[]email1=email.split("@");
    if (email1[1].equals("staf.com")){
        SceneManager.load(SceneLocator.INSTRUCTOR_FRONT_PAGE);
    } else if (email1[1].equals("kandidat.com")) {
SceneManager.load(SceneLocator.FRONT_PAGE);
    } else if (email1.equals("admin.com")) {
        SceneManager.load(SceneLocator.FRONT_PAGE_ADMIN);
    }
}
@FXML
private void handleSignup()throws Exception {
    SceneManager.load(SceneLocator.SIGNUP_PAGE);
}
@FXML
private void languageClick() throws Exception{
    languageManager.toggleLanguage();
    SceneManager.reload();
}

private void showAlert(String title, String message, Alert.AlertType type) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}}
