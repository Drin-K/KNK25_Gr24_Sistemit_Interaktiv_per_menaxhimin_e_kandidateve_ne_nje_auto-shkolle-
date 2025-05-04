package repository;

import models.Dto.kandidatet.CreateKandidatetDto;
import models.Dto.kandidatet.UpdateKandidatetDto;
import models.Kandidatet;
import models.User;

import java.sql.*;
import java.util.ArrayList;

public class KandidatetRepository extends UserRepository {
    public KandidatetRepository() {
        super();}
    @Override
    public ArrayList<User> findByRole(String role) {
        // Filtron përdoruesit me rolin 'kandidat'
        return super.findByRole("Kandidat");
    }

}