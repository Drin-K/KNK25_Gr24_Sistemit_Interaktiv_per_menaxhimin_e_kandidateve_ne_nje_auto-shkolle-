package models.Dto.admin;
import models.Dto.user.UpdateUserDto;

import java.time.LocalDate;

public class UpdateAdminDto extends UpdateUserDto {
    public UpdateAdminDto(int id, String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth,
                          String password, String salt, String role, String adresa, String gjinia) {
        super(id, name, surname, email, phoneNumber, dateOfBirth, password, salt, role, adresa, gjinia);
    }

}
