package repository;

import models.Dto.orari.CreateOrariDto;
import models.Dto.orari.UpdateOrariDto;
import models.Orari;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrariRepository extends BaseRepository<Orari, CreateOrariDto, UpdateOrariDto> {
    public OrariRepository(){super("Orari");}
    public Orari fromResultSet(ResultSet result)throws SQLException{
        return Orari.getInstance(result);
    }
    public Orari create(CreateOrariDto orariDto){
        String query= """
                INSERT INTO
                ORARI(ID_KANDIDAT, ID_STAF,DATA_E_SESIONIT, ORA_E_FILLIMIT, ORA_E_PERFUNDIMIT, LLOJI_I_MESIMIT, STATUSI, ID_AUTOMJET)
                VALUES(?,?,?,?,?,?,?,?);
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,orariDto.getIdKandidat());
            pstm.setInt(2,orariDto.getIdStaf());
            pstm.setObject(3,orariDto.getDataSesionit());
            pstm.setObject(4,orariDto.getOraFillimit());
            pstm.setObject(5,orariDto.getOraPerfundimit());
            pstm.setString(6,orariDto.getLlojiMesimit());
            pstm.setString(7,orariDto.getStatusi());
            pstm.setInt(8,orariDto.getIdAutomjet());
            ResultSet res=pstm.getGeneratedKeys();
            if(res.next()){
                int id=res.getInt(1);
                return this.getById(id);}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

        }
        public Orari update(UpdateOrariDto orariDto){return null;}
    }

