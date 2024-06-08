package org.joseaguilar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import org.joseaguilar.dao.Conexion;
import org.joseaguilar.dto.ProductoDTO;
import org.joseaguilar.model.CategoriaProducto;
import org.joseaguilar.model.Distribuidor;
import org.joseaguilar.model.Producto;
import org.joseaguilar.report.GenerarReporte;
import org.joseaguilar.system.Main;
import org.joseaguilar.utilis.SuperKinalAlert;

public class MenuProductosController implements Initializable {
    
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    private List<File> files = null;
    
    @FXML
    TableView tblProductos;
    
    @FXML
    TextField tfNombre, tfStock, tfPUnitario, tfPMayor, tfPCompra, tfProductoId, tfBuscar;
    
    @FXML
    TextArea taDescripcion;
    
    @FXML
    ComboBox cmbDistribuidor, cmbCategoria;
    
    @FXML
    Button btnGuardar, btnEliminar, btnRegresar, btnBuscar, btnReporte;
    
    @FXML
    ImageView imgCargar, imgMostrar;
    
    @FXML
    Label lblNombre;
    
    @FXML
    TableColumn colProductoId, colNombre, colDescripcion, colStock, colPUnitario, colPMayor, colPCompra, colDistribuidor, colCategoria;
    
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        try{
            if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
            }else if(event.getSource() == btnGuardar){
                if(tfProductoId.getText().equals("")){
                    agregarProductos();
                }else{
                    editarProductos();
                }
            }else if(event.getSource() == btnEliminar){
                if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                    int proId = ((Producto)tblProductos.getSelectionModel().getSelectedItem()).getProductoId();
                    eliminarProducto(proId);
                    cargarDato();
                }
            }else if(event.getSource() == btnBuscar){
                Producto producto = buscarProducto();
                tblProductos.getItems().clear();
                if(tfProductoId.getText().equals("")){
                    cargarDato();
                }else{
                    lblNombre.setText(producto.getNombreProducto());
                    InputStream file = producto.getImagenProducto().getBinaryStream();
                    Image image = new Image(file);
                    imgMostrar.setImage(image);
                    tblProductos.getItems().add(buscarProducto());
                    colProductoId.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("productoId"));
                    colNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
                    colDescripcion.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcionProducto"));
                    colStock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidadStock"));
                    colPUnitario.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioVentaUnitario"));
                    colPMayor.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioVentaMayor"));
                    colPCompra.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioCompra"));
                    colDistribuidor.setCellValueFactory(new PropertyValueFactory<Producto, String>("distribuidorId"));
                    colCategoria.setCellValueFactory(new PropertyValueFactory<Producto, String>("categoriaProductosId"));
                }
            }else if(event.getSource() == btnReporte){
                GenerarReporte.getInstance().generarProductos();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    @FXML
    public void handleOnDrag(DragEvent event){
        if(event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    
    @FXML
    public void handleOnDrop(DragEvent event){
        try{
            files = event.getDragboard().getFiles();
            FileInputStream file = new FileInputStream(files.get(0));
            Image image = new Image(file);
            imgCargar.setImage(image);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void cargarDato(){
        try{
            tblProductos.setItems(listarProductos());
            colProductoId.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("productoId"));
            colNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcionProducto"));
            colStock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidadStock"));
            colPUnitario.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioVentaUnitario"));
            colPMayor.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioVentaMayor"));
            colPCompra.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioCompra"));
            colDistribuidor.setCellValueFactory(new PropertyValueFactory<Producto, String>("distribuidor"));
            colCategoria.setCellValueFactory(new PropertyValueFactory<Producto, String>("categoria"));
            tblProductos.getSortOrder().add(colProductoId);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void cargarDatos(Producto producto){
        tfProductoId.setText(Integer.toString(producto.getProductoId()));
        tfNombre.setText(producto.getNombreProducto());
        taDescripcion.setText(producto.getDescripcionProducto());
        tfStock.setText(Integer.toString(producto.getCantidadStock()));
        tfPUnitario.setText(Double.toString(producto.getPrecioVentaUnitario()));
        tfPMayor.setText(Double.toString(producto.getPrecioVentaMayor()));
        tfPCompra.setText(Double.toString(producto.getPrecioCompra()));
        cmbDistribuidor.getSelectionModel().select(obtenerIndexDistribuidor());
        cmbCategoria.getSelectionModel().select(obtenerIndexCategoriaProductos());

    }
    
    public ObservableList<Producto> listarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarProducto()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int productoId = resultset.getInt("productoId");
                String nombre = resultset.getString("nombreProducto");
                String descripcion = resultset.getString("descripcionProducto");
                int stock = resultset.getInt("cantidadStock");
                double precioVentaUnitario = resultset.getDouble("precioVentaUnitario");
                double precioVentaMayor = resultset.getDouble("precioVentaMayor");
                double precioCompra = resultset.getDouble("precioCompra");
                Blob imagenProducto = resultset.getBlob("imagenProducto");
                String distribuidor = resultset.getString("nombreDistribuidor");
                String categoriaProductoS = resultset.getString("nombreCategoria");
                
                productos.add(new Producto(productoId, nombre, descripcion, stock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidor, categoriaProductoS));
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
        return FXCollections.observableList(productos);
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public int obtenerIndexDistribuidor(){
        int index = 0;
        for(int i = 0 ; i <= cmbDistribuidor.getItems().size() ; i++){
            String distribuidorCmb = cmbDistribuidor.getItems().get(i).toString();
            String distribuidorTbl = ((Producto)tblProductos.getSelectionModel().getSelectedItems()).getDistribuidor();
            if(distribuidorCmb.equals(distribuidorTbl)){
                index = i;
                break;
            }
            
        }
        return index;
    }
    
    public int obtenerIndexCategoriaProductos(){
        int index = 0;
        for(int i = 0 ; i <= cmbCategoria.getItems().size() ; i++){
            String categoriaCmb = cmbCategoria.getItems().get(i).toString();
            String categoriaTbl = ((Producto)tblProductos.getSelectionModel().getSelectedItems()).getCategoria();
            if(categoriaCmb.equals(categoriaTbl)){
                index = i;
                break;
            }
            
        }
        return index;
    }    
    
    public void agregarProductos(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarProducto(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, taDescripcion.getText());
            statement.setInt(3, Integer.parseInt(tfStock.getText()));
            statement.setDouble(4, Double.parseDouble(tfPUnitario.getText()));
            statement.setDouble(5, Double.parseDouble(tfPMayor.getText()));
            statement.setDouble(6, Double.parseDouble(tfPCompra.getText()));
            InputStream img = new FileInputStream(files.get(0));
            statement.setBinaryStream(7, img);
            statement.setInt(8, ((Distribuidor)cmbDistribuidor.getSelectionModel().getSelectedItem()).getDistribuidorId());
            statement.setInt(9, ((CategoriaProducto)cmbCategoria.getSelectionModel().getSelectedItem()).getCategoriaProductosId());
            statement.execute();
        }catch(Exception e){
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
    
    public void editarProductos(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarProducto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfProductoId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, taDescripcion.getText());
            statement.setInt(4, Integer.parseInt(tfStock.getText()));
            statement.setDouble(5, Double.parseDouble(tfPUnitario.getText()));
            statement.setDouble(6, Double.parseDouble(tfPMayor.getText()));
            statement.setDouble(7, Double.parseDouble(tfPCompra.getText()));
            InputStream img = new FileInputStream(files.get(0));
            statement.setBinaryStream(8, img);
            statement.setInt(9, ((Distribuidor)cmbDistribuidor.getSelectionModel().getSelectedItem()).getDistribuidorId());
            statement.setInt(10, ((CategoriaProducto)cmbCategoria.getSelectionModel().getSelectedItem()).getCategoriaProductosId());
            statement.execute();
        }catch(Exception e){
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
    

    
    public Producto buscarProducto(){
        Producto producto = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarProducto(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfProductoId.getText()));
            resultset = statement.executeQuery();
            if(resultset.next()){
                int productoId = resultset.getInt("productoId");
                String nombre = resultset.getString("nombreProducto");
                String descripcion = resultset.getString("descripcionProducto");
                int stock = resultset.getInt("cantidadStock");
                Double PVUnitario = resultset.getDouble("precioVentaUnitario");
                Double PVMayor = resultset.getDouble("precioVentaMayor");
                Double PCompra = resultset.getDouble("precioCompra");
                String distribuidor = resultset.getString("nombreDistribuidor");
                String categoria = resultset.getString("nombreCategoria");
                
                producto = new Producto(productoId, nombre, descripcion, stock, PVUnitario, PVMayor, PCompra, distribuidor, categoria);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return producto;
    } 
    
    public void eliminarProducto(int proId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_eliminarProducto(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, proId);
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
    
    public ObservableList<Distribuidor> listarDistribuidores(){
        ArrayList<Distribuidor> distribuidores = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarDistribuidor()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int distribuidorId = resultset.getInt("distribuidorId");
                String nombre = resultset.getString("nombreDistribuidor");
                String direccion = resultset.getString("direccionDistribuidor");
                String nit = resultset.getString("nitDistribuidor");
                String telefono = resultset.getString("telefonoDistribuidor");
                String web = resultset.getString("web");
                
                distribuidores.add(new Distribuidor(distribuidorId, nombre, direccion, nit, telefono, web));
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
        return FXCollections.observableList(distribuidores);
    }
    
    public ObservableList<CategoriaProducto> listarCategoriaProductos(){
        ArrayList<CategoriaProducto> categoriaProductos = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCategoriaProducto()";
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(ProductoDTO.getProductoDTO().getProducto() != null){
            cargarDatos(ProductoDTO.getProductoDTO().getProducto());
        }
        cmbCategoria.setItems(listarCategoriaProductos());
        cmbDistribuidor.setItems(listarDistribuidores());
    }
}
