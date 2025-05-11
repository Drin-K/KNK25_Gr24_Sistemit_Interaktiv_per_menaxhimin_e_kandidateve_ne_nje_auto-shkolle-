package repository;

import models.Dto.orari.CreateOrariDto;
import models.Dto.orari.UpdateOrariDto;
import models.Orari;
import models.Pagesat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
                Orari(ID_Kandidat, ID_Staf, Data_e_Sesionit, Ora_e_Fillimit, Ora_e_Perfundimit, Lloji_i_Mesimit, Statusi, ID_Automjet)
                VALUES(?,?,?,?,?,?,?,?)
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
    public Orari update(UpdateOrariDto orariDto){
        StringBuilder query = new StringBuilder("UPDATE Orari SET ");
        ArrayList<Object> params = new ArrayList<>();
        if (orariDto.getDataSesionit()!=null){
            query.append("Data_e_Sesionit=?, ");
            params.add(orariDto.getDataSesionit());
        }
        if (orariDto.getOraFillimit()!=null){
            query.append("Ora_e_Fillimit=?, ");
            params.add(orariDto.getOraFillimit());
        }
        if (orariDto.getOraPerfundimit()!=null){
            query.append("Ora_e_perfundimit=?, ");
            params.add(orariDto.getOraPerfundimit());
        }
        if (orariDto.getLlojiMesimit()!=null){
            query.append("Lloji_i_Mesimit=?, ");
            params.add(orariDto.getLlojiMesimit());
        }
        if (orariDto.getStatusi()!=null){
            query.append("Statusi=?, ");
            params.add(orariDto.getStatusi());
        }
        if (params.isEmpty()) {
            return getById(orariDto.getIdSesioni());
        }
        query.setLength(query.length() - 2);//me largu "? "->se paraqet gabim ne sintakse
        query.append(" WHERE id = ?");
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
            throw new RuntimeException("Gabim gjatë përditësimit të orarit", e);
        }
        return null;}

    //na duhet te service ->
    public List<Orari> gjejOraretPerDate(LocalDate data) {
        String query = "SELECT * FROM ORARI WHERE Data_e_Sesionit = ?";
        List<Orari> oraret = new ArrayList<>();

        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setObject(1, data);
            ResultSet res = pstm.executeQuery();

            while (res.next()) {
                Orari orar = fromResultSet(res);
                oraret.add(orar);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gabim gjatë kërkimit të orareve për datën: " + data, e);
        }

        return oraret;
    }


    public List<Orari> gjejOraretPerId(int id) {
        String query = "SELECT * FROM Orari WHERE ID_Kandidat = ?";
        List<Orari> oraret = new ArrayList<>();

        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();

            while (res.next()) {
                Orari orar = fromResultSet(res);
                oraret.add(orar);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nuk mund të lexohen oraret nga databaza për kolonën me ID: " + id, e);
        }

        return oraret;
    }

    public int numeroSesione(int idKandidat, String llojiMesimit, String statusi) {
        String query = "SELECT COUNT(*) FROM Orari WHERE ID_Kandidat = ? AND Lloji_i_Mesimit = ? AND Statusi = ?";
        int numri = 0;

        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, idKandidat);
            pstm.setString(2, llojiMesimit);
            pstm.setString(3, statusi);
            ResultSet res = pstm.executeQuery();

            if (res.next()) {
                numri = res.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gabim gjatë numërimit të sesioneve për kandidat: " + idKandidat, e);
        }

        return numri;
    }



}

