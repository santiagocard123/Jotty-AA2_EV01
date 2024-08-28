package sena.com.vista;

import sena.com.modelo.Mantenimiento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MantenimientoView {
    public Mantenimiento obtenerDatosMantenimiento(Scanner scanner) {
        Mantenimiento mantenimiento = new Mantenimiento();
        System.out.print("ID del Equipo: ");
        mantenimiento.setEquipoId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Fecha de Mantenimiento (dd/MM/yyyy): ");
        String fechaStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        mantenimiento.setFechaMantenimiento(LocalDate.parse(fechaStr, formatter));
        System.out.print("Descripción: ");
        mantenimiento.setDescripcion(scanner.nextLine());
        System.out.print("Costo: ");
        mantenimiento.setCosto(scanner.nextDouble());
        scanner.nextLine();
        return mantenimiento;
    }
}