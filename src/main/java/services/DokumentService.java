package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
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
import java.util.List;

public class DokumentService {
    private DokumentetRepository dokumentRepository;
    private KandidatetRepository kandidatRepository;

    public DokumentService() {
        this.dokumentRepository = new DokumentetRepository();
        this.kandidatRepository = new KandidatetRepository();
    }

    // Funksioni për krijimin e dokumentit
    public Dokumentet create(CreateDokumentetDto createDokumentDto) throws Exception {
        if (createDokumentDto.getIdKandidat() <= 0) {
            throw new Exception("The candidate ID is not valid.");
        }
        if (createDokumentDto.getLlojiDokumentit() == null || createDokumentDto.getLlojiDokumentit().isEmpty()) {
            throw new Exception("The document type is required.");
        }
        if (!isValidDokumentType(createDokumentDto.getLlojiDokumentit())) {
            throw new Exception("The document type must be 'Identification Card', 'Medical Certificate', 'Application', or 'Photo'.");
        }
        Kandidatet kandidati =(Kandidatet) this.kandidatRepository.getById(createDokumentDto.getIdKandidat());
        if (kandidati == null) {
            throw new Exception("The Candidate with ID: " + createDokumentDto.getIdKandidat() + " does not exist");
        }
        Dokumentet dokument = this.dokumentRepository.create(createDokumentDto);
        if (dokument == null) {
            throw new Exception("The document was created successfully");
        }

        return dokument;
    }
    public Dokumentet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("The document ID is not valid");
        }
        Dokumentet dokument = this.dokumentRepository.getById(id);
        if (dokument == null) {
            throw new Exception("The document with ID: " + id+" was not found.");
        }

        return dokument;
    }
    private boolean isValidDokumentType(String llojiDokumentit) {
        return llojiDokumentit.equals("Leternjoftim") ||
                llojiDokumentit.equals("Certifikate Mjekësore") ||
                llojiDokumentit.equals("Aplikim") ||
                llojiDokumentit.equals("Foto");
    }
    public void uploadDokument(CreateDokumentetDto dto, File file) throws Exception {
        if (dto.getIdKandidat() <= 0) {
            throw new Exception("The candidate id is not valid.");
        }

        if (dto.getLlojiDokumentit() == null || dto.getLlojiDokumentit().isEmpty()) {
            throw new Exception("The document type is mandatory.");
        }

        if (!isValidDokumentType(dto.getLlojiDokumentit())) {
            throw new Exception("The document type is incorrect.");
        }

        Kandidatet kandidati = kandidatRepository.getById(dto.getIdKandidat());
        if (kandidati == null) {
            throw new Exception("The candidate does not exist.");
        }

        dokumentRepository.create(dto,file);
    }

    public void delete(int dokumentId) throws Exception {
        Dokumentet ekzistues = dokumentRepository.getById(dokumentId);
        if (ekzistues == null) {
            throw new Exception("The payment with ID " + dokumentId + " does not exist.");
        }
        boolean fshirje = dokumentRepository.delete(dokumentId);
        if (!fshirje) {
            throw new Exception("Error deleting the document with ID " + dokumentId);
        }
    }
    public void download(String emriSkedarit) throws IOException {
        Path uploadDir = Paths.get("src", "main", "java", "utils", "uploads");
        Path sourcePath = uploadDir.resolve(emriSkedarit);
        Path destinationPath = Paths.get(System.getProperty("user.home"), "Desktop").resolve(emriSkedarit);
        if (Files.notExists(uploadDir)) Files.createDirectories(uploadDir);
        if (Files.exists(sourcePath)) {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            Path source = Paths.get("src/main/java/utils/uploads", emriSkedarit);
            Path destination = Paths.get(System.getProperty("user.home"), "Desktop", emriSkedarit);
            Files.createDirectories(source.getParent());
            if (Files.exists(source)) {
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            } else {
                throw new IOException("The document does not exist.");
            }
        }}

    public List<Dokumentet> getAll() {
        List<Dokumentet> dokumentetList = dokumentRepository.getAll();
        List<Dokumentet> validDocuments = new ArrayList<>();

        for (Dokumentet d : dokumentetList) {
            if (d.getId() > 0 && d.getIdKandidat() > 0 && isValidDokumentType(d.getLlojiDokumentit())) {
                validDocuments.add(d);
            }
        }
        return validDocuments;
    }
    public void getProfilePicture(ImageView profilePicture){
        String filename = dokumentRepository.getFotoFileNameForCurrentUser();

        if (filename != null && !filename.isBlank()) {
            File imageFile = new File("src/main/java/utils/uploads", filename);

            if (imageFile.exists()) {
                Image profileImage = new Image(imageFile.toURI().toString());
                profilePicture.setImage(profileImage);
            } else {
                System.err.println("File not found: " + imageFile.getAbsolutePath());
            }
        } else {
            File defaultProfilePicture = new File("src/main/resources/images/Default Profile Picture.jpg");
            Image defaultPicture = new Image(defaultProfilePicture.toURI().toString());
            profilePicture.setImage(defaultPicture);
            System.err.println("No filename found for profile picture.");
        }
        Circle clip = new Circle(
                profilePicture.getFitWidth() / 2,
                profilePicture.getFitHeight() / 2,
                Math.min(profilePicture.getFitWidth(), profilePicture.getFitHeight()) / 2
        ); // Qe ta bejme foton e profilit ne form rrethore
        profilePicture.setClip(clip);
    }
}
