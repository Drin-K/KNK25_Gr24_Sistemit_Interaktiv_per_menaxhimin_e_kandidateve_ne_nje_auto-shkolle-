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
    @Override
    public ArrayList<User> findByRole(String role) {
        // Filtron përdoruesit me rolin 'kandidat'
        return super.findByRole("Kandidat");
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
    LEFT JOIN Pagesat p ON u.id = p.ID_Kandidat
    LEFT JOIN Testet t ON u.id = t.ID_Kandidat
    LEFT JOIN Regjistrimet r ON u.id = r.ID_Kandidat
    WHERE k.statusiProcesit = ?
      AND (p.Statusi_i_Pageses = ? OR p.Statusi_i_Pageses IS NULL)
      AND (t.Rezultati = ? OR t.Rezultati IS NULL)
      AND (r.Statusi = ? OR r.Statusi IS NULL)
    ORDER BY u.id
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



}