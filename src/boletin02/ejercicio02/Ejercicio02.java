package boletin02.ejercicio02;

import java.io.*;
import java.util.*;

public class Ejercicio02 {
    private static final String ARCHIVO = "src/boletin02/ejercicio02/firmas.txt";
    private static List<String> firmas = new ArrayList<>();

    public static void main(String[] args) {
        cargarFirmas();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- LIBRO DE FIRMAS ---");
            System.out.println("1. Mostrar libro de firmas.");
            System.out.println("2. Insertar nuevo nombre.");
            System.out.println("3. Salir.");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        mostrarFirmas();
                        break;
                    case 2:
                        insertarFirma(sc);
                        break;
                    case 3:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
            }
        } while (opcion != 3);

        sc.close();
    }

    private static void mostrarFirmas() {
        if (firmas.isEmpty()) {
            System.out.println("El libro de firmas está vacío.");
            return;
        }
        System.out.println("\n--- FIRMAS REGISTRADAS ---");
        for (String firma : firmas) {
            System.out.println("- " + firma);
        }
    }

    private static void insertarFirma(Scanner sc) {
        System.out.print("Introduce tu nombre para firmar: ");
        String nombre = sc.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        if (firmas.contains(nombre)) {
            System.out.println("Este nombre ya se encuentra en el libro de firmas.");
            return;
        }

        firmas.add(nombre);
        guardarFirmaEnArchivo(nombre);
        System.out.println("¡Firma añadida correctamente!");
    }

    private static void guardarFirmaEnArchivo(String nombre) {
        File file = new File(ARCHIVO);
        File carpeta = file.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
            out.write(nombre);
            out.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    private static void cargarFirmas() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader in = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = in.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    firmas.add(linea.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
