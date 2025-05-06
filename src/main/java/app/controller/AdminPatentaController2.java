//package app.controller;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import models.Dokumentet;
//import models.Kandidatet;
//import models.Pagesat;
//import services.*;
//
//import java.util.ArrayList;
//
//
//public class AdminPatentaController2 {
//    @FXML
//    private TableView<Kandidatet> tableKandidatet;
//    @FXML
//    private TableColumn<Kandidatet, String> colEmriK;
//    @FXML
//    private TableColumn<Kandidatet, String> colMbiemriK;
//    @FXML
//    private TableColumn<Kandidatet, String> colStatusiK;
//    @FXML
//    private TableColumn<Kandidatet, String> colRezultatetK;
//
//
//    @FXML
//    private TableView<Dokumentet> tableDokumente;
//    @FXML
//    private TableView<Pagesat> tablePagesat;
//
//    @FXML
//    private TextField txtIDKandidat, txtIDStatusi, txtIDPatenta;
//    @FXML
//    private ComboBox<String> comboStatusiProcesit, comboStatusiPageses, comboStatusiPatentes;
//    @FXML
//    private TextArea txtRezultatet;
//
//    private KandidateService kandidatService;
//    private PatentaService patentaService;
//    private DokumentService dokumentService;
//    private PagesaService pagesaService;
//    private TestiService testiService;
//
//    public AdminPatentaController2() {
//        kandidatService = new KandidateService();
//        patentaService = new PatentaService();
//        dokumentService = new DokumentService();
//        pagesaService=new PagesaService();
//        testiService=new TestiService();
//    }
//    @FXML
//    private void initialize() {
//        // Merrni të dhënat e kandidatëve nga KandidateService
//        ArrayList<Kandidatet> kandidatet = kandidatService.getAllKandidatet();
//        System.out.println("Kandidatet size: " + kandidatet.size());
//        // Lidhni ArrayList drejtpërdrejt me TableView pas konvertimit në ObservableList
//        tableKandidatet.setItems(FXCollections.observableArrayList(kandidatet));
//
//        // Lidhni kolonat me emrat e fushave të modelit
//        colEmriK.setCellValueFactory(new PropertyValueFactory<>("emri"));
//        colMbiemriK.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
////        colStatusiK.setCellValueFactory(new PropertyValueFactory<>("statusiProcesit"));
////        colRezultatetK.setCellValueFactory(new PropertyValueFactory<>("rezultatet"));
//    }
//
//    // Handle Verifikimin e Dokumenteve
//    @FXML
//    private void handleVerifikoDokumentin() {
//        String kandidatId = txtIDKandidat.getText();
//        if (kandidatId != null && !kandidatId.isEmpty()) {
//            ObservableList<Dokumentet> dokumentet = dokumentService.getDokumenteByKandidatId(kandidatId);
//            tableDokumente.setItems(dokumentet);
//        } else {
//            showError("Ju lutem, shkruani ID-në e Kandidatit.");
//        }
//    }
//
//
//    @FXML
//    private void handleShkarkoDokumentin() {
//        Dokumentet dokument = tableDokumente.getSelectionModel().getSelectedItem();
//        if (dokument != null) {
//            boolean success = dokumentService.downloadDokument(dokument);
//            if (success) {
//                showInfo("Dokumenti është shkarkuar me sukses!");
//            } else {
//                showError("Ka ndodhur një gabim gjatë shkarkimit të dokumentit.");
//            }
//        } else {
//            showError("Ju lutem, zgjidhni një dokument për të shkarkuar.");
//        }
//    }
//
////    @FXML
////    private void handleNdryshoStatusin() {
////        String kandidatId = txtIDStatusi.getText();
////        String statusi = comboStatusiProcesit.getValue();
////        if (kandidatId != null && !kandidatId.isEmpty() && statusi != null) {
////            kandidatService.updateStatusiProcesit(kandidatId, statusi);
////        } else {
////            showError("Ju lutem, plotësoni ID-në e Kandidatit dhe Statusin.");
////        }
////    }
//
//    @FXML
//    private void handleNdryshoStatusinPageses() {
//        Pagesat pagesa = tablePagesat.getSelectionModel().getSelectedItem();
//        String statusiPageses = comboStatusiPageses.getValue();
//        if (pagesa != null && statusiPageses != null) {
//            pagesaService.updateStatusiPageses(pagesa, statusiPageses);
//        } else {
//            showError("Ju lutem, zgjidhni një pagesë dhe statusin.");
//        }
//    }
//
//    @FXML
//    private void handleUpdateRezultatet() {
//        String rezultat = txtRezultatet.getText();
//        if (rezultat != null && !rezultat.isEmpty()) {
//            testiService.updateRezultatetKandidat(txtIDKandidat.getText(), rezultat);
//        } else {
//            showError("Ju lutem, shkruani rezultatet e testeve.");
//        }
//    }
//
//    // Handle Azhurnimi i Statusit të Patentës
//    @FXML
//    private void handleAzhurnoStatusinPatentes() {
//        String kandidatId = txtIDPatenta.getText();
//        String statusiPatentes = comboStatusiPatentes.getValue();
//        if (kandidatId != null && !kandidatId.isEmpty() && statusiPatentes != null) {
//            patentaService.updateStatusiPatentes(kandidatId, statusiPatentes);
//        } else {
//            showError("Ju lutem, plotësoni ID-në e Kandidatit dhe Statusin e Patentës.");
//        }
//    }
//
//    // Help function to show error messages
//    private void showError(String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Gabim");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//    private void showInfo(String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Sukses");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}
