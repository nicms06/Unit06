package trabajo;

import java.io.*;
import java.util.*;

/**
 * Gestiona el conjunto de libros de la biblioteca en memoria
 * y la persistencia en el archivo de texto.
 */
public class Biblioteca {
    private static final String ARCHIVO = "src/trabajo/libros.txt";
    private Set<Libro> libros;

    /**
     * Crea una nueva biblioteca e inicializa la colección ordenada de libros.
     */
    public Biblioteca() {
        this.libros = new TreeSet<>();
    }

    /**
     * Añade un libro a la biblioteca comprobando primero si ya existe su ISBN.
     * @param nuevo El libro a añadir.
     * @return true si se añade con éxito, false si ya existe el ISBN.
     */
    public boolean agregarLibro(Libro nuevo) {
        for (Libro l : libros) {
            if (l.getIsbn().equals(nuevo.getIsbn())) {
                return false;
            }
        }
        return libros.add(nuevo);
    }

    /**
     * Busca un libro por su título y su autor.
     * @param titulo Título del libro a buscar.
     * @param autor Autor del libro a buscar.
     * @return El libro encontrado, o null si no existe.
     */
    public Libro buscarPorTituloYAutor(String titulo, String autor) {
        for (Libro l : libros) {
            if (l.getTitulo().equalsIgnoreCase(titulo) && l.getAutor().equalsIgnoreCase(autor)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Busca un libro por su código ISBN.
     * @param isbn ISBN del libro a buscar.
     * @return El libro encontrado, o null si no existe.
     */
    public Libro buscarPorIsbn(String isbn) {
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Lista todos los libros almacenados en la biblioteca.
     */
    public void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("La biblioteca está vacía.");
            return;
        }
        for (Libro l : libros) {
            System.out.println(l);
            System.out.println("---------------------------------------------------");
        }
    }

    /**
     * Guarda la información de los libros en el archivo de texto.
     */
    public void guardarEnFichero() {
        File file = new File(ARCHIVO);
        File carpeta = file.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            for (Libro l : libros) {
                out.write(l.toFileFormat());
                out.newLine();
            }
            System.out.println("¡Datos guardados con éxito en el archivo!");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Carga los libros desde el archivo de texto a la memoria de la aplicación.
     */
    public void cargarDesdeFichero() {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = in.readLine()) != null) {
                String[] partes = linea.split("-");
                if (partes.length == 4) {
                    String titulo = partes[0];
                    String autor = partes[1];
                    String isbn = partes[2];
                    boolean prestado = partes[3].equalsIgnoreCase("sí");

                    Libro l = new Libro(titulo, autor, isbn, prestado);
                    libros.add(l);
                }
            }
            System.out.println("Se han cargado " + libros.size() + " libros desde el fichero.");
        } catch (IOException e) {
            System.out.println("Error al cargar los datos del archivo: " + e.getMessage());
        }
    }
}