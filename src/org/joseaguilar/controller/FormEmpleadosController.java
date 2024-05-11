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
import org.joseaguilar.dto.EmpleadoDTO;
import org.joseaguilar.model.Empleado;
import org.joseaguilar.system.Main;

public class FormEmpleadosController implements Initializable {
    private Main stage;    
    private int op;
    private static Connection conexion;
    private static PreparedStatement statement;
    
    @FXML
    TextField tfEmpleadoId, tfNombre, tfApellido, tfSueldo, tfEntrada, tfSalida, tfCargo, tfEncargado;
    
    @FXML
    Button btnAgregar, btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(EmpleadoDTO.getEmpleadoDTO().getEmpleado() != null){
            cargarDatos(EmpleadoDTO.getEmpleadoDTO().getEmpleado());
        }
    }    
    
    public void cargarDatos(Empleado empleado){
        tfEmpleadoId.setText(Integer.toString(empleado.getEmpleadoId()));
        tfNombre.setText(empleado.getNombreEmpleado());
        tfApellido.setText(empleado.getApellidoEmpleado());
        tfSueldo.setText(Double.toString(empleado.getSueldo()));
        tfEntrada.setText(empleado.getHoraEntrada());
        tfSalida.setText(empleado.getHoraSalida());
        tfCargo.setText(empleado.getCargo());
        tfEncargado.setText(empleado.getEncargado());
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
    
    public void agregarEmpleado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarEmpleados(?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfApellido.getText());
            statement.setString(3, tfSueldo.getText());
            statement.setString(4, tfEntrada.getText());
            statement.setString(5, tfSalida.getText());
            statement.setString(6, tfCargo.getText());
            statement.setString(7, tfEncargado.getText());

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
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void editarEmpleado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarEmpleados(?,?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfEmpleadoId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfApellido.getText());
            statement.setString(4, tfSueldo.getText());
            statement.setString(5, tfEntrada.getText());
            statement.setString(6, tfSalida.getText());
            statement.setString(7, tfCargo.getText());
            statement.setString(8, tfEncargado.getText());
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
            }
        }
    }

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuEmpleadosView();
        }else if(event.getSource() == btnAgregar){
            if(op == 1){
                agregarEmpleado();
                EmpleadoDTO.getEmpleadoDTO().setEmpleado(null);
                stage.menuEmpleadosView();
            }else if(op == 2){
                editarEmpleado();
                EmpleadoDTO.getEmpleadoDTO().setEmpleado(null);
                stage.menuEmpleadosView();
            }
            
            stage.menuEmpleadosView();
        }
    }
}
