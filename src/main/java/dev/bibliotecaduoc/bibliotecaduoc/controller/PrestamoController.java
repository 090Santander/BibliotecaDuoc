package dev.bibliotecaduoc.bibliotecaduoc.controller;

import dev.bibliotecaduoc.bibliotecaduoc.model.Prestamo;
import dev.bibliotecaduoc.bibliotecaduoc.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        return ResponseEntity.ok(prestamoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPrestamoPorId(@PathVariable int id) {
        return prestamoService.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Préstamo no encontrado."));
    }

    @PostMapping
    public ResponseEntity<?> guardarPrestamo(@RequestBody Prestamo prestamo) {
        try {
            Prestamo nuevoPrestamo = prestamoService.guardar(prestamo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPrestamo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPrestamo(@PathVariable int id, @RequestBody Prestamo prestamo) {
        try {
            return prestamoService.actualizar(id, prestamo)
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Préstamo no encontrado."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable int id) {
        boolean eliminado = prestamoService.eliminar(id);

        if (eliminado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}