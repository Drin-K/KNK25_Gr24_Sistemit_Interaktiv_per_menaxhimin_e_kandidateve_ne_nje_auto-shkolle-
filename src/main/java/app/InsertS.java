package app;

import models.Dto.user.CreateUserDto;
import repository.AdminRepository;
import repository.StafiRepository;
import services.PasswordHasher;

import java.time.LocalDate;

public class InsertS {
    public static void main(String[] args) {
    String name = "Drin";
    String surname = "Kurti";
    String email = "drin.kurti@admin.com";
    String phone = "045879237";
    String address = "Qaber";
    String gender = "M";
    LocalDate dob = LocalDate.of(1995, 5, 20);
    String password = "12345678";


    String salt = PasswordHasher.generateSalt();
    String hashedPassword = PasswordHasher.generateSaltedHash(password, salt);


    CreateUserDto user = new CreateUserDto(
            name,
            surname,
            email,
            phone,
            dob,
            hashedPassword,
            salt,
            "Admin",
            address,
            gender
    );
        AdminRepository adminRepository=new AdminRepository();
        adminRepository.create(user);

}}
