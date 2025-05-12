package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Dokumentet;
import models.Dto.dokumentet.CreateDokumentetDto;
import models.Kandidatet;
import models.Pagesat;
import repository.DokumentetRepository;
import repository.KandidatetRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public void uploadDokument(CreateDokumentetDto dto, File file) throws Exception {
        if (dto.getIdKandidat() <= 0) {
            throw new Exception("ID e Kandidatit nuk është valide.");
        }

        if (dto.getLlojiDokumentit() == null || dto.getLlojiDokumentit().isEmpty()) {
            throw new Exception("Lloji i dokumentit është i detyrueshëm.");
        }

        if (!isValidDokumentType(dto.getLlojiDokumentit())) {
            throw new Exception("Lloji i dokumentit është i gabuar.");
        }

        Kandidatet kandidati = kandidatRepository.getById(dto.getIdKandidat());
        if (kandidati == null) {
            throw new Exception("Kandidati nuk ekziston.");
        }

        dokumentRepository.create(dto,file);
    }

    public void delete(int dokumentId) throws Exception {
        Dokumentet ekzistues = dokumentRepository.getById(dokumentId);
        if (ekzistues == null) {
            throw new Exception("Pagesa me ID " + dokumentId + " nuk ekziston.");
        }
        boolean fshirje = dokumentRepository.delete(dokumentId);
        if (!fshirje) {
            throw new Exception("Gabim gjatë fshirjes së dokumentit me ID " + dokumentId);
        }
    }
    public void download(String emriSkedarit) throws IOException {
        Path uploadDir = Paths.get("src", "main", "java", "utils", "uploads");
        Path sourcePath = uploadDir.resolve(emriSkedarit);
        Path destinationPath = Paths.get(System.getProperty("user.home"), "Desktop").resolve(emriSkedarit);
        if (Files.notExists(uploadDir)) Files.createDirectories(uploadDir);
        if (Files.exists(sourcePath)) {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } else {
            throw new IOException("The document does not exist.");
        }
    }

}
