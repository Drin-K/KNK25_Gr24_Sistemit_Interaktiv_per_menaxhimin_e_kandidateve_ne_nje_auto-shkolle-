package repository;

import models.Automjetet;
import models.Dto.automjetet.CreateAutomjetetDto;
import models.Dto.automjetet.UpdateAutomjetetDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class AutomjetetRepository extends BaseRepository<Automjetet, CreateAutomjetetDto, UpdateAutomjetetDto>{
    public AutomjetetRepository() {super("Automjetet");}
    public Automjetet fromResultSet(ResultSet result) throws SQLException{
        return Automjetet.getInstance(result);
    }
    public Automjetet create(CreateAutomjetetDto automjetetDto){
        String query = """
                   INSERT INTO
                   AUTOMJETET("LLOJI_I_AUTOMJETIT","STATUSI","ID_STAF","ID_KATEGORI")
                   VALUES (?,?,?,?);
                   """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1,automjetetDto.getLlojiAutomjetit());
            pstm.setString(2,automjetetDto.getStatusi());
            pstm.setInt(3,automjetetDto.getIdStaf());
            pstm.setInt(4,automjetetDto.getIdKategori());
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
        public Automjetet update(UpdateAutomjetetDto automjetetDto){
        return null;
        }
    }
