package sena.com.mantenimiento.usuario;

import sena.com.modelo.Usuario;
import sena.com.vista.UsuarioView;
import sena.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioController {
    private UsuarioView usuarioView;

    public UsuarioController() {
        usuarioView = new UsuarioView();
    }

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Actualizar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("0. Volver");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarUsuario(scanner);
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    actualizarUsuario(scanner);
                    break;
                case 4:
                    eliminarUsuario(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void registrarUsuario(Scanner scanner) {
        Usuario usuario = usuarioView.obtenerDatosUsuario(scanner);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Usuarios (nombre, email, telefono) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getTelefono());
            statement.executeUpdate();
            System.out.println("Usuario registrado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarUsuarios() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Usuarios";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Teléfono: " + resultSet.getString("telefono"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(Scanner scanner) {
        System.out.print("Ingrese el ID del usuario a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Usuario usuario = usuarioView.obtenerDatosUsuario(scanner);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE Usuarios SET nombre = ?, email = ?, telefono = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getTelefono());
            statement.setInt(4, id);
            statement.executeUpdate();
            System.out.println("Usuario actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(Scanner scanner) {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int id = scanner.nextInt();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Usuarios WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Usuario eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}