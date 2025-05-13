package models.Dto.kandidatet;

import models.Dto.user.UpdateUserDto;

import java.time.LocalDate;

public class UpdateKandidatetDto extends UpdateUserDto {
    private LocalDate dataRegjistrimi;//
    private String statusiProcesit;

    public UpdateKandidatetDto (int id, String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String password, String salt, String role, String adresa, String gjinia, LocalDate dataRegjistrimi, String statusiProcesit) {
       super(id,name,surname,email,phoneNumber,dateOfBirth,password,salt,role,adresa,gjinia);
       this.dataRegjistrimi=dataRegjistrimi;
       this.statusiProcesit=statusiProcesit;
    }

    public LocalDate getDataRegjistrimi() {
        return dataRegjistrimi;
    }

    public void setDataRegjistrimi(LocalDate dataRegjistrimi) {
        this.dataRegjistrimi = dataRegjistrimi;
    }

    public String getStatusiProcesit() {
        return statusiProcesit;
    }

    public void setStatusiProcesit(String statusiProcesit) {
        this.statusiProcesit = statusiProcesit;
    }
}

