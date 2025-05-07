package repository;

import Database.DBConnector;
import models.Dto.user.CreateUserDto;
import models.Dto.user.UpdateUserDto;
import models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class UserRepository extends BaseRepository<User, CreateUserDto, UpdateUserDto> {

    public UserRepository() {
        super("\"User\"");
    }

    @Override
    public User create(CreateUserDto createUserDto) {
        String query = """
                INSERT INTO 
                \"User\"(name, surname, email, phoneNumber, dateOfBirth, hashedPassword, salt, role, adresa, gjinia)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, createUserDto.getName());
            pstm.setString(2, createUserDto.getSurname());
            pstm.setString(3, createUserDto.getEmail());
            pstm.setString(4, createUserDto.getPhoneNumber());
            pstm.setObject(5, createUserDto.getDateOfBirth());
            pstm.setString(6, createUserDto.getPassword());
            pstm.setString(7, createUserDto.getSalt());
            pstm.setString(8, createUserDto.getRole());
            pstm.setString(9, createUserDto.getAdresa());
            pstm.setString(10, createUserDto.getGjinia());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(UpdateUserDto updateUserDto) {
        StringBuilder query = new StringBuilder("UPDATE \"User\" SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (updateUserDto.getName() != null) {
            query.append("name=?, ");
            params.add(updateUserDto.getName());
        }
        if (updateUserDto.getSurname() != null) {
            query.append("surname=?, ");
            params.add(updateUserDto.getSurname());
        }
        if (updateUserDto.getEmail() != null) {
            query.append("email=?, ");
            params.add(updateUserDto.getEmail());
        }
        if (updateUserDto.getPhoneNumber() != null) {
            query.append("phoneNumber=?, ");
            params.add(updateUserDto.getPhoneNumber());
        }
        if (updateUserDto.getDateOfBirth() != null) {
            query.append("dateOfBirth=?, ");
            params.add(updateUserDto.getDateOfBirth());
        }
        if (updateUserDto.getPassword() != null) {
            query.append("hashedPassword=?, ");
            params.add(updateUserDto.getPassword());
        }
        if (updateUserDto.getSalt() != null) {
            query.append("salt=?, ");
            params.add(updateUserDto.getSalt());
        }
        if (updateUserDto.getRole() != null) {
            query.append("role=?, ");
            params.add(updateUserDto.getRole());
        }
        if (updateUserDto.getAdresa() != null) {
            query.append("adresa=?, ");
            params.add(updateUserDto.getAdresa());
        }
        if (updateUserDto.getGjinia() != null) {
            query.append("gjinia=?, ");
            params.add(updateUserDto.getGjinia());
        }

        if (params.isEmpty()) {
            return null;
        }

        query.setLength(query.length() - 2); //per me largu  ", "
        query.append(" WHERE id = ?");
        params.add(updateUserDto.getIdUser());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(updateUserDto.getIdUser());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    //qetu ma vone duhe me bo reduktim te kodit se po perseriten shume rreshta disa here
//    public ArrayList<User> findByRole(String role) {
//        ArrayList<User> userList = new ArrayList<>();
//        String query = "SELECT * FROM \"User\" WHERE role = ?";//se user osht fjal e rezervume ne postgresql
//
//        try {
//            PreparedStatement pstm = this.connection.prepareStatement(query);
//            pstm.setString(1, role);
//            ResultSet rs = pstm.executeQuery();
//
//            while (rs.next()) {
//                User user = fromResultSet(rs);
//                userList.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return userList;
//    }
    public User findByEmail(String email) {
        String query = "SELECT * FROM \"User\" WHERE email = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return fromResultSet(rs); //e kthejna perdorusin qe u gjet nbaz t dhanave
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public int countByRole(String role) {
        String query = "SELECT COUNT(*) FROM \"User\" WHERE role = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, role);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public HashMap<String, Integer> countKandidatetByStatusiProcesit() {
        HashMap<String, Integer> result = new HashMap<>();
        String query = "SELECT statusiProcesit, COUNT(*) as total FROM Kandidatet GROUP BY statusiProcesit";

        try (PreparedStatement stmt = this.connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();){

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
