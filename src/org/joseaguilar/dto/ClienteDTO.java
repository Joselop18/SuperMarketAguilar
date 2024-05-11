package org.joseaguilar.dto;
import org.joseaguilar.model.Cliente;

public class ClienteDTO {
    private static ClienteDTO instance;
    private Cliente cliente;

    public ClienteDTO() {
    }
    
    public static ClienteDTO getClienteDTO(){
        if(instance == null){
            instance = new ClienteDTO();
        }
        return instance;
    } 

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
