package org.joseaguilar.system;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.joseaguilar.controller.FormClientesController;
import org.joseaguilar.controller.MenuClientesController;
import org.joseaguilar.controller.MenuPrincipalController;
import org.joseaguilar.controller.MenuTicketSoporteController;

public class Main extends Application {
    private Stage stage;
    private Scene scene;
    private final String URLVIEW = "/org/joseaguilar/view/";
    
    @Override
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        Image icon = new Image("org/joseaguilar/image/Icon.png");
        stage.setTitle("SuperMarket Aguilar's");
        stage.getIcons().add(icon);
        menuPrincipalView();
        stage.show(); 
    }
    
    public Initializable switchScene(String fxmlName, int width, int height)throws Exception{
        Initializable resultado;
        FXMLLoader loader = new FXMLLoader();
        
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        
        scene = new Scene((AnchorPane)loader.load(file), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        
        resultado = (Initializable)loader.getController();
        return resultado;
    }
    
    public void menuPrincipalView(){
        try{
           MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml", 650, 500);
           menuPrincipalView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuClientesView(){
        try{
           MenuClientesController menuClientesView = (MenuClientesController)switchScene("MenuClientesView.fxml", 950, 700);
           menuClientesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formClientesView(int op){
        try{
            FormClientesController formClientesView = (FormClientesController)switchScene("FormClientesView.fxml", 480, 720);
            formClientesView.setOp(op);
            formClientesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuTicketSoporteView(){
        try{
            MenuTicketSoporteController menuTicketSoporteView = (MenuTicketSoporteController)switchScene("MenuTicketSoporteView.fxml", 1200, 800);
            menuTicketSoporteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}