package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Dto.orari.CreateOrariDto;
import models.Orari;
import services.AutomjetService;
import services.OrariService;
import services.UserContext;
import services.SceneManager;
import utils.SceneLocator;

import java.time.LocalDate;
import java.time.LocalTime;

public class InstructorSchedulerController {
    // Services & context
    private final OrariService orariService;
    private final AutomjetService automjetService;
    private Integer vehicleId;

    // Form fields
    @FXML private AnchorPane rightPane;
    @FXML private MenuButton chooseLessonBttn;
    @FXML private TextField candidateId;
    @FXML private DatePicker dateForLesson;
    @FXML private TextField txtStart;
    @FXML private TextField txtEnd;

    // Lesson type and vehicle type
    private String llojiMesimit;
    private String selectedVehicleType;  // Stores "Motoçikletë", "Vetura", or "Kamion"

    public InstructorSchedulerController() {
        this.orariService = new OrariService();
        this.automjetService = new AutomjetService();
    }

    @FXML
    private void initialize() {
        // No initialization of vehicleId here
    }

    @FXML
    private void scheduleClick() {
        try {
            CreateOrariDto dto = this.getScheduleInputData();
            Orari orari = this.orariService.create(dto);
            System.out.println("Schedule inserted successfully; session ID=" + orari.getIdSesioni());
            this.cleanFields();
        } catch (Exception e) {
            System.err.println("---Error Scheduling--- " + e.getMessage());
            e.printStackTrace();
        }
    }

    private CreateOrariDto getScheduleInputData() {
        // Validate vehicle selection
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

    // Lesson type handlers
    @FXML private void teoriClick()   {
        this.llojiMesimit = "Teori";
        chooseLessonBttn.setText("Teori");
    }

    @FXML private void praktikClick() {
        this.llojiMesimit = "Praktik";
        chooseLessonBttn.setText("Praktik");
    }

    // Vehicle type handlers
    @FXML
    private void AClick() {
        this.selectedVehicleType = "Motoçikletë";
        fetchVehicleId();  // Initialize vehicleId AFTER selecting the type
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