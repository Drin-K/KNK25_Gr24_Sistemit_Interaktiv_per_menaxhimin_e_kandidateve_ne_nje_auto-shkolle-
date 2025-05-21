package repository;

import models.Dokumentet;
import models.Dto.dokumentet.CreateDokumentetDto;
import models.Dto.dokumentet.UpdateDokumentetDto;
import models.Kandidatet;
import services.DokumentService;
import services.KandidateService;
import services.UserContext;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DokumentetRepository extends BaseRepository<Dokumentet, CreateDokumentetDto, UpdateDokumentetDto> {
    public DokumentetRepository() {
        super("Dokumentet");
    }

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
            throw new RuntimeException("Error while creating the document", e);
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
            query.append("Emri_Skedari = ?, ");
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


    public int numeroDokumentet(int kandidatiId) {
        String query = """
                    SELECT COUNT(DISTINCT Lloji_Dokumentit)
                    FROM Dokumentet
                    WHERE ID_Kandidat = ?
                      AND Lloji_Dokumentit IN ('Leternjoftim', 'Certifikate Mjekesore', 'Aplikim', 'Foto')
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, kandidatiId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Dokumentet create(CreateDokumentetDto dto, File fileToUpload) throws Exception {
        String relativePath = "src/main/java/utils/uploads";
        File uploadDir = new File(relativePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File targetFile = new File(uploadDir, dto.getEmriSkedarit());

        try (FileInputStream fis = new FileInputStream(fileToUpload);
             FileOutputStream fos = new FileOutputStream(targetFile)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new Exception("Error while saving the file.", e);
        }
        System.out.println("Inserting: " + dto.getIdKandidat() + ", " +
                dto.getLlojiDokumentit() + ", " +
                dto.getEmriSkedarit() + ", " +
                dto.getDataNgarkimit());

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
                throw new Exception("The document was not saved to the database.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Database error.");
        }
    }

    public String getFotoFileNameForCurrentUser() {
        int currentUserId = UserContext.getUserId();
        String query = """
                    SELECT Emri_Skedari
                    FROM Dokumentet
                    WHERE ID_Kandidat = ? AND Lloji_Dokumentit = 'Foto'
                    LIMIT 1
                """;

        try {
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, currentUserId);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getString("Emri_Skedari");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}

