package org.joseaguilar.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joseaguilar.dao.Conexion;
import org.joseaguilar.dto.CargoDTO;
import org.joseaguilar.model.Cargo;
import org.joseaguilar.system.Main;

public class MenuCargosController implements Initializable {
    private Main stage;
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    TextField tfNombreCargo, tfCargoId;
    
    @FXML
    TextArea taDescripcion;
    
    @FXML
    TableColumn colCargoId, colNombre, colDescripcion;
    
    @FXML
    TableView tblCargos;
    
    @FXML
    Button btnAgregar, btnRegresar, btnEditar;

    public ObservableList<Cargo> listarCargos(){
        ArrayList<Cargo> cargos = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCargos()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int cargoId = resultset.getInt("cargoId");
                String nombreCargo = resultset.getString("nombreCargo");
                String descripcion = resultset.getString("descripcionCargo");
                
                cargos.add(new Cargo(cargoId, nombreCargo, descripcion));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
                }
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
        return FXCollections.observableList(cargos);
    }
    
    public void cargarLista(){
        tblCargos.setItems(listarCargos());
        colCargoId.setCellValueFactory(new PropertyValueFactory<Cargo, Integer>("cargoId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cargo, String>("nombreCargo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Cargo, String>("descripcionCargo"));
    }
    
     public void agregarCargo(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarCargos(?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreCargo.getText());
            statement.setString(2, taDescripcion.getText());
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
     
     
    public void vaciarCampos(){
        tfNombreCargo.clear();
        taDescripcion.clear();
    }
     
    @FXML
    
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnAgregar){
            agregarCargo();
            vaciarCampos();
            cargarLista();
        }else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnEditar){
            CargoDTO.getCargoDTO().setCargo((Cargo)tblCargos.getSelectionModel().getSelectedItem());
            stage.formCargosView();
        }
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
      
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarLista();
    }
}
