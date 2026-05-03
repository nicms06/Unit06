package trabajo;

import java.util.Scanner;

/**
 * Clase principal que ejecuta el menú interactivo de la biblioteca.
 */
public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.cargarDesdeFichero();

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE BIBLIOTECA ---");
            System.out.println("1. Añadir libro.");
            System.out.println("2. Buscar libro.");
            System.out.println("3. Listar libros.");
            System.out.println("4. Prestar libro.");
            System.out.println("5. Devolver un libro.");
            System.out.println("6. Guardar.");
            System.out.println("7. Salir.");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        agregarLibroMenu(biblioteca, sc);
                        break;
                    case 2:
                        buscarLibroMenu(biblioteca, sc);
                        break;
                    case 3:
                        biblioteca.listarLibros();
                        break;
                    case 4:
                        prestarLibroMenu(biblioteca, sc);
                        break;
                    case 5:
                        devolverLibroMenu(biblioteca, sc);
                        break;
                    case 6:
                        biblioteca.guardarEnFichero();
                        break;
                    case 7:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
            }
        } while (opcion != 7);

        sc.close();
    }

    private static void agregarLibroMenu(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Introduce el título: ");
        String titulo = sc.nextLine().trim();

        System.out.print("Introduce el autor: ");
        String autor = sc.nextLine().trim();

        String isbn = "";
        boolean isbnValido = false;
        while (!isbnValido) {
            System.out.print("Introduce el ISBN (13 dígitos numéricos): ");
            isbn = sc.nextLine().trim();
            if (Libro.validarIsbn(isbn)) {
                isbnValido = true;
            } else {
                System.out.println("ISBN no válido. Debe tener exactamente 13 dígitos numéricos.");
            }
        }

        Libro nuevo = new Libro(titulo, autor, isbn, false);
        if (biblioteca.agregarLibro(nuevo)) {
            System.out.println("¡Libro añadido correctamente!");
        } else {
            System.out.println("Error: Ya existe un libro con ese ISBN en el sistema.");
        }
    }

    private static void buscarLibroMenu(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Introduce el título del libro: ");
        String titulo = sc.nextLine().trim();

        System.out.print("Introduce el autor del libro: ");
        String autor = sc.nextLine().trim();

        Libro l = biblioteca.buscarPorTituloYAutor(titulo, autor);
        if (l != null) {
            System.out.println("\n--- Información del Libro ---");
            System.out.println(l);
        } else {
            System.out.println("No se ha encontrado ningún libro con ese título y autor.");
        }
    }

    private static void prestarLibroMenu(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Introduce el ISBN del libro a prestar: ");
        String isbn = sc.nextLine().trim();

        Libro l = biblioteca.buscarPorIsbn(isbn);
        if (l == null) {
            System.out.println("El libro indicado no existe.");
        } else if (l.isPrestado()) {
            System.out.println("No se puede prestar porque ya se encuentra prestado.");
        } else {
            l.setPrestado(true);
            System.out.println("El libro ha sido prestado con éxito.");
        }
    }

    private static void devolverLibroMenu(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Introduce el ISBN del libro a devolver: ");
        String isbn = sc.nextLine().trim();

        Libro l = biblioteca.buscarPorIsbn(isbn);
        if (l == null) {
            System.out.println("El libro indicado no existe.");
        } else if (!l.isPrestado()) {
            System.out.println("Este libro ya estaba disponible (no estaba prestado).");
        } else {
            l.setPrestado(false);
            System.out.println("El libro ha sido devuelto con éxito.");
        }
    }
}