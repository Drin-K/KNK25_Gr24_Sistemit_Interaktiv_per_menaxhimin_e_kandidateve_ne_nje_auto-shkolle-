package repository;

import models.Dto.stafi.CreateStafiDto;
import models.Dto.stafi.UpdateStafiDto;
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

}