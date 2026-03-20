package dev.bibliotecaduoc.bibliotecaduoc.repository;

import dev.bibliotecaduoc.bibliotecaduoc.model.Prestamo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PrestamoRepository {

    private final List<Prestamo> listaPrestamos = new ArrayList<>();
    private int contadorId = 1;

    public PrestamoRepository() {
        listaPrestamos.add(new Prestamo(1, 1, "20.111.111-1",
                LocalDate.of(2026, 3, 10), null, 7, 0));

        listaPrestamos.add(new Prestamo(2, 2, "21.222.222-2",
                LocalDate.of(2026, 3, 12), LocalDate.of(2026, 3, 19), 7, 500));

        listaPrestamos.add(new Prestamo(3, 3, "22.333.333-3",
                LocalDate.of(2026, 3, 15), null, 5, 0));

        contadorId = 4;
    }

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
            return prestamo;
        }

        for (int i = 0; i < listaPrestamos.size(); i++) {
            if (listaPrestamos.get(i).getIdPrestamo() == prestamo.getIdPrestamo()) {
                listaPrestamos.set(i, prestamo);
                return prestamo;
            }
        }

        listaPrestamos.add(prestamo);
        return prestamo;
    }

    public boolean deleteById(int idPrestamo) {
        return listaPrestamos.removeIf(prestamo -> prestamo.getIdPrestamo() == idPrestamo);
    }
}