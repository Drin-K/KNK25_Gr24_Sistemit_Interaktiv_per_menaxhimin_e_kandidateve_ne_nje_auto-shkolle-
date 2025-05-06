package app.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Dokumentet;
import models.Kandidatet;
import models.Orari;
import models.Pagesat;
import repository.KandidatetRepository;
import services.DokumentService;
import services.KandidateService;
import services.OrariService;
import services.PagesaService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CandidateManagmentController{
    @FXML
    private TableView<Kandidatet> kandidatTable;

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

    private ObservableList<Pagesat> pagesatList = FXCollections.observableArrayList();
    private KandidateService kandidateService=new KandidateService();
    private DokumentService dokumentService=new DokumentService();
    private PagesaService pagesaService=new PagesaService();
    private OrariService orariService=new OrariService();
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
            idColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
            idKandidatColumn.setCellValueFactory(new PropertyValueFactory<>("idKandidat"));
            idStafColumn.setCellValueFactory(new PropertyValueFactory<>("idStaf"));
            dataSesioniColumn.setCellValueFactory(new PropertyValueFactory<>("dataSesioni"));
            oraFillimitColumn.setCellValueFactory(new PropertyValueFactory<>("oraFillimit"));
            oraPerfundimitColumn.setCellValueFactory(new PropertyValueFactory<>("oraPerfundimit"));
            llojiMesimitColumn.setCellValueFactory(new PropertyValueFactory<>("llojiMesimit"));
            statusiColumn.setCellValueFactory(new PropertyValueFactory<>("statusi"));
            idAutomjetColumn.setCellValueFactory(new PropertyValueFactory<>("idAutomjet"));


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
    @FXML
    public void initialize(){
        loadKandidatetData();
        loadDokumentetData();
        loadPagesatData();
        loadOrariData();























        setColumnFactories();
        setDokumentetColumnFactories();
        setColumnPayments();
        setColumnOrari();

    }

}
