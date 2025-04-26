package repository;

import models.Dto.kandidatet.CreateKandidatetDto;
import models.Dto.kandidatet.UpdateKandidatetDto;
import models.Kandidatet;

import java.sql.*;
import java.util.ArrayList;

public class KandidatetRepository extends BaseRepository<Kandidatet, CreateKandidatetDto, UpdateKandidatetDto> {
    public KandidatetRepository() {
        super("Kandidatet");
    }

    public Kandidatet fromResultSet(ResultSet result) throws SQLException {
        return Kandidatet.getInstance(result);
    }


    public Kandidatet create(CreateKandidatetDto kandidatetDto) {
        String query = """
                INSERT INTO KANDIDATET(EMRI, MBIEMRI, DATELINDJA, GJINIA, NUMRI_TELEFONIT, EMAIL, ADRESA, DATA_E_REGJISTRIMIT, STATUSI_I_PROCESIT)
                VALUES(?,?,?,?,?,?,?,?,?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, kandidatetDto.getEmri());
            pstm.setString(2, kandidatetDto.getMbiemri());
            pstm.setObject(3, kandidatetDto.getDatelindja());
            pstm.setString(4, kandidatetDto.getGjinia());
            pstm.setString(5, kandidatetDto.getNumriTelefonit());
            pstm.setString(6, kandidatetDto.getEmail());
            pstm.setString(7, kandidatetDto.getAdresa());
            pstm.setObject(8, kandidatetDto.getDataRegjistrimit());
            pstm.setString(9, kandidatetDto.getStatusiProcesit());

            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);

                return this.getById(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Gabim gjatë krijimit të kandidatit", e);
        }
        return null;
    }

    public Kandidatet update(UpdateKandidatetDto kandidatetDto) {
        StringBuilder query = new StringBuilder("UPDATE KANDIDATET SET ");
        ArrayList<Object> params = new ArrayList<>();
        if (kandidatetDto.getEmail() != null) {
            query.append("EMAIL = ?, ");
            params.add(kandidatetDto.getEmail());
        }
        if (kandidatetDto.getNumriTelefonit() != null) {
            query.append("NUMRI_TELEFONIT = ?, ");
            params.add(kandidatetDto.getNumriTelefonit());
        }
        if (kandidatetDto.getStatusiProcesit() != null) {
            query.append("STATUSI_I_PROCESIT = ?, ");
            params.add(kandidatetDto.getStatusiProcesit());
        }
        if (kandidatetDto.getAdresa() != null) {
            query.append("ADRESA = ?, ");
            params.add(kandidatetDto.getAdresa());
        }
        if (params.isEmpty()) {
            return getById(kandidatetDto.getId());
        }
        query.setLength(query.length() - 2);//me largu "? "->se paraqet gabim ne sintakse
        query.append(" WHERE ID_KANDIDAT = ?");
        params.add(kandidatetDto.getId());
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(kandidatetDto.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gabim gjatë përditësimit të kandidatit", e);
        }
        return null;
    }

}