package repository;

import models.Dto.kandidatet.UpdateKandidatetDto;
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

    public abstract User fromResultSet(ResultSet result) throws SQLException;

    @Override
    public abstract User create(CreateUserDto createUserDto);

    @Override
    public User update(UpdateUserDto updateUserDto) {
        StringBuilder query = new StringBuilder("UPDATE \"User\" SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (updateUserDto.getEmail() != null) {
            query.append("email=?, ");
            params.add(updateUserDto.getEmail());
        }
        if (updateUserDto.getPhoneNumber() != null) {
            query.append("phoneNumber=?, ");
            params.add(updateUserDto.getPhoneNumber());
        }
        if (updateUserDto.getPassword() != null) {
            query.append("hashedPassword=?, ");
            params.add(updateUserDto.getPassword());
        }
        if (updateUserDto.getSalt() != null) {
            query.append("salt=?, ");
            params.add(updateUserDto.getSalt());
        }
        if (updateUserDto.getAdresa() != null) {
            query.append("adresa=?, ");
            params.add(updateUserDto.getAdresa());
        }
        if (params.isEmpty()) {
            return null;
        }

        // Heqim ", "
        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(updateUserDto.getIdUser());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();

            if (updated == 1) {
                if (updateUserDto.getEmail().endsWith("@kandidat.com")) {
                    if (updateUserDto instanceof UpdateKandidatetDto) {
                        UpdateKandidatetDto kandidatetDto = (UpdateKandidatetDto) updateUserDto;
                        updateKandidatetDetails(kandidatetDto);
                    } else {
                        System.err.println("This is an error, the object type is not UpdateKandidatetDto.");
                    }
                }
                return this.getById(updateUserDto.getIdUser());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void updateKandidatetDetails(UpdateKandidatetDto updateUserDto) throws SQLException {
        StringBuilder kandidatiQuery = new StringBuilder("UPDATE Kandidatet SET ");
        ArrayList<Object> kandidatiParams = new ArrayList<>();

        if ((updateUserDto.getDataRegjistrimi() != null)) {
            kandidatiQuery.append("dataRegjistrimi=?, ");
            kandidatiParams.add(updateUserDto.getDataRegjistrimi());
        }
        if (updateUserDto.getStatusiProcesit() != null) {
            kandidatiQuery.append("statusiProcesit=?, ");
            kandidatiParams.add(updateUserDto.getStatusiProcesit());
        }

        if (!kandidatiParams.isEmpty()) {
            kandidatiQuery.setLength(kandidatiQuery.length() - 2);
            kandidatiQuery.append(" WHERE id = ?");
            kandidatiParams.add(updateUserDto.getIdUser());

            PreparedStatement pstmKandidati = this.connection.prepareStatement(kandidatiQuery.toString());
            for (int i = 0; i < kandidatiParams.size(); i++) {
                pstmKandidati.setObject(i + 1, kandidatiParams.get(i));
            }
            pstmKandidati.executeUpdate();
        }
    }


    public abstract User findByEmail(String email);

    public abstract User getById(int id);

    public abstract ArrayList<User> getAll();

}
