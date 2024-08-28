package sena.com.modelo;

import java.time.LocalDate;

public class Mantenimiento {
    private int id;
    private int equipoId;
    private LocalDate fechaMantenimiento;
    private String descripcion;
    private double costo;


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getEquipoId() { return equipoId; }
    public void setEquipoId(int equipoId) { this.equipoId = equipoId; }
    public LocalDate getFechaMantenimiento() { return fechaMantenimiento; }
    public void setFechaMantenimiento(LocalDate fechaMantenimiento) { this.fechaMantenimiento = fechaMantenimiento; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }
}