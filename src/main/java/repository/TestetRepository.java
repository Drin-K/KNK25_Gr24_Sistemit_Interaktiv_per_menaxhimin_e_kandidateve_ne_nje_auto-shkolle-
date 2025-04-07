package repository;
import models.Dto.testet.CreateTestetDto;
import models.Testet;
import models.Dto.testet.UpdateTestetDto;
import models.Dto.testet.CreateTestetDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestetRepository extends BaseRepository<Testet, CreateTestetDto, UpdateTestetDto> {

    public TestetRepository() {
        super("testet");
    }

    public Testet fromResultSet(ResultSet result) throws SQLException {
        return Testet.getInstance(result);
    }

    public Testet create(CreateTestetDto testetDto) {
        String query = """
                INSERT INTO 
                TESTET (id_kandidat, id_staf , lloji_i_testit, data_e_testit)
                VALUES (?, ?, ?,?)
                """;
        try {

            PreparedStatement pstm =
                    this.connection.prepareStatement(
                            query, Statement.RETURN_GENERATED_KEYS);
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

    public Testet update(UpdateTestetDto testetDto) {
//        String query = """
//                UPDATE USERS
//                SET EMAIL = ?
//                WHERE ID = ?
//                """;
//        try{
//            PreparedStatement pstm = this.connection.prepareStatement(query);
//            pstm.setString(1, testetDto.getEmail());
//            pstm.setInt(2, testetDto.getId());
//            int updatedRecords = pstm.executeUpdate();
//            if(updatedRecords == 1){
//                return this.getById(testetDto.getId());
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
        return null;
    }
}