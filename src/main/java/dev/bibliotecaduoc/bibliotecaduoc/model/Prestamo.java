package dev.bibliotecaduoc.bibliotecaduoc.model;

import java.time.LocalDate;

public class Prestamo {

    private int idPrestamo;
    private int idLibro;
    private String runSolicitante;
    private LocalDate fechaSolicitud;
    private LocalDate fechaEntrega;
    private int cantidadDias;
    private int multas;

    public Prestamo() {
        this.multas = 0;
    }

    public Prestamo(int idPrestamo, int idLibro, String runSolicitante,
                    LocalDate fechaSolicitud, LocalDate fechaEntrega,
                    int cantidadDias, int multas) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.runSolicitante = runSolicitante;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEntrega = fechaEntrega;
        this.cantidadDias = cantidadDias;
        this.multas = multas;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getRunSolicitante() {
        return runSolicitante;
    }

    public void setRunSolicitante(String runSolicitante) {
        this.runSolicitante = runSolicitante;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public int getMultas() {
        return multas;
    }

    public void setMultas(int multas) {
        this.multas = multas;
    }
}