package repository;

import models.Dokumentet;
import models.Dto.dokumentet.CreateDokumentetDto;
import models.Dto.dokumentet.UpdateDokumentetDto;

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
                INSERT INTO DOKUMENTET(ID_KANDIDAT, LLOJI_DOKUMENTIT, EMRI_SKEDARI, DATA_NGARKIMIT)
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

        StringBuilder query = new StringBuilder("UPDATE DOKUMENTET SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (dokumentetDto.getIdKandidat() != 0) {
            query.append("ID_KANDIDAT = ?, ");
            params.add(dokumentetDto.getIdKandidat());
        }
        if (dokumentetDto.getEmriSkedarit() != null) {
            query.append("EMRI_SKEDARI = ?, ");
            params.add(dokumentetDto.getEmriSkedarit());
        }
        if (dokumentetDto.getLlojiDokumentit() != null) {
            query.append("LLOJI_DOKUMENTIT = ?, ");
            params.add(dokumentetDto.getLlojiDokumentit());
        }

        if (params.isEmpty()) {
            return getById(dokumentetDto.getId());
        }
        query.setLength(query.length() - 2);//me largu "? "->se paraqet gabim ne sintakse
        query.append(" WHERE ID_DOKUMENT = ?");
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
}

