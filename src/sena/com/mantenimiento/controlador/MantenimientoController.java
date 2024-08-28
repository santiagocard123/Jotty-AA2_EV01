package sena.com.mantenimiento.controlador;


import sena.com.modelo.Mantenimiento;
import sena.com.vista.MantenimientoView;
import sena.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MantenimientoController {
    private MantenimientoView mantenimientoView;

    public MantenimientoController() {
        mantenimientoView = new MantenimientoView();
    }

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("1. Registrar Mantenimiento");
            System.out.println("2. Listar Mantenimientos");
            System.out.println("3. Actualizar Mantenimiento");
            System.out.println("4. Eliminar Mantenimiento");
            System.out.println("0. Volver");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarMantenimiento(scanner);
                    break;
                case 2:
                    listarMantenimientos();
                    break;
                case 3:
                    actualizarMantenimiento(scanner);
                    break;
                case 4:
                    eliminarMantenimiento(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void registrarMantenimiento(Scanner scanner) {
        Mantenimiento mantenimiento = mantenimientoView.obtenerDatosMantenimiento(scanner);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Mantenimientos (equipo_id, fecha_mantenimiento, descripcion, costo) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mantenimiento.getEquipoId());
            statement.setDate(2, java.sql.Date.valueOf(mantenimiento.getFechaMantenimiento()));
            statement.setString(3, mantenimiento.getDescripcion());
            statement.setDouble(4, mantenimiento.getCosto());
            statement.executeUpdate();
            System.out.println("Mantenimiento registrado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarMantenimientos() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Mantenimientos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Equipo ID: " + resultSet.getInt("equipo_id"));
                System.out.println("Fecha de Mantenimiento: " + resultSet.getDate("fecha_mantenimiento"));
                System.out.println("Descripción: " + resultSet.getString("descripcion"));
                System.out.println("Costo: " + resultSet.getDouble("costo"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarMantenimiento(Scanner scanner) {
        System.out.print("Ingrese el ID del mantenimiento a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        Mantenimiento mantenimiento = mantenimientoView.obtenerDatosMantenimiento(scanner);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE Mantenimientos SET equipo_id = ?, fecha_mantenimiento = ?, descripcion = ?, costo = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mantenimiento.getEquipoId());
            statement.setDate(2, java.sql.Date.valueOf(mantenimiento.getFechaMantenimiento()));
            statement.setString(3, mantenimiento.getDescripcion());
            statement.setDouble(4, mantenimiento.getCosto());
            statement.setInt(5, id);
            statement.executeUpdate();
            System.out.println("Mantenimiento actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarMantenimiento(Scanner scanner) {
        System.out.print("Ingrese el ID del mantenimiento a eliminar: ");
        int id = scanner.nextInt();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Mantenimientos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Mantenimiento eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}