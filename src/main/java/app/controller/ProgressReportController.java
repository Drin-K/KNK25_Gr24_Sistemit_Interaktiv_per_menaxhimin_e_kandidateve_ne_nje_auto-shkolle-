package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import models.RaportiProgresit;
import repository.RaportiProgresitRepository;

import java.util.List;

public class ProgressReportController extends BaseController {
    @FXML private Label idLabel;
    @FXML private Label kandidatIdLabel;
    @FXML private Label stafIdLabel;
    @FXML private Label dataRaportitLabel;
    @FXML private Label piketTeorikeLabel;
    @FXML
    private Label piketPraktikeLabel;
    @FXML private Label komentetLabel;
    @FXML private Label performancaGjeneraleLabel;
    private int kandidatId;
    private int stafId;

    public void setKandidatId(int kandidatId, int stafId) {
        this.kandidatId = kandidatId;
        this.stafId=stafId;
    }

    private final RaportiProgresitRepository repository = new RaportiProgresitRepository();

    public void shfaqRaportin() {
      List<RaportiProgresit> raportet = repository.getRaportetByStaf(this.kandidatId, this.stafId);
        if (raportet == null || raportet.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "There is no report for this id");
            return;
        }
        RaportiProgresit raporti = raportet.get(0);
        idLabel.setText("ID: " + raporti.getId());
        kandidatIdLabel.setText("Candidate ID: " + raporti.getIdKandidat());
        stafIdLabel.setText("Staf ID: " + raporti.getIdStaf());
        dataRaportitLabel.setText("Report Date: " + raporti.getDataRaportit());
        piketTeorikeLabel.setText("Theoritical Scores: " + raporti.getPiketTeorike());
        piketPraktikeLabel.setText(" Practical Scores: " + raporti.getPiketPraktike());
        komentetLabel.setText("Comments: " + raporti.getKomentet());
        performancaGjeneraleLabel.setText("General Performance: " + raporti.getPerformancaGjenerale());
    }
}

