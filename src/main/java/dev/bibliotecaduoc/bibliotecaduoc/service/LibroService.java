package dev.bibliotecaduoc.bibliotecaduoc.service;

import dev.bibliotecaduoc.bibliotecaduoc.model.Libro;
import dev.bibliotecaduoc.bibliotecaduoc.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerLibros() { return libroRepository.obtenerLibros(); }
    public Libro buscarPorId(int id) { return libroRepository.buscarPorId(id); }
    public Libro buscarPorIsbn(String isbn) { return libroRepository.buscarPorIsbn(isbn); }
    public Libro guardar(Libro lib) { return libroRepository.guardar(lib); }
    public Libro actualizar(Libro lib) { return libroRepository.actualizar(lib); }
    public void eliminar(int id) { libroRepository.eliminar(id); }

    // --- NUEVOS MÉTODOS DE REPORTE ---
    public int totalLibros() { return libroRepository.totalLibros(); }
    public int cantidadLibrosPorAnio(int anio) { return libroRepository.cantidadLibrosPorAnio(anio); }
    public List<Libro> buscarPorAutor(String autor) { return libroRepository.buscarPorAutor(autor); }
    public Libro libroMasAntiguo() { return libroRepository.libroMasAntiguo(); }
    public Libro libroMasNuevo() { return libroRepository.libroMasNuevo(); }
    public List<Libro> listarOrdenadosPorAnio() { return libroRepository.listarOrdenadosPorAnio(); }
}