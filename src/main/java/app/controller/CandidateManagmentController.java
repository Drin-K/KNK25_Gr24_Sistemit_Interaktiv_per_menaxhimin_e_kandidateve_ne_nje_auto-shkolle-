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
import repository.KandidatetRepository;
import services.DokumentService;
import services.KandidateService;

import java.sql.SQLException;

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

    private KandidateService kandidateService=new KandidateService();
    private DokumentService dokumentService=new DokumentService();
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
    private void loadKandidatetData(){
        ObservableList<Kandidatet> kandidatetList = FXCollections.observableArrayList(kandidateService.getAllKandidatet());
        kandidatTable.setItems(kandidatetList);
    }
    @FXML
    public void initialize(){
        loadKandidatetData();
        loadDokumentetData();
        setColumnFactories();
        setDokumentetColumnFactories();

    }

}
