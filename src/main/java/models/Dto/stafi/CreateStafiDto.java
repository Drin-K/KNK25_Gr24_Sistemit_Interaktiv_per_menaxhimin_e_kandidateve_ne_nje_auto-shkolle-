package models.Dto.stafi;

import models.Dto.user.CreateUserDto;

import java.time.LocalDate;

public class CreateStafiDto extends CreateUserDto {

    public CreateStafiDto(String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth,
                          String password, String salt, String role, String adresa, String gjinia) {
        super(name, surname, email, phoneNumber, dateOfBirth, password, salt, "Staf", adresa, gjinia);
    }
}
