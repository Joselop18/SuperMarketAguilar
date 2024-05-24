package org.joseaguilar.dto;
import org.joseaguilar.model.Empleado;

public class EmpleadoDTO {
    private static EmpleadoDTO instance;
    private Empleado empleado;

    private EmpleadoDTO() {
    }
    
    public static EmpleadoDTO getEmpleadoDTO(){
        if(instance == null){
            instance = new EmpleadoDTO();
        }
        return instance;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
