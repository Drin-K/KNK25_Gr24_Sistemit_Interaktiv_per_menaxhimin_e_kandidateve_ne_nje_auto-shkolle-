package repository;

import models.Dto.regjistrimet.CreateRegjistrimetDto;
import models.Dto.regjistrimet.UpdateRegjistrimetDto;
import models.Dto.testet.UpdateTestetDto;

import models.Regjistrimet;
import models.Dto.regjistrimet.CreateRegjistrimetDto;
import models.Dto.regjistrimet.UpdateRegjistrimetDto;
import models.Testet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RegjistrimetRepository extends BaseRepository<Regjistrimet, CreateRegjistrimetDto, UpdateRegjistrimetDto> {

    public RegjistrimetRepository() {
        super("Regjistrimet");
    }

    public Regjistrimet fromResultSet(ResultSet result) throws SQLException {
        return Regjistrimet.getInstance(result);
    }

    public void mirato(int id) {
        String query = "UPDATE Regjistrimet SET Statusi = 'Përfunduar' WHERE ID_Kandidat = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Statusi u përditësua me sukses në 'Përfunduar'.");
            } else {
                System.out.println("Asnjë regjistrim nuk u gjet për ID_Kandidat = " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Regjistrimet create(CreateRegjistrimetDto regjistrimetDto) {
        String query = """
                INSERT INTO 
                Regjistrimet(ID_Kandidat, ID_Kategori ,Statusi)
                VALUES (?, ?, ?);
                """;
        try {

            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, regjistrimetDto.getIdKandidat());
            pstm.setInt(2, regjistrimetDto.getIdKategori());
            pstm.setString(3, regjistrimetDto.getStatusi());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Regjistrimet update(UpdateRegjistrimetDto regjistrtimetDto) {
        StringBuilder query = new StringBuilder("UPDATE Regjistrimet SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (regjistrtimetDto.getStatusi() != null) {
            query.append("Statusi = ?, ");
            params.add(regjistrtimetDto.getStatusi());
        }
        if (params.isEmpty()) {
            return getById(regjistrtimetDto.getIdRegjistrim());
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(regjistrtimetDto.getIdRegjistrim());
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(regjistrtimetDto.getIdRegjistrim());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the exams.", e);
        }
        return null;
    }
}