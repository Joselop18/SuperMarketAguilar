package org.joseaguilar.model;
import java.sql.Blob;
import javafx.scene.image.Image;

public class Producto {
    private int productoId;
    private String nombreProducto;
    private String descripcionProducto;
    private int cantidadStock;
    private double precioVentaUnitario;
    private double precioVenteMayor;
    private double precioCompra;
    private Image imagenProducto;
    private int distribuidorId;
    private String distribuidor;
    private int categoriaProdcutoId;
    private String categoriaProdcutoS;

    public Producto() {
        
    }

    public Producto(int productoId, String nombreProducto, String descripcionProducto, int cantidadStock, double precioVentaUnitario, double precioVenteMayor, double precioCompra, Image imagenProducto, int distribuidorId, int categoriaProdcutoId) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadStock = cantidadStock;
        this.precioVentaUnitario = precioVentaUnitario;
        this.precioVenteMayor = precioVenteMayor;
        this.precioCompra = precioCompra;
        this.imagenProducto = imagenProducto;
        this.distribuidorId = distribuidorId;
        this.categoriaProdcutoId = categoriaProdcutoId;
    }

    public Producto(int productoId, String nombreProducto, String descripcionProducto, int cantidadStock, double precioVentaUnitario, double precioVenteMayor, double precioCompra, Image imagenProducto, String distribuidor, String categoriaProdcutoS) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadStock = cantidadStock;
        this.precioVentaUnitario = precioVentaUnitario;
        this.precioVenteMayor = precioVenteMayor;
        this.precioCompra = precioCompra;
        this.imagenProducto = imagenProducto;
        this.distribuidor = distribuidor;
        this.categoriaProdcutoS = categoriaProdcutoS;
    }

    public Producto(int productoId, String nombreProducto, String descripcionProducto, int cantidadStock, double precioVentaUnitario, double precioVenteMayor, double precioCompra, String distribuidor, String categoriaProdcutoS) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadStock = cantidadStock;
        this.precioVentaUnitario = precioVentaUnitario;
        this.precioVenteMayor = precioVenteMayor;
        this.precioCompra = precioCompra;
        this.distribuidor = distribuidor;
        this.categoriaProdcutoS = categoriaProdcutoS;
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

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public double getPrecioVentaUnitario() {
        return precioVentaUnitario;
    }

    public void setPrecioVentaUnitario(double precioVentaUnitario) {
        this.precioVentaUnitario = precioVentaUnitario;
    }

    public double getPrecioVenteMayor() {
        return precioVenteMayor;
    }

    public void setPrecioVenteMayor(double precioVenteMayor) {
        this.precioVenteMayor = precioVenteMayor;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Image getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(Image imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public int getDistribuidorId() {
        return distribuidorId;
    }

    public void setDistribuidorId(int distribuidorId) {
        this.distribuidorId = distribuidorId;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public int getCategoriaProdcutoId() {
        return categoriaProdcutoId;
    }

    public void setCategoriaProdcutoId(int categoriaProdcutoId) {
        this.categoriaProdcutoId = categoriaProdcutoId;
    }

    public String getCategoriaProdcutoS() {
        return categoriaProdcutoS;
    }

    public void setCategoriaProdcutoS(String categoriaProdcutoS) {
        this.categoriaProdcutoS = categoriaProdcutoS;
    }

    @Override
    public String toString() {
        return "Producto{" + "productoId=" + productoId + ", nombreProducto=" + nombreProducto + ", descripcionProducto=" + descripcionProducto + ", cantidadStock=" + cantidadStock + ", precioVentaUnitario=" + precioVentaUnitario + ", precioVenteMayor=" + precioVenteMayor + ", precioCompra=" + precioCompra + ", imagenProducto=" + imagenProducto + ", distribuidorId=" + distribuidorId + ", distribuidor=" + distribuidor + ", categoriaProdcutoId=" + categoriaProdcutoId + ", categoriaProdcutoS=" + categoriaProdcutoS + '}';
    }
    
}
