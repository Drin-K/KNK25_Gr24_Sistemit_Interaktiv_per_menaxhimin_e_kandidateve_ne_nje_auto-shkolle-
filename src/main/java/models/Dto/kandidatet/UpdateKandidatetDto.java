package models.Dto.kandidatet;

import models.Dto.user.UpdateUserDto;

import java.time.LocalDate;

public class UpdateKandidatetDto extends UpdateUserDto {
    private LocalDate dataRegjistrimi;
    private String statusiProcesit;

    public UpdateKandidatetDto (int id, String email, String phoneNumber, String password,String adresa, LocalDate dataRegjistrimi, String statusiProcesit) {
       super(id,email,phoneNumber,password,adresa);
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

