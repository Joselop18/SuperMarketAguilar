package org.joseaguilar.controller;

import java.net.URL;
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
import org.joseaguilar.system.Main;
import org.joseaguilar.utilis.SuperKinalAlert;

public class MenuFacturaController implements Initializable {
    
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    Button btnRegresar, btnGuardar, btnVaciar;
    
    @FXML
    TextField tfFacturaId, tfHora, tfTotal, tfFecha;
    
    @FXML
    ComboBox cmbCliente, cmbEmpleado;
    
    @FXML
    TableView tblFacturas;
    
    @FXML
    TableColumn colFacturaId, colCliente, colEmpleado, colFecha, colHora, colTotal;
    
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
        }
    }
    
    
    public void vaciarCampos(){
        tfFecha.clear();
        tfFacturaId.clear();
        tfHora.clear();
        tfTotal.clear();
        cmbCliente.getSelectionModel().clearSelection();
        cmbEmpleado.getSelectionModel().clearSelection();
        
    }
    
    public void cargarDatos(){
        tblFacturas.setItems(listarFacturas());
        colFacturaId.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("facturaId"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Factura, LocalDate>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<Factura, LocalTime>("hora"));
        colCliente.setCellValueFactory(new PropertyValueFactory<Factura, String>("cliente"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Factura, String>("empleado"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Factura, Double>("total"));
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
            String sql = "call sp_listarFactura()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int facturaId = resultset.getInt("facturaId");
                Date fecha = resultset.getDate("fecha");
                Time hora = resultset.getTime("hora");
                String cliente = resultset.getString("cliente");
                String empleado = resultset.getString("empleado");
                Double total = resultset.getDouble("total");

                
                facturas.add(new Factura(facturaId, fecha.toLocalDate(), hora.toLocalTime(), cliente, empleado, total));
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
            String sql = "call sp_listarEmpleado()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int empleadoId = resultset.getInt("empleadoId");
                String nombreEmpleado = resultset.getString("nombreEmpleado");
                String apellidoEmpleado = resultset.getString("apellidoEmpleado");
                double sueldo = resultset.getDouble("sueldo");
                String horaEntrada = resultset.getString("horaEntrada");
                String horaSalida = resultset.getString("horaSalida");
                String cargo = resultset.getString("cargoId");
                String encargado = resultset.getString("encargadoId");

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
            String sql = "call sp_agregarFactura(?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setTime(2, Time.valueOf(LocalTime.now()));
            statement.setInt(3, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(4, ((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.setDouble(5, 0);
            
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
            String sql = "call sp_editarFactura(?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfFacturaId.getText()));
            statement.setInt(2, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(3, ((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.setDouble(4, 0);
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
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbCliente.setItems(listarClientes());
        cmbEmpleado.setItems(listarEmpleados());
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
