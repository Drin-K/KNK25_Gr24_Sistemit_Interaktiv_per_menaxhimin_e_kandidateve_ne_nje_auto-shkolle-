package app.controller;
import javafx.scene.control.ToggleGroup;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.user.CreateUserDto;
import services.PasswordHasher;

import services.UserService;

import java.time.LocalDate;

public class SignUpController {
    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private DatePicker dobPicker;
    @FXML private Button signUpButton;
    @FXML private Button loginButton;
    @FXML private Label messageLabel;
    @FXML private Label errorLabel;
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    private ToggleGroup genderGroup;
    @FXML
    public void initialize(){
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
    }
    @FXML
    public void handleSave() {
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        String name = nameField.getText();
        String surname = surnameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String gender = maleRadio.isSelected() ? "M" : femaleRadio.isSelected() ? "F" : "";
        LocalDate dob = dobPicker.getValue();
        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
            errorLabel.setText("All fields must be filled!");
            errorLabel.setVisible(true);
            return;
        }

        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match!");
            errorLabel.setVisible(true);
            return;
        }

        CreateUserDto dto = new CreateUserDto(name, surname, email, phone, dob, password, PasswordHasher.generateSalt(),"Kandidat", address, gender);

        boolean signupSuccess = UserService.signup(dto);

        if (signupSuccess) {
            messageLabel.setText("User successfully signed up!");
            messageLabel.setVisible(true);
            errorLabel.setVisible(false);
        } else {
            errorLabel.setText("Signup failed, please check your details.");
            errorLabel.setVisible(true);
            messageLabel.setVisible(false);
        }
    }
    @FXML
    public void gotoLogIn() throws Exception {
     //
    }
}

