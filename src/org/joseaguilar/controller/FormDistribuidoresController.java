package org.joseaguilar.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.joseaguilar.dao.Conexion;
import org.joseaguilar.dto.DistribuidorDTO;
import org.joseaguilar.model.Distribuidor;
import org.joseaguilar.system.Main;

public class FormDistribuidoresController implements Initializable {
    
    private Main stage;
    private int op;
    private static Connection conexion;
    private static PreparedStatement statement;
    
    @FXML
    Button btnAgregar, btnCancelar;
    
    @FXML
    TextField tfDistribuidorId, tfNombre, tfDireccion, tfNIT, tfTelefono, tfWeb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(DistribuidorDTO.getDistribuidorDTO().getDistribuidor() != null){
            cargarDatos(DistribuidorDTO.getDistribuidorDTO().getDistribuidor());
        }
    }    
    
    public void cargarDatos(Distribuidor distribuidor){
        tfDistribuidorId.setText(Integer.toString(distribuidor.getDistribuidorId()));
        tfNombre.setText(distribuidor.getNombreDistribuidor());
        tfDireccion.setText(distribuidor.getDireccionDistribuidor());
        tfNIT.setText(distribuidor.getNitDistribuidor());
        tfTelefono.setText(distribuidor.getTelefonoDistribuidor());
        tfWeb.setText(distribuidor.getWeb());

    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    public void setOp(int op) {
        this.op = op;
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuDistribuidoresView();
        }else if(event.getSource() == btnAgregar){
            if(op == 1){
                agregarDistribuidor();
                DistribuidorDTO.getDistribuidorDTO().setDistribuidor(null);
                stage.menuDistribuidoresView();
            }else if(op == 2){
                editarDistribuidor();
                DistribuidorDTO.getDistribuidorDTO().setDistribuidor(null);
                stage.menuDistribuidoresView();
            }
            
            stage.menuDistribuidoresView();
        }
    }
    
    public void agregarDistribuidor(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarDistribuidor(?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfDireccion.getText());
            statement.setString(3, tfNIT.getText());
            statement.setString(4, tfTelefono.getText());
            statement.setString(5, tfWeb.getText());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(conexion != null){
                    conexion.close();
                }else if(statement != null){
                    statement.close();
                }
            }catch(SQLException e){
                
            }
        }
    }
    
    public void editarDistribuidor(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarDistribuidor(?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfDistribuidorId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfDireccion.getText());
            statement.setString(4, tfNIT.getText());
            statement.setString(5, tfTelefono.getText());
            statement.setString(6, tfWeb.getText());
            statement.execute();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
