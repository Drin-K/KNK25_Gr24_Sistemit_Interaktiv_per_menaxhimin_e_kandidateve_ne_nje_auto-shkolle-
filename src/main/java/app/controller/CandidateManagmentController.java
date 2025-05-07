package app.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import models.*;
import models.Dto.kandidatet.CreateKandidatetDto;
import repository.KandidatetRepository;
import services.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CandidateManagmentController{
    @FXML
    private TableView<Kandidatet> kandidatTable;
    @FXML
    private HBox krijoFormenHBox;

    @FXML private TableColumn<Kandidatet, Integer> idColumn;
    @FXML private TableColumn<Kandidatet, String> nameColumn;
    @FXML private TableColumn<Kandidatet, String> surnameColumn;
    @FXML private TableColumn<Kandidatet, String> emailColumn;
    @FXML private TableColumn<Kandidatet, String> phoneColumn;
    @FXML private TableColumn<Kandidatet, String> dobColumn;
    @FXML private TableColumn<Kandidatet, String> roleColumn;
    @FXML private TableColumn<Kandidatet, String> adresaColumn;
    @FXML private TableColumn<Kandidatet, String> gjiniaColumn;
    @FXML private TableColumn<Kandidatet, String> dataRegjistrimiColumn;
    @FXML private TableColumn<Kandidatet, String> statusiProcesitColumn;
    @FXML
    private TableView<Dokumentet> tableViewDokumente;
    @FXML
    private TableColumn<Dokumentet, String> colEmriSkedari;
    @FXML
    private TableColumn<Dokumentet, String> colLlojiDokumentit;
    @FXML
    private TableColumn<Dokumentet, String> colDataNgarkimit;
    @FXML
    private TableView<Pagesat> tabelaPagesat;
    @FXML
    private TableColumn<Pagesat, Integer> colId;
    @FXML
    private TableColumn<Pagesat, Integer> colIdKandidat;
    @FXML
    private TableColumn<Pagesat, Double> colShuma;
    @FXML
    private TableColumn<Pagesat, java.time.LocalDate> colDataPageses;
    @FXML
    private TableColumn<Pagesat, String> colMetodaPageses;
    @FXML
    private TableColumn<Pagesat, String> colStatusiPageses;
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
    @FXML private TableView<Testet> testetTable;

    @FXML private TableColumn<Testet, Integer> idColumn2;
    @FXML private TableColumn<Testet, Integer> idKandidatColumn2;
    @FXML private TableColumn<Testet, Integer> idStafColumn2;
    @FXML private TableColumn<Testet, String> llojiTestitColumn;
    @FXML private TableColumn<Testet, LocalDate> dataTestitColumn;
    @FXML private TableColumn<Testet, String> rezultatiColumn;
    @FXML private TableColumn<Testet, Integer> piketColumn;
    @FXML
    private Button btnCreateKandidat;
    @FXML
    private Button btnDeletePagesat;
    @FXML
    private Button btnDeleteKandidat;
    @FXML
    private Button btnDeleteOrari;
    @FXML
    private Button btnDeleteDokumenttet;
    @FXML
    private Button btnDeleteTestet;
    @FXML
    private Button btnMiratoDokumentet;
    @FXML
    private Button btnDownloadDokumentet;
    @FXML
    private TextField txtEmri, txtMbiemri, txtEmail, txtPhone, txtAdresa, txtStatusiProcesit;
    @FXML
    private DatePicker datePickerDob;
    @FXML
    private ComboBox<String> comboGjinia;
    @FXML
    private PasswordField txtPassword;


    private ObservableList<Pagesat> pagesatList = FXCollections.observableArrayList();
    private KandidateService kandidateService=new KandidateService();
    private DokumentService dokumentService=new DokumentService();
    private PagesaService pagesaService=new PagesaService();
    private OrariService orariService=new OrariService();
    private TestiService testiService=new TestiService();
    private RegjistrimiService regjistrimiService=new RegjistrimiService();
    private void setDokumentetColumnFactories() {
        colEmriSkedari.setCellValueFactory(new PropertyValueFactory<>("emriSkedarit"));
        colLlojiDokumentit.setCellValueFactory(new PropertyValueFactory<>("llojiDokumentit"));
        colDataNgarkimit.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataNgarkimit().toString()));
    }
    private void loadDokumentetData(){
        ObservableList<Dokumentet>dokumentetsList = FXCollections.observableArrayList(dokumentService.getAllDokumentet());
        tableViewDokumente.setItems(dokumentetsList);
    }
    private void setColumnPayments(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdKandidat.setCellValueFactory(new PropertyValueFactory<>("idKandidat"));
        colShuma.setCellValueFactory(new PropertyValueFactory<>("shuma"));
        colDataPageses.setCellValueFactory(new PropertyValueFactory<>("dataPageses"));
        colMetodaPageses.setCellValueFactory(new PropertyValueFactory<>("metodaPageses"));
        colStatusiPageses.setCellValueFactory(new PropertyValueFactory<>("statusiPageses"));
    }
    private void setColumnFactories() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        gjiniaColumn.setCellValueFactory(new PropertyValueFactory<>("gjinia"));
        statusiProcesitColumn.setCellValueFactory(new PropertyValueFactory<>("statusiProcesit"));

        dobColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDateOfBirth().toString()));

        dataRegjistrimiColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataRegjistrimit().toString()));
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
    private void setColumnTestet() {
        idColumn2.setCellValueFactory(new PropertyValueFactory<>("id"));
        idKandidatColumn2.setCellValueFactory(new PropertyValueFactory<>("idKandidat"));
        idStafColumn2.setCellValueFactory(new PropertyValueFactory<>("idStaf"));
        llojiTestitColumn.setCellValueFactory(new PropertyValueFactory<>("llojiTestit"));
        dataTestitColumn.setCellValueFactory(new PropertyValueFactory<>("dataTestit"));
        rezultatiColumn.setCellValueFactory(new PropertyValueFactory<>("rezultati"));
        piketColumn.setCellValueFactory(new PropertyValueFactory<>("piket"));
    }

    private void loadKandidatetData(){
        ObservableList<Kandidatet> kandidatetList = FXCollections.observableArrayList(kandidateService.getAllKandidatet());
        kandidatTable.setItems(kandidatetList);
    }
    private void loadPagesatData(){
        ObservableList<Pagesat>pagesatList=FXCollections.observableArrayList(pagesaService.getAllPagesat());
        tabelaPagesat.setItems(pagesatList);
    }
    private void loadOrariData(){
        ObservableList<Orari>orariList=FXCollections.observableArrayList(orariService.getAllOrari());
        orariTable.setItems(orariList);
    }
    private void loadTestetData(){
        ObservableList<Testet>testetList=FXCollections.observableArrayList(testiService.getAllTestet());
        testetTable.setItems(testetList);
    }
    @FXML
    private void onCreateKandidatClick() {
        krijoFormenHBox.setVisible(true);
    }
    @FXML
    private void ruajKandidatin() throws Exception {
        // Marrim vlerat nga inputet
        String emri = txtEmri.getText();
        String mbiemri = txtMbiemri.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        LocalDate datelindja = datePickerDob.getValue();
        String password=txtPassword.getText();
        String salt=PasswordHasher.generateSalt();
        String adresa = txtAdresa.getText();
        String gjinia = comboGjinia.getValue();
        String statusiProcesit = txtStatusiProcesit.getText();

        // Validimi: kontrollon nëse ndonjë është i zbrazët
        if (emri.isEmpty() || mbiemri.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                datelindja == null  || adresa.isEmpty() || gjinia == null ||
                statusiProcesit.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText("Të dhëna të paplota");
            alert.setContentText("Ju lutem plotësoni të gjitha fushat përpara se të ruani.");
            alert.showAndWait();
            return;
        }else{
           CreateKandidatetDto kandidatet=new CreateKandidatetDto(emri,mbiemri,email,phone,datelindja,password,salt,"Kandidat",adresa,gjinia,null,statusiProcesit);
            this.kandidateService.create(kandidatet);
            txtEmri.clear(); txtMbiemri.clear(); txtEmail.clear(); txtPhone.clear();
             txtAdresa.clear(); txtStatusiProcesit.clear();
            datePickerDob.setValue(null);
            comboGjinia.setValue(null);
        }
    }
    @FXML
    private void onDeleteKandidatClick() throws Exception {
        Kandidatet selectedKandidat = kandidatTable.getSelectionModel().getSelectedItem();

        if (selectedKandidat != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmo Fshirjen");
            alert.setHeaderText(null);
            alert.setContentText("A dëshironi të fshini kandidatin: " +
                    selectedKandidat.getName() + " " + selectedKandidat.getSurname() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Fshi nga tabela në GUI
                kandidatTable.getItems().remove(selectedKandidat);

                // Fshi nga databaza vetëm duke kaluar ID-në
                this.kandidateService.delete(selectedKandidat.getIdUser());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kujdes");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem zgjidhni një kandidat për ta fshirë.");
            alert.showAndWait();
        }
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
                // Fshi nga tabela në GUI
                orariTable.getItems().remove(selectedOrari);

                // Fshi nga databaza vetëm duke kaluar ID-në
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
    private void onDeletePagesatClick() throws Exception {
        Pagesat selectedPagesat=tabelaPagesat.getSelectionModel().getSelectedItem();

        if (selectedPagesat != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmo Fshirjen");
            alert.setHeaderText(null);
            alert.setContentText("A dëshironi të fshini orarin per kandidatin me id: " +
                    selectedPagesat.getIdKandidat() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                tabelaPagesat.getItems().remove(selectedPagesat);

                this.pagesaService.delete(selectedPagesat.getId());
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
    private void onDeleteDokumentetClick() throws Exception {
        Dokumentet selectedDokumentet=tableViewDokumente.getSelectionModel().getSelectedItem();

        if (selectedDokumentet != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmo Fshirjen");
            alert.setHeaderText(null);
            alert.setContentText("A dëshironi të fshini dokumentin per kandidatin me id: " +
                    selectedDokumentet.getIdKandidat() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                tableViewDokumente.getItems().remove(selectedDokumentet);

                this.dokumentService.delete(selectedDokumentet.getId());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kujdes");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem zgjidhni një dokument për ta fshirë.");
            alert.showAndWait();
        }
    }
    @FXML
    private void onDeleteTestetClick() throws Exception {
        Testet selectedTest=testetTable.getSelectionModel().getSelectedItem();

        if (selectedTest != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmo Fshirjen");
            alert.setHeaderText(null);
            alert.setContentText("A dëshironi të fshini testin per kandidatin me id: " +
                    selectedTest.getIdKandidat() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                testetTable.getItems().remove(selectedTest);

                this.testiService.delete(selectedTest.getId());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kujdes");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem zgjidhni një test për ta fshirë.");
            alert.showAndWait();
        }
    }
    @FXML
    private void onMiratoClick() {
        List<Dokumentet> selectedRegjistrimi = tableViewDokumente.getSelectionModel().getSelectedItems();

        if (!selectedRegjistrimi.isEmpty()) {
            int kandidatiId = selectedRegjistrimi.get(0).getIdKandidat();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmo Regjistrimin e Kandidatit");
            alert.setHeaderText(null);
            alert.setContentText("A dëshironi të regjistrosh kandidatin me ID: " + kandidatiId + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                boolean uMiratua = regjistrimiService.mirato(kandidatiId);

                if (uMiratua) {
                    tableViewDokumente.getItems().removeAll(selectedRegjistrimi);

                    Alert suksesAlert = new Alert(Alert.AlertType.INFORMATION);
                    suksesAlert.setTitle("Sukses");
                    suksesAlert.setHeaderText(null);
                    suksesAlert.setContentText("Kandidati u regjistrua me sukses.");
                    suksesAlert.showAndWait();

                } else {
                    tableViewDokumente.getItems().removeAll(selectedRegjistrimi);
                    Alert mungesAlert = new Alert(Alert.AlertType.WARNING);
                    mungesAlert.setTitle("Dokumentet mungojnë");
                    mungesAlert.setHeaderText(null);
                    mungesAlert.setContentText("Kandidati nuk i ka të gjitha dokumentet e nevojshme:\n" +
                            "- Leternjoftim\n- Certifikatë Mjekësore\n- Aplikim\n- Foto");
                    mungesAlert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kujdes");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem zgjidhni një kandidat për ta miratuar.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onDownloadClick(){
        Dokumentet selectedDokument=tableViewDokumente.getSelectionModel().getSelectedItem();

        if (selectedDokument != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmo shkarkimin");
            alert.setHeaderText(null);
            alert.setContentText("A dëshironi të shkarkoni dokumentin: "+selectedDokument.getEmriSkedarit()
                    +" per kandidatin me id: " +
                    selectedDokument.getIdKandidat() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                download(selectedDokument.getEmriSkedarit());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kujdes");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem zgjidhni një test për ta fshirë.");
            alert.showAndWait();
        }
    }
    @FXML
    public void initialize(){
        krijoFormenHBox.setVisible(false);
        loadKandidatetData();
        loadDokumentetData();
        loadPagesatData();
        loadOrariData();
        loadTestetData();
        setColumnFactories();
        setDokumentetColumnFactories();
        setColumnPayments();
        setColumnOrari();
        setColumnTestet();
//        btnCreateKandidat.setOnAction(event -> handleCreateKandidat());
//        btnReplaceKandidat.setOnAction(event -> handleReplaceKandidat());
//        btnDeleteKandidat.setOnAction(event -> handleDeleteKandidat());

    }
        public void download(String emriSkedarit) {
           final Path UPLOAD_DIR = Paths.get("src", "main", "java", "utils", "uploads");
            Path sourcePath = UPLOAD_DIR.resolve(emriSkedarit);
            Path desktop = Paths.get(System.getProperty("user.home"), "Desktop");
            Path destinationPath = desktop.resolve(emriSkedarit);

            try {
                if (Files.notExists(UPLOAD_DIR)) {
                    Files.createDirectories(UPLOAD_DIR);
                }
                if (Files.exists(sourcePath)) {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Sukses");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Dokumenti u shkarkua me sukses në: " + destinationPath.toString());
                    successAlert.showAndWait();
                } else {
                    throw new IOException("Dokumenti nuk ekziston.");
                }

            } catch (IOException e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Gabim");
                errorAlert.setHeaderText("Shkarkimi dështoi");
                errorAlert.setContentText("Nuk mund të shkarkohet dokumenti: " + e.getMessage());
                errorAlert.showAndWait();
            }
        }
    }




