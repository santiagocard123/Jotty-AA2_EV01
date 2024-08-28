package sena.com.modelo;

import java.time.LocalDate;

public class Equipo {
    private int id;
    private String nombre;
    private String marca;
    private String modelo;
    private String estado;
    private LocalDate fechaAdquisicion;


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDate getFechaAdquisicion() { return fechaAdquisicion; }
    public void setFechaAdquisicion(LocalDate fechaAdquisicion) { this.fechaAdquisicion = fechaAdquisicion; }
}