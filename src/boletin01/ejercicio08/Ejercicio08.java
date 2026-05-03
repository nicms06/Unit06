package boletin01.ejercicio08;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Ejercicio08 {
    private static final String ARCHIVO = "src/boletin01.ejercicio07/temperaturas.txt";
    private static List<Registro> historial = new ArrayList<>();

    static class Registro {
        LocalDate fecha;
        double max;
        double min;

        public Registro(LocalDate fecha, double max, double min) {
            this.fecha = fecha;
            this.max = max;
            this.min = min;
        }

        @Override
        public String toString() {
            return fecha + " | Máxima: " + max + "°C | Mínima: " + min + "°C";
        }
    }

    public static void main(String[] args) {
        cargarHistorial();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- ESTACIÓN METEOROLÓGICA ---");
            System.out.println("1. Registrar nueva temperatura.");
            System.out.println("2. Mostrar historial de registros.");
            System.out.println("3. Salir.");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        registrarTemperatura(sc);
                        break;
                    case 2:
                        mostrarHistorial();
                        break;
                    case 3:
                        System.out.println("¡Saliendo del programa!");
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

    private static void registrarTemperatura(Scanner sc) {
        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print("Introduce la fecha (AAAA-MM-DD) o pulsa Enter para hoy: ");
            String inputFecha = sc.nextLine().trim();

            if (inputFecha.isEmpty()) {
                fecha = LocalDate.now(); // Usa LocalDate.now() de los apuntes
            } else {
                try {
                    fecha = LocalDate.parse(inputFecha);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha incorrecto. Usa AAAA-MM-DD.");
                }
            }
        }

        for (Registro r : historial) {
            if (r.fecha.equals(fecha)) {
                System.out.println("Ya existe un registro para la fecha " + fecha);
                return;
            }
        }

        try {
            System.out.print("Introduce la temperatura máxima: ");
            double max = Double.parseDouble(sc.nextLine());

            System.out.print("Introduce la temperatura mínima: ");
            double min = Double.parseDouble(sc.nextLine());

            if (min > max) {
                System.out.println("Error: La temperatura mínima no puede ser mayor que la máxima.");
                return;
            }

            Registro nuevo = new Registro(fecha, max, min);
            historial.add(nuevo);
            guardarRegistroEnArchivo(nuevo);

            System.out.println("¡Registro guardado con éxito!");

        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce un valor numérico válido para las temperaturas.");
        }
    }

    private static void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay datos registrados en el historial.");
            return;
        }

        System.out.println("\n--- HISTORIAL DE TEMPERATURAS ---");
        double maxAbsoluta = Double.NEGATIVE_INFINITY;
        double minAbsoluta = Double.POSITIVE_INFINITY;

        for (Registro r : historial) {
            System.out.println(r);
            if (r.max > maxAbsoluta) {
                maxAbsoluta = r.max;
            }
            if (r.min < minAbsoluta) {
                minAbsoluta = r.min;
            }
        }

        System.out.println("---------------------------------");
        System.out.println("Temperatura máxima histórica: " + maxAbsoluta + "°C");
        System.out.println("Temperatura mínima histórica: " + minAbsoluta + "°C");
    }

    private static void guardarRegistroEnArchivo(Registro r) {
        File file = new File(ARCHIVO);

        File carpeta = file.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        boolean escribirCabecera = !file.exists() || file.length() == 0;

        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
            if (escribirCabecera) {
                out.write("Fecha,Temperatura máxima,Temperatura mínima");
                out.newLine();
            }
            out.write(r.fecha + "," + r.max + "," + r.min);
            out.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    private static void cargarHistorial() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader in = new BufferedReader(new FileReader(archivo))) {
            String linea = in.readLine();

            while ((linea = in.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    LocalDate fecha = LocalDate.parse(partes[0]);
                    double max = Double.parseDouble(partes[1]);
                    double min = Double.parseDouble(partes[2]);
                    historial.add(new Registro(fecha, max, min));
                }
            }
            System.out.println("Se han cargado " + historial.size() + " registros desde el archivo.");
        } catch (IOException | DateTimeParseException | NumberFormatException e) {
            System.out.println("Nota: No se pudo cargar el historial anterior o el archivo está vacío.");
        }
    }
}