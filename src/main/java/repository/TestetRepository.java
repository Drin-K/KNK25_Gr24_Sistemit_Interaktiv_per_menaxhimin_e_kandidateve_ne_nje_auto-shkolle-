package repository;

import models.Dto.testet.CreateTestetDto;
import models.Dto.testet.UpdateTestetDto;
import models.Testet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestetRepository extends BaseRepository<Testet, CreateTestetDto, UpdateTestetDto> {

    public TestetRepository() {
        super("Testet");
    }

    @Override
    public Testet fromResultSet(ResultSet result) throws SQLException {
        return Testet.getInstance(result);
    }

    @Override
    public Testet create(CreateTestetDto testetDto) {
        String query = """
                INSERT INTO 
                Testet (ID_Kandidat, ID_Staf, Lloji_i_Testit, Data_e_testit, Rezultati, Piket)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS
            );
            pstm.setInt(1, testetDto.getIdKandidat());
            pstm.setInt(2, testetDto.getIdStaf());
            pstm.setString(3, testetDto.getLlojiTestit());
            pstm.setObject(4, testetDto.getDataTestit());
            pstm.setString(5, testetDto.getRezultati());
            pstm.setInt(6, testetDto.getPiket());

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

    @Override
    public Testet update(UpdateTestetDto testetDto) {
        StringBuilder query = new StringBuilder("UPDATE Testet SET ");
                ArrayList<Object> params = new ArrayList<>();

                if (testetDto.getLlojiTestit() != null) {
                    query.append("Lloji_i_Testit = ?, ");
                    params.add(testetDto.getLlojiTestit());
                }
                if (testetDto.getDataTestit() != null) {
                    query.append("Data_e_testit = ?, ");
                    params.add(testetDto.getDataTestit());
                }

                if (testetDto.getRezultati() != null) {
                    query.append("Rezultati = ?, ");
                    params.add(testetDto.getRezultati());
                }
                if (testetDto.getPiket() != 0) {
                    query.append("Piket = ?, ");
                    params.add(testetDto.getPiket());
                }
                if (params.isEmpty()) {
                    return getById(testetDto.getIdTest());
                }
                query.setLength(query.length() - 2);//me largu "? "->se paraqet gabim ne sintakse
                query.append(" WHERE id = ?");
                params.add(testetDto.getIdTest());
                try {
                    PreparedStatement pstm = this.connection.prepareStatement(query.toString());
                    for (int i = 0; i < params.size(); i++) {
                        pstm.setObject(i + 1, params.get(i));
                    }
                    int updated = pstm.executeUpdate();
                    if (updated == 1) {
                        return this.getById(testetDto.getIdTest());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Gabim gjatë përditësimit të testeve", e);
                }
                return null;
         }
    public void updateRezultatiKandidatit(int idKandidat, String rezultati) throws SQLException {
        String sql = "UPDATE Testet SET Rezultati = ? WHERE ID_Kandidat = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, rezultati);
            statement.setInt(2, idKandidat);
            statement.executeUpdate();
        }
    }
    public List<Testet> getTestetByKandidatId(int idKandidat) {
        List<Testet> testetList = new ArrayList<>();
        String sql = "SELECT * FROM Testet WHERE ID_Kandidat = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idKandidat);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                testetList.add(fromResultSet(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testetList;
    }

}
