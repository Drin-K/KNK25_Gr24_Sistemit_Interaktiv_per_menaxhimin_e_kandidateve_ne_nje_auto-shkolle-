package app.controller;

import app.controller.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import models.Dto.regjistrimet.CreateRegjistrimetDto;
import models.Regjistrimet;
import services.*;
import utils.SceneLocator;

public class CategoryConsumatorController extends BaseController {
    private int kategoriId;
    private final RegjistrimiService regjistrimiService;
    private final int kandidatId = UserContext.getUserId();
    private final String statusi = "Në proces";
    public CategoryConsumatorController(){
        this.regjistrimiService = new RegjistrimiService();
    }
    @FXML
    private AnchorPane rightPage;
    @FXML
    private void applyClickA(){
        this.kategoriId = 1;
        try{
              CreateRegjistrimetDto dto = new CreateRegjistrimetDto(this.kandidatId,this.kategoriId, statusi);
              Regjistrimet result = regjistrimiService.create(dto);
              System.out.printf("Category inserted successfuly !");
              System.out.println("Kategoria ID: " + result.getId());
              showAlert(Alert.AlertType.INFORMATION,"Notification","Category A successfully saved");
              SceneManager.load(SceneLocator.DOCUMENTS_PAGE_A,this.rightPage);

        }
        catch (Exception e){
            System.out.println("Kategoria nuk u ruajt me sukses !");
            showAlert(Alert.AlertType.ERROR,"Notification","Error while saving category A");
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
            showAlert(Alert.AlertType.INFORMATION,"Notification","Category B successfully saved");
            SceneManager.load(SceneLocator.DOCUMENTS_PAGE_A,this.rightPage);
        }
        catch (Exception e){
            System.out.println("Kategoria nuk u ruajt me sukses !");
            showAlert(Alert.AlertType.ERROR,"Notification","Error while saving category B");
        }
    }
    @FXML
    private void applyClickC(){
        this.kategoriId = 3;
        try{
            CreateRegjistrimetDto dto = new CreateRegjistrimetDto(this.kandidatId,this.kategoriId, statusi);
            Regjistrimet result = regjistrimiService.create(dto);
            System.out.printf("Category inserted successfuly !");
            System.out.println("Kategoria ID: " + result.getId());
            showAlert(Alert.AlertType.INFORMATION,"Notification","Category C successfully saved");
            SceneManager.load(SceneLocator.DOCUMENTS_PAGE_A,this.rightPage);
        }
        catch (Exception e){
            System.out.println("Kategoria nuk u ruajt me sukses !");
            showAlert(Alert.AlertType.ERROR,"Notification","Error while saving category C");
        }
    }
}
