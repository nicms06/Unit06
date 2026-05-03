package boletin02.ejercicio01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Ejercicio01 {
    private static final String ARCHIVO = "src/boletin02/ejercicio01/carta.txt";

    public static void main(String[] args) {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("El archivo " + ARCHIVO + " no existe. Por favor, créalo primero.");
            return;
        }

        int totalLineas = 0;
        int totalPalabras = 0;
        int totalCaracteres = 0;

        try (BufferedReader in = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = in.readLine()) != null) {
                totalLineas++;

                totalCaracteres += linea.length();

                linea = linea.trim();

                if (!linea.isEmpty()) {
                    String[] palabras = linea.split("\\s+");
                    totalPalabras += palabras.length;
                }
            }

            System.out.println("--- RESULTADOS DEL ANÁLISIS ---");
            System.out.println("Total de líneas: " + totalLineas);
            System.out.println("Total de palabras: " + totalPalabras);
            System.out.println("Total de caracteres: " + totalCaracteres);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}