package repository;

import models.Dto.feedBack.CreateFeedBackDto;
import models.Dto.feedBack.UpdateFeedBackDto;
import models.FeedBack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedBackRepository extends BaseRepository<FeedBack, CreateFeedBackDto, UpdateFeedBackDto> {
    public FeedBackRepository(){super("FeedBack");}
    public FeedBack fromResultSet(ResultSet result)throws SQLException{
        return FeedBack.getInstance(result);
    }
    public FeedBack create(CreateFeedBackDto createFeedBackDto){
        String query= """
                INSERT INTO
                FEEDBACK(ID_KANDIDAT, ID_STAF, DATA_FEEDBACK, VLERSIMI, KOMENT)
                VALUES(?,?,?,?,?);
                """;
        try {
            PreparedStatement pstm=this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,createFeedBackDto.getIdKandidat());
            pstm.setInt(2,createFeedBackDto.getIdStaf());
            pstm.setObject(3,createFeedBackDto.getDataFeedback());
            pstm.setInt(4,createFeedBackDto.getVlersimi());
            pstm.setString(5,createFeedBackDto.getKoment());
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
        public FeedBack update(UpdateFeedBackDto updateFeedBackDto){return null;}
    }

