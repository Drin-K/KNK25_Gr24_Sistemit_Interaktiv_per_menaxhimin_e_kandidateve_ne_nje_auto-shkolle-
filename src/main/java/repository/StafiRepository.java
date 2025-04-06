package repository;

import models.Dto.stafi.CreateStafiDto;
import models.Dto.stafi.UpdateStafiDto;
import models.Stafi;

import java.nio.file.attribute.UserPrincipal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                STAFI(EMRI, MBIEMRI, NUMRITELEFONIT, EMAIL, ADRESA, ROLI)
                VALUES(?,?,?,?,?,?);
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1,stafiDto.getEmri());
            pstm.setString(2, stafiDto.getMbiemri());
            pstm.setString(3, stafiDto.getNumriTelefonit());
            pstm.setString(4, stafiDto.getEmail());
            pstm.setString(5, stafiDto.getAdresa());
            pstm.setString(6, stafiDto.getRoli());
            pstm.execute();
            ResultSet res=pstm.getGeneratedKeys();
            if(res.next()){
                int id=res.getInt(1);
                return this.getById(id);}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
public Stafi update(UpdateStafiDto stafiDto)

}

