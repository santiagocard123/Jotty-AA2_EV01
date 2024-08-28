package sena.com.vista;

import sena.com.modelo.Equipo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EquipoView {
    public Equipo obtenerDatosEquipo(Scanner scanner) {
        Equipo equipo = new Equipo();
        System.out.print("Nombre: ");
        equipo.setNombre(scanner.nextLine());
        System.out.print("Marca: ");
        equipo.setMarca(scanner.nextLine());
        System.out.print("Modelo: ");
        equipo.setModelo(scanner.nextLine());
        System.out.print("Estado: ");
        equipo.setEstado(scanner.nextLine());
        System.out.print("Fecha de Adquisición (dd/MM/yyyy): ");
        String fechaStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        equipo.setFechaAdquisicion(LocalDate.parse(fechaStr, formatter));
        return equipo;
    }
}