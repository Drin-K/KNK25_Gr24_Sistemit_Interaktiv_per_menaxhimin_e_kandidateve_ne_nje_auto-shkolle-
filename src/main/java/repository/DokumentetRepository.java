package repository;

import models.Dokumentet;
import models.Dto.dokumentet.CreateDokumentetDto;
import models.Dto.dokumentet.UpdateDokumentetDto;
import models.Kandidatet;
import services.DokumentService;
import services.KandidateService;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DokumentetRepository extends BaseRepository<Dokumentet, CreateDokumentetDto, UpdateDokumentetDto> {
    public DokumentetRepository() {
        super("Dokumentet");
    }
    private KandidateService kandidateService;
    private DokumentService dokumentService;
    public Dokumentet fromResultSet(ResultSet result) throws SQLException {
        return Dokumentet.getInstance(result);
    }
    public Dokumentet create(CreateDokumentetDto dokumentetDto) {
        String query = """
                INSERT INTO Dokumentet(ID_Kandidat, Lloji_Dokumentit, Emri_Skedari, Data_Ngarkimit)
                VALUES(?,?,?,?);
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dokumentetDto.getIdKandidat());
            pstm.setString(2, dokumentetDto.getLlojiDokumentit());
            pstm.setString(3, dokumentetDto.getEmriSkedarit());
            pstm.setObject(4, dokumentetDto.getDataNgarkimit());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return this.getById(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Gabim gjatë krijimit të dokumentit", e);
        }
        return null;
    }

    public Dokumentet update(UpdateDokumentetDto dokumentetDto) {

        StringBuilder query = new StringBuilder("UPDATE Dokumentet SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (dokumentetDto.getIdKandidat() != 0) {
            query.append("ID_Kandidat = ?, ");
            params.add(dokumentetDto.getIdKandidat());
        }
        if (dokumentetDto.getEmriSkedarit() != null) {
            query.append("Emri_Skeadri = ?, ");
            params.add(dokumentetDto.getEmriSkedarit());
        }
        if (dokumentetDto.getLlojiDokumentit() != null) {
            query.append("Lloji_Dokumentit = ?, ");
            params.add(dokumentetDto.getLlojiDokumentit());
        }

        if (params.isEmpty()) {
            return getById(dokumentetDto.getId());
        }
        query.setLength(query.length() - 2);//me largu "? "->se paraqet gabim ne sintakse
        query.append(" WHERE id = ?");
        params.add(dokumentetDto.getId());
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dokumentetDto.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gabim gjatë përditësimit të dokumentit", e);
        }
        return null;
    }
    public ArrayList<Dokumentet> getDokumenteByKandidatId(String kandidatId) throws SQLException {
        ArrayList<Dokumentet> dokumentetList = new ArrayList<>();
        String query = "SELECT * FROM Dokumentet WHERE ID_Kandidat = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, kandidatId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                dokumentetList.add(Dokumentet.getInstance(resultSet));
            }
        }

        return dokumentetList;
    }
//    public boolean downloadDokument(Dokumentet dokument) {
//        String query = "SELECT * FROM Dokumentet WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, dokument.getId());
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                String emriSkedarit = resultSet.getString("Emri_Skedari");
//                String filePath = "path_to_directory" + File.separator + emriSkedarit;  // Vendosni këtu udhëzimin për ruajtjen
//                File file = new File(filePath);
//
//                // Nëse dokumenti ekziston, e shkarkoni
//                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
//                     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:/path_to_download_location" + File.separator + emriSkedarit))) {
//                    byte[] buffer = new byte[1024];
//                    int length;
//                    while ((length = bis.read(buffer)) > 0) {
//                        bos.write(buffer, 0, length);
//                    }
//                    return true;
//                }
//            }
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
public int numeroDokumentet(int kandidatiId) {
    String query = """
        SELECT COUNT(DISTINCT Lloji_Dokumentit)
        FROM Dokumentet
        WHERE ID_Kandidat = ?
          AND Lloji_Dokumentit IN ('Leternjoftim', 'Certifikate Mjekësore', 'Aplikim', 'Foto')
    """;

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, kandidatiId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1); // numri i dokumenteve të ndryshme
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 0;
}
public ArrayList<Dokumentet> getAllDokumentet() {
    ArrayList<Dokumentet> dokumentetList = new ArrayList<>();
    String query = "SELECT d.id, d.ID_Kandidat, d.Lloji_Dokumentit, d.Emri_Skedari, d.Data_Ngarkimit " +
            "FROM Dokumentet d";

    try (
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()
    ) {
        while (rs.next()) {
            Dokumentet dokument = Dokumentet.getInstance(rs);
            dokumentetList.add(dokument);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return dokumentetList;
}
    public Dokumentet create(CreateDokumentetDto dto, File fileToUpload) throws Exception {
        // Folder where to store files
        String relativePath = "src/main/java/utils/uploads";
        File uploadDir = new File(relativePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File targetFile = new File(uploadDir, dto.getEmriSkedarit());

        // Copy file
        try (FileInputStream fis = new FileInputStream(fileToUpload);
             FileOutputStream fos = new FileOutputStream(targetFile)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new Exception("Gabim gjatë ruajtjes së skedarit.", e);
        }
        System.out.println("Inserting: " + dto.getIdKandidat() + ", " +
                dto.getLlojiDokumentit() + ", " +
                dto.getEmriSkedarit() + ", " +
                dto.getDataNgarkimit());

        // Insert into DB
        String query = "INSERT INTO dokumentet (id_kandidat, lloji_dokumentit, emri_skedari, data_ngarkimit) VALUES (?, ?, ?, ?) RETURNING id";

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, dto.getIdKandidat());
            pstm.setString(2, dto.getLlojiDokumentit());
            pstm.setString(3, dto.getEmriSkedarit());
            pstm.setDate(4, Date.valueOf(dto.getDataNgarkimit()));

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                return new Dokumentet(id, dto.getIdKandidat(), dto.getLlojiDokumentit(),
                        dto.getEmriSkedarit(), dto.getDataNgarkimit());
            } else {
                throw new Exception("Dokumenti nuk u ruajt në DB.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Gabim në DB.");
        }
    }


}

