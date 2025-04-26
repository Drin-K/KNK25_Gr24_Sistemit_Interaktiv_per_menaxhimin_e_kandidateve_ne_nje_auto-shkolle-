package repository;

import models.Dto.testet.CreateTestetDto;
import models.Dto.testet.UpdateTestetDto;
import models.Testet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        //
        return null;
    }
}
