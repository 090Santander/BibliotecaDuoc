package dev.bibliotecaduoc.bibliotecaduoc.repository;

import dev.bibliotecaduoc.bibliotecaduoc.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRepository {

    private List<Libro> listaLibros = new ArrayList<>();

    public LibroRepository() {
        listaLibros.add(new Libro(1, "9789569646638", "Fuego y Sangre", "Penguin Random House Grupo Editorial", 2018, "George R. R. Martin"));
        listaLibros.add(new Libro(2, "9789563494150", "Quique Hache: El Mall Embrujado y Otras Historias", "Sm Ediciones", 2014, "Sergio Gomez"));
        listaLibros.add(new Libro(3, "9781484256251", "Spring Boot Persistence Best Practices", "Apress", 2020, "Anghel Leonard"));
        listaLibros.add(new Libro(4, "9789566075752", "Harry Potter y la piedra filosofal", "Salamandra", 2024, "J. K. Rowling"));
        listaLibros.add(new Libro(5, "9780439139601", "Harry Potter y el prisionero de Azkaban", "Scholastic", 1999, "J. K. Rowling"));
        listaLibros.add(new Libro(6, "9780439136365", "Harry Potter y el cáliz de fuego", "Scholastic", 2000, "J. K. Rowling"));
        listaLibros.add(new Libro(7, "9780321127426", "Effective Java", "Addison-Wesley", 2008, "Joshua Bloch"));
        listaLibros.add(new Libro(8, "9780134685991", "Clean Architecture", "Prentice Hall", 2017, "Robert C. Martin"));
        listaLibros.add(new Libro(9, "9780201633610", "Design Patterns", "Addison-Wesley", 1994, "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides"));
        listaLibros.add(new Libro(10, "9780132350884", "Clean Code", "Prentice Hall", 2008, "Robert C. Martin"));
    }

    public List<Libro> obtenerLibros() { return listaLibros; }

    public Libro buscarPorId(int id) {
        for (Libro libro : listaLibros) {
            if (libro.getId() == id) return libro;
        }
        return null;
    }

    public Libro buscarPorIsbn(String isbn) {
        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equals(isbn)) return libro;
        }
        return null;
    }

    public Libro guardar(Libro lib) {
        listaLibros.add(lib);
        return lib;
    }

    public Libro actualizar(Libro lib) {
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getId() == lib.getId()) {
                listaLibros.set(i, lib);
                return lib;
            }
        }
        return null;
    }

    public void eliminar(int id) {
        listaLibros.removeIf(x -> x.getId() == id);
    }

    // --- NUEVOS MÉTODOS DE REPORTE ---

    // Total de libros
    public int totalLibros() {
        return listaLibros.size();
    }

    // Cantidad de libros en un año específico
    public int cantidadLibrosPorAnio(int anio) {
        int contador = 0;
        for (Libro libro : listaLibros) {
            if (libro.getFechaPublicacion() == anio) {
                contador++;
            }
        }
        return contador;
    }

    // Buscar por autor
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> librosAutor = new ArrayList<>();
        for (Libro libro : listaLibros) {
            if (libro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                librosAutor.add(libro);
            }
        }
        return librosAutor;
    }

    // Buscar el libro más antiguo
    public Libro libroMasAntiguo() {
        if (listaLibros.isEmpty()) return null;
        Libro masAntiguo = listaLibros.get(0);
        for (Libro libro : listaLibros) {
            if (libro.getFechaPublicacion() < masAntiguo.getFechaPublicacion()) {
                masAntiguo = libro;
            }
        }
        return masAntiguo;
    }

    // Buscar el libro más nuevo
    public Libro libroMasNuevo() {
        if (listaLibros.isEmpty()) return null;
        Libro masNuevo = listaLibros.get(0);
        for (Libro libro : listaLibros) {
            if (libro.getFechaPublicacion() > masNuevo.getFechaPublicacion()) {
                masNuevo = libro;
            }
        }
        return masNuevo;
    }

    // Listar todos los libros ordenados por año de publicación
    public List<Libro> listarOrdenadosPorAnio() {
        List<Libro> listaOrdenada = new ArrayList<>(listaLibros);
        listaOrdenada.sort((l1, l2) -> Integer.compare(l1.getFechaPublicacion(), l2.getFechaPublicacion()));
        return listaOrdenada;
    }
    public List<Libro> findAll() {
        return listaLibros;
    }
}