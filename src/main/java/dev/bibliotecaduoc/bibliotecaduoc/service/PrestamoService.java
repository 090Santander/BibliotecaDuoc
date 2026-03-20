package dev.bibliotecaduoc.bibliotecaduoc.service;

import dev.bibliotecaduoc.bibliotecaduoc.model.Libro;
import dev.bibliotecaduoc.bibliotecaduoc.model.Prestamo;
import dev.bibliotecaduoc.bibliotecaduoc.repository.LibroRepository;
import dev.bibliotecaduoc.bibliotecaduoc.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
    }

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public Optional<Prestamo> obtenerPorId(int idPrestamo) {
        return prestamoRepository.findById(idPrestamo);
    }

    public Prestamo guardar(Prestamo prestamo) {
        validarPrestamo(prestamo);
        prestamo.setIdPrestamo(0);

        if (prestamo.getMultas() < 0) {
            prestamo.setMultas(0);
        }

        return prestamoRepository.save(prestamo);
    }

    public Optional<Prestamo> actualizar(int idPrestamo, Prestamo prestamoActualizado) {
        Optional<Prestamo> prestamoExistente = prestamoRepository.findById(idPrestamo);

        if (prestamoExistente.isEmpty()) {
            return Optional.empty();
        }

        validarPrestamo(prestamoActualizado);

        Prestamo prestamo = prestamoExistente.get();
        prestamo.setIdPrestamo(idPrestamo);
        prestamo.setIdLibro(prestamoActualizado.getIdLibro());
        prestamo.setRunSolicitante(prestamoActualizado.getRunSolicitante());
        prestamo.setFechaSolicitud(prestamoActualizado.getFechaSolicitud());
        prestamo.setFechaEntrega(prestamoActualizado.getFechaEntrega());
        prestamo.setCantidadDias(prestamoActualizado.getCantidadDias());
        prestamo.setMultas(Math.max(prestamoActualizado.getMultas(), 0));

        return Optional.of(prestamoRepository.save(prestamo));
    }

    public boolean eliminar(int idPrestamo) {
        return prestamoRepository.deleteById(idPrestamo);
    }

    private void validarPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser nulo.");
        }

        if (prestamo.getIdLibro() <= 0) {
            throw new IllegalArgumentException("El id del libro es obligatorio.");
        }

        if (prestamo.getRunSolicitante() == null || prestamo.getRunSolicitante().trim().isEmpty()) {
            throw new IllegalArgumentException("El RUN del solicitante es obligatorio.");
        }

        if (prestamo.getFechaSolicitud() == null) {
            throw new IllegalArgumentException("La fecha de solicitud es obligatoria.");
        }

        if (prestamo.getCantidadDias() <= 0) {
            throw new IllegalArgumentException("La cantidad de días debe ser mayor a 0.");
        }

        boolean libroExiste = libroRepository.findAll().stream()
                .map(Libro::getId)
                .anyMatch(id -> id == prestamo.getIdLibro());

        if (!libroExiste) {
            throw new IllegalArgumentException("El libro asociado no existe.");
        }
    }
}