package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Dto.kategorite_patentes.CreateKategoritePatentesDto;
import models.KategoritePatentes;
import services.KategoritePatentesService;

public class categoryConsumatorController {

    @FXML private TextArea txtDescriptionA, txtDescriptionB, txtDescriptionC, txtDescriptionD;
    @FXML private TextField txtCategoryA, txtCategoryB, txtCategoryC, txtCategoryD;

    private final KategoritePatentesService kategoritePatentesService;

    public categoryConsumatorController() {
        this.kategoritePatentesService = new KategoritePatentesService();
    }

    @FXML
    private void applyClickA() {
        saveCategory(txtCategoryA, txtDescriptionA);
    }

    @FXML
    private void applyClickB() {
        saveCategory(txtCategoryB, txtDescriptionB);
    }

    @FXML
    private void applyClickC() {
        saveCategory(txtCategoryC, txtDescriptionC);
    }

    @FXML
    private void applyClickD() {
        saveCategory(txtCategoryD, txtDescriptionD);
    }

    private void saveCategory(TextField categoryField, TextArea descriptionArea) {
        try {
            CreateKategoritePatentesDto dto = getKategoriaInputData(categoryField.getText(), descriptionArea.getText());
            KategoritePatentes result = kategoritePatentesService.create(dto); // Fixed method call
            System.out.println("Kategoria inserted successfully!");
            System.out.println("Kategoria ID: " + result.getId());
            cleanFields(categoryField, descriptionArea);
        } catch (Exception e) {
            System.out.println("Error inserting category: " + e.getMessage());
        }
    }

    private CreateKategoritePatentesDto getKategoriaInputData(String categoryInput, String description) {
        return new CreateKategoritePatentesDto(categoryInput, description);
    }

    private void cleanFields(TextField categoryField, TextArea descriptionArea) {
        categoryField.setText("");
        descriptionArea.setText("");
    }
}
