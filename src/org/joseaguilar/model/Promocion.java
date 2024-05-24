package org.joseaguilar.model;

public class Promocion {
    private int promocionId;
    private String precio;
    private String descripcion;
    private String fechaInicio;
    private String fechaFinalizacion;
    private int productoId;
    private String producto;

    public Promocion() {
    }

    public Promocion(int promocionId, String precio, String descripcion, String fechaInicio, String fechaFinalizacion, int productoId) {
        this.promocionId = promocionId;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.productoId = productoId;
    }

    public Promocion(int promocionId, String precio, String descripcion, String fechaInicio, String fechaFinalizacion, String producto) {
        this.promocionId = promocionId;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.producto = producto;
    }

    public int getPromocionId() {
        return promocionId;
    }

    public void setPromocionId(int promocionId) {
        this.promocionId = promocionId;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Promocion{" + "promocionId=" + promocionId + ", precio=" + precio + ", descripcion=" + descripcion + ", fechaInicio=" + fechaInicio + ", fechaFinalizacion=" + fechaFinalizacion + ", productoId=" + productoId + ", producto=" + producto + '}';
    }
}
