package ejercicio05;

import java.io.*;
import java.util.Scanner;

public class Ejercicio05 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String ruta = "C:\\Users\\nicolas.mingorance\\IdeaProjects\\Unit06\\src\\ejercicio05\\datos.txt";

        System.out.print("Introduce tu nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Introduce tu edad: ");
        int edad = teclado.nextInt();

        try (PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(ruta, true)))) {

            salida.println("Nombre: " + nombre + " | Edad: " + edad);

            System.out.println("Datos guardados correctamente en " + ruta);

        } catch (IOException e) {
            System.out.println("Error al intentar escribir en el archivo: " + e.getMessage());
        }
    }
}