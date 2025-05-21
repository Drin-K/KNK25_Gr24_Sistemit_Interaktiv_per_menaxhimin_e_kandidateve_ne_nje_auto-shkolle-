package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.LanguageManager;
import services.SceneManager;
import services.UserContext;
import services.UserService;
import utils.SceneLocator;

public class LogInController extends BaseController {
    private final LanguageManager languageManager = LanguageManager.getInstance();
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {

        passwordField.setOnAction(event -> {
            try {
                handleLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        emailField.setOnAction(event -> {
            try {
                handleLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void handleLogin() throws Exception {
        String email = emailField.getText().toLowerCase();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("All fields must be filled!");
            errorLabel.setVisible(true);
            return;
        }

        boolean isLoggedIn = UserService.login(email, password);
        if (!isLoggedIn) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid email or password!");
            emailField.clear();
            passwordField.clear();
            return;
        }

        if (UserContext.getRole().equals("Staf")) {
            SceneManager.load(SceneLocator.INSTRUCTOR_FRONT_PAGE);
        } else if (UserContext.getRole().equals("Kandidat")) {
            SceneManager.load(SceneLocator.FRONT_PAGE);
        } else if (UserContext.getRole().equals("Admin")) {
            SceneManager.load(SceneLocator.FRONT_PAGE_ADMIN);
        }
    }

    @FXML
    private void handleSignup() throws Exception {
        SceneManager.load(SceneLocator.SIGNUP_PAGE);
    }

    @FXML
    private void languageClick() throws Exception {
        languageManager.toggleLanguage();
        SceneManager.reload();
    }
}
