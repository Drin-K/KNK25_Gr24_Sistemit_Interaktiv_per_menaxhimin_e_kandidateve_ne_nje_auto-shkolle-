package repository;

import models.Dto.pagesat.CreatePagesatDto;

import models.Pagesat;
import models.Dto.pagesat.UpdatePagesatDto;


import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PagesatRepository extends BaseRepository<Pagesat, CreatePagesatDto, UpdatePagesatDto> {

    public PagesatRepository() {
        super("pagesat");
    }

    public Pagesat fromResultSet(ResultSet result) throws SQLException {
        return Pagesat.getInstance(result);
    }

    public Pagesat create(CreatePagesatDto pagesatDto) {
        String query = """
                INSERT INTO 
                Pagesat(ID_Kandidat,Numri_Xhirollogarise,Shuma,Data_e_Pageses,Metoda_e_Pageses,Statusi_i_Pageses)
                VALUES (?,?,?,?,?,?);
                """;
        try {

            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, pagesatDto.getIdKandidat());
            pstm.setString(2, pagesatDto.getNumriXhirollogarise());
            pstm.setDouble(3, pagesatDto.getShuma());
            pstm.setObject(4, pagesatDto.getDataPageses());
            pstm.setString(5, pagesatDto.getMetodaPageses());
            pstm.setString(6, pagesatDto.getStatusiPageses());
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

    public Pagesat update(UpdatePagesatDto pagesatDto) {
        StringBuilder query = new StringBuilder("UPDATE Pagesat SET ");
        ArrayList<Object> params = new ArrayList<>();
        if (pagesatDto.getShuma() != 0) {
            query.append("Shuma=?, ");
            params.add(pagesatDto.getShuma());
        }
        if (pagesatDto.getNumriXhirollogarise() != null) {
            query.append("Numri_Xhirollogarise=?, ");
            params.add(pagesatDto.getNumriXhirollogarise());
        }
        if (pagesatDto.getIdKandidat() != 0) {
            query.append("ID_Kandidat=?, ");
            params.add(pagesatDto.getIdKandidat());
        }
        if (pagesatDto.getMetodaPageses() != null) {
            query.append("Metoda_e_Pageses=?, ");
            params.add(pagesatDto.getMetodaPageses());
        }
        if (pagesatDto.getStatusiPageses() != null) {
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
            throw new RuntimeException("Error updating the payments.", e);
        }
        return null;
    }


    public List<Pagesat> getUnpaidPayments() {
        List<Pagesat> pagesat = new ArrayList<>();
        String query = "SELECT * FROM Pagesat WHERE Statusi_i_Pageses = 'Mbetur'";

        try{PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Pagesat pagesa = fromResultSet(resultSet);
                pagesat.add(pagesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pagesat;
    }

    public HashMap<String, Integer> getPayments() {
        HashMap<String, Integer> data = new HashMap<>();

        String sql = "SELECT TO_CHAR(Data_e_Pageses, 'YYYY-MM') AS month, COUNT(*) AS total " +
                "FROM Pagesat " +
                "GROUP BY TO_CHAR(Data_e_Pageses, 'YYYY-MM') " +
                "ORDER BY month ASC";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
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

    public void updateStatusiPageses(int pagesaId, String statusiRi){
        String sql = "UPDATE Pagesat SET Statusi_i_Pageses = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, statusiRi);
            statement.setInt(2, pagesaId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pagesat> filterPagesat(String name, LocalDate fromDate, LocalDate toDate, String metodaPageses, String statusiPageses) throws SQLException {
        String sql = "SELECT * FROM Pagesat p " +
                "JOIN Kandidatet k ON p.ID_Kandidat = k.id " +
                "JOIN \"User\" u ON k.id = u.id " +
                "WHERE u.name LIKE ? " +
                "AND p.Data_e_Pageses BETWEEN ? AND ? " +
                "AND p.Metoda_e_Pageses = ? " +
                "AND p.Statusi_i_Pageses = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            statement.setObject(2, fromDate);
            statement.setObject(3, toDate);
            statement.setString(4, metodaPageses);
            statement.setString(5, statusiPageses);

            ResultSet rs = statement.executeQuery();
            List<Pagesat> pagesatList = new ArrayList<>();
            while (rs.next()) {
                Pagesat pagesat = fromResultSet(rs);
                pagesatList.add(pagesat);
            }
            return pagesatList;

        } catch (SQLException e) {
            System.err.println("Error executing the SQL query: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public int countPagesatOnDate(LocalDate date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Pagesat WHERE Data_e_Pageses = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setObject(1,date);
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getInt(1) : 0;
    }

    public int countPagesatInMonth(YearMonth month) throws SQLException {
        LocalDate start = month.atDay(1);
        LocalDate end = month.atEndOfMonth();
        String sql = "SELECT COUNT(*) FROM Pagesat WHERE Data_e_Pageses BETWEEN ? AND ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setObject(1, start);
        stmt.setObject(2, end);
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getInt(1) : 0;
    }

    public int countPagesatInYear(int year) throws SQLException {
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        String sql = "SELECT COUNT(*) FROM Pagesat WHERE Data_e_Pageses BETWEEN ? AND ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setObject(1, start);
        stmt.setObject(2, end);
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getInt(1) : 0;
    }

    public Map<String, Integer> getPagesatCountByStatus() {
        Map<String, Integer> result = new HashMap<>();
        String query = "SELECT Statusi_i_Pageses, COUNT(*) AS total FROM Pagesat GROUP BY Statusi_i_Pageses";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String status = resultSet.getString("Statusi_i_Pageses");
                int count = resultSet.getInt("total");
                result.put(status, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}