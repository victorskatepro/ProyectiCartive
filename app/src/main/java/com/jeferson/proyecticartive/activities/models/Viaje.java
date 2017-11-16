package com.jeferson.proyecticartive.activities.models;

/**
 * Created by JARVIS on 5/11/2017.
 */

public class Viaje {

    private int id;
    private String tipo;
    private String destino;
    private String fechapartida;
    private String horapartida;
    private int numerotickets;
    private int numeroticketsdisponibles;
    private int numerohoras;
    private int empresa_id;
    private int bus_id;
    private int precio;

    public Viaje(int id, String tipo, String destino, String fechapartida, String horapartida, int numerotickets, int numeroticketsdisponibles, int numerohoras, int empresa_id, int bus_id, int precio) {
        this.id = id;
        this.tipo = tipo;
        this.destino = destino;
        this.fechapartida = fechapartida;
        this.horapartida = horapartida;
        this.numerotickets = numerotickets;
        this.numeroticketsdisponibles = numeroticketsdisponibles;
        this.numerohoras = numerohoras;
        this.empresa_id = empresa_id;
        this.bus_id = bus_id;
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechapartida() {
        return fechapartida;
    }

    public void setFechapartida(String fechapartida) {
        this.fechapartida = fechapartida;
    }

    public String getHorapartida() {
        return horapartida;
    }

    public void setHorapartida(String horapartida) {
        this.horapartida = horapartida;
    }

    public int getNumerotickets() {
        return numerotickets;
    }

    public void setNumerotickets(int numerotickets) {
        this.numerotickets = numerotickets;
    }

    public int getNumeroticketsdisponibles() {
        return numeroticketsdisponibles;
    }

    public void setNumeroticketsdisponibles(int numeroticketsdisponibles) {
        this.numeroticketsdisponibles = numeroticketsdisponibles;
    }

    public int getNumerohoras() {
        return numerohoras;
    }

    public void setNumerohoras(int numerohoras) {
        this.numerohoras = numerohoras;
    }

    public int getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(int empresa_id) {
        this.empresa_id = empresa_id;
    }

    public int getBus_id() {
        return bus_id;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", destino='" + destino + '\'' +
                ", fechapartida='" + fechapartida + '\'' +
                ", horapartida='" + horapartida + '\'' +
                ", numerotickets=" + numerotickets +
                ", numeroticketsdisponibles=" + numeroticketsdisponibles +
                ", numerohoras=" + numerohoras +
                ", empresa_id=" + empresa_id +
                ", bus_id=" + bus_id +
                ", precio=" + precio +
                '}';
    }
}
