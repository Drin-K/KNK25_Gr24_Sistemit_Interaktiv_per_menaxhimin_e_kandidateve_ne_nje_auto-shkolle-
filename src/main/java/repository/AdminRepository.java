package repository;


import models.Admin;
import models.Stafi;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminRepository extends UserRepository {

    public AdminRepository() {
        super();
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

}