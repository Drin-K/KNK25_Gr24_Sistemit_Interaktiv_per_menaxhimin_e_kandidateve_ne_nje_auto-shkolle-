package repository;

import models.Dto.kandidatet.CreateKandidatetDto;
import models.Dto.kandidatet.UpdateKandidatetDto;
import models.Kandidatet;
import models.User;

import java.sql.*;
import java.util.*;

public class KandidatetRepository extends UserRepository {

    public KandidatetRepository() {
        super();}

    public ArrayList<Kandidatet> findByRole(String role) {
        // Filtron përdoruesit me rolin 'kandidat'
        return this.findByRole("Kandidat");
    }
    @Override
    public Kandidatet fromResultSet(ResultSet result) throws SQLException {
        return Kandidatet.getInstance(result);
    }
    public HashMap<String, Integer> getRegistrationsLast12Months() throws SQLException {
        HashMap<String, Integer> data = new HashMap<>();

        String query = "SELECT TO_CHAR(dataRegjistrimi, 'YYYY-MM') AS month, COUNT(*) AS total " +
                "FROM Kandidatet " +
                "WHERE dataRegjistrimi >= CURRENT_DATE - INTERVAL '12 MONTH' " +
                "GROUP BY TO_CHAR(dataRegjistrimi, 'YYYY-MM') " +
                "ORDER BY month ASC";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String muaji = rs.getString("month");
                int totali = rs.getInt("total");
                data.put(muaji, totali);
            }
        }

        return data;
    }
    public List<Kandidatet> gjejKandidatetMeTeDrejte(boolean statusiProcesit, boolean pagesa, boolean testet, boolean regjistrimi) throws SQLException {
        String sql = """
            SELECT DISTINCT ON (u.id)
                   u.id, u.name, u.surname, u.email, u.phoneNumber, u.dateOfBirth,
                   u.hashedPassword, u.salt, u.role, u.adresa, u.gjinia,
                   k.dataRegjistrimi, k.statusiProcesit
            FROM "User" u
            JOIN Kandidatet k ON u.id = k.id
            JOIN Pagesat p ON u.id = p.ID_Kandidat
            JOIN Testet t ON u.id = t.ID_Kandidat
            JOIN Regjistrimet r ON u.id = r.ID_Kandidat
            LEFT JOIN Patenta pa ON u.id = pa.ID_Kandidat AND pa.Statusi = 'E lëshuar'
            WHERE pa.ID_Kandidat IS NULL
              AND k.statusiProcesit = ?
              AND p.Statusi_i_Pageses = ?
              AND t.Rezultati = ?
              AND r.Statusi = ?
            ORDER BY u.id;
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Vendosja e parametrave për filtrim në query
            stmt.setString(1, statusiProcesit ? "Përfunduar" : "Në proces");
            stmt.setString(2, pagesa ? "Paguar" : "Mbetur");
            stmt.setString(3, testet ? "Kaluar" : "Dështuar");
            stmt.setString(4, regjistrimi ? "Përfunduar" : "Në proces");

            ResultSet rs = stmt.executeQuery();
            List<Kandidatet> kandidatet =new ArrayList<>();


            while (rs.next()) {
                int id = rs.getInt("id");
                // Kontrollojmë nëse kandidati është shtuar më parë

                Kandidatet kandidat = Kandidatet.getInstance(rs); // Merr instancën nga ResultSet
                kandidatet.add(kandidat);

            }

            return kandidatet;
        }
    }
    public void updateStatusiProcesit(int kandidatId, String statusi) throws SQLException {
        String sql = "UPDATE Kandidatet SET statusiProcesit = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, statusi);
            statement.setInt(2, kandidatId);
            statement.executeUpdate();
        }
    }

    public ArrayList<Kandidatet> getAllKandidatet() {
        ArrayList<Kandidatet> kandidatet = new ArrayList<>();
        String query = "SELECT u.id, u.name, u.surname, u.email, u.phoneNumber, u.dateOfBirth, u.hashedPassword, u.salt, u.adresa, u.gjinia, k.dataRegjistrimi, k.statusiProcesit, u.role " +
                "FROM Kandidatet k " +
                "JOIN \"User\" u ON k.id = u.id " +
                "WHERE u.role = 'Kandidat'";
        try (
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Kandidatet kandidat = Kandidatet.getInstance(rs);
                kandidatet.add(kandidat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kandidatet;
    }

    public int countKandidatet() {
        String query = "SELECT COUNT(*) FROM Kandidatet";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Kandidatet getbyID(int id){
        String query = "SELECT u.id, u.name, u.surname, u.email, u.phoneNumber, " +
                "u.dateOfBirth, u.hashedPassword, u.salt, u.adresa, u.gjinia, " +
                "k.dataRegjistrimi, k.statusiProcesit, u.role " +
                "FROM Kandidatet k " +
                "JOIN \"User\" u ON k.id = u.id " +
                "WHERE u.role = 'Kandidat' AND u.id = ?";

        try{
            PreparedStatement pstm = this.connection.prepareStatement(
                    query);
            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();
            if(res.next()){
                return this.fromResultSet(res);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}