package boletin02.ejercicio03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;


public class Ejercicio03 {
    private static final String ARCHIVO = "src/boletin02/ejercicio01/carta.txt";

    public static void main(String[] args) {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe en la ruta especificada.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        try (BufferedReader in = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int contadorLineas = 0;

            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
                contadorLineas++;

                if (contadorLineas == 24) {
                    System.out.print("\n--- Presiona Enter para ver más ---");
                    sc.nextLine();
                    contadorLineas = 0;
                }
            }

            System.out.println("\n--- Fin del archivo ---");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
