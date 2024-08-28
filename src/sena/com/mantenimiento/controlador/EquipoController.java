package sena.com.mantenimiento.controlador;

import sena.com.modelo.Equipo;
import sena.com.vista.EquipoView;
import sena.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EquipoController {
    private EquipoView equipoView;

    public EquipoController() {
        equipoView = new EquipoView();
    }

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("1. Crear Equipo");
            System.out.println("2. Listar Equipos");
            System.out.println("3. Actualizar Equipo");
            System.out.println("4. Eliminar Equipo");
            System.out.println("0. Volver");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearEquipo(scanner);
                    break;
                case 2:
                    listarEquipos();
                    break;
                case 3:
                    actualizarEquipo(scanner);
                    break;
                case 4:
                    eliminarEquipo(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void crearEquipo(Scanner scanner) {
        Equipo equipo = equipoView.obtenerDatosEquipo(scanner);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Equipos (nombre, marca, modelo, estado, fecha_adquisicion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, equipo.getNombre());
            statement.setString(2, equipo.getMarca());
            statement.setString(3, equipo.getModelo());
            statement.setString(4, equipo.getEstado());
            statement.setDate(5, java.sql.Date.valueOf(equipo.getFechaAdquisicion()));
            statement.executeUpdate();
            System.out.println("Equipo creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarEquipos() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Equipos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Marca: " + resultSet.getString("marca"));
                System.out.println("Modelo: " + resultSet.getString("modelo"));
                System.out.println("Estado: " + resultSet.getString("estado"));
                System.out.println("Fecha de Adquisición: " + resultSet.getDate("fecha_adquisicion"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarEquipo(Scanner scanner) {
        System.out.print("Ingrese el ID del equipo a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Equipo equipo = equipoView.obtenerDatosEquipo(scanner);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE Equipos SET nombre = ?, marca = ?, modelo = ?, estado = ?, fecha_adquisicion = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, equipo.getNombre());
            statement.setString(2, equipo.getMarca());
            statement.setString(3, equipo.getModelo());
            statement.setString(4, equipo.getEstado());
            statement.setDate(5, java.sql.Date.valueOf(equipo.getFechaAdquisicion()));
            statement.setInt(6, id);
            statement.executeUpdate();
            System.out.println("Equipo actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEquipo(Scanner scanner) {
        System.out.print("Ingrese el ID del equipo a eliminar: ");
        int id = scanner.nextInt();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Equipos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Equipo eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}