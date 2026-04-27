package ejercicio02;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio02 {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\nicolas.mingorance\\IdeaProjects\\Unit06\\src\\ejercicio02\\Enteros.txt";

        double suma = 0;
        double contador = 0;

        try{
            Scanner sc = new Scanner(new FileReader(ruta));

            while (sc.hasNextDouble()){
                double numero = sc.nextDouble();
                suma += numero;
                contador ++;
            }

            if(contador > 0){
                double media = suma / contador;
                System.out.println("Suma: " + suma);
                System.out.println("Media: " + media);
            } else{
                System.out.println("El archivo no contiene números.");
            }

            sc.close();

        } catch (IOException e){
            System.out.println("Error al acceder al fichero: " + e.getMessage());
        }
    }
}
