package models.Dto.kandidatet;

import models.Dto.user.CreateUserDto;

import java.time.LocalDate;


public class CreateKandidatetDto extends CreateUserDto {
    private LocalDate dataRegjistrimit;
    private String statusiProcesit;

    public CreateKandidatetDto(String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth,
                               String password, String salt, String adresa, String gjinia,
                               LocalDate dataRegjistrimit, String statusiProcesit) {
        super(name, surname, email, phoneNumber, dateOfBirth, password, salt, "Kandidat", adresa, gjinia);
        this.dataRegjistrimit = dataRegjistrimit;
        this.statusiProcesit = statusiProcesit;
    }

    public LocalDate getDataRegjistrimit() {
        return dataRegjistrimit;
    }

    public void setDataRegjistrimit(LocalDate dataRegjistrimit) {
        this.dataRegjistrimit = dataRegjistrimit;
    }

    public String getStatusiProcesit() {
        return statusiProcesit;
    }

    public void setStatusiProcesit(String statusiProcesit) {
        this.statusiProcesit = statusiProcesit;
    }
}
