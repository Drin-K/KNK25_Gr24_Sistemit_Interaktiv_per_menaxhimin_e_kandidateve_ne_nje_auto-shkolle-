package repository;

import models.Dto.kategorite_patentes.CreateKategoritePatentesDto;
import models.Dto.kategorite_patentes.UpdateKategoritePatentesDto;
import models.KategoritePatentes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KategoritePatentesRepository extends BaseRepository<KategoritePatentes, CreateKategoritePatentesDto, UpdateKategoritePatentesDto> {
    public KategoritePatentesRepository(){super("kategorite_patentes");}
    public KategoritePatentes fromResultSet(ResultSet result) throws SQLException{
        return KategoritePatentes.getInstance(result);
    }
    public KategoritePatentes create(CreateKategoritePatentesDto kategoritePatentesDto){
        String query = """
                    INSERT INTO
                    KATEGORITE_PATENTES("KATEGORIA","PERSHKRIMI")
                    VALUES(?,?);
                    """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1,kategoritePatentesDto.getKategoria());
            pstm.setString(2,kategoritePatentesDto.getPershkrimi());
            ResultSet res  = pstm.getGeneratedKeys();
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
    public KategoritePatentes update(UpdateKategoritePatentesDto kategoritePatentesDto){return null;}
}
