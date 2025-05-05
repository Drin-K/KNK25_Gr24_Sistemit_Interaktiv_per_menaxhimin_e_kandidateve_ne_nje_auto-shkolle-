package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
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
    private final UserContext context;
    private AutomjetService automjetService;
    private Integer vehicleId;

    // Form fields
    @FXML private AnchorPane rightPane;
    @FXML private MenuButton chooseLessonBttn;
    @FXML private TextField candidateId;
    @FXML private DatePicker dateForLesson;
    @FXML private TextField txtStart;
    @FXML private TextField txtEnd;
    @FXML private TextField txtSpecify;
    @FXML private RadioButton golf4Bttn;
    @FXML private RadioButton bmwBttn;
    @FXML private RadioButton mercedesBttn;
    @FXML private RadioButton golf7Bttn;

    // Used to store which lesson type was picked
    private String llojiMesimit;

    /** True no-arg constructor for FXMLLoader */
    public InstructorSchedulerController() {
        this.orariService = new OrariService();
        this.context      = new UserContext();
        this.automjetService = new AutomjetService();
    }

    /** Called by FXMLLoader after @FXML injections */
    @FXML
    private void initialize() {
        // Pre-fetch an available vehicle (or defer this logic to scheduleClick if preferred)
        try {
            this.vehicleId = automjetService.getFirstAvailableVehicleIdByLloji(txtSpecify.getText());
        } catch (Exception e) {
            e.printStackTrace();
            this.vehicleId = null;
        }

        // You can also set up any dynamic UI state here if needed
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
        int kandId   = Integer.parseInt(this.candidateId.getText().trim());
        int stafId   = UserContext.getUserId();
        LocalDate date = this.dateForLesson.getValue();
        LocalTime start = LocalTime.parse(this.txtStart.getText().trim());
        LocalTime end   = LocalTime.parse(this.txtEnd.getText().trim());

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
        this.txtSpecify.clear();
        this.txtEnd.clear();
        this.txtStart.clear();
        this.dateForLesson.setValue(null);
        // reset lesson type if desired:
        this.llojiMesimit = null;
        this.chooseLessonBttn.setText("Choose lesson type");
    }

    // Lesson‐type menu handlers
    @FXML private void teoriClick()   { this.llojiMesimit = "Teori";   chooseLessonBttn.setText("Teori"); }
    @FXML private void praktikClick() { this.llojiMesimit = "Praktik"; chooseLessonBttn.setText("Praktik"); }

    // Vehicle radio‐buttons
    @FXML private void AClick()    { this.txtSpecify.setText("Motoçikletë"); }
    @FXML private void BClick()      { this.txtSpecify.setText("Vetura");    }
    @FXML private void CClick() { this.txtSpecify.setText("Kamion"); }


    @FXML
    private void editClick() throws Exception {
        SceneManager.load(SceneLocator.INSTRUCTOR_EDIT, rightPane);
    }
}
