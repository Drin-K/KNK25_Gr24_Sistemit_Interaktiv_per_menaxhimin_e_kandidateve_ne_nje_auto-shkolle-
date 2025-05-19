package app.controller;

import app.controller.base.BaseController;
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

public class PagesatHandleController extends BaseController {

    private final PagesaService pagesaService;


    public PagesatHandleController() {
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
        String idText = this.idKand.getText().trim();
        String shumaText = this.shumaTxt.getText().trim();
        String nrXhirollog = this.bankNumTxt.getText().trim();

        if (idText.isEmpty() || shumaText.isEmpty() || nrXhirollog.isEmpty()) {
            this.showAlert(AlertType.ERROR, "Empty Fields", "Please fill in all the fields!");
            return null;
        }
        int kandidatId = Integer.parseInt(idText);
        double shuma = Double.parseDouble(nrXhirollog);
        LocalDate data = LocalDate.now();
        String Statusi="";
        try {
            if (shuma < 500.0) {Statusi = "Pjesërisht";}
            else if (shuma == 500) {Statusi = "Paguar";}
            else if(shuma==0){ Statusi = "Mbetur";}
            else {
//                Alert errorAlert = new Alert(AlertType.ERROR);
//                errorAlert.setTitle("Error");
//                errorAlert.setHeaderText("Something went wrong");
//                errorAlert.setContentText("Enter a money value bigger than 0 and smaller than 500");
//                errorAlert.showAndWait();
                this.showAlert(AlertType.ERROR, "Error", "Enter a money value bigger than 0 and smaller than 500");
            }
        }
        catch (Exception e){
            System.out.println("Ka ndodhur nje problem gjate pageses");
        }
        return new CreatePagesatDto(UserContext.getUserId(), nrXhirollog, shuma, data,"Online" , Statusi);
    }

    @FXML
    private void saveClick() {
        try {
            // Build the DTO from the input data
            CreatePagesatDto dto = this.getPagesaInputData();
            Pagesat pagesat = this.pagesaService.create(dto);
            clearFields();
            showAlert(AlertType.INFORMATION, "Success", "Payment saved successfully!");
        } catch (Exception e) {

            e.printStackTrace();

            //showAlert(AlertType.ERROR,"Error","An error occurred while saving the payment data. Please try again.");
        }
    }

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


