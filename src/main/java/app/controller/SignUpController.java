package app.controller;
import app.controller.base.BaseController;
import javafx.scene.control.ToggleGroup;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.kandidatet.CreateKandidatetDto;
import services.*;

import utils.SceneLocator;

import java.time.LocalDate;

public class SignUpController extends BaseController {
    private final LanguageManager languageManager = LanguageManager.getInstance();
    private final KandidateService kandidateService= new KandidateService();
    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private DatePicker dobPicker;
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
        //kemi mundsu qe veq kandidati me mujt me u bo signup, instruktoret shtohen veq permes adminit
        CreateKandidatetDto dto = new CreateKandidatetDto(name, surname, email, phone, dob, password, PasswordHasher.generateSalt(), address, gender);

        boolean signupSuccess = UserService.signup(dto);
        if (signupSuccess) {
            messageLabel.setText("Successfully signed up!");
            messageLabel.setVisible(true);
            errorLabel.setVisible(false);
        } else {
            errorLabel.setText("Signup failed, please check your details.");
            errorLabel.setVisible(true);
            messageLabel.setVisible(false);
        }
        this.clearFields();

    }
    @FXML
    public void gotoLogIn() throws Exception {
        SceneManager.load(SceneLocator.LOGIN_PAGE);
    }
    @FXML
    private void languageClick() throws Exception{
        languageManager.toggleLanguage();
        SceneManager.reload();
    }

    public void clearFields() {
        nameField.clear();
        surnameField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        dobPicker.setValue(null);
        genderGroup.selectToggle(null);

    }

}

