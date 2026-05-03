package boletin01.ejercicio04;

import java.io.*;
import java.util.Scanner;

public class Ejercicio04 {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        String ruta = "C:\\Users\\nicolas.mingorance\\IdeaProjects\\Unit06\\src\\ejercicio04\\Cadenas.txt";

        System.out.println("Escribe frases para guardar en el archivo (escribe 'fin' para terminar):");

        try (PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(ruta, true)))) {

            String cadena = "";

            while (!cadena.equalsIgnoreCase("fin")) {
                System.out.print("> ");
                cadena = teclado.nextLine();

                if (!cadena.equalsIgnoreCase("fin")) {
                    salida.println(cadena); // Escribe la cadena y añade el salto de línea automáticamente
                }
            }

            System.out.println("Proceso finalizado. El archivo se ha guardado.");

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }
}