package models.Dto.user;
import java.time.LocalDate;
public class UpdateUserDto {
    private int idUser;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String password; // Opcionale: e shikojm serish nese dojna me mundsu me perditsu passwordin
    private String salt; // Opcionale: gjithashtu si me lart
    private String role;
    private String adresa;
    private String gjinia;
    public UpdateUserDto() {} //Shtimi i nje konstruktori pa parametra na sherben per te ndrruar passwordin
//disa prej features per update duhet me i shiku edhe njehere p.sh gjinia :)
    public UpdateUserDto(int id,String name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, String password, String salt, String role, String adresa, String gjinia) {
        this.idUser=id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.salt = salt;
        this.role = role;
        this.adresa = adresa;
        this.gjinia = gjinia;
    }
    public int getIdUser(){
        return idUser;
    }
    public void setIdUser(int idUser){
        this.idUser=idUser;
    }
    // Getter dhe setter për secilën fushë
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

    public String getSalt() {
        return salt;
    }

    public String getRole() {
        return role;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getGjinia() {
        return gjinia;
    }

    public void setPassword(String password) {this.password = password;}

    public void setSalt(String salt) {this.salt = salt;}
}
