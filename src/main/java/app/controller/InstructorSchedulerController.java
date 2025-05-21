package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Dto.orari.CreateOrariDto;
import models.Kandidatet;
import models.Orari;
import services.*;
import utils.SceneLocator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class InstructorSchedulerController extends BaseController {
    private final OrariService orariService;
    private final AutomjetService automjetService;
    private final KandidateService kandidateService;
    private Integer vehicleId;


    @FXML
    private AnchorPane rightPane;
    @FXML
    private MenuButton chooseLessonBttn;
    @FXML
    private TextField candidateId;
    @FXML
    private DatePicker dateForLesson;
    @FXML
    private TextField txtStart;
    @FXML
    private TextField txtEnd;

    private String llojiMesimit;
    private String selectedVehicleType;

    public InstructorSchedulerController() {
        this.orariService = new OrariService();
        this.automjetService = new AutomjetService();
        this.kandidateService = new KandidateService();
    }


    @FXML
    private void scheduleClick() {
        try {
            CreateOrariDto dto = this.getScheduleInputData();
            if (!kandidateService.ekzistonKandidatMeId(dto.getIdKandidat())) {
                showAlert(Alert.AlertType.ERROR, "Error", "The candidate with this id doesn't exist.");
            } else {
                Orari orari = this.orariService.create(dto);

                this.showAlert(Alert.AlertType.INFORMATION, "Sucess", "The schedule was successfully created! Session ID: " + orari.getIdSesioni());
                this.cleanFields();
            }

        } catch (IllegalArgumentException iae) {
            this.showAlert(Alert.AlertType.WARNING, "Warning", "Incomplete or invalid data" + iae.getMessage());

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "The schedule could not be created." + e.getMessage());
        }
    }


    private CreateOrariDto getScheduleInputData() {

        if (vehicleId == null) {
            throw new IllegalArgumentException("No vehicle selected or available.");
        }

        int kandId = Integer.parseInt(this.candidateId.getText().trim());
        int stafId = UserContext.getUserId();
        LocalDate date = this.dateForLesson.getValue();
        LocalTime start = LocalTime.parse(this.txtStart.getText().trim());
        LocalTime end = LocalTime.parse(this.txtEnd.getText().trim());

        return new CreateOrariDto(
                kandId,
                stafId,
                date,
                start,
                end,
                this.llojiMesimit,
                "Planifikuar",
                this.vehicleId
        );
    }

    private void cleanFields() {
        this.candidateId.clear();
        this.txtEnd.clear();
        this.txtStart.clear();
        this.dateForLesson.setValue(null);
        this.llojiMesimit = null;
        this.selectedVehicleType = null;
        this.vehicleId = null;
        this.chooseLessonBttn.setText("Choose lesson type");
    }


    @FXML
    private void teoriClick() {
        this.llojiMesimit = "Teori";
        chooseLessonBttn.setText("Teori");
    }

    @FXML
    private void praktikClick() {
        this.llojiMesimit = "Praktikë";
        chooseLessonBttn.setText("Praktikë");
    }


    @FXML
    private void AClick() {
        this.selectedVehicleType = "Motoçikletë";
        fetchVehicleId();
    }

    @FXML
    private void BClick() {
        this.selectedVehicleType = "Vetura";
        fetchVehicleId();
    }

    @FXML
    private void CClick() {
        this.selectedVehicleType = "Kamion";
        fetchVehicleId();
    }

    private void fetchVehicleId() {
        try {
            this.vehicleId = automjetService.getFirstAvailableVehicleIdByLloji(selectedVehicleType);
        } catch (Exception e) {
            e.printStackTrace();
            this.vehicleId = null;
        }
    }

    @FXML
    private void editClick() throws Exception {
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, rightPane);
    }

}