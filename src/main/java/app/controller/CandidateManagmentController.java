package app.controller;

import app.controller.base.BaseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import models.*;
import models.Dto.kandidatet.CreateKandidatetDto;
import repository.DokumentetRepository;
import repository.OrariRepository;
import repository.PagesatRepository;
import repository.TestetRepository;
import services.*;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static app.controller.components.CommonTables.configureTable;

public class CandidateManagmentController extends BaseController {
    @FXML private TableView<Kandidatet> kandidatTable;
    @FXML private HBox krijoFormenHBox;
    @FXML private TableColumn<Kandidatet, Integer> idColumn;
    @FXML private TableColumn<Kandidatet, String> nameColumn, surnameColumn, emailColumn, phoneColumn;
    @FXML private TableColumn<Kandidatet, String> dobColumn, roleColumn, adresaColumn, gjiniaColumn;
    @FXML private TableColumn<Kandidatet, String> dataRegjistrimiColumn, statusiProcesitColumn;
    @FXML private TableView<Dokumentet> tableViewDokumente;
    @FXML private TableColumn<Dokumentet, String> colEmriSkedari, colLlojiDokumentit, colDataNgarkimit;
    @FXML private TableView<Pagesat> tabelaPagesat;
    @FXML private TableColumn<Pagesat, Integer> colId, colIdKandidat;
    @FXML private TableColumn<Pagesat, Double> colShuma;
    @FXML private TableColumn<Pagesat, LocalDate> colDataPageses;
    @FXML private TableColumn<Pagesat, String> colMetodaPageses, colStatusiPageses;
    @FXML private TableView<Orari> orariTable;
    @FXML private TableColumn<Orari, Integer> idColumn1, idKandidatColumn, idStafColumn, idAutomjetColumn;
    @FXML private TableColumn<Orari, LocalDate> dataSesioniColumn;
    @FXML private TableColumn<Orari, LocalTime> oraFillimitColumn, oraPerfundimitColumn;
    @FXML private TableColumn<Orari, String> llojiMesimitColumn, statusiColumn;
    @FXML private TableView<Testet> testetTable;
    @FXML private TableColumn<Testet, Integer> idColumn2, idKandidatColumn2, idStafColumn2, piketColumn;
    @FXML private TableColumn<Testet, String> llojiTestitColumn, rezultatiColumn;
    @FXML private TableColumn<Testet, LocalDate> dataTestitColumn;
    @FXML private TextField txtEmri, txtMbiemri, txtEmail, txtPhone, txtAdresa, txtStatusiProcesit;
    @FXML private DatePicker datePickerDob;
    @FXML private ComboBox<String> comboGjinia;
    @FXML private PasswordField txtPassword;
    private final KandidateService kandidateService;
    private final DokumentService dokumentService;
    private final PagesaService pagesaService;
    private final OrariService orariService;
    private final TestiService testiService;
    private final RegjistrimiService regjistrimiService;
    private final TestetRepository testiRepository;
    private final DokumentetRepository dokumentetRepository;
    private final PagesatRepository pagesatRepository;
    private final OrariRepository orariRepository;

    public CandidateManagmentController() {
        this.kandidateService = new KandidateService();
        this.dokumentService = new DokumentService();
        this.pagesaService = new PagesaService();
        this.orariService = new OrariService();
        this.testiService = new TestiService();
        this.regjistrimiService = new RegjistrimiService();
        this.testiRepository=new TestetRepository();
        this.dokumentetRepository=new DokumentetRepository();
        this.pagesatRepository=new PagesatRepository();
        this.orariRepository=new OrariRepository();
    }
    @FXML
    public void initialize() {
        krijoFormenHBox.setVisible(false);

        setupKandidatTable();
        setupDokumentetTable();
        setupPagesatTable();
        setupOrariTable();
        setupTestetTable();

        loadKandidatetData();
        loadDokumentetData();
        loadPagesatData();
        loadOrariData();
        loadTestetData();
    }
    private void setupKandidatTable() {
        configureTable(kandidatTable, List.of(
                        idColumn, nameColumn, surnameColumn, emailColumn, phoneColumn,
                        roleColumn, adresaColumn, gjiniaColumn, statusiProcesitColumn),
                new String[]{"idUser", "name", "surname", "email", "phoneNumber", "role", "adresa", "gjinia", "statusiProcesit"});

        dobColumn.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getDateOfBirth().toString()));
        dataRegjistrimiColumn.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getDataRegjistrimit().toString()));
    }

    private void setupDokumentetTable() {
        configureTable(tableViewDokumente,
                List.of(colEmriSkedari, colLlojiDokumentit),
                new String[]{"emriSkedarit", "llojiDokumentit"});

        colDataNgarkimit.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getDataNgarkimit().toString()));
    }

    private void setupPagesatTable() {
        configureTable(tabelaPagesat,
                List.of(colId, colIdKandidat, colShuma, colDataPageses, colMetodaPageses, colStatusiPageses),
                new String[]{"id", "idKandidat", "shuma", "dataPageses", "metodaPageses", "statusiPageses"});
    }

    private void setupOrariTable() {
        configureTable(orariTable,
                List.of(idColumn1, idKandidatColumn, idStafColumn, dataSesioniColumn,
                        oraFillimitColumn, oraPerfundimitColumn, llojiMesimitColumn, statusiColumn, idAutomjetColumn),
                new String[]{"idSesioni", "idKandidat", "idStaf", "dataSesionit", "oraFillimit", "oraPerfundimit",
                        "llojiMesimit", "statusi", "idAutomjet"});
    }

    private void setupTestetTable() {
        configureTable(testetTable,
                List.of(idColumn2, idKandidatColumn2, idStafColumn2, llojiTestitColumn,
                        dataTestitColumn, rezultatiColumn, piketColumn),
                new String[]{"id", "idKandidat", "idStaf", "llojiTestit", "dataTestit", "rezultati", "piket"});
    }


    private void loadKandidatetData() {
        kandidatTable.setItems(FXCollections.observableArrayList(kandidateService.getAll()));
    }
    private void loadDokumentetData() {
        tableViewDokumente.setItems(FXCollections.observableArrayList(dokumentetRepository.getAll()));
    }
    private void loadPagesatData() {
        tabelaPagesat.setItems(FXCollections.observableArrayList(pagesatRepository.getAll()));
    }
    private void loadOrariData() {
        orariTable.setItems(FXCollections.observableArrayList(orariRepository.getAll()));
    }
    private void loadTestetData() {
        testetTable.setItems(FXCollections.observableArrayList(testiRepository.getAll()));
    }

    @FXML
    private void onCreateKandidatClick() {
        krijoFormenHBox.setVisible(true);
    }

    @FXML
    private void saveClick() throws Exception {
        String emri = txtEmri.getText(), mbiemri = txtMbiemri.getText(), email = txtEmail.getText(),
                phone = txtPhone.getText(), adresa = txtAdresa.getText(),
                gjinia = comboGjinia.getValue(), password = txtPassword.getText(),
                statusiProcesit = txtStatusiProcesit.getText();
        LocalDate datelindja = datePickerDob.getValue();

        if (emri.isEmpty() || mbiemri.isEmpty() || email.isEmpty() || phone.isEmpty()
                || datelindja == null || adresa.isEmpty() || gjinia == null || statusiProcesit.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Incomplete data. Please fill in all fields.");
            return;
        }

        CreateKandidatetDto kandidatet = new CreateKandidatetDto(
                emri, mbiemri, email, phone, datelindja, password, PasswordHasher.generateSalt(),
                "Kandidat", adresa, gjinia, null, statusiProcesit);
        kandidateService.create(kandidatet);
       clearData();
    }
private void clearData(){
    txtEmri.clear();
    txtMbiemri.clear();
    txtEmail.clear();
    txtPhone.clear();
    txtAdresa.clear();
    txtStatusiProcesit.clear();
    datePickerDob.setValue(null);
    comboGjinia.setValue(null);
}
    private boolean kontrolloDheKonfirmo(Object selected, String konfirmoMesazhi) {
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning","Please select a candidate.");
            return false;
        }
        return showConfirmationDialog("Confirm Deletion", konfirmoMesazhi);
    }
    @FXML
    private void onDeleteKandidatClick() throws Exception {
        Kandidatet selected = kandidatTable.getSelectionModel().getSelectedItem();

        String confirmMsg = (selected != null)
                ? "Do you want to delete the candidate: " + selected.getName() + " " + selected.getSurname() + "?"
                : "";

        if (kontrolloDheKonfirmo(selected, confirmMsg)) {
            kandidatTable.getItems().remove(selected);
            kandidateService.delete(selected.getIdUser());
        }
    }

    @FXML
    private void onDeleteOrariClick() throws Exception {
        Orari selected = orariTable.getSelectionModel().getSelectedItem();

        String confirmMsg = (selected != null)
                ? "Do you want to delete the schedule for the candidate with ID: " + selected.getIdKandidat() + "?"
                : "";

        if (kontrolloDheKonfirmo(selected, confirmMsg)) {
            orariTable.getItems().remove(selected);
            orariService.delete(selected.getIdSesioni());
        }
    }

    @FXML
    private void onDeletePagesatClick() throws Exception {
        Pagesat selected = tabelaPagesat.getSelectionModel().getSelectedItem();

        String confirmMsg = (selected != null)
                ? "Do you want to delete the payment for the candidate with ID: " + selected.getIdKandidat() + "?"
                : "";

        if (kontrolloDheKonfirmo(selected, confirmMsg)) {
            tabelaPagesat.getItems().remove(selected);
            pagesaService.delete(selected.getId());
        }
    }

    @FXML
    private void onDeleteDokumentetClick() throws Exception {
        Dokumentet selected = tableViewDokumente.getSelectionModel().getSelectedItem();

        String confirmMsg = (selected != null)
                ? "Do you want to delete the document for the candidate with ID: " + selected.getIdKandidat() + "?"
                : "";

        if (kontrolloDheKonfirmo(selected, confirmMsg)) {
            tableViewDokumente.getItems().remove(selected);
            dokumentService.delete(selected.getId());
        }
    }

    @FXML
    private void onDeleteTestetClick() throws Exception {
        Testet selected = testetTable.getSelectionModel().getSelectedItem();

        String confirmMsg = (selected != null)
                ? "Do you want to delete the test for the candidate with ID: " + selected.getIdKandidat() + "?"
                : "";

        if (kontrolloDheKonfirmo(selected, confirmMsg)) {
            testetTable.getItems().remove(selected);
            testiService.delete(selected.getId());
        }
    }


    @FXML private void onMiratoClick() {
        List<Dokumentet> selected = tableViewDokumente.getSelectionModel().getSelectedItems();
        if (selected.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a candidate.");
            return;
        }

        int kandidatiId = selected.getFirst().getIdKandidat();
        if (showConfirmationDialog("Confirm Registration",
                "Do you want to register the candidate with ID: " + kandidatiId + "?")) {
            boolean uMiratua = regjistrimiService.mirato(kandidatiId);

            if (uMiratua) {
                tableViewDokumente.getItems().removeAll(selected);
                showAlert(Alert.AlertType.INFORMATION, "Success", "The candidate was successfully registered.");
            } else {
                tableViewDokumente.getItems().removeAll(selected);
                showAlert(Alert.AlertType.WARNING, "Documents Missing",
                        "The candidate is missing the following documents:\\n- ID Card\\n- Medical Certificate\\n- Application\\n- Photo");
            }
        }
    }

    @FXML private void onDownloadClick() {
        Dokumentet selected = tableViewDokumente.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a document.");
            return;
        }

        if (showConfirmationDialog("Confirm Download",
                "Do you want to download the document: " + selected.getEmriSkedarit() + "?")) {
            download(selected.getEmriSkedarit());
        }
    }
    public void download(String emriSkedarit) {
        Path uploadDir = Paths.get("src", "main", "java", "utils", "uploads");
        Path sourcePath = uploadDir.resolve(emriSkedarit);
        Path destinationPath = Paths.get(System.getProperty("user.home"), "Desktop").resolve(emriSkedarit);

        try {
            if (Files.notExists(uploadDir)) Files.createDirectories(uploadDir);
            if (Files.exists(sourcePath)) {
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                showAlert(Alert.AlertType.INFORMATION, "Success", "The document was successfully downloaded to: " + destinationPath);
            } else {
                throw new IOException("The document does not exist.");
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "The document could not be downloaded: " + e.getMessage());
        }
    }
}

