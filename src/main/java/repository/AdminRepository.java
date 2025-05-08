package repository;


import models.Admin;
import models.Dto.user.CreateUserDto;
import models.Kandidatet;
import models.Stafi;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminRepository extends UserRepository {

    public AdminRepository() {
        super();
    }
    @Override
    public Admin create(CreateUserDto dto) {
        String insertUser = """
        INSERT INTO 
        "User"(name, surname, email, phoneNumber, dateOfBirth, hashedPassword, salt, role, adresa, gjinia)
        VALUES (?, ?, ?, ?, ?, ?, ?,'Admin', ?, ?);
    """;

        try {
            PreparedStatement userStmt = this.connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, dto.getName());
            userStmt.setString(2, dto.getSurname());
            userStmt.setString(3, dto.getEmail());
            userStmt.setString(4, dto.getPhoneNumber());
            userStmt.setObject(5, dto.getDateOfBirth());
            userStmt.setString(6, dto.getPassword());
            userStmt.setString(7, dto.getSalt());
            userStmt.setString(8, dto.getRole());
            userStmt.setString(9, dto.getAdresa());
            userStmt.setString(10, dto.getGjinia());

            userStmt.executeUpdate();

            ResultSet res = userStmt.getGeneratedKeys();
            if (res.next()) {
                int userId = res.getInt(1);

                if (dto.getRole().equalsIgnoreCase("Admin")) {
                    String insertAdmin = "INSERT INTO Admin(id) VALUES (?);";
                    PreparedStatement adminStmt = this.connection.prepareStatement(insertAdmin);
                    adminStmt.setInt(1, userId);
                    adminStmt.executeUpdate();
                }

                return this.getById(userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Admin fromResultSet(ResultSet result) throws SQLException {
        return Admin.getInstance(result);
    }
    @Override
    public Admin findByEmail(String email){
        String query = "SELECT *" +
                "FROM Admin s\n" +
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
    @Override
    public Admin getById(int id){
        String query = "SELECT u.id, u.name, u.surname, u.email, u.phoneNumber, " +
                "u.dateOfBirth, u.hashedPassword, u.salt, u.adresa, u.gjinia, " +
                "k.dataRegjistrimi, k.statusiProcesit, u.role " +
                "FROM Admin k " +
                "JOIN \"User\" u ON k.id = u.id " +
                "WHERE u.role = 'Admin' AND u.id = ?";

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