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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joseaguilar.dao.Conexion;
import org.joseaguilar.model.DetalleFactura;
import org.joseaguilar.system.Main;

public class MenuDetalleFacturaController implements Initializable {
    
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    TableView tblDetalleFacturas;
    
    @FXML
    TableColumn colDetalleFacturaId, colFecha, colHora, colTotal, colNombre, colApellido, colEmpleado;

    public void cargarDatos(){
        tblDetalleFacturas.setItems(sp_ListarDetalleFactura());
        colFecha.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<DetalleFactura, String>("hora"));
        colTotal.setCellValueFactory(new PropertyValueFactory<DetalleFactura, String>("total"));
        colNombre.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Double>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<DetalleFactura, String>("apellido"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<DetalleFactura, String>("nombreEmpleado"));
        tblDetalleFacturas.getSortOrder().add(colDetalleFacturaId);
    }
    
    
    public ObservableList<DetalleFactura> sp_ListarDetalleFactura(){
        ArrayList<DetalleFactura> detalleFacturas = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarDetalleFactura()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int detalleFacturaId = resultset.getInt("detalleFacturaId");
                String factura = resultset.getString("factura");
                String producto = resultset.getString("producto");
                
                detalleFacturas.add(new DetalleFactura(detalleFacturaId, factura, producto));
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
        return FXCollections.observableList(detalleFacturas);
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }
}
