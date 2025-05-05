package repository;


import models.Admin;
import models.User;

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

}