package repository;

import models.Dto.kategorite_patentes.CreateKategoritePatentesDto;
import models.Dto.kategorite_patentes.UpdateKategoritePatentesDto;
import models.KategoritePatentes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            pstm.execute();
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
    public KategoritePatentes update(UpdateKategoritePatentesDto kategoritePatentesDto){
        StringBuilder query = new StringBuilder("UPDATE KATEGORITE_PATENTES SET ");
        ArrayList<Object> params = new ArrayList<>();
        if(kategoritePatentesDto.getPershkrimi() != null){
            query.append("PERSHKRIMI = ?, ");
            params.add(kategoritePatentesDto.getPershkrimi());
        }
        if(params.isEmpty()){
            return getById(kategoritePatentesDto.getId());
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE ID_KATEGORI = ?");
        params.add(kategoritePatentesDto.getId());
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for(int i=0;i<params.size();i++){
                pstm.setObject(i+1,params.get(i));
            }
            int update = pstm.executeUpdate();
            if(update == 1){
                return this.getById(kategoritePatentesDto.getId());
            }
        }
        catch (SQLException e){
            throw new RuntimeException("Gabim gjate perditsimit te kategorise_patentes",e);
        }
        return null;
    }
}
