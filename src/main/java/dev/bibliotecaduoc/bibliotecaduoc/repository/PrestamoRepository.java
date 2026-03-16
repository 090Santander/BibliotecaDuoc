package dev.bibliotecaduoc.bibliotecaduoc.repository;

import dev.bibliotecaduoc.bibliotecaduoc.model.Prestamo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PrestamoRepository {

    private final List<Prestamo> listaPrestamos = new ArrayList<>();
    private int contadorId = 1;

    public List<Prestamo> findAll() {
        return listaPrestamos;
    }

    public Optional<Prestamo> findById(int idPrestamo) {
        return listaPrestamos.stream()
                .filter(prestamo -> prestamo.getIdPrestamo() == idPrestamo)
                .findFirst();
    }

    public Prestamo save(Prestamo prestamo) {
        if (prestamo.getIdPrestamo() == 0) {
            prestamo.setIdPrestamo(contadorId++);
            listaPrestamos.add(prestamo);
        } else {
            deleteById(prestamo.getIdPrestamo());
            listaPrestamos.add(prestamo);
        }
        return prestamo;
    }

    public boolean deleteById(int idPrestamo) {
        return listaPrestamos.removeIf(prestamo -> prestamo.getIdPrestamo() == idPrestamo);
    }
}