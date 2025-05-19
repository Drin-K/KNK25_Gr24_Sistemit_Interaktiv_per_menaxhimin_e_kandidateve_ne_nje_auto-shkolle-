package models.Dto.kandidatet;

import models.Dto.user.CreateUserDto;

import java.time.LocalDate;


public class CreateKandidatetDto extends CreateUserDto {

    public CreateKandidatetDto(String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth,
                               String password, String salt, String adresa, String gjinia) {
        super(name, surname, email, phoneNumber, dateOfBirth, password, salt, "Kandidat", adresa, gjinia);
    }
}
