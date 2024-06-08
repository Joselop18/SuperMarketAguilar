package org.joseaguilar.model;
import java.time.LocalDate;
import java.time.LocalTime;

public class Factura {
    private int facturaId;
    private LocalDate fecha;
    private LocalTime hora;
    private double total;
    private int clienteId;
    private String cliente;
    private int empleadoId;
    private String empleado;
    private int productoId;
    private String nombreProducto;

    public Factura() {
    }

    public Factura(int facturaId, LocalDate fecha, LocalTime hora, double total, int clienteId, int empleadoId, int productoId) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.productoId = productoId;
    }

    public Factura(int facturaId,  LocalDate fecha, LocalTime hora, double total ,String cliente, String empleado, String nombreProducto) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        this.cliente = cliente;
        this.empleado = empleado;
        this.nombreProducto = nombreProducto;
    }
    

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    

    @Override
    public String toString() {
        return "ID: " + facturaId + " - " + fecha + " - " + hora + " - " + total;
    }

}