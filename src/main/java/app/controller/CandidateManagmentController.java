package app.controller;

import app.controller.base.BaseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import models.*;
import models.Dto.kandidatet.CreateKandidatetDto;
import services.*;
import utils.SceneLocator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class CandidateManagmentController extends BaseController {
    @FXML
    private TableView<Kandidatet> kandidatTable;
    @FXML
    private HBox krijoFormenHBox;
    @FXML
    private TableColumn<Kandidatet, Integer> idColumn;
    @FXML
    private TableColumn<Kandidatet, String> nameColumn, surnameColumn, emailColumn, phoneColumn;
    @FXML
    private TableColumn<Kandidatet, String> dobColumn, roleColumn, adresaColumn, gjiniaColumn;
    @FXML
    private TableColumn<Kandidatet, String> dataRegjistrimiColumn, statusiProcesitColumn;
    @FXML
    private TableView<Dokumentet> tableViewDokumente;
    @FXML
    private TableColumn<Dokumentet, String> colEmriSkedari, colLlojiDokumentit, colDataNgarkimit;
    @FXML
    private TableView<Pagesat> tabelaPagesat;
    @FXML
    private TableColumn<Pagesat, Integer> colId, colIdKandidat;
    @FXML
    private TableColumn<Pagesat, Double> colShuma;
    @FXML
    private TableColumn<Pagesat, LocalDate> colDataPageses;
    @FXML
    private TableColumn<Pagesat, String> colMetodaPageses, colStatusiPageses;
    @FXML
    private TableView<Orari> orariTable;
    @FXML
    private TableColumn<Orari, Integer> idColumn1, idKandidatColumn, idStafColumn, idAutomjetColumn;
    @FXML
    private TableColumn<Orari, LocalDate> dataSesioniColumn;
    @FXML
    private TableColumn<Orari, LocalTime> oraFillimitColumn, oraPerfundimitColumn;
    @FXML
    private TableColumn<Orari, String> llojiMesimitColumn, statusiColumn;
    @FXML
    private TableView<Testet> testetTable;
    @FXML
    private TableColumn<Testet, Integer> idColumn2, idKandidatColumn2, idStafColumn2, piketColumn;
    @FXML
    private TableColumn<Testet, String> llojiTestitColumn, rezultatiColumn;
    @FXML
    private TableColumn<Testet, LocalDate> dataTestitColumn;
    @FXML
    private TextField txtEmri, txtMbiemri, txtEmail, txtPhone, txtAdresa;
    @FXML
    private DatePicker datePickerDob;
    @FXML
    private ComboBox<String> comboGjinia;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private AnchorPane rightPage;
    private final KandidateService kandidateService;
    private final OrariService orariService;
    private final RegjistrimiService regjistrimiService;
    private final DokumentService dokumentService;
    private final PagesaService pagesaService;
    private final TestiService testiService;


    public CandidateManagmentController() {
        this.kandidateService = new KandidateService();
        this.orariService = new OrariService();
        this.regjistrimiService = new RegjistrimiService();
        this.dokumentService = new DokumentService();
        this.pagesaService = new PagesaService();
        this.testiService = new TestiService();
    }

    @FXML
    public void initialize() {
        krijoFormenHBox.setVisible(false);
        setupTables();
        loadData();
    }

    private void setupTables() {
        //kandidatet
        configureTable(kandidatTable, List.of(
                        idColumn, nameColumn, surnameColumn, emailColumn, phoneColumn,
                        roleColumn, adresaColumn, gjiniaColumn, statusiProcesitColumn),
                new String[]{"idUser", "name", "surname", "email", "phoneNumber", "role", "adresa", "gjinia", "statusiProcesit"});
        dobColumn.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getDateOfBirth().toString()));
        dataRegjistrimiColumn.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getDataRegjistrimit().toString()));
        //dokumentet
        configureTable(tableViewDokumente,
                List.of(colEmriSkedari, colLlojiDokumentit),
                new String[]{"emriSkedarit", "llojiDokumentit"});

        colDataNgarkimit.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getDataNgarkimit().toString()));
        //pagesat
        configureTable(tabelaPagesat,
                List.of(colId, colIdKandidat, colShuma, colDataPageses, colMetodaPageses, colStatusiPageses),
                new String[]{"id", "idKandidat", "shuma", "dataPageses", "metodaPageses", "statusiPageses"});
        //orari
        configureTable(orariTable,
                List.of(idColumn1, idKandidatColumn, idStafColumn, dataSesioniColumn,
                        oraFillimitColumn, oraPerfundimitColumn, llojiMesimitColumn, statusiColumn, idAutomjetColumn),
                new String[]{"idSesioni", "idKandidat", "idStaf", "dataSesionit", "oraFillimit", "oraPerfundimit",
                        "llojiMesimit", "statusi", "idAutomjet"});
        //testet

        configureTable(testetTable,
                List.of(idColumn2, idKandidatColumn2, idStafColumn2, llojiTestitColumn,
                        dataTestitColumn, rezultatiColumn, piketColumn),
                new String[]{"id", "idKandidat", "idStaf", "llojiTestit", "dataTestit", "rezultati", "piket"});


    }

    private void loadData() {
        kandidatTable.setItems(FXCollections.observableArrayList(kandidateService.getAll()));
        tableViewDokumente.setItems(FXCollections.observableArrayList(dokumentService.getAll()));
        tabelaPagesat.setItems(FXCollections.observableArrayList(pagesaService.getAll()));
        orariTable.setItems(FXCollections.observableArrayList(orariService.getAll()));
        testetTable.setItems(FXCollections.observableArrayList(testiService.getAll()));
    }

    @FXML
    private void onCreateKandidatClick() {
        krijoFormenHBox.setVisible(true);
    }

    @FXML
    private void saveClick() throws Exception {
        String emri = txtEmri.getText(), mbiemri = txtMbiemri.getText(), email = txtEmail.getText(),
                phone = txtPhone.getText(), adresa = txtAdresa.getText(),
                gjinia = comboGjinia.getValue(), password = txtPassword.getText();
        LocalDate datelindja = datePickerDob.getValue();
        String salt = PasswordHasher.generateSalt();

        if (emri.isEmpty() || mbiemri.isEmpty() || email.isEmpty() || phone.isEmpty()
                || datelindja == null || adresa.isEmpty() || gjinia == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Incomplete data. Please fill in all fields.");
            return;
        }

        CreateKandidatetDto kandidatet = new CreateKandidatetDto(
                emri, mbiemri, email, phone, datelindja, PasswordHasher.generateSaltedHash(password, salt), salt,
                adresa, gjinia);
        try {
            kandidateService.create(kandidatet);
            showAlert(Alert.AlertType.INFORMATION, "Success", "The candidate was created successfully!");
            SceneManager.reload();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private boolean kontrolloDheKonfirmo(Object selected, String konfirmoMesazhi) {
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select an item.");
            return false;
        }
        return showConfirmationDialog("Confirm Deletion", konfirmoMesazhi);
    }

    @FXML
    private void onDeleteKandidatClick() throws Exception {
        Kandidatet selected = kandidatTable.getSelectionModel().getSelectedItem();
        if (kontrolloDheKonfirmo(selected, "Delete candidate: " + selected.getName() + " " + selected.getSurname() + "?")) {
            kandidatTable.getItems().remove(selected);
            kandidateService.delete(selected.getIdUser());
        }
    }

    @FXML
    private void onDeleteOrariClick() throws Exception {
        Orari selected = orariTable.getSelectionModel().getSelectedItem();
        if (kontrolloDheKonfirmo(selected, "Delete schedule for candidate ID: " + selected.getIdKandidat() + "?")) {
            orariTable.getItems().remove(selected);
            orariService.delete(selected.getIdSesioni());
        }
    }

    @FXML
    private void onDeletePagesatClick() throws Exception {
        Pagesat selected = tabelaPagesat.getSelectionModel().getSelectedItem();
        if (kontrolloDheKonfirmo(selected, "Delete payment with ID: " + selected.getId() + "?")) {
            tabelaPagesat.getItems().remove(selected);
            pagesaService.delete(selected.getId());
        }
    }

    @FXML
    private void onDeleteTestetClick() throws Exception {
        Testet selected = testetTable.getSelectionModel().getSelectedItem();
        if (kontrolloDheKonfirmo(selected, "Delete test with ID: " + selected.getId() + "?")) {
            testetTable.getItems().remove(selected);
            testiService.delete(selected.getId());
        }
    }

    @FXML
    private void onDeleteDokumentetClick() throws Exception {
        Dokumentet selected = tableViewDokumente.getSelectionModel().getSelectedItem();
        if (kontrolloDheKonfirmo(selected, "Delete document: " + selected.getEmriSkedarit() + "?")) {
            tableViewDokumente.getItems().remove(selected);
            dokumentService.delete(selected.getId());
        }
    }


    @FXML
    private void onMiratoClick() {
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
                showAlert(Alert.AlertType.WARNING, "Documents Missing",
                        "The candidate hasn't uploaded all documents.");
            }
        }
    }

    @FXML
    private void onUpdateKandidatClick() throws Exception {
        SceneManager.load(SceneLocator.UPDATE_ADMIN_CANDIDATE_PAGE, this.rightPage);
    }

    @FXML
    private void onDownloadClick() throws IOException {
        Dokumentet selected = tableViewDokumente.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a document.");
            return;
        }
        if (showConfirmationDialog("Confirm Download",
                "Do you want to download the document: " + selected.getEmriSkedarit() + "?")) {
            dokumentService.download(selected.getEmriSkedarit());
            showAlert(Alert.AlertType.INFORMATION, "Download Completed",
                    "The document has been saved to your Desktop successfully.");
        }
    }


}

