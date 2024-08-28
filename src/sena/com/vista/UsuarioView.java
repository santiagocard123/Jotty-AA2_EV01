package sena.com.vista;

import sena.com.modelo.Usuario;

import java.util.Scanner;

public class UsuarioView {
    public Usuario obtenerDatosUsuario(Scanner scanner) {
        Usuario usuario = new Usuario();
        System.out.print("Nombre: ");
        usuario.setNombre(scanner.nextLine());
        System.out.print("Email: ");
        usuario.setEmail(scanner.nextLine());
        System.out.print("Teléfono: ");
        usuario.setTelefono(scanner.nextLine());
        return usuario;
    }
}