package repository;

import models.Dto.stafi.CreateStafiDto;
import models.Dto.stafi.UpdateStafiDto;
import models.Kandidatet;
import models.Stafi;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StafiRepository extends UserRepository {
    public StafiRepository() {
        super();
    }
    @Override
    public Stafi fromResultSet(ResultSet result) throws SQLException {
        return Stafi.getInstance(result);
    }
    public int countStafi() {
        String query = "SELECT COUNT(*) FROM Stafi";
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
    public Stafi getByIDstaf(int id) {
        String query = """
        SELECT 
            u.id, 
            u.name, 
            u.surname, 
            u.email, 
            u.phoneNumber, 
            u.dateOfBirth, 
            u.hashedPassword, 
            u.salt, 
            u.adresa, 
            u.gjinia, 
            u.role
        FROM Stafi s
        JOIN "User" u ON s.id = u.id
        WHERE u.role = 'Staf' AND u.id = ?
    """;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return Stafi.getInstance(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Stafi> getAllStafi(){
            ArrayList<Stafi> stafi = new ArrayList<>();
            String query = "SELECT u.id, u.name, u.surname, u.email, u.phoneNumber, u.dateOfBirth, u.hashedPassword, u.salt, u.adresa, u.gjinia, k.dataRegjistrimi, k.statusiProcesit, u.role " +
                    "FROM Stafi k " +
                    "JOIN \"User\" u ON k.id = u.id " +
                    "WHERE u.role = 'Staf'";
            try (
                    PreparedStatement stmt = connection.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Stafi stafi1 = Stafi.getInstance(rs);
                    stafi.add(stafi1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return stafi;

    }
    public String getMostRatedInstructorName() {
        String query = """
      SELECT u.name, u.surname
                  FROM Feedback fb
                  JOIN "User" u ON fb.ID_Staf = u.id
                  WHERE fb.Vleresimi = (SELECT MAX(Vleresimi) FROM Feedback)
                  GROUP BY fb.ID_Staf, u.name, u.surname
                  ORDER BY COUNT(*) DESC
                  LIMIT 1;
                
    """;

        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String emri = rs.getString("name");
                String mbiemri = rs.getString("surname");
                return emri + " " + mbiemri;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getLeastRatedInstructorName() {
        String query = """
        SELECT u.name, u.surname
                            FROM Feedback fb
                            JOIN "User" u ON fb.ID_Staf = u.id
                            WHERE fb.Vleresimi = (SELECT MIN(Vleresimi) FROM Feedback)
                            GROUP BY fb.ID_Staf, u.name, u.surname
                            ORDER BY COUNT(*) DESC
                            LIMIT 1;
                
    """;

        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String emri = rs.getString("name");
                String mbiemri = rs.getString("surname");
                return emri + " " + mbiemri;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Stafi findByEmail(String email){
        String query = "SELECT *" +
                "FROM Stafi s\n" +
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



}