package repository;

import models.Dokumentet;
import models.Dto.pagesat.CreatePagesatDto;
import models.Dto.pagesat.UpdatePagesatDto;
import models.Pagesat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagesatRepository extends BaseRepository<Pagesat, CreatePagesatDto, UpdatePagesatDto> {
    public PagesatRepository(){super("Pagesat");}
    public Pagesat fromResultSet(ResultSet result)throws SQLException{
        return Pagesat.getInstance(result);
    }
    public Pagesat create(CreatePagesatDto createPagesatDto){
        String query= """
                INSERT INTO
                PAGESAT(ID_KANDIDAT, SHUMA, DATA_E_PAGESES, METODA_E_PAGESES, STATUSI_I_PAGESES)
                VALUES(?,?,?,?,?);
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,createPagesatDto.getIdKandidat());
            pstm.setDouble(2,createPagesatDto.getShuma());
            pstm.setObject(3,createPagesatDto.getDataPageses());
            pstm.setString(4,createPagesatDto.getMetodaPageses());
            pstm.setString(5,createPagesatDto.getStatusiPageses());
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
    public Pagesat update(UpdatePagesatDto updatePagesatDto){return null;}
    }



