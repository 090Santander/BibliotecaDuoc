package dev.bibliotecaduoc.bibliotecaduoc.controller;

import dev.bibliotecaduoc.bibliotecaduoc.model.Prestamo;
import dev.bibliotecaduoc.bibliotecaduoc.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // 🔹 GET: listar todos
    @GetMapping
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        return ResponseEntity.ok(prestamoService.obtenerTodos());
    }

    // 🔹 GET: obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPrestamoPorId(@PathVariable int id) {
        return prestamoService.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "Préstamo no encontrado.")));
    }

    // 🔹 POST: guardar (🔥 CON DEBUG DE ERROR)
    @PostMapping
    public ResponseEntity<?> guardarPrestamo(@RequestBody Prestamo prestamo) {
        try {
            Prestamo nuevoPrestamo = prestamoService.guardar(prestamo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPrestamo);

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 IMPORTANTE (ver error en consola)

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "Error en la solicitud",
                            "detalle", e.getMessage()
                    ));
        }
    }

    // 🔹 PUT: actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPrestamo(@PathVariable int id, @RequestBody Prestamo prestamo) {
        try {
            return prestamoService.actualizar(id, prestamo)
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(Map.of("mensaje", "Préstamo no encontrado.")));

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "Error en la actualización",
                            "detalle", e.getMessage()
                    ));
        }
    }

    // 🔹 DELETE: eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPrestamo(@PathVariable int id) {
        boolean eliminado = prestamoService.eliminar(id);

        if (eliminado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensaje", "Préstamo no encontrado."));
    }
}