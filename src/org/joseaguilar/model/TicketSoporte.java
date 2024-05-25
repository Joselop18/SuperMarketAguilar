
package org.joseaguilar.model;


public class TicketSoporte {
    private int ticketSoporteId;
    private String descripcionTicket;
    private String estatus;
    private String cliente;
    private int clienteId;
    private String factura;
    private int facturaId;

    public TicketSoporte() {
    }

    public TicketSoporte(int ticketSoporteId, String descripcionTicket, String estatus, String cliente, String factura) {
        this.ticketSoporteId = ticketSoporteId;
        this.descripcionTicket = descripcionTicket;
        this.estatus = estatus;
        this.cliente = cliente;
        this.clienteId = clienteId;
        this.factura = factura;
    }

    public TicketSoporte(int ticketSoporteId, String descripcionTicket, String estatus, int clienteId, int facturaId) {
        this.ticketSoporteId = ticketSoporteId;
        this.descripcionTicket = descripcionTicket;
        this.estatus = estatus;
        this.cliente = cliente;
        this.clienteId = clienteId;
        this.facturaId = facturaId;
    }
    
    

    public int getTicketSoporteId() {
        return ticketSoporteId;
    }

    public void setTicketSoporteId(int ticketSoporteId) {
        this.ticketSoporteId = ticketSoporteId;
    }

    public String getDescripcionTicket() {
        return descripcionTicket;
    }

    public void setDescripcionTicket(String descripcionTicket) {
        this.descripcionTicket = descripcionTicket;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    @Override
    public String toString() {
        return "ID: " + ticketSoporteId;
    }

    
    
    
    
}
