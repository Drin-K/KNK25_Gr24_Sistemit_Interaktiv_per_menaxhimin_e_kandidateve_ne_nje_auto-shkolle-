package app.controller;
import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Dto.kandidatet.UpdateKandidatetDto;
import models.Dto.user.UpdateUserDto;
import repository.KandidatetRepository;
import repository.StafiRepository;
import repository.UserRepository;

public class UpdateUserController extends BaseController {

    @FXML
    private TextField idField, nameField, surnameField, emailField, phoneField, passwordField, saltField, addressField;
    @FXML private DatePicker dobPicker, dataRegjistrimiPicker;
    @FXML private ComboBox<String> genderCombo, roleCombo, statusiProcesitCombo;
    @FXML private VBox kandidatExtraFields;

    @FXML
    private void initialize() {
        roleCombo.setOnAction(event -> updateKandidatExtraFields());
    }

    @FXML
    private void updateKandidatExtraFields() {
        String role = roleCombo.getValue();
        boolean isKandidat = "Kandidat".equals(role);
        kandidatExtraFields.setVisible(isKandidat);
        kandidatExtraFields.setManaged(isKandidat);
    }

    @FXML
    private void handleUpdate() {
        String role = roleCombo.getValue();
        int id = Integer.parseInt(idField.getText());
        UserRepository userRepository;
        UpdateUserDto userDto;

        if ("Kandidat".equals(role)) {
            userDto = new UpdateKandidatetDto(
                    id,
                    nameField.getText(),
                    surnameField.getText(),
                    emailField.getText(),
                    phoneField.getText(),
                    dobPicker.getValue(),
                    passwordField.getText(),
                    saltField.getText(),
                    role,
                    addressField.getText(),
                    genderCombo.getValue(),
                    dataRegjistrimiPicker.getValue(),
                    statusiProcesitCombo.getValue()
            );
            userRepository = new KandidatetRepository();
        } else {
            userDto = new UpdateUserDto(
                    id,
                    nameField.getText(),
                    surnameField.getText(),
                    emailField.getText(),
                    phoneField.getText(),
                    dobPicker.getValue(),
                    passwordField.getText(),
                    saltField.getText(),
                    role,
                    addressField.getText(),
                    genderCombo.getValue()
            );
            userRepository = new StafiRepository();
        }

        userRepository.update(userDto);

        showAlert(Alert.AlertType.INFORMATION, "Updated", "The user was successfully updated!");
    }
}
