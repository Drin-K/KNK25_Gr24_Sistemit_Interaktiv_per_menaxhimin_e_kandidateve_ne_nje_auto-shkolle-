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
    public ArrayList<User> findByRole(String role) {
        // Filtron përdoruesit me rolin Staf
        return super.findByRole("Staf");
    }



}