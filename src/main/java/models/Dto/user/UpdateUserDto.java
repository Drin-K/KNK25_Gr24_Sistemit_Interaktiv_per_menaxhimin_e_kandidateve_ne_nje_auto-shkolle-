package models.Dto.user;
import java.time.LocalDate;
public class UpdateUserDto {
    private int idUser;
    private String email;
    private String phoneNumber;
    private String password;
    private String adresa;
    public UpdateUserDto() {} //Shtimi i nje konstruktori pa parametra na sherben per te ndrruar passwordin
//disa prej features per update duhet me i shiku edhe njehere p.sh gjinia :)
    public UpdateUserDto(int id,String email, String phoneNumber, String password, String adresa) {
        this.idUser=id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.adresa = adresa;

    }
    public int getIdUser(){
        return idUser;
    }
    public void setIdUser(int idUser){
        this.idUser=idUser;
    }
    // Getter dhe setter për secilën fushë

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getPassword() {
        return password;
    }



    public String getAdresa() {
        return adresa;
    }


    public void setPassword(String password) {this.password = password;}

}
