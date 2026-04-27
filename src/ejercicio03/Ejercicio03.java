package ejercicio03;

import java.io.*;

public class Ejercicio03 {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\nicolas.mingorance\\IdeaProjects\\Unit06\\src\\ejercicio03\\Alumnos.txt";

        double sumaEdades = 0;
        double sumaEstaturas = 0;
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            System.out.println("Lista de Alumnos:");
            System.out.println("-----------------");

            while ((linea = br.readLine()) != null) {
                // Dividimos la línea por los espacios
                // partes[0] = nombre, partes[1] = edad, partes[2] = estatura
                String[] partes = linea.trim().split("\\s+");

                if (partes.length == 3) {
                    String nombre = partes[0];
                    // Usamos los métodos que pide el enunciado
                    int edad = Integer.parseInt(partes[1]);
                    double estatura = Double.parseDouble(partes[2]);

                    System.out.println("- " + nombre);

                    sumaEdades += edad;
                    sumaEstaturas += estatura;
                    contador++;
                }
            }

            if (contador > 0) {
                System.out.println("-----------------");
                System.out.printf("Media de edad: %.2f años%n", (sumaEdades / contador));
                System.out.printf("Media de estatura: %.2f metros%n", (sumaEstaturas / contador));
            } else {
                System.out.println("El archivo está vacío.");
            }

        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número incorrecto en el archivo.");
        }
    }
}