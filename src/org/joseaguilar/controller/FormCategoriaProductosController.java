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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.joseaguilar.dao.Conexion;
import org.joseaguilar.system.Main;
import org.joseaguilar.dto.CategoriaProductoDTO;
import org.joseaguilar.model.CategoriaProducto;
import org.joseaguilar.utilis.SuperKinalAlert;

public class FormCategoriaProductosController implements Initializable {
    private Main stage;    
    private int op;
    private static Connection conexion;
    private static PreparedStatement statement;
    
    @FXML
    TableView tblCategoriaProductos;
    
    @FXML
    TableColumn colCategoriaProductosId, colNombreCategoria, colDescripcionCategoria;
    
    @FXML
    TextField tfCategoriaProductosId, tfNombreCategoria;
    
    @FXML
    TextArea taDescripcionCategoria;
    
    @FXML
    Button btnAgregar, btnCancelar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(CategoriaProductoDTO.getCategoriaProductoDTO().getCategoriaProducto() != null){
            cargarDatos(CategoriaProductoDTO.getCategoriaProductoDTO().getCategoriaProducto());
        }
    }    
    
    public void cargarDatos(CategoriaProducto categoriaProducto){
        tfCategoriaProductosId.setText(Integer.toString(categoriaProducto.getCategoriaProductosId()));
        tfNombreCategoria.setText(categoriaProducto.getNombreCategoria());
        taDescripcionCategoria.setText(categoriaProducto.getDescripcionCategoria());

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
            stage.menuCategoriaProductosView();
        }else if(event.getSource() == btnAgregar){
            if(op == 1){
                if(!tfNombreCategoria.getText().equals("") && !taDescripcionCategoria.getText().equals("")){
                    agregarCategoriaProductos();
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                    CategoriaProductoDTO.getCategoriaProductoDTO().setCategoriaProducto(null);
                    stage.menuCategoriaProductosView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfNombreCategoria.requestFocus();
                    return;
                }
            }else if(op == 2){
                if(!tfNombreCategoria.getText().equals("") && !taDescripcionCategoria.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK){
                        editarCategoriaProductos();
                        CategoriaProductoDTO.getCategoriaProductoDTO().setCategoriaProducto(null);
                        stage.menuCategoriaProductosView();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfNombreCategoria.requestFocus();    
                    return;
                }
            }
            stage.menuCategoriaProductosView();
        }
    }
    
    public void agregarCategoriaProductos(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarCategoriaProductos(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreCategoria.getText());
            statement.setString(2, taDescripcionCategoria.getText());
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
    public void editarCategoriaProductos(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarCategoriaProducto(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCategoriaProductosId.getText()));
            statement.setString(2, tfNombreCategoria.getText());
            statement.setString(3, taDescripcionCategoria.getText());
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
