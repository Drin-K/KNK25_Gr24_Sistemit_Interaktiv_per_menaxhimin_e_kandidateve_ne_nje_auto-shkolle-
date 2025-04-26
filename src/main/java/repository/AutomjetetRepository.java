package repository;

import models.Automjetet;
import models.Dto.automjetet.CreateAutomjetetDto;
import models.Dto.automjetet.UpdateAutomjetetDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

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
        public Automjetet update(UpdateAutomjetetDto automjetetDto){
            StringBuilder query = new StringBuilder("UPDATE AUTOMJETET SET ");
            ArrayList<Object> params = new ArrayList<>();

            if(automjetetDto.getIdStaf() != 0){
                query.append("ID_STAF = ?, ");
                params.add(automjetetDto.getIdStaf());
            }
            if(automjetetDto.getStatusi() != null){
                query.append("STATUSI = ?, ");
                params.add(automjetetDto.getStatusi());
            }
            if(params.isEmpty()){
                return getById(automjetetDto.getId());
            }
            query.setLength(query.length() -2);
            query.append(" WHERE ID_AUTOMJET = ?");
            params.add(automjetetDto.getId());
            try{
                PreparedStatement pstm = this.connection.prepareStatement(query.toString());
                for(int i=0;i<params.size();i++){
                    pstm.setObject(i+1,params.get(i));
                }
                int update = pstm.executeUpdate();
                if(update == 1){
                    return this.getById(automjetetDto.getId());
                }
            }
            catch (SQLException e){
                throw new RuntimeException("Gabim gjate perditsimit te automjetit",e);
            }
            return null;
        }
    }
