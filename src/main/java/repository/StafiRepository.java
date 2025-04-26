package repository;

import models.Dto.stafi.CreateStafiDto;
import models.Dto.stafi.UpdateStafiDto;
import models.Stafi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StafiRepository extends BaseRepository<Stafi, CreateStafiDto, UpdateStafiDto> {
    public StafiRepository() {
        super("Stafi");
    }

    public Stafi fromResultSet(ResultSet result) throws SQLException {
        return Stafi.getInstance(result);
    }

    public Stafi create(CreateStafiDto stafiDto) {
        String query = """
                INSERT INTO
                STAFI(EMRI, MBIEMRI, NUMRI_TELEFONIT, EMAIL, ADRESA, ROLI)
                VALUES(?,?,?,?,?,?);
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, stafiDto.getEmri());
            pstm.setString(2, stafiDto.getMbiemri());
            pstm.setString(3, stafiDto.getNumriTelefonit());
            pstm.setString(4, stafiDto.getEmail());
            pstm.setString(5, stafiDto.getAdresa());
            pstm.setString(6, stafiDto.getRoli());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return this.getById(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Gabim gjatë krijimit të stafit", e);
        }
        return null;
    }

    public Stafi update(UpdateStafiDto stafiDto) {

        StringBuilder query = new StringBuilder("UPDATE STAFI SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (stafiDto.getEmail() != null) {
            query.append("EMAIL = ?, ");
            params.add(stafiDto.getEmail());
        }
        if (stafiDto.getNumriTelefonit() != null) {
            query.append("NUMRI_TELEFONIT = ?, ");
            params.add(stafiDto.getNumriTelefonit());
        }

        if (stafiDto.getRoli() != null) {
            query.append("ROLI = ?, ");
            params.add(stafiDto.getRoli());
        }
        if (stafiDto.getAdresa() != null) {
            query.append("ADRESA = ?, ");
            params.add(stafiDto.getAdresa());
        }
        if (params.isEmpty()) {
            return getById(stafiDto.getId());
        }
        query.setLength(query.length() - 2);//me largu "? "->se paraqet gabim ne sintakse
        query.append(" WHERE ID_STAF = ?");
        params.add(stafiDto.getId());
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(stafiDto.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gabim gjatë përditësimit të stafit", e);
        }
        return null;
    }
}

