package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Automjetet;
import models.Dto.automjetet.CreateAutomjetetDto;
import models.Dto.automjetet.UpdateAutomjetetDto;
import services.AutomjetService;
import services.SceneManager;
import services.UserContext;
import utils.SceneLocator;



public class InstructorManageVehicleController extends BaseController {
    private int idStaf = UserContext.getUserId();
    private final AutomjetService automjetService;
    private String llojiKategorise;
    private String llojiStatusit;
    private int kategoriaId;
    private int kategoriaserviceId;
    private String llojiKategoriseService;
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
    private MenuButton chooseCategory;
    @FXML
    private TextField vehicleId;

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
    private void chooseServiceMotorBike(){
        this.kategoriaserviceId=1;
        this.llojiKategoriseService = "Motoçikletë";
        chooseCategory.setText("MotorBike");
    }
    @FXML
    private void chooseServiceCar(){
        this.kategoriaserviceId = 2;
        this.llojiKategoriseService = "Vetura";
        chooseCategory.setText("Car");
    }
    @FXML
    private void chooseServiceTruck(){
        this.kategoriaserviceId = 3;
        this.llojiKategoriseService = "Kamion";
        chooseCategory.setText("Truck");
    }
    @FXML
    private void onClickUpdateServiceBtn() throws Exception {
        try {

            int automjetId = Integer.parseInt(this.vehicleId.getText());

            if (automjetId == 0) {
                showAlert(Alert.AlertType.ERROR,"Error","Please enter a vehicle ID.");
                return;
            }


            if (this.llojiKategoriseService == null || this.kategoriaserviceId == 0) {
                showAlert(Alert.AlertType.WARNING,"Error","Please select a vehicle category.");
                return;
            }


            String newStatus = "Mirëmbajtje";

            // 4. Call the service method
            Automjetet updated = automjetService.updateStatusByIdLlojiAndKategori(
                    automjetId, this.llojiKategoriseService, this.kategoriaserviceId, newStatus, this.idStaf
            );

            // 5. Notify the user
            if (updated != null) {
                showAlert(Alert.AlertType.INFORMATION,"Success","Vehicle status updated to 'Në servisim'.");
            } else {
                showAlert(Alert.AlertType.WARNING,"Error","Vehicle not found or update failed.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING,"Error","Invalid vehicle ID format.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.WARNING,"Error","An unexpected error occurred.");
        }
    }
    @FXML
    private void onClickAddVehicle() {
        try {
            CreateAutomjetetDto dto = new CreateAutomjetetDto(this.llojiKategorise, this.llojiStatusit, this.idStaf, this.kategoriaId);
            Automjetet automjetet = automjetService.create(dto);
            System.out.println("Automjeti u shtua me sukses: " + automjetet.getId());
            showAlert(Alert.AlertType.INFORMATION,"Notification","Vehicle inserted succecfuly !");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR,"Notification","Problem while inserting vehicle!");
        }
    }

}
