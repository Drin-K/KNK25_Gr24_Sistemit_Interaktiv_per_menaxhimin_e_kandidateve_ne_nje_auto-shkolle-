package models.Dto.stafi;

import models.Dto.user.UpdateUserDto;

import java.time.LocalDate;

public class UpdateStafiDto extends UpdateUserDto {


    public UpdateStafiDto(int id,  String email, String phoneNumber,
                          String password,  String adresa) {
        super(id,  email, phoneNumber, password, adresa);

    }
}
