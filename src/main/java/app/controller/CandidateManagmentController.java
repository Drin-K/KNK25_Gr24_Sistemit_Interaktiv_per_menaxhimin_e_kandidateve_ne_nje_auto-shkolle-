package app.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import models.*;
import repository.KandidatetRepository;
import services.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private Button btnReplaceKandidat;

    @FXML
    private Button btnDeleteKandidat;
    @FXML
    private TextField txtEmri, txtMbiemri, txtEmail, txtPhone, txtRoli, txtAdresa, txtStatusiProcesit;
    @FXML
    private DatePicker datePickerDob, datePickerRegjistrimi;
    @FXML
    private ComboBox<String> comboGjinia;

    private ObservableList<Pagesat> pagesatList = FXCollections.observableArrayList();
    private KandidateService kandidateService=new KandidateService();
    private DokumentService dokumentService=new DokumentService();
    private PagesaService pagesaService=new PagesaService();
    private OrariService orariService=new OrariService();
    private TestiService testiService=new TestiService();
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



}
