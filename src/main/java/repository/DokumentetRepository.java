package repository;

import models.Dokumentet;
import models.Dto.dokumentet.CreateDokumentetDto;
import models.Dto.dokumentet.UpdateDokumentetDto;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public boolean downloadDokument(Dokumentet dokument) {
        String query = "SELECT * FROM Dokumentet WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dokument.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String emriSkedarit = resultSet.getString("Emri_Skedari");
                String filePath = "path_to_directory" + File.separator + emriSkedarit;  // Vendosni këtu udhëzimin për ruajtjen
                File file = new File(filePath);

                // Nëse dokumenti ekziston, e shkarkoni
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:/path_to_download_location" + File.separator + emriSkedarit))) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = bis.read(buffer)) > 0) {
                        bos.write(buffer, 0, length);
                    }
                    return true;
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

