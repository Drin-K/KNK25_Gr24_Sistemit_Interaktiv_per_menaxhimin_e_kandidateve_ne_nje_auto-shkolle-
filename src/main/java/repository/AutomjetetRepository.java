package repository;

import models.Automjetet;
import models.Dto.automjetet.CreateAutomjetetDto;
import models.Dto.automjetet.UpdateAutomjetetDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutomjetetRepository extends BaseRepository<Automjetet, CreateAutomjetetDto, UpdateAutomjetetDto> {
    public AutomjetetRepository() {
        super("Automjetet");
    }

    public Automjetet fromResultSet(ResultSet result) throws SQLException {
        return Automjetet.getInstance(result);
    }

    public Automjetet create(CreateAutomjetetDto automjetetDto) {
        String query = """
                INSERT INTO
                Automjetet(Lloji_i_Automjetit,Statusi,ID_Staf,ID_Kategori)
                VALUES (?,?,?,?);
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, automjetetDto.getLlojiAutomjetit());
            pstm.setString(2, automjetetDto.getStatusi());
            pstm.setInt(3, automjetetDto.getIdStaf());
            pstm.setInt(4, automjetetDto.getIdKategori());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Automjetet update(UpdateAutomjetetDto automjetetDto) {
        StringBuilder query = new StringBuilder("UPDATE Automjetet SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (automjetetDto.getIdStaf() != 0) {
            query.append("ID_Staf = ?, ");
            params.add(automjetetDto.getIdStaf());
        }
        if (automjetetDto.getStatusi() != null) {
            query.append("Statusi = ?, ");
            params.add(automjetetDto.getStatusi());
        }
        if (params.isEmpty()) {
            return getById(automjetetDto.getId());
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(automjetetDto.getId());
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int update = pstm.executeUpdate();
            if (update == 1) {
                return this.getById(automjetetDto.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating the vehicle.", e);
        }
        return null;
    }

    public ArrayList<Automjetet> getByStatus(String lloji) {
        //Funksion i cili kthen te gjitha automjetet e nje lloji te caktuar si Veture, Motociklete apo Kamion
        ArrayList<Automjetet> automjetetList = new ArrayList<>();
        String query = "SELECT * FROM Automjetet WHERE Lloji_i_Automjetit = ?  AND Statusi = 'Në përdorim'";

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, lloji);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                automjetetList.add(this.fromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return automjetetList;
    }

    public List<Automjetet> getByLlojiAndStafi(String statusi, int id) {
        List<Automjetet> automjetet = new ArrayList<>();
        String query = "SELECT * FROM AUTOMJETET WHERE ID_STAF = ? AND  STATUSI = ?";
        try {

            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, id);
            pstm.setString(2, statusi);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                automjetet.add(fromResultSet(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return automjetet;
    }
}
