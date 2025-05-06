package repository;
import models.Dto.pagesat.CreatePagesatDto;

import models.Pagesat;
import models.Dto.pagesat.UpdatePagesatDto;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PagesatRepository extends BaseRepository<Pagesat, CreatePagesatDto, UpdatePagesatDto>{

    public PagesatRepository(){
        super("pagesat");
    }

    public Pagesat fromResultSet(ResultSet result) throws SQLException {
        return Pagesat.getInstance(result);
    }

    public Pagesat create(CreatePagesatDto pagesatDto){
        String query = """
                INSERT INTO 
                Pagesat(ID_Kandidat, Shuma, Data_e_Pageses, Medota_e_Pageses, Statusi_i_Pageses)
                VALUES (?, ?, ?, ?, ?);
                """;
        try{

            PreparedStatement pstm =
                    this.connection.prepareStatement(
                            query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, pagesatDto.getIdKandidat());
            pstm.setDouble(2, pagesatDto.getShuma());
            pstm.setObject(3, pagesatDto.getDataPageses());
            pstm.setString(4, pagesatDto.getMetodaPageses());
            pstm.setString(5, pagesatDto.getStatusiPageses());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if(res.next()){
                int id = res.getInt(1);
                return this.getById(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Pagesat update(UpdatePagesatDto pagesatDto) {
        StringBuilder query = new StringBuilder("UPDATE Pagesat SET ");
        ArrayList<Object> params = new ArrayList<>();
        if (pagesatDto.getShuma() !=0){
            query.append("Shuma=?, ");
            params.add(pagesatDto.getShuma());
        }
        if (pagesatDto.getMetodaPageses()!=null){
            query.append("Metoda_e_Pageses=?, ");
            params.add(pagesatDto.getMetodaPageses());
        }
        if (pagesatDto.getStatusiPageses()!=null){
            query.append("Statusi_i_Pageses=?, ");
        }
        if (params.isEmpty()) {
            return getById(pagesatDto.getId());
        }
        query.setLength(query.length() - 2);//me largu "? "->se paraqet gabim ne sintakse
        query.append(" WHERE id = ?");
        params.add(pagesatDto.getId());
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(pagesatDto.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gabim gjatë përditësimit të pagesave", e);
        }
        return null;
    }
    public ArrayList<Pagesat> findByStatus(String statusi) {
        //Funksion i cili gjen te gjitha pagesat ne baze te statusit
        ArrayList<Pagesat> pagesatList = new ArrayList<>();
        String query = "SELECT * FROM Pagesat WHERE Statusi_i_Pageses = ?";

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, statusi);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Pagesat pagesa = fromResultSet(rs);
                pagesatList.add(pagesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pagesatList;
    }

    public List<Pagesat> getUnpaidPayments() {
        List<Pagesat> pagesat = new ArrayList<>();
        String query = "SELECT * FROM Pagesat WHERE Statusi_i_Pageses = 'Mbetur'";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Pagesat pagesa = Pagesat.getInstance(resultSet);
                pagesat.add(pagesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pagesat;
    }
    public HashMap<String, Integer> getPaymentsLast12Months() {
        HashMap<String, Integer> data = new HashMap<>();

        String sql = "SELECT TO_CHAR(Data_e_Pageses, 'YYYY-MM') AS month, COUNT(*) AS total " +
                "FROM Pagesat " +
                "WHERE Data_e_Pageses >= CURRENT_DATE - INTERVAL '12 MONTH' " +
                "GROUP BY TO_CHAR(Data_e_Pageses, 'YYYY-MM') " +
                "ORDER BY month ASC";

        try (
             PreparedStatement stmt = this.connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String month = rs.getString("month");
                int total = rs.getInt("total");
                data.put(month, total);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
    public void updateStatusiPageses(int pagesaId, String statusiRi) throws SQLException {
        String sql = "UPDATE Pagesat SET Statusi_i_Pageses = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, statusiRi);
            statement.setInt(2, pagesaId);
            statement.executeUpdate();
        }
    }
    public List<Pagesat> loadPagesatData() {
        List<Pagesat> pagesatList = new ArrayList<>();
        String query = "SELECT * FROM Pagesat";
        try (PreparedStatement stmt = this.connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pagesat pagesat = Pagesat.getInstance(rs);
                pagesatList.add(pagesat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagesatList;
    }

    }