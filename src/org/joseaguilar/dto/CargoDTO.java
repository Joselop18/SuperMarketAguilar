package org.joseaguilar.dto;
import org.joseaguilar.model.Cargo;

public class CargoDTO {
    private static CargoDTO instance;
    private Cargo cargo;

    public CargoDTO() {
    }
    
    public static CargoDTO getCargoDTO(){
        if(instance == null){
            instance = new CargoDTO();
        }
        return instance;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
