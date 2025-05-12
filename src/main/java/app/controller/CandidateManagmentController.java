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
import repository.DokumentetRepository;
import repository.OrariRepository;
import repository.PagesatRepository;
import repository.TestetRepository;
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
    private TextField txtEmri, txtMbiemri, txtEmail, txtPhone, txtAdresa, txtStatusiProcesit;
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
    private final TestetRepository testiRepository;
    private final DokumentetRepository dokumentetRepository;
    private final PagesatRepository pagesatRepository;
    private final OrariRepository orariRepository;
    private final DokumentService dokumentService;


    public CandidateManagmentController() {
        this.kandidateService = new KandidateService();
        this.orariService = new OrariService();
        this.regjistrimiService = new RegjistrimiService();
        this.testiRepository = new TestetRepository();
        this.dokumentetRepository = new DokumentetRepository();
        this.pagesatRepository = new PagesatRepository();
        this.orariRepository = new OrariRepository();
        this.dokumentService=new DokumentService();
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
        tableViewDokumente.setItems(FXCollections.observableArrayList(dokumentetRepository.getAll()));
        tabelaPagesat.setItems(FXCollections.observableArrayList(pagesatRepository.getAll()));
        orariTable.setItems(FXCollections.observableArrayList(orariRepository.getAll()));
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
                 adresa, gjinia, null, statusiProcesit);
      try{  kandidateService.create(kandidatet);}
      catch (Exception e){
          showAlert(Alert.AlertType.ERROR,"Error",e.getMessage());
      }
        clearData();
    }

    private void clearData() {
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
    private void onDeletePagesatClick() {
        Pagesat selected = tabelaPagesat.getSelectionModel().getSelectedItem();
        if (kontrolloDheKonfirmo(selected, "Delete payment with ID: " + selected.getId() + "?")) {
            tabelaPagesat.getItems().remove(selected);
            pagesatRepository.delete(selected.getId());
        }
    }

    @FXML
    private void onDeleteTestetClick() {
        Testet selected = testetTable.getSelectionModel().getSelectedItem();
        if (kontrolloDheKonfirmo(selected, "Delete test with ID: " + selected.getId() + "?")) {
            testetTable.getItems().remove(selected);
            testiRepository.delete(selected.getId());
        }
    }

    @FXML
    private void onDeleteDokumentetClick() {
        Dokumentet selected = tableViewDokumente.getSelectionModel().getSelectedItem();
        if (kontrolloDheKonfirmo(selected, "Delete document: " + selected.getEmriSkedarit() + "?")) {
            tableViewDokumente.getItems().remove(selected);
            dokumentetRepository.delete(selected.getId());
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
                        "The candidate is missing the following documents:\\n- ID Card\\n- Medical Certificate\\n- Application\\n- Photo");
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
        }
    }

    @FXML
    private void onShfaqRaportinClick() throws Exception {
        Testet selected = testetTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            int kandidatId = selected.getIdKandidat();
            int stafId = selected.getIdStaf();
            ProgressReportController controller = SceneManager.loadWithController(
                    SceneLocator.PROGRESS_ADMIN_REPORT_PAGE, this.rightPage, kandidatId, stafId
            );
            controller.shfaqRaportin();
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a row.");
        }
    }

}

