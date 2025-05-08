package repository;
import models.Dto.user.CreateUserDto;
import models.Dto.user.UpdateUserDto;
import models.Stafi;
import models.User;
import java.sql.*;
import java.util.ArrayList;
public abstract class UserRepository extends BaseRepository<User, CreateUserDto, UpdateUserDto> {

    public UserRepository() {
        super("\"User\"");
    }

    public abstract User fromResultSet(ResultSet result)throws SQLException;
    @Override
    public abstract User create(CreateUserDto createUserDto);

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

    public abstract User findByEmail(String email);
    public abstract User getById(int id);
    public abstract ArrayList<User> getAll();


}
