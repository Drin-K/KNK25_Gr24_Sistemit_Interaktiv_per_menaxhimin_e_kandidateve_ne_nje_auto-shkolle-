package repository;

import models.Dto.orari.CreateOrariDto;
import models.Dto.orari.UpdateOrariDto;
import models.Orari;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        public Orari update(UpdateOrariDto orariDto){
            StringBuilder query = new StringBuilder("UPDATE KANDIDATET SET ");
            ArrayList<Object> params = new ArrayList<>();
            if (orariDto.getDataSesionit()!=null){
                query.append("data_e_sesionit=?, ");
                params.add(orariDto.getDataSesionit());
            }
            if (orariDto.getOraFillimit()!=null){
                query.append("ora_e_fillimit=?, ");
                params.add(orariDto.getOraFillimit());
            }
            if (orariDto.getOraPerfundimit()!=null){
                query.append("ora_e_perfundimit=?, ");
                params.add(orariDto.getOraPerfundimit());
            }
            if (orariDto.getLlojiMesimit()!=null){
                query.append("lloji_i_mesimit=?, ");
                params.add(orariDto.getLlojiMesimit());
            }
            if (orariDto.getStatusi()!=null){
                query.append("statusi=?, ");
                params.add(orariDto.getStatusi());
            }
            if (params.isEmpty()) {
                return getById(orariDto.getIdSesioni());
            }
            query.setLength(query.length() - 2);//me largu "? "->se paraqet gabim ne sintakse
            query.append(" WHERE id_sesioni = ?");
            params.add(orariDto.getIdSesioni());
            try {
                PreparedStatement pstm = this.connection.prepareStatement(query.toString());
                for (int i = 0; i < params.size(); i++) {
                    pstm.setObject(i + 1, params.get(i));
                }
                int updated = pstm.executeUpdate();
                if (updated == 1) {
                    return this.getById(orariDto.getIdSesioni());
                }
            } catch (SQLException e) {
                throw new RuntimeException("Gabim gjatë përditësimit të kandidatit", e);
            }
        return null;}

//na duhet te repository ->
    public List<Orari> gjejOraretPerId(String columnName, int id) {
        String query = String.format("SELECT * FROM ORARI WHERE %s = ?", columnName);
        List<Orari> oraret = new ArrayList<>();

        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();

            while (res.next()) {
                Orari orar = fromResultSet(res);
                oraret.add(orar);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nuk mund të lexohen oraret nga databaza për kolonën: " + columnName + " me ID: " + id, e);
        }

        return oraret;
    }


}

