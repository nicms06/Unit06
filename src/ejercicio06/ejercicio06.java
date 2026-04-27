package ejercicio06;

import java.io.*;
import java.util.*;

public class ejercicio06 {
    public static void main(String[] args) {

        String rutaEntrada = "C:\\Users\\nicolas.mingorance\\IdeaProjects\\Unit06\\src\\ejercicio06\\desordenados.txt";
        String rutaSalida = "C:\\Users\\nicolas.mingorance\\IdeaProjects\\Unit06\\src\\ejercicio06\\ordenados.txt";

        List<Integer> listaNumeros = new ArrayList<>();

        try (Scanner sc = new Scanner(new FileReader(rutaEntrada))) {
            while (sc.hasNextInt()) {
                listaNumeros.add(sc.nextInt());
            }
            System.out.println("Archivo leído correctamente. Números encontrados: " + listaNumeros.size());

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return; // Salimos si no se puede leer el origen
        }

        Collections.sort(listaNumeros);
        System.out.println("Lista ordenada ascendentemente.");

        try (PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(rutaSalida)))) {
            for (Integer num : listaNumeros) {
                salida.println(num); // Escribe cada número en una línea nueva
            }
            System.out.println("Datos guardados en: " + rutaSalida);

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}