package models.Dto.user;

import java.time.LocalDate;

public class SignUpDto {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String password;
    private String confirmPassword;
    private String gjinia;
    private String adresa;

    public SignUpDto(String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String password, String confirmPassword, String gjinia, String adresa) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.gjinia=gjinia;
        this.adresa=adresa;
    }

    public String getGjinia() {
        return gjinia;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
