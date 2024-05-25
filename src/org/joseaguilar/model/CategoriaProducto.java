package org.joseaguilar.model;

public class CategoriaProducto {
    private int categoriaProductosId;
    private String nombreCategoria;
    private String descripcionCategoria;

    public CategoriaProducto() {
    }

    public CategoriaProducto(int categoriaProductosId, String nombreCategoria, String descripcionCategoria) {
        this.categoriaProductosId = categoriaProductosId;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
    }

    public int getCategoriaProductosId() {
        return categoriaProductosId;
    }

    public void setCategoriaProductosId(int categoriaProductosId) {
        this.categoriaProductosId = categoriaProductosId;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    @Override
    public String toString() {
        return "ID: " + categoriaProductosId + " - " + nombreCategoria;
    }
}
