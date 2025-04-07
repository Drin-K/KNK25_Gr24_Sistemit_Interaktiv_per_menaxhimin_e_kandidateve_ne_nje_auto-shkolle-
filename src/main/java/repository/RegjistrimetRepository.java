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

public class RegjistrimetRepository extends BaseRepository<Regjistrimet, CreateRegjistrimetDto, UpdateRegjistrimetDto> {

    public RegjistrimetRepository() {
        super("regjistrimet");
    }

    public Regjistrimet fromResultSet(ResultSet result) throws SQLException {
        return Regjistrimet.getInstance(result);
    }

    public Regjistrimet create(CreateRegjistrimetDto regjistrimetDto) {
        String query = """
                INSERT INTO 
                REGJISTRIMET (id_kandidat, id_kateogri , statusi)
                VALUES (?, ?, ?);
                """;
        try {

            PreparedStatement pstm =
                    this.connection.prepareStatement(
                            query, Statement.RETURN_GENERATED_KEYS);
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
//        String query = """
//                UPDATE regjistrimet
//                SET EMAIL = ?
//                WHERE ID = ?
//                """;
//        try{
//            PreparedStatement pstm = this.connection.prepareStatement(query);
//            pstm.setString(1, regjistrimetDto.getEmail());
//            pstm.setInt(2, regjistrimetDto.getId());
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