package repository;

import models.Dto.raporti_progresit.CreateRaportiProgresitDto;
import models.Dto.raporti_progresit.UpdateRaportiProgresitDto;
import models.RaportiProgresit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RaportiProgresitRepository extends BaseRepository<RaportiProgresit, CreateRaportiProgresitDto, UpdateRaportiProgresitDto>{
    public RaportiProgresitRepository(){super("Raporti_Progresit");}
    public RaportiProgresit fromResultSet(ResultSet result) throws SQLException {
        return RaportiProgresit.getInstance(result);
    }
    public RaportiProgresit create(CreateRaportiProgresitDto raportiProgresitDto){
        String query = """
                INSERT INTO 
                Raporti_Progresit(ID_Kandidat,ID_Staf,Data_Raportit,Piket_Teorike,Piket_Praktike,Komentet,Performanca_Gjenerale)
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
        StringBuilder query = new StringBuilder("UPDATE Raporti_Progresit SET ");
        ArrayList<Object> params = new ArrayList<>();
        if(raportiProgresitDto.getKomentet() != null){
            query.append("Komentet = ?, ");
            params.add(raportiProgresitDto.getKomentet());
        }
        if(raportiProgresitDto.getPiketTeorike() != 0){
            query.append("Piket_Teorike = ?, ");
            params.add(raportiProgresitDto.getPiketTeorike());
        }
        if(raportiProgresitDto.getPiketPraktike() != 0){
            query.append("Piket_Praktike = ?, ");
            params.add(raportiProgresitDto.getPiketTeorike());
        }
        if(raportiProgresitDto.getPerformancaGjenerale() != null){
            query.append("Performanca_Gjenerale = ?, ");
            params.add(raportiProgresitDto.getPerformancaGjenerale());
        }
        if(params.isEmpty()){
            return getById(raportiProgresitDto.getId());
        }
        query.setLength(query.length() -2 );
        query.append(" WHERE id = ?");
        params.add(raportiProgresitDto.getId());
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for(int i=0;i<params.size();i++){
                pstm.setObject(i+1,params.get(i));
            }
            int update = pstm.executeUpdate();
            if(update == 1){
                return this.getById(raportiProgresitDto.getId());
            }
        }
        catch (SQLException e){
            throw new RuntimeException("Gabim gjatë përditësimit të raportit të progresit",e);
        }
        return null;
    }
    public List<RaportiProgresit> getRaportetByStaf(int idStaf,int idKandidat) {
        List<RaportiProgresit> raportet = new ArrayList<>();

        String query = """
        SELECT *
        FROM Raporti_Progresit
        WHERE ID_Staf = ? and ID_Kandidat = ?
    """;

        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setInt(1, idStaf);
            stmt.setInt(2,idKandidat);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    raportet.add(this.fromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching raportet: " + e.getMessage());
            throw new RuntimeException("Failed to fetch progress reports for staff " + idStaf, e);
        }
        System.out.println("Found " + raportet.size() + " reports entries.");
        return raportet;
    }

}
