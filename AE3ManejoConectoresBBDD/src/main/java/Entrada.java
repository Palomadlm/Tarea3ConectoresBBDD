import java.sql.SQLException;
import controller.Concesionario;
import model.Coche;
import model.Pasajero;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {

    // Inicializamos Scanner y Concesionario como variables globales
    private static Scanner scanner = new Scanner(System.in);
    private static Concesionario concesionario;

    public static void main(String[] args) {
        try {
            concesionario = new Concesionario();
            // Bucle principal del menú
            while (true) {
                menuPrincipal();
                try {
                    int opcion = Integer.parseInt(scanner.nextLine());
                    switch (opcion) {
                        case 1: {
                            añadirNuevoCoche();
                            break;
                        }
                        case 2: {
                            borrarCoche();
                            break;
                        }
                        case 3: {
                            consultarCochePorId();
                            break;
                        }
                        case 4: {
                            modificarCoche();
                            break;
                        }
                        case 5: {
                            listarCoches();
                            break;
                        }
                        case 6: {
                            gestionPasajeros();
                            break;
                        }
                        case 7: {
                            System.out.println("Cerrando el programa..");
                            return; // Termina el programa
                        }
                        default: {
                            System.out.println("Intenta una opción valida");
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número.");
                } catch (SQLException e) {
                    System.out.println("Error en la base de datos: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void menuPrincipal() {

        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Añadir nuevo coche");
        System.out.println("2. Borrar coche por ID");
        System.out.println("3. Consultar coche por ID");
        System.out.println("4. Modificar coche por ID");
        System.out.println("5. Listar todos los coches");
        System.out.println("6. Gestión de pasajeros");
        System.out.println("7. Terminar el programa");
        System.out.print("Seleccione una opción: ");
    }

    // Métodos de Coche
    private static void añadirNuevoCoche() throws SQLException {
        System.out.print("Introduzca la matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Introduzca la marca: ");
        String marca = scanner.nextLine();
        System.out.print("Introduzca el modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Introduzca el color: ");
        String color = scanner.nextLine();

        Coche coche = new Coche(matricula, marca, modelo, color);
        concesionario.añadirCoche(coche);
        System.out.println("Coche añadido correctamente.");
    }

    private static void borrarCoche() throws SQLException {
        System.out.print("Introduzca el ID del coche a borrar: ");
        int id = Integer.parseInt(scanner.nextLine());
        concesionario.borrarCoche(id);
        System.out.println("Coche borrado correctamente.");
    }

    private static void consultarCochePorId() throws SQLException {
        System.out.print("Introduzca el ID del coche: ");
        int id = Integer.parseInt(scanner.nextLine());
        Coche coche = concesionario.consultarCochePorId(id);
        if (coche == null) {
            System.out.println("No se encontró ningún coche con ese ID.");
        } else {
            coche.mostrarDatos();
        }
    }

    private static void modificarCoche() throws SQLException {
        System.out.print("Introduzca el ID del coche a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduzca los nuevos datos del coche:");
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();

        Coche coche = new Coche(matricula, marca, modelo, color);
        concesionario.modificarCoche(id, coche);
        System.out.println("Coche modificado correctamente.");
    }

    private static void listarCoches() throws SQLException {
        ArrayList<Coche> coches = concesionario.listarCoches();
        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
        } else {
            coches.forEach(Coche::mostrarDatos);
        }
    }

    // Menú Pasajeros
    private static void gestionPasajeros() throws SQLException {
        while (true) {
            mostrarMenuPasajeros();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1: {
                        añadirNuevoPasajero();
                        break;
                    }
                    case 2: {
                        borrarPasajero();
                        break;
                    }
                    case 3: {
                        consultarPasajeroPorId();
                        break;
                    }
                    case 4: {
                        listarTodosLosPasajeros();
                        break;
                    }
                    case 5: {
                        añadirPasajeroCoche();
                        break;
                    }
                    case 6: {
                        eliminarPasajeroCoche();
                        break;
                    }
                    case 7: {
                        listarPasajerosDeCoche();
                        break;
                    }
                    case 8: {
                        System.out.println("Volviendo al menú principal...");
                        return; // Sale del submenú
                    }
                    default: {
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Introduzca un número.");
            }
        }
    }

    private static void mostrarMenuPasajeros() {
        System.out.println("\n--- Gestión de Pasajeros ---");
        System.out.println("1. Añadir nuevo pasajero");
        System.out.println("2. Borrar pasajero por ID");
        System.out.println("3. Consultar pasajero por ID");
        System.out.println("4. Listar todos los pasajeros");
        System.out.println("5. Añadir pasajero a coche");
        System.out.println("6. Eliminar pasajero de coche");
        System.out.println("7. Listar todos los pasajeros de un coche");
        System.out.println("8. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    private static void añadirNuevoPasajero() throws SQLException {
        System.out.print("Introduzca el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduzca la edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.print("Introduzca el peso: ");
        double peso = Double.parseDouble(scanner.nextLine());

        Pasajero pasajero = new Pasajero();
        pasajero.setNombre(nombre);
        pasajero.setEdad(edad);
        pasajero.setPeso(peso);

        concesionario.añadirPasajero(pasajero);
        System.out.println("Pasajero añadido correctamente.");
    }

    private static void borrarPasajero() throws SQLException {
        System.out.print("Introduzca el ID del pasajero a borrar: ");
        int id = Integer.parseInt(scanner.nextLine());
        concesionario.borrarPasajero(id);
        System.out.println("Pasajero borrado correctamente.");
    }

    private static void consultarPasajeroPorId() throws SQLException {
        System.out.print("Introduzca el ID del pasajero: ");
        int id = Integer.parseInt(scanner.nextLine());
        ArrayList<Pasajero> pasajeros = concesionario.consultarPasajeroId(id); // Devuelve una lista de pasajeros
        if (pasajeros.isEmpty()) {
            System.out.println("No se encontró ningún pasajero con ese ID.");
        } else {
            // Accedemos al primer pasajero en la lista
            pasajeros.get(0).mostrarDatos(); // Llamamos a mostrarDatos en el primer pasajero de la lista
        }
    }

    private static void listarTodosLosPasajeros() throws SQLException {
        ArrayList<Pasajero> pasajeros = concesionario.listarPasajeros();
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
        } else {
            pasajeros.forEach(Pasajero::mostrarDatos);
        }
    }

    private static void añadirPasajeroCoche() throws SQLException {
        System.out.print("Introduzca el ID del coche: ");
        int idCoche = Integer.parseInt(scanner.nextLine());
        System.out.print("Introduzca el ID del pasajero: ");
        int idPasajero = Integer.parseInt(scanner.nextLine());
        concesionario.añadirPasajeroACoche(idCoche, idPasajero);
        System.out.println("Pasajero añadido al coche correctamente.");
    }

    private static void eliminarPasajeroCoche() throws SQLException {
        System.out.print("Introduzca el ID del coche: ");
        int idCoche = Integer.parseInt(scanner.nextLine());
        System.out.print("Introduzca el ID del pasajero: ");
        int idPasajero = Integer.parseInt(scanner.nextLine());
        concesionario.eliminarPasajeroDeCoche(idCoche);
        System.out.println("Pasajero eliminado del coche correctamente.");
    }

    private static void listarPasajerosDeCoche() throws SQLException {
        System.out.print("Introduzca el ID del coche: ");
        int idCoche = Integer.parseInt(scanner.nextLine());
        ArrayList<Pasajero> pasajeros = concesionario.listarPasajerosDeCoche(idCoche);
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros en este coche.");
        } else {
            pasajeros.forEach(Pasajero::mostrarDatos);
        }
    }
}