package repository;

import models.Dto.testet.CreateTestetDto;
import models.Dto.testet.UpdateTestetDto;
import models.Testet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestetRepository extends BaseRepository<Testet, CreateTestetDto, UpdateTestetDto> {

    public TestetRepository() {
        super("testet");
    }

    @Override
    public Testet fromResultSet(ResultSet result) throws SQLException {
        return Testet.getInstance(result);
    }

    @Override
    public Testet create(CreateTestetDto testetDto) {
        String query = """
                INSERT INTO 
                TESTET (ID_Kandidat, ID_Staf, Lloji_i_Testit, Data_e_Testit, Rezultati, Piket)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS
            );
            pstm.setInt(1, testetDto.getIdKandidat());
            pstm.setInt(2, testetDto.getIdStaf());
            pstm.setString(3, testetDto.getLlojiTestit().name()); // Enumi kthehet në String
            pstm.setObject(4, testetDto.getDataTestit());
            pstm.setString(5, testetDto.getRezultati().name());  // Edhe rezultati kthehet në String
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
        StringBuilder query = new StringBuilder("UPDATE TESTET SET ");
                ArrayList<Object> params = new ArrayList<>();

                if (testetDto.getLlojiTestit() != null) {
                    query.append("LLOJI_I_TESTIT = ?, ");
                    params.add(testetDto.getLlojiTestit());
                }
                if (testetDto.getDataTestit() != null) {
                    query.append("DATA_E_TESTIT = ?, ");
                    params.add(testetDto.getDataTestit());
                }

                if (testetDto.getRezultati() != null) {
                    query.append("REZULTATI = ?, ");
                    params.add(testetDto.getRezultati());
                }
                if (testetDto.getPiket() != 0) {
                    query.append("PIKET = ?, ");
                    params.add(testetDto.getPiket());
                }
                if (params.isEmpty()) {
                    return getById(testetDto.getId());
                }
                query.setLength(query.length() - 2);//me largu "? "->se paraqet gabim ne sintakse
                query.append(" WHERE ID_TEST = ?");
                params.add(testetDto.getId());
                try {
                    PreparedStatement pstm = this.connection.prepareStatement(query.toString());
                    for (int i = 0; i < params.size(); i++) {
                        pstm.setObject(i + 1, params.get(i));
                    }
                    int updated = pstm.executeUpdate();
                    if (updated == 1) {
                        return this.getById(testetDto.getId());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Gabim gjatë përditësimit të testeve", e);
                }
                return null;
         }
}
