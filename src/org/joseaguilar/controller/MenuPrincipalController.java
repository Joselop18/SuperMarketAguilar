package org.joseaguilar.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.joseaguilar.system.Main;

public class MenuPrincipalController implements Initializable{
    private Main stage;
    
    @FXML
    MenuItem btnMenuClientes, btnTicketSoporte;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        
    }
    
    public Main getStage() {
        return stage;
    }
    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnMenuClientes){
            stage.menuClientesView();
        }else if(event.getSource() == btnTicketSoporte){
            stage.menuTicketSoporteView();
        }
    }
}