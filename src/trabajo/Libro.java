package trabajo;

import java.util.Objects;

/**
 * Representa un libro de la biblioteca con sus datos principales.
 * Implementa la interfaz Comparable para mantener los libros ordenados
 * primero por autor y luego por título.
 */
public class Libro implements Comparable<Libro> {
    private String titulo;
    private String autor;
    private String isbn;
    private boolean prestado;

    /**
     * Constructor para crear un nuevo libro.
     * @param titulo El título del libro.
     * @param autor El autor del libro.
     * @param isbn El ISBN único de 13 dígitos.
     * @param prestado Indica si el libro está prestado o no.
     */
    public Libro(String titulo, String autor, String isbn, boolean prestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.prestado = prestado;
    }

    /**
     * Valida si un ISBN tiene exactamente 13 dígitos numéricos.
     * @param isbn El código ISBN a validar.
     * @return true si es válido, false en caso contrario.
     */
    public static boolean validarIsbn(String isbn) {
        if (isbn == null) {
            return false;
        }
        return isbn.matches("\\d{13}");
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    /**
     * Compara dos libros para mantener el orden.
     * Ordena primero por autor (ignorando mayúsculas) y luego por título.
     */
    @Override
    public int compareTo(Libro otro) {
        int compAutor = this.autor.compareToIgnoreCase(otro.autor);
        if (compAutor != 0) {
            return compAutor;
        }
        return this.titulo.compareToIgnoreCase(otro.titulo);
    }

    /**
     * Considera que dos libros son iguales si tienen el mismo ISBN.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libro libro = (Libro) obj;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    /**
     * Muestra la información del libro en el formato solicitado.
     */
    @Override
    public String toString() {
        return "Título: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "ISBN: " + isbn + "\n" +
                (prestado ? "Prestado" : "Disponible");
    }

    /**
     * Convierte el libro a una cadena con el formato para guardar en el fichero.
     * @return Cadena formateada para persistencia.
     */
    public String toFileFormat() {
        return titulo + "-" + autor + "-" + isbn + "-" + (prestado ? "sí" : "no");
    }
}