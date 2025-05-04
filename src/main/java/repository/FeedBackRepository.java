package repository;

import models.Dto.feedBack.CreateFeedBackDto;
import models.Dto.feedBack.UpdateFeedBackDto;
import models.FeedBack;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedBackRepository extends BaseRepository<FeedBack, CreateFeedBackDto, UpdateFeedBackDto> {
    public FeedBackRepository(){super("FeedBack");}
    public FeedBack fromResultSet(ResultSet result)throws SQLException{
        return FeedBack.getInstance(result);
    }
    public FeedBack create(CreateFeedBackDto createFeedBackDto){
        String query= """
                INSERT INTO
                Feedback(ID_Kandidat, ID_Staf, Data_Feedback, Vleresimi, Koment)
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
        public FeedBack update(UpdateFeedBackDto updateFeedBackDto){
        StringBuilder query=new StringBuilder("UPDATE Feedback SET");
            ArrayList<Object>params=new ArrayList<>();
            if (updateFeedBackDto.getVlersimi() !=0){
                query.append("Vleresimi=?, ");
                params.add(updateFeedBackDto.getVlersimi());
            }
            if (updateFeedBackDto.getKoment()!=null){
                query.append("Koment=?, ");
                params.add(updateFeedBackDto.getKoment());
            }
            if (params.isEmpty()) {
                return getById(updateFeedBackDto.getId());
            }
            query.setLength(query.length() - 2);//me largu ", "->se paraqet gabim ne sintakse
            query.append(" WHERE id = ?");
            params.add(updateFeedBackDto.getId());
            try {
                PreparedStatement pstm = this.connection.prepareStatement(query.toString());
                for (int i = 0; i < params.size(); i++) {
                    pstm.setObject(i + 1, params.get(i));
                }
                int updated = pstm.executeUpdate();
                if (updated == 1) {
                    return this.getById(updateFeedBackDto.getId());
                }
            } catch (SQLException e) {
                throw new RuntimeException("Gabim gjatë përditësimit të kandidatit", e);
            }
            return null;
    }
    }

