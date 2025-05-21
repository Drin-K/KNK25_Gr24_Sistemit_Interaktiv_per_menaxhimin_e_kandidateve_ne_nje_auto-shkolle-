package models.Dto.admin;


import models.Dto.user.CreateUserDto;

import java.time.LocalDate;

public class CreateAdminDto extends CreateUserDto {
    public CreateAdminDto(String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth,
                          String password, String salt, String adresa, String gjinia) {
        super(name, surname, email, phoneNumber, dateOfBirth, password, salt, "Admin", adresa, gjinia);
    }


}
