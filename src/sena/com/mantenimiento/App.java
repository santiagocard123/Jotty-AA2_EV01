package sena.com.mantenimiento;

import sena.com.mantenimiento.controlador.EquipoController;
import sena.com.mantenimiento.controlador.MantenimientoController;
import sena.com.mantenimiento.usuario.UsuarioController;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EquipoController equipoController = new EquipoController();
        MantenimientoController mantenimientoController = new MantenimientoController();
        UsuarioController usuarioController = new UsuarioController();

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Gestionar Equipos");
            System.out.println("2. Gestionar Mantenimientos");
            System.out.println("3. Gestionar Usuarios");
            System.out.println("0. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    equipoController.menu(scanner);
                    break;
                case 2:
                    mantenimientoController.menu(scanner);
                    break;
                case 3:
                    usuarioController.menu(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}