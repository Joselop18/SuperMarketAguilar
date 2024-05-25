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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joseaguilar.dao.Conexion;
import org.joseaguilar.model.Compra;
import org.joseaguilar.system.Main;
import org.joseaguilar.utilis.SuperKinalAlert;

public class MenuComprasController implements Initializable {
    
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    TableView tblCompras;
    
    @FXML
    TableColumn colCompraId, colFecha, colTotal;
    
    @FXML
    TextField tfCompraId, tfFecha, tfTotal;
    
    @FXML
    Button btnGuardar, btnEliminar, btnRegresar, btnVaciar;
    
    public ObservableList<Compra> listarCompra(){
        ArrayList<Compra> compras = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCompra()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int compraId = resultset.getInt("compraId");
                String fecha = resultset.getString("fechaCompra");
                String total = resultset.getString("totalCompra");
                
                compras.add(new Compra(compraId, fecha, total));
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
        return FXCollections.observableList(compras);
    }
    
    public void cargarLista(){
        tblCompras.setItems(listarCompra());
        colCompraId.setCellValueFactory(new PropertyValueFactory<Compra, Integer>("compraId"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Compra, String>("fechaCompra"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Compra, String>("totalCompra"));
    }
    
    public void eliminarCompras(int comId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_eliminarCompra(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, comId);
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
    
    public void cargarDatosEditar(){
        Compra cr = (Compra)tblCompras.getSelectionModel().getSelectedItem();
        if(cr != null){
            tfCompraId.setText(Integer.toString(cr.getCompraId()));
            tfFecha.setText(cr.getFechaCompra());
            tfTotal.setText(cr.getTotalCompra());
        }
    }
    
    public Compra buscarCompra(){
        Compra compra = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarCompra(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1 ,Integer.parseInt(tfCompraId.getText()));
            resultset = statement.executeQuery();
            
            if(resultset.next()){
                int compraId = resultset.getInt("compraId");
                String fecha = resultset.getString("fechaCompra");
                String total = resultset.getString("totalCompra");
                
                compra = (new Compra(compraId, fecha, total));
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
        
        return compra;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarLista();
    }    
    
    public void agregarCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarCompra(?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfFecha.getText());
            statement.setDouble(2, Double.parseDouble(tfTotal.getText()));
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
    
    
    public void editarCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarCompra(?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCompraId.getText()));
            statement.setString(2, tfFecha.getText());
            statement.setDouble(3, Double.parseDouble(tfTotal.getText()));
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(conexion != null){
                conexion.close();
                }
                if(statement != null){
                    statement.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void vaciarCampos(){
        tfCompraId.clear();
        tfFecha.clear();
        tfTotal.clear();
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnGuardar){
            if(tfCompraId.getText().equals("")){
                if(!tfFecha.getText().equals("") && !tfTotal.getText().equals("")){
                    agregarCompra();
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                    cargarLista();
                    stage.menuCompraView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfFecha.requestFocus();
                    return;
                }
                
                
            }else{
                if(!tfFecha.getText().equals("") && !tfTotal.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK){
                        editarCompra();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfFecha.requestFocus();    
                    return;
                }
                
            }
        }else if(event.getSource() == btnVaciar){
            vaciarCampos();
        }else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                int comId = ((Compra)tblCompras.getSelectionModel().getSelectedItem()).getCompraId();
                eliminarCompras(comId);
                cargarLista();
            }
        }    
    }       

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}
