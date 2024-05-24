package org.joseaguilar.model;

public class Usuario {
    private int usuarioId;
    private String usuario;
    private String contrasenia;
    private int nivelAccesoId;
    private int empleadosId;

    public Usuario() {
    }

    public Usuario(int usuarioId, String usuario, String contrasenia, int nivelAccesoId, int empleadosId) {
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nivelAccesoId = nivelAccesoId;
        this.empleadosId = empleadosId;
    }


    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getNivelAccesoId() {
        return nivelAccesoId;
    }

    public void setNivelAccesoId(int nivelAccesoId) {
        this.nivelAccesoId = nivelAccesoId;
    }

    public int getEmpleadosId() {
        return empleadosId;
    }

    public void setEmpleadosId(int empleadosId) {
        this.empleadosId = empleadosId;
    }

    @Override
    public String toString() {
        return "Id: " + usuarioId + " | " + usuario;
    }
}
