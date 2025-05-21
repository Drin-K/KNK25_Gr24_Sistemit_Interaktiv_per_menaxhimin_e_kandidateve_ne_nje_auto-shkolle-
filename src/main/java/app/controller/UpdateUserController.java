package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.Dto.kandidatet.UpdateKandidatetDto;
import models.Dto.stafi.UpdateStafiDto;
import models.Dto.user.UpdateUserDto;
import repository.KandidatetRepository;
import repository.StafiRepository;
import repository.UserRepository;

public class UpdateUserController extends BaseController {
    @FXML
    private TextField idField, emailField, phoneField, passwordField, addressField;
    @FXML
    private DatePicker dataRegjistrimiPicker;
    @FXML
    private ComboBox<String> statusiProcesitCombo;
    @FXML
    private VBox kandidatExtraFields;

    @FXML
    private void initialize() {
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            toggleKandidatExtraFields(newValue);
        });
    }

    private void toggleKandidatExtraFields(String email) {
        if (email != null && email.toLowerCase().endsWith("@kandidat.com")) {
            kandidatExtraFields.setVisible(true);
            kandidatExtraFields.setManaged(true);
        } else {
            kandidatExtraFields.setVisible(false);
            kandidatExtraFields.setManaged(false);
        }
    }

    @FXML
    private void handleUpdate() {
        String email = emailField.getText();
        int id = Integer.parseInt(idField.getText());
        UserRepository userRepository;
        UpdateUserDto userDto;

        if (email != null && email.toLowerCase().endsWith("@kandidat.com")) {
            userDto = new UpdateKandidatetDto(
                    id,
                    email,
                    phoneField.getText(),
                    passwordField.getText(),
                    addressField.getText(),
                    dataRegjistrimiPicker.getValue(),
                    statusiProcesitCombo.getValue()
            );
            userRepository = new KandidatetRepository();
        } else {
            userDto = new UpdateStafiDto(
                    id,
                    email,
                    phoneField.getText(),
                    passwordField.getText(),
                    addressField.getText()
            );
            userRepository = new StafiRepository();
        }

        userRepository.update(userDto);

        showAlert(Alert.AlertType.INFORMATION, "Updated", "The user was successfully updated!");
    }
}
