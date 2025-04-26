package repository;

import models.Dto.raporti_progresit.CreateRaportiProgresitDto;
import models.Dto.raporti_progresit.UpdateRaportiProgresitDto;
import models.RaportiProgresit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RaportiProgresitRepository extends BaseRepository<RaportiProgresit, CreateRaportiProgresitDto, UpdateRaportiProgresitDto>{
    public RaportiProgresitRepository(){super("raporti_progresit");}
    public RaportiProgresit fromResultSet(ResultSet result) throws SQLException {
        return RaportiProgresit.getInstance(result);
    }
    public RaportiProgresit create(CreateRaportiProgresitDto raportiProgresitDto){
        String query = """
                INSERT INTO 
                RAPORTI_PROGRESIT("ID_KANDIDAT","ID_STAF","DATA_RAPORTIT","PIKET_TEORIKE","PIKET_PRAKTIKE","KOMENTET","PERFORMACA_GJENERALE")
                VALUES(?,?,?,?,?,?,?);""";
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,raportiProgresitDto.getIdKandidat());
            pstm.setInt(2,raportiProgresitDto.getIdStaf());
            pstm.setObject(3,raportiProgresitDto.getDataRaportit());
            pstm.setInt(4,raportiProgresitDto.getPiketTeorike());
            pstm.setInt(5,raportiProgresitDto.getPiketPraktike());
            pstm.setString(6,raportiProgresitDto.getKomentet());
            pstm.setString(7,raportiProgresitDto.getPerformancaGjenerale());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if(res.next()){
                int id = res.getInt(1);
                return this.getById(id);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public RaportiProgresit update(UpdateRaportiProgresitDto raportiProgresitDto){
        return null;
    }
}
