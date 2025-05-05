package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Dokumentet;
import models.Dto.dokumentet.CreateDokumentetDto;
import models.Kandidatet;
import repository.DokumentetRepository;
import repository.KandidatetRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class DokumentService {
    private DokumentetRepository dokumentRepository;
    private KandidatetRepository kandidatRepository;

    public DokumentService() {
        this.dokumentRepository = new DokumentetRepository();
        this.kandidatRepository = new KandidatetRepository();
    }

    // Funksioni për krijimin e dokumentit
    public Dokumentet create(CreateDokumentetDto createDokumentDto) throws Exception {
        // Validimet bazike për të kontrolluar të dhënat e dokumentit
        if (createDokumentDto.getIdKandidat() <= 0) {
            throw new Exception("ID e Kandidatit nuk është valide.");
        }

        if (createDokumentDto.getLlojiDokumentit() == null || createDokumentDto.getLlojiDokumentit().isEmpty()) {
            throw new Exception("Lloji i dokumentit është i detyrueshëm.");
        }

        if (!isValidDokumentType(createDokumentDto.getLlojiDokumentit())) {
            throw new Exception("Lloji i dokumentit duhet të jetë 'Leternjoftim', 'Certifikate Mjekësore', 'Aplikim', ose 'Foto'.");
        }

        // Kontrollojmë nëse kandidati ekziston
        Kandidatet kandidati =(Kandidatet) this.kandidatRepository.getById(createDokumentDto.getIdKandidat());
        if (kandidati == null) {
            throw new Exception("Kandidati me ID: " + createDokumentDto.getIdKandidat() + " nuk ekziston.");
        }

        // Krijo dokumentin
        Dokumentet dokument = this.dokumentRepository.create(createDokumentDto);
        if (dokument == null) {
            throw new Exception("Dokumenti nuk u krijua me sukses.");
        }

        return dokument;
    }

    // Funksioni për të marrë dokumentin me ID
    public Dokumentet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID e dokumentit nuk është valide.");
        }

        Dokumentet dokument = this.dokumentRepository.getById(id);
        if (dokument == null) {
            throw new Exception("Nuk u gjet dokumenti me ID: " + id);
        }

        return dokument;
    }

    // Funksioni për të kontrolluar nëse lloji i dokumentit është i vlefshëm
    private boolean isValidDokumentType(String llojiDokumentit) {
        return llojiDokumentit.equals("Leternjoftim") ||
                llojiDokumentit.equals("Certifikate Mjekësore") ||
                llojiDokumentit.equals("Aplikim") ||
                llojiDokumentit.equals("Foto");
    }
    public ObservableList<Dokumentet> getDokumenteByKandidatId(String kandidatId) {
        try {
            ArrayList<Dokumentet> dokumentetList = dokumentRepository.getDokumenteByKandidatId(kandidatId);
            return FXCollections.observableArrayList(dokumentetList);
        } catch (SQLException e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }
    public boolean downloadDokument(Dokumentet dokument) {
        return dokumentRepository.downloadDokument(dokument);
    }
}
