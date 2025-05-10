package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Dto.orari.CreateOrariDto;
import models.Dto.pagesat.CreatePagesatDto;
import models.Orari;
import models.Pagesat;
import repository.PagesatRepository;
import services.*;
import utils.SceneLocator;

import java.time.LocalDate;

public class PagesatHandleController {

    private final UserContext userContext;
    private final PagesaService pagesaService;


    public PagesatHandleController() {
        this.userContext = new UserContext();
        this.pagesaService = new PagesaService();
    }
    @FXML
    private AnchorPane rightPane;

    @FXML
    private MenuButton choosePayment;

    @FXML
    private MenuItem cashOption;

    @FXML
    private TextField bankNumTxt;

    @FXML
    private TextField shumaTxt;

    @FXML
    private TextField idKand;



    public CreatePagesatDto getPagesaInputData() {
        int kandidatId = Integer.parseInt(this.idKand.getText().trim());
        double shuma = Double.parseDouble(this.shumaTxt.getText().trim());
        String nrXhirollog = this.bankNumTxt.getText().trim();
        LocalDate data = LocalDate.now();
        String Statusi="";
        if (shuma<500.0){
            Statusi="Pjesërisht";
        } else if (shuma==500) {
            Statusi="Paguar";
        }
        else Statusi="Mbetur";
        return new CreatePagesatDto(this.userContext.getUserId(), nrXhirollog, shuma, data,"Online" , Statusi);
    }

    @FXML
    private void saveClick() {
        try {
            // Build the DTO from the input data
            CreatePagesatDto dto = this.getPagesaInputData();
            Pagesat pagesat = this.pagesaService.create(dto);

            clearFields();
            // Optionally, you can display a success message if needed
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Payment saved successfully!");
            successAlert.showAndWait();

        } catch (Exception e) {
            // Handle the exception and display an alert
            e.printStackTrace();
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Something went wrong");
            errorAlert.setContentText("An error occurred while saving the payment data. Please try again.");
            errorAlert.showAndWait();
        }
    }

    /**
     * Clears the input fields.
     */
    private void clearFields() {
        idKand.clear();
        bankNumTxt.clear();
        shumaTxt.clear();
    }

    @FXML
    private void cashClick() throws Exception {
        SceneManager.load(SceneLocator.PAYMENTS_CASH, this.rightPane);
    }
}


