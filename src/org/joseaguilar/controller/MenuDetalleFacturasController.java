package org.joseaguilar.controller;

import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joseaguilar.dao.Conexion;
import org.joseaguilar.model.Cliente;
import org.joseaguilar.model.Empleado;
import org.joseaguilar.model.Factura;
import org.joseaguilar.model.Producto;
import org.joseaguilar.report.GenerarReporte;
import org.joseaguilar.system.Main;
import org.joseaguilar.utilis.SuperKinalAlert;

public class MenuDetalleFacturasController implements Initializable {
    
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    Button btnRegresar, btnGuardar, btnVaciar, btnVerFactura;
    
    @FXML
    TextField tfFacturaId, tfHora, tfTotal, tfFecha;
    
    @FXML
    ComboBox cmbCliente, cmbEmpleado, cmbProducto;
    
    @FXML
    TableView tblFacturas;
    
    @FXML
    TableColumn colFacturaId, colCliente, colEmpleado, colFecha, colHora, colTotal, colProducto;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnGuardar){
            if(tfFacturaId.getText().equals("")){
                if(!tfHora.getText().equals("") && !tfFecha.getText().equals("")){
                    agregarFacturas();
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                    cargarDatos();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfHora.requestFocus();
                    return;
                }
               
            }else{
                if(!tfHora.getText().equals("") && !tfTotal.getText().equals("") && !tfFecha.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK){
                        editarFacturas();
                        cargarDatos();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfHora.requestFocus();    
                    return;
                }
                
            }
        }else if(event.getSource() == btnVaciar){
            vaciarCampos();
        }else if(event.getSource() == btnVerFactura){
            GenerarReporte.getInstance().generarFactura(((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getFacturaId());
        }
    }
    
    
    public void vaciarCampos(){
        tfFacturaId.clear();
        tfTotal.clear();
        cmbCliente.getSelectionModel().clearSelection();
        cmbEmpleado.getSelectionModel().clearSelection();
        cmbProducto.getSelectionModel().clearSelection();
        
    }
    
    public void cargarDatos(){
        tblFacturas.setItems(listarFacturas());
        colFacturaId.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("facturaId"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Factura, LocalDate>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<Factura, LocalTime>("hora"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Factura, Double>("total"));
        colCliente.setCellValueFactory(new PropertyValueFactory<Factura, String>("cliente"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Factura, String>("empleado"));
        colProducto.setCellValueFactory(new PropertyValueFactory<Factura, String>("nombreProducto"));
        tblFacturas.getSortOrder().add(colFacturaId);
    }
    
    public void cargarDatosEditar(){
        Factura fa = (Factura)tblFacturas.getSelectionModel().getSelectedItem();
        if(fa != null){
            tfFacturaId.setText(Integer.toString(fa.getFacturaId()));
            tfFecha.setText(fa.getFecha().toString());
            tfHora.setText(fa.getHora().toString());
            tfTotal.setText(Double.toString(fa.getTotal()));
            cmbCliente.getSelectionModel().select(obtenerIndexCliente());
            cmbEmpleado.getSelectionModel().select(obtenerIndexEmpleado());
        }
    }
    
    public int obtenerIndexCliente(){
        int index = 0;
        for(int i = 0 ; i < cmbCliente.getItems().size() ; i++){
            String clienteCmb = cmbCliente.getItems().get(i).toString();
            String clienteTbl = ((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getCliente();
            if(clienteCmb.equals(clienteTbl)){
                index = i;
                break;
            }
            
        }
        return index;
    }
    
    public int obtenerIndexEmpleado(){
        int index = 0;
        for(int i = 0 ; i < cmbEmpleado.getItems().size() ; i++){
            String empleadoCmb = cmbEmpleado.getItems().get(i).toString();
            String empleadoTbl = ((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getEmpleado();
            if(empleadoCmb.equals(empleadoTbl)){
                index = i;
                break;
            }
            
        }
        return index;
    }
    
    public ObservableList<Factura> listarFacturas(){
        ArrayList<Factura> facturas = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarFacturas()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int facturaId = resultset.getInt("facturaId");
                Date fecha = resultset.getDate("fecha");
                Time hora = resultset.getTime("hora");
                Double total = resultset.getDouble("total");
                String cliente = resultset.getString("nombre");
                String empleado = resultset.getString("nombreEmpleado");
                String producto = resultset.getString("nombreProducto");

                
                facturas.add(new Factura(facturaId, fecha.toLocalDate(), hora.toLocalTime(), total, cliente, empleado, producto));
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
                
            }
        }
        
        return FXCollections.observableList(facturas);
    }
    
    public ObservableList<Cliente> listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCliente()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int clienteId = resultset.getInt("clienteId");
                String nombre = resultset.getString("nombre");
                String apellido = resultset.getString("apellido");
                String telefono = resultset.getString("telefono");
                String direccion = resultset.getString("direccion");
                String nit = resultset.getString("nit");
                
                clientes.add(new Cliente(clienteId, nombre, apellido, telefono, direccion, nit));
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
        return FXCollections.observableList(clientes);
    }
    
    public ObservableList<Empleado> listarEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int empleadoId = resultset.getInt("empleadoId");
                String nombreEmpleado = resultset.getString("nombreEmpleado");
                String apellidoEmpleado = resultset.getString("apellidoEmpleado");
                double sueldo = resultset.getDouble("sueldo");
                String horaEntrada = resultset.getString("horaEntrada");
                String horaSalida = resultset.getString("horaSalida");
                String cargo = resultset.getString("nombreCargo");
                String encargado = resultset.getString("encargado");

                empleados.add(new Empleado(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargo, encargado));
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
        return FXCollections.observableList(empleados);
    }
    
    public void agregarFacturas(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarFacturas(?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setTime(2, Time.valueOf(LocalTime.now()));
            statement.setDouble(3, 0);
            statement.setInt(4, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(5, ((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.setInt(6, ((Producto)cmbProducto.getSelectionModel().getSelectedItem()).getProductoId());
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
    
    
    
    public void editarFacturas(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarFacturas(?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfFacturaId.getText()));
            statement.setDate(2, Date.valueOf(tfFecha.getText()));
            statement.setTime(3, Time.valueOf(tfHora.getText()));
            statement.setDouble(4, 0);
            statement.setInt(5, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(6, ((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
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
                String distribuidor = resultset.getString("distribuidor");
                String categoria = resultset.getString("categoria");
                
                productos.add(new Producto(productoId, nombre, descripcion, stock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidor, categoria));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbCliente.setItems(listarClientes());
        cmbEmpleado.setItems(listarEmpleados());
        cmbProducto.setItems(listarProductos());
        cargarDatos();
        tfFecha.setText(LocalDate.now().toString());

        tfHora.setText(LocalTime.now().toString());
    }  
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
}