package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import services.SceneManager;
import utils.SceneLocator;



public class InstructorManageVehicleController {
    private String llojiKategorise;
    private String llojiStatusit;
    private int kategoriaId;
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
    private void onClickAddVehicle(){
        System.out.println(llojiKategorise); //test
        System.out.println(llojiStatusit); //test
        System.out.println(kategoriaId); //test
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

}
