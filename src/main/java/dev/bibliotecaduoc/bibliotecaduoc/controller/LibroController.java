package dev.bibliotecaduoc.bibliotecaduoc.controller;

import dev.bibliotecaduoc.bibliotecaduoc.model.Libro;
import dev.bibliotecaduoc.bibliotecaduoc.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> obtenerLibros() { return libroService.obtenerLibros(); }

    @GetMapping("/{id}")
    public Libro buscarPorId(@PathVariable int id) { return libroService.buscarPorId(id); }

    @PostMapping
    public Libro guardar(@RequestBody Libro libro) { return libroService.guardar(libro); }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable int id, @RequestBody Libro libro) {
        libro.setId(id);
        return libroService.actualizar(libro);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) { libroService.eliminar(id); }





    // --- NUEVOS ENDPOINTS DE REPORTE ---

    // 1. Total de libros
    @GetMapping("/total")
    public int total() {
        return libroService.totalLibros();
    }

    // 2. Buscar un libro por ISBN
    @GetMapping("/isbn/{isbn}")
    public Libro buscarPorIsbn(@PathVariable String isbn) {
        return libroService.buscarPorIsbn(isbn);
    }

    // 3. Cantidad de libros en un año
    @GetMapping("/anio/{anio}/cantidad")
        public int cantidadLibrosPorAnio(@PathVariable int anio) {
        return libroService.cantidadLibrosPorAnio(anio);
    }

    // 4. Buscar por autor
    @GetMapping("/autor/{autor}")
    public List<Libro> buscarPorAutor(@PathVariable String autor) {
        return libroService.buscarPorAutor(autor);
    }

    // 5. Libro más antiguo
    @GetMapping("/antiguo")
    public Libro libroMasAntiguo() {
        return libroService.libroMasAntiguo();
    }

    // 6. Libro más nuevo
    @GetMapping("/nuevo")
    public Libro libroMasNuevo() {
        return libroService.libroMasNuevo();
    }

    // 7. Libros ordenados por año
    @GetMapping("/ordenados")
    public List<Libro> listarOrdenadosPorAnio() {
        return libroService.listarOrdenadosPorAnio();
    }
}