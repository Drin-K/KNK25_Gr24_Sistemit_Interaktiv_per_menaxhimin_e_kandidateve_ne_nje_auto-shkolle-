package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import models.*;
import models.Dto.stafi.CreateStafiDto;
import services.FeedBackService;
import services.OrariService;
import services.PasswordHasher;
import services.StafiService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class StafManagmentController {
    @FXML
    private TableView<Stafi> tableStafi;
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
    @FXML
    private TextField txtEmri;
    @FXML
    private TextField txtMbiemri;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private DatePicker datePickerDob;
    @FXML
    private TextField txtAdresa;
    @FXML
    private ComboBox<String> comboGjinia;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label high;
    @FXML
    private Label low;

    @FXML
    private HBox krijoFormenHBox;
    @FXML
    private TableView<FeedBack> feedbackTable;

    @FXML
    private TableColumn<FeedBack, Integer> colId;

    @FXML
    private TableColumn<FeedBack, Integer> colIdKandidat;

    @FXML
    private TableColumn<FeedBack, Integer> colIdStaf;

    @FXML
    private TableColumn<FeedBack, java.time.LocalDate> colDataFeedback;

    @FXML
    private TableColumn<FeedBack, Integer> colVleresimi;

    @FXML
    private TableColumn<FeedBack, String> colKoment;
    @FXML
    private TableView<Orari> orariTable;
    @FXML
    private TableColumn<Orari, Integer> idColumn1;
    @FXML
    private TableColumn<Orari, Integer> idKandidatColumn;
    @FXML
    private TableColumn<Orari, Integer> idStafColumn;
    @FXML
    private TableColumn<Orari, LocalDate> dataSesioniColumn;
    @FXML
    private TableColumn<Orari, LocalTime> oraFillimitColumn;
    @FXML
    private TableColumn<Orari, LocalTime> oraPerfundimitColumn;
    @FXML
    private TableColumn<Orari, String> llojiMesimitColumn;
    @FXML
    private TableColumn<Orari, String> statusiColumn;
    @FXML
    private TableColumn<Orari, Integer> idAutomjetColumn;


    private ObservableList<FeedBack> feedbackList = FXCollections.observableArrayList();
    private ObservableList<Stafi>stafList = FXCollections.observableArrayList();
    private StafiService stafiService=new StafiService();
    private OrariService orariService=new OrariService();
    private FeedBackService feedBackService=new FeedBackService();
    public void initialize() {
        krijoFormenHBox.setVisible(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        hashedPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("hashedPassword"));
        saltColumn.setCellValueFactory(new PropertyValueFactory<>("salt"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        gjiniaColumn.setCellValueFactory(new PropertyValueFactory<>("gjinia"));


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdKandidat.setCellValueFactory(new PropertyValueFactory<>("idKandidat"));
        colIdStaf.setCellValueFactory(new PropertyValueFactory<>("idStaf"));
        colDataFeedback.setCellValueFactory(new PropertyValueFactory<>("dataFeedback"));
        colVleresimi.setCellValueFactory(new PropertyValueFactory<>("vlersimi"));
        colKoment.setCellValueFactory(new PropertyValueFactory<>("koment"));
        setColumnOrari();
        loadOrariData();

        loadFeedbacksFromDatabase();
        loadStafData();
        String emriInstruktori = stafiService.getMostRatedInstructorName();
        high.setText(emriInstruktori);
        String emriInstruktori2 = stafiService.getLeastRatedInstructorName();
        low.setText(emriInstruktori2);
    }
    private void setColumnOrari(){
        idColumn1.setCellValueFactory(new PropertyValueFactory<>("idSesioni"));
        idKandidatColumn.setCellValueFactory(new PropertyValueFactory<>("idKandidat"));
        idStafColumn.setCellValueFactory(new PropertyValueFactory<>("idStaf"));
        dataSesioniColumn.setCellValueFactory(new PropertyValueFactory<>("dataSesionit"));
        oraFillimitColumn.setCellValueFactory(new PropertyValueFactory<>("oraFillimit"));
        oraPerfundimitColumn.setCellValueFactory(new PropertyValueFactory<>("oraPerfundimit"));
        llojiMesimitColumn.setCellValueFactory(new PropertyValueFactory<>("llojiMesimit"));
        statusiColumn.setCellValueFactory(new PropertyValueFactory<>("statusi"));
        idAutomjetColumn.setCellValueFactory(new PropertyValueFactory<>("idAutomjet"));


    }
    private void loadOrariData(){
        ObservableList<Orari>orariList=FXCollections.observableArrayList(orariService.getAllOrari());
        orariTable.setItems(orariList);
    }
    private void loadFeedbacksFromDatabase() {
        ObservableList<FeedBack>feedbackList = FXCollections.observableArrayList(feedBackService.getAll());
        feedbackTable.setItems(feedbackList);
    }
    @FXML
    private void onDeleteStafiClick() throws Exception {
        Stafi selectedStaf = tableStafi.getSelectionModel().getSelectedItem();

        if (selectedStaf != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmo Fshirjen");
            alert.setHeaderText(null);
            alert.setContentText("A dëshironi të fshini stafin: " +
                    selectedStaf.getName() + " " + selectedStaf.getSurname() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                tableStafi.getItems().remove(selectedStaf);
                this.stafiService.delete(selectedStaf.getIdUser());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kujdes");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem zgjidhni një staf për ta fshirë.");
            alert.showAndWait();
        }
    }
    @FXML
    private void onCreateStafiClick() {
        krijoFormenHBox.setVisible(true);
    }
    private void loadStafData(){
        ObservableList<Stafi>stafiList = FXCollections.observableArrayList(stafiService.getAll());
        tableStafi.setItems(stafiList);
    }
    @FXML
    private void onDeleteOrariClick() throws Exception {
        Orari selectedOrari=orariTable.getSelectionModel().getSelectedItem();

        if (selectedOrari != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmo Fshirjen");
            alert.setHeaderText(null);
            alert.setContentText("A dëshironi të fshini orarin per kandidatin me id: " +
                    selectedOrari.getIdKandidat() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                orariTable.getItems().remove(selectedOrari);
                this.orariService.delete(selectedOrari.getIdSesioni());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kujdes");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem zgjidhni një orar për ta fshirë.");
            alert.showAndWait();
        }
    }
    @FXML
    private void onDeleteFeedBackClick() throws Exception {
       FeedBack selectedFeedback=feedbackTable.getSelectionModel().getSelectedItem();

        if (selectedFeedback != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmo Fshirjen");
            alert.setHeaderText(null);
            alert.setContentText("A dëshironi të fshini feedback-un per instruktorin me id: " +
                    selectedFeedback.getIdKandidat() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                feedbackTable.getItems().remove(selectedFeedback);
                this.feedBackService.delete(selectedFeedback.getId());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kujdes");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem zgjidhni një feedback për ta fshirë.");
            alert.showAndWait();
        }
    }

    @FXML
    private void ruajStafin() throws Exception {
        String emri = txtEmri.getText();
        String mbiemri = txtMbiemri.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        LocalDate datelindja = datePickerDob.getValue();
        String password=txtPassword.getText();
        String salt= PasswordHasher.generateSalt();
        String adresa = txtAdresa.getText();
        String gjinia = comboGjinia.getValue();

        if (emri.isEmpty() || mbiemri.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                datelindja == null  || adresa.isEmpty() || gjinia == null
                ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText("Të dhëna të paplota");
            alert.setContentText("Ju lutem plotësoni të gjitha fushat përpara se të ruani.");
            alert.showAndWait();
            return;
        }else{
            CreateStafiDto stafi=new CreateStafiDto(emri,mbiemri,email,phone,datelindja,password,salt,"Staf",adresa,gjinia);
            this.stafiService.create(stafi);
            txtEmri.clear(); txtMbiemri.clear(); txtEmail.clear(); txtPhone.clear();
            txtAdresa.clear();
            datePickerDob.setValue(null);
            comboGjinia.setValue(null);
        }
    }
}
