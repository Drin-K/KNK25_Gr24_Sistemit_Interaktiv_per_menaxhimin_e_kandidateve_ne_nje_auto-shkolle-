package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.kategorite_patentes.CreateKategoritePatentesDto;
import models.Dto.regjistrimet.CreateRegjistrimetDto;
import models.KategoritePatentes;
import models.Regjistrimet;
import services.KandidateService;
import services.KategoritePatentesService;
import services.RegjistrimiService;
import services.UserContext;

//
//CreateRegjistrimetDto dto = new CreateRegjistrimetDto(this.kandidatId, /*kategoritePatentesService.getId()*/, statusi);
////              Regjistrimet result = regjistrimiService.create(dto);
////              System.out.printf("Category inserted successfuly !");
////              System.out.println("Kategoria ID: " + result.getId());
public class categoryConsumatorController {
    private int kategoriId;
    private final RegjistrimiService regjistrimiService;
    private final KategoritePatentesService kategoritePatentesService;
    private final int kandidatId = UserContext.getUserId();
    private final String statusi = "Në proces";
    public categoryConsumatorController(){
        this.regjistrimiService = new RegjistrimiService();
        this.kategoritePatentesService = new KategoritePatentesService();
    }

    @FXML
    private void applyClickA(){
        this.kategoriId = 1;
        try{
              CreateRegjistrimetDto dto = new CreateRegjistrimetDto(this.kandidatId,this.kategoriId, statusi);
              Regjistrimet result = regjistrimiService.create(dto);
              System.out.printf("Category inserted successfuly !");
              System.out.println("Kategoria ID: " + result.getId());
              showAlert("Notification","Kategoria A u ruajt me sukses");
        }
        catch (Exception e){
            System.out.println("Kategoria nuk u ruajt me sukses !");
            showAlert("Notification","Kategoria A nuk u ruajt me sukses");
        }
    }
    @FXML
    private void applyClickB(){
        this.kategoriId = 2;
        try{
            CreateRegjistrimetDto dto = new CreateRegjistrimetDto(this.kandidatId,this.kategoriId, statusi);
            Regjistrimet result = regjistrimiService.create(dto);
            System.out.printf("Category inserted successfuly !");
            System.out.println("Kategoria ID: " + result.getId());
            showAlert("Notification","Kategoria B u ruajt me sukses");
        }
        catch (Exception e){
            System.out.println("Kategoria nuk u ruajt me sukses !");
            showAlert("Notification","Kategoria B nuk u ruajt me sukses");
        }
    }
    @FXML
    private void applyClickC(){
        this.kategoriId = 2;
        try{
            CreateRegjistrimetDto dto = new CreateRegjistrimetDto(this.kandidatId,this.kategoriId, statusi);
            Regjistrimet result = regjistrimiService.create(dto);
            System.out.printf("Category inserted successfuly !");
            System.out.println("Kategoria ID: " + result.getId());
            showAlert("Notification","Kategoria C u ruajt me sukses");
        }
        catch (Exception e){
            System.out.println("Kategoria nuk u ruajt me sukses !");
            showAlert("Notification","Kategoria C nuk u ruajt me sukses");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
