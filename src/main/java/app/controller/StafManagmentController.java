package app.controller;

import app.controller.base.BaseController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import models.*;
import models.Dto.stafi.CreateStafiDto;
import repository.OrariRepository;
import repository.StafiRepository;
import services.*;
import utils.SceneLocator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
public class StafManagmentController extends BaseController {

    @FXML private TableView<Stafi> tableStafi;
    @FXML private TableColumn<Stafi, Integer> idColumn;
    @FXML private TableColumn<Stafi, String> nameColumn;
    @FXML private TableColumn<Stafi, String> surnameColumn;
    @FXML private TableColumn<Stafi, String> emailColumn;
    @FXML private TableColumn<Stafi, String> phoneColumn;
    @FXML private TableColumn<Stafi, LocalDate> dobColumn;
    @FXML private TableColumn<Stafi, String> hashedPasswordColumn;
    @FXML private TableColumn<Stafi, String> saltColumn;
    @FXML private TableColumn<Stafi, String> roleColumn;
    @FXML private TableColumn<Stafi, String> adresaColumn;
    @FXML private TableColumn<Stafi, String> gjiniaColumn;

    @FXML private TextField txtEmri, txtMbiemri, txtEmail, txtPhone, txtAdresa;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> comboGjinia;
    @FXML private DatePicker datePickerDob;
    @FXML private Label high, low;
    @FXML private HBox krijoFormenHBox;

    @FXML private TableView<FeedBack> feedbackTable;
    @FXML private TableColumn<FeedBack, Integer> colId, colIdKandidat, colIdStaf, colVleresimi;
    @FXML private TableColumn<FeedBack, LocalDate> colDataFeedback;
    @FXML private TableColumn<FeedBack, String> colKoment;

    @FXML private TableView<Orari> orariTable;
    @FXML private TableColumn<Orari, Integer> idColumn1, idKandidatColumn, idStafColumn, idAutomjetColumn;
    @FXML private TableColumn<Orari, LocalDate> dataSesioniColumn;
    @FXML private TableColumn<Orari, LocalTime> oraFillimitColumn, oraPerfundimitColumn;
    @FXML private TableColumn<Orari, String> llojiMesimitColumn, statusiColumn;
    @FXML
    private AnchorPane rightPage;

    private final StafiService stafiService;
    private final OrariService orariService;
    private final FeedBackService feedBackService;
    private final StafiRepository stafiRepository;
    private final OrariRepository orariRepository;
    public StafManagmentController(){
        this.stafiService=new StafiService();
        this.orariService=new OrariService();
        this.feedBackService=new FeedBackService();
        this.stafiRepository=new StafiRepository();
        this.orariRepository=new OrariRepository();
    }

    @FXML
    public void initialize() {
        krijoFormenHBox.setVisible(false);
        configureAllTables();
        loadAllData();
        showTopAndBottomInstructors();
    }

    private void configureAllTables() {
        configureStafiTable();
        configureFeedbackTable();
        configureOrariTable();
    }

    private void configureStafiTable() {
      configureTable(
                tableStafi,
                List.of(idColumn, nameColumn, surnameColumn, emailColumn, phoneColumn, dobColumn,
                        hashedPasswordColumn, saltColumn, roleColumn, adresaColumn, gjiniaColumn),
                new String[]{"idUser", "name", "surname", "email", "phoneNumber", "dateOfBirth",
                        "hashedPassword", "salt", "role", "adresa", "gjinia"});
    }

    private void configureFeedbackTable() {
       configureTable(
                feedbackTable,
                List.of(colId, colIdKandidat, colIdStaf, colDataFeedback, colVleresimi, colKoment),
                new String[]{"id", "idKandidat", "idStaf", "dataFeedback", "vlersimi", "koment"});
    }

    private void configureOrariTable() {
       configureTable(
                orariTable,
                List.of(idColumn1, idKandidatColumn, idStafColumn, dataSesioniColumn,
                        oraFillimitColumn, oraPerfundimitColumn, llojiMesimitColumn, statusiColumn, idAutomjetColumn),
                new String[]{"idSesioni", "idKandidat", "idStaf", "dataSesionit",
                        "oraFillimit", "oraPerfundimit", "llojiMesimit", "statusi", "idAutomjet"});
    }

    private void loadAllData() {
        loadStafData();
        loadFeedbacksFromDatabase();
        loadOrariData();
    }

    private void showTopAndBottomInstructors() {
        high.setText(stafiRepository.getMostRatedInstructorName());
        low.setText(stafiRepository.getLeastRatedInstructorName());
    }

    private void loadStafData() {
        tableStafi.setItems(FXCollections.observableArrayList(stafiService.getAll()));
    }

    private void loadFeedbacksFromDatabase() {
        feedbackTable.setItems(FXCollections.observableArrayList(feedBackService.getAll()));
    }

    private void loadOrariData() {
        orariTable.setItems(FXCollections.observableArrayList(orariRepository.getAll()));
    }
    private boolean checkAndConfirm(Object selected, String confirmMessage, String warningMessage) {
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", warningMessage);
            return false;
        }
        return showConfirmationDialog("Confirm Deletion", confirmMessage);
    }
    @FXML
    private void onDeleteStafiClick() throws Exception {
        Stafi selected = tableStafi.getSelectionModel().getSelectedItem();

        String confirmMsg = (selected != null)
                ? "Are you sure you want to delete the staff member: " + selected.getName() + " " + selected.getSurname() + "?"
                : "";

        if (checkAndConfirm(selected, confirmMsg, "Please select a staff member to delete.")) {
            tableStafi.getItems().remove(selected);
            stafiService.delete(selected.getIdUser());
        }
    }

    @FXML
    private void onDeleteOrariClick() throws Exception {
        Orari selected = orariTable.getSelectionModel().getSelectedItem();

        String confirmMsg = (selected != null)
                ? "Are you sure you want to delete the schedule for candidate with ID: " + selected.getIdKandidat() + "?"
                : "";

        if (checkAndConfirm(selected, confirmMsg, "Please select a schedule to delete.")) {
            orariTable.getItems().remove(selected);
            orariService.delete(selected.getIdSesioni());
        }
    }

    @FXML
    private void onDeleteFeedBackClick() throws Exception {
        FeedBack selected = feedbackTable.getSelectionModel().getSelectedItem();

        String confirmMsg = (selected != null)
                ? "Are you sure you want to delete the feedback for instructor with candidate ID: " + selected.getIdKandidat() + "?"
                : "";

        if (checkAndConfirm(selected, confirmMsg, "Please select a feedback to delete.")) {
            feedbackTable.getItems().remove(selected);
            feedBackService.delete(selected.getId());
        }
    }

    @FXML
    private void onCreateStafiClick() {
        krijoFormenHBox.setVisible(true);
    }

    @FXML
    private void ruajStafin() throws Exception {
        String emri = txtEmri.getText();
        String mbiemri = txtMbiemri.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        LocalDate datelindja = datePickerDob.getValue();
        String password = txtPassword.getText();
        String salt = PasswordHasher.generateSalt();
        String adresa = txtAdresa.getText();
        String gjinia = comboGjinia.getValue();

        if (emri.isEmpty() || mbiemri.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                datelindja == null || adresa.isEmpty() || gjinia == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields before saving.");
            return;
        }

        CreateStafiDto stafi = new CreateStafiDto(emri, mbiemri, email, phone, datelindja, password, salt, "Staf", adresa, gjinia);
        stafiService.create(stafi);
        clear();

    }
    public void clear(){
        txtEmri.clear();
        txtMbiemri.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAdresa.clear();
        txtPassword.clear();
        datePickerDob.setValue(null);
        comboGjinia.setValue(null);
    }
    public void onUpdateStafClick() throws Exception {
        SceneManager.load(SceneLocator.UPDATE_ADMIN_CANDIDATE_PAGE,this.rightPage);
    }
}
