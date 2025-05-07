package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import models.Automjetet;
import models.Dto.automjetet.CreateAutomjetetDto;
import services.AutomjetService;
import services.SceneManager;
import services.UserContext;
import utils.SceneLocator;



public class InstructorManageVehicleController {
    private int idStaf = UserContext.getUserId();
    private final AutomjetService automjetService;
    private String llojiKategorise;
    private String llojiStatusit;
    private int kategoriaId;
    public InstructorManageVehicleController(){
        this.automjetService = new AutomjetService();
    }
    @FXML
    private AnchorPane rightPane;
    @FXML
    private MenuButton category;
    @FXML
    private MenuButton statusi;
    @FXML
    private void onClickPutForService() throws Exception {
        SceneManager.load(SceneLocator.INSTRUCTOR_PUT_SERVICE, rightPane);
    }
    @FXML
    private void chooseMotorBike(){
        this.kategoriaId = 1;
        this.llojiKategorise = "Motoçikletë";
        category.setText("MotorBike");
    }
    @FXML
    private void chooseCar(){
        this.kategoriaId = 2;
        category.setText("Car");
        this.llojiKategorise = "Vetura";
    }
    @FXML
    private void chooseTruck(){
        this.kategoriaId = 3;
        category.setText("Truck");
        this.llojiKategorise = "Kamion";
    }
    @FXML
    private void listOnUse(){
        statusi.setText("On Use");
        this.llojiStatusit = "Në përdorim";
    }
    @FXML
    private void listOnService(){
        statusi.setText("On Service");
        this.llojiStatusit = "Mirëmbajtje";
    }
    @FXML
    private void listOutOfUse(){
        statusi.setText("Out of use");
        this.llojiStatusit = "Jashtë shërbimit";
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    @FXML
    private void onClickAddVehicle() {
        try {
            CreateAutomjetetDto dto = new CreateAutomjetetDto(this.llojiKategorise, this.llojiStatusit, this.idStaf, this.kategoriaId);
            Automjetet automjetet = automjetService.create(dto);
            System.out.println("Automjeti u shtua me sukses: " + automjetet.getId());
            showAlert("Notification","Vehicle inserted succecfuly !");
        } catch (Exception e) {
            showAlert("Notification","Problem while inserting vehicle!");
            e.printStackTrace();
        }
    }

}
