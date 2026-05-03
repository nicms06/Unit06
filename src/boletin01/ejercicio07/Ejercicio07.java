package boletin01.ejercicio07;

import java.io.*;
import java.util.*;

public class Ejercicio07 {
    private static final String ARCHIVO = "src/boletin01.ejercicio07/agenda.txt";
    private static final int MAX_CONTACTOS = 20;
    private static Map<String, String> agenda = new TreeMap<>();

    public static void main(String[] args) {
        cargarAgenda();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ AGENDA ---");
            System.out.println("1. Nuevo contacto.");
            System.out.println("2. Buscar por nombre.");
            System.out.println("3. Mostrar todos.");
            System.out.println("4. Salir.");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        nuevoContacto(sc);
                        break;
                    case 2:
                        buscarContacto(sc);
                        break;
                    case 3:
                        mostrarTodos();
                        break;
                    case 4:
                        guardarAgenda();
                        System.out.println("¡Datos guardados! Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
            }
        } while (opcion != 4);

        sc.close();
    }

    private static void nuevoContacto(Scanner sc) {
        if (agenda.size() >= MAX_CONTACTOS) {
            System.out.println("Error: La agenda está llena (máximo 20 contactos).");
            return;
        }

        System.out.print("Introduce el nombre del contacto: ");
        String nombre = sc.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        if (agenda.containsKey(nombre)) {
            System.out.println("Error: Ya existe un contacto con ese nombre.");
            return;
        }

        System.out.print("Introduce el teléfono: ");
        String telefono = sc.nextLine().trim();

        agenda.put(nombre, telefono);
        System.out.println("Contacto guardado con éxito.");
    }

    private static void buscarContacto(Scanner sc) {
        System.out.print("Introduce el nombre a buscar: ");
        String nombre = sc.nextLine().trim();

        if (agenda.containsKey(nombre)) {
            System.out.println("Teléfono de " + nombre + ": " + agenda.get(nombre));
        } else {
            System.out.println("No se ha encontrado ningún contacto con ese nombre.");
        }
    }

    private static void mostrarTodos() {
        if (agenda.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }

        System.out.println("\n--- LISTA DE CONTACTOS (Orden Alfabético) ---");
        // Al ser TreeMap, el entrySet ya viene ordenado por la clave (nombre)
        for (Map.Entry<String, String> contacto : agenda.entrySet()) {
            System.out.println("Nombre: " + contacto.getKey() + " | Teléfono: " + contacto.getValue());
        }
    }

    private static void guardarAgenda() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Map.Entry<String, String> contacto : agenda.entrySet()) {
                out.write(contacto.getKey() + ";" + contacto.getValue());
                out.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    private static void cargarAgenda() {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader in = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = in.readLine()) != null) {
                // Separamos el nombre y el teléfono por el punto y coma
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    agenda.put(partes[0], partes[1]);
                }
            }
            System.out.println("Se han cargado " + agenda.size() + " contactos desde el archivo.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de la agenda: " + e.getMessage());
        }
    }
}