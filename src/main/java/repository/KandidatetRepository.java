package repository;
import models.Dto.user.CreateUserDto;
import models.Kandidatet;
import models.User;

import java.sql.*;
import java.util.*;

public class KandidatetRepository extends UserRepository {

    public KandidatetRepository() {
        super();}
    @Override
    public Kandidatet create(CreateUserDto dto) {
        String insertUser = """
            INSERT INTO "User"(name, surname, email, phoneNumber, dateOfBirth, hashedPassword, salt, role, adresa, gjinia)
            VALUES (?, ?, ?, ?, ?, ?, ?, 'Kandidat', ?, ?);
        """;
        try {
            PreparedStatement userStmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, dto.getName());
            userStmt.setString(2, dto.getSurname());
            userStmt.setString(3, dto.getEmail());
            userStmt.setString(4, dto.getPhoneNumber());
            userStmt.setObject(5, dto.getDateOfBirth());
            userStmt.setString(6, dto.getPassword());
            userStmt.setString(7, dto.getSalt());
            userStmt.setString(8, dto.getAdresa());
            userStmt.setString(9, dto.getGjinia());
            userStmt.execute();

            ResultSet keys = userStmt.getGeneratedKeys();
            if (keys.next()) {
                int userId = keys.getInt(1);

                String insertKandidat = """
                    INSERT INTO Kandidatet(id, dataRegjistrimi, statusiProcesit)
                    VALUES (?, DEFAULT, 'Në proces');
                """;
                PreparedStatement kandidatStmt = connection.prepareStatement(insertKandidat);
                kandidatStmt.setInt(1, userId);
                kandidatStmt.execute();
                return getById(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Kandidatet fromResultSet(ResultSet result) throws SQLException {
        return Kandidatet.getInstance(result);
    }
    public HashMap<String, Integer> getAllRegistrationsGroupedByMonth() throws SQLException {
        HashMap<String, Integer> data = new HashMap<>();

        String query = "SELECT TO_CHAR(dataRegjistrimi, 'YYYY-MM') AS month, COUNT(*) AS total " +
                "FROM Kandidatet " +
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



    public ArrayList<User> getAll() {
        ArrayList<User> kandidatet = new ArrayList<>();
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
    //Shembull perfekt se qysh ni metod joabstrakte-> e kthejme ne metode abstrakte ne
    //userrepository dhe me pas e implementojme ne stafirepository !!!
    // !!!!!Isolating Specific Logic!!!!     :) :) :)
    @Override
    public Kandidatet getById(int id){
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

    public List<Kandidatet> shfaqKandidatetMeTeDrejte() {
        List<Kandidatet> kandidatet = new ArrayList<>();
        String sql = """
                SELECT DISTINCT k.id, k.dataRegjistrimi, k.statusiProcesit,\s
                                u.name, u.surname, u.email, u.phoneNumber, u.dateOfBirth,\s
                                u.hashedPassword, u.salt, u.adresa, u.gjinia,\s
                                p1.Statusi AS StatusiPatentes\s
                FROM Kandidatet k\s
                JOIN "User" u ON u.id = k.id\s
                JOIN Pagesat p2 ON p2.ID_Kandidat = k.id\s
                JOIN Regjistrimet r ON r.ID_Kandidat = k.id\s
                JOIN Testet t ON t.ID_Kandidat = k.id\s
                JOIN Patenta p1 ON p1.ID_Kandidat = k.id\s
                WHERE k.statusiProcesit = 'Përfunduar'\s
                AND r.Statusi = 'Përfunduar'\s
                AND t.Rezultati = 'Kaluar'\s
                AND p1.Statusi = 'Në proces'
                AND p2.Statusi_i_Pageses IN ('Paguar');
                
        """;

            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
                kandidatet.add(Kandidatet.getInstance(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kandidatet;
    }
    public List<Kandidatet> shfaqKandidatetMeTeDrejtePaPagesa() {
        List<Kandidatet> kandidatet = new ArrayList<>();
        String query = """
                SELECT DISTINCT k.id, k.dataRegjistrimi, k.statusiProcesit,\s
                                u.name, u.surname, u.email, u.phoneNumber, u.dateOfBirth,\s
                                u.hashedPassword, u.salt, u.adresa, u.gjinia,\s
                                p1.Statusi AS StatusiPatentes\s
                FROM Kandidatet k\s
                JOIN "User" u ON u.id = k.id\s
                JOIN Pagesat p2 ON p2.ID_Kandidat = k.id\s
                JOIN Regjistrimet r ON r.ID_Kandidat = k.id\s
                JOIN Testet t ON t.ID_Kandidat = k.id\s
                JOIN Patenta p1 ON p1.ID_Kandidat = k.id\s
                WHERE k.statusiProcesit = 'Përfunduar'\s
                AND r.Statusi = 'Përfunduar'\s
                AND t.Rezultati = 'Kaluar'\s
                AND p1.Statusi = 'Në proces'
                AND p2.Statusi_i_Pageses IN ('Mbetur', 'Pjesërisht');
                
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
                kandidatet.add(Kandidatet.getInstance(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kandidatet;
    }
    @Override
    public Kandidatet findByEmail(String email){
        String query = "SELECT *" +
                "FROM Kandidatet s\n" +
                "JOIN \"User\" u ON s.id = u.id\n" +
                "WHERE u.email = ?;";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return fromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    //e kam perdor te AdminHomeController me percaktu numrin e kandidateve sipas statusit te procesit
    //sa jane me status 'Në proces' e sa 'Përfunduar'
    public HashMap<String, Integer> countKandidatetByStatusiProcesit() {
        HashMap<String, Integer> result = new HashMap<>();
        String query = "SELECT statusiProcesit, COUNT(*) as total FROM Kandidatet GROUP BY statusiProcesit";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String status = rs.getString("statusiProcesit");
                int count = rs.getInt("total");
                result.put(status, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}