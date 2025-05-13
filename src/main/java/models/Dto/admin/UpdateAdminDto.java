package models.Dto.admin;
import models.Dto.user.UpdateUserDto;

import java.time.LocalDate;

public class UpdateAdminDto extends UpdateUserDto {
    public UpdateAdminDto(int id, String email, String phoneNumber,
                          String password, String adresa) {
        super(id, email, phoneNumber, password, adresa);
    }

}
