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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joseaguilar.dao.Conexion;
import org.joseaguilar.dto.CategoriaProductoDTO;
import org.joseaguilar.model.CategoriaProducto;
import org.joseaguilar.system.Main;

public class MenuCategoriaProductosController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    TableView tblCategoriaProductos;
    
    @FXML
    TableColumn colCategoriaProductosId, colNombreCategoria, colDescripcionCategoria;
    
    @FXML
    TextField tfCategoriaProductoId;
    
    @FXML
    Button btnAgregar, btnRegresar, btnBuscar, btnEliminar, btnEditar;
    
    public ObservableList<CategoriaProducto> listarCategoriaProductos(){
        ArrayList<CategoriaProducto> categoriaProductos = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCategoriaProductos()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int categoriaProductosId = resultset.getInt("categoriaProductosId");
                String nombreCategoria = resultset.getString("nombreCategoria");
                String descripcionCategoria = resultset.getString("descripcionCategoria");
                categoriaProductos.add(new CategoriaProducto(categoriaProductosId, nombreCategoria, descripcionCategoria));
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
        return FXCollections.observableList(categoriaProductos);
    }

    public void cargarLista(){
        tblCategoriaProductos.setItems(listarCategoriaProductos());
        colCategoriaProductosId.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, Integer>("categoriaProductosId"));
        colNombreCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("nombreCategoria"));
        colDescripcionCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("descripcionCategoria"));
    }
    
    public void eliminarCategoriaProducto(int catProId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_eliminarCategoriaProductos(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, catProId);
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
    
    public CategoriaProducto buscarCategoriaProducto(){
        CategoriaProducto categoriaProducto = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarCategoriaProductos(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1 ,Integer.parseInt(tfCategoriaProductoId.getText()));
            resultset = statement.executeQuery();
            
            if(resultset.next()){
                int categoriaProductosId = resultset.getInt("categoriaProductosId");
                String nombreCategoria = resultset.getString("nombreCategoria");
                String descripcionCategoria = resultset.getString("descripcionCategoria");
                
                categoriaProducto = (new CategoriaProducto(categoriaProductosId, nombreCategoria, descripcionCategoria));
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
        
        return categoriaProducto;
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarLista();
    }  
    
    @FXML
    
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnAgregar){
            stage.formCategoriaProductosView(1);
        }else if(event.getSource() == btnEditar){
            CategoriaProductoDTO.getCategoriaProductoDTO().setCategoriaProducto((CategoriaProducto)tblCategoriaProductos.getSelectionModel().getSelectedItem());
            stage.formCategoriaProductosView(2);
        }else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnEliminar){
            int catProId = ((CategoriaProducto)tblCategoriaProductos.getSelectionModel().getSelectedItem()).getCategoriaProductosId();
            eliminarCategoriaProducto(catProId);
            cargarLista();
        }else if (event.getSource() == btnBuscar){
            tblCategoriaProductos.getItems().clear();
            if(tfCategoriaProductoId.getText().equals("")){
                cargarLista();
            }else{
                tblCategoriaProductos.setItems(listarCategoriaProductos());
                colCategoriaProductosId.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, Integer>("categoriaProductosId"));
                colNombreCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("nombreCategoria"));
                colDescripcionCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("descripcionCategoria"));
            }
        }
    }
}
