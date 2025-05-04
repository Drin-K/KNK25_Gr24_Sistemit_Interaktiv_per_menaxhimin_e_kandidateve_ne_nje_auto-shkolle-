package repository;


import models.User;

import java.util.ArrayList;

public class AdminRepository extends UserRepository {

    public AdminRepository() {
        super();
    }
    @Override
    public ArrayList<User> findByRole(String role) {
        // Filtron perdorusit me rolin 'Admin'
        return super.findByRole("Admin");
    }

}