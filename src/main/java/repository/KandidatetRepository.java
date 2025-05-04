package repository;

import models.Dto.kandidatet.CreateKandidatetDto;
import models.Dto.kandidatet.UpdateKandidatetDto;
import models.Kandidatet;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

}