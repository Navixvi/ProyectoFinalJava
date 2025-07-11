package com.techlab.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Positive(message = "El ID del usuario debe ser mayor que cero")
    private int usuarioId;

    private LocalDate fecha = LocalDate.now();

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado = "pendiente";

    @NotEmpty(message = "El pedido debe tener al menos una línea")
    @ElementCollection
    private List<LineaPedido> lineas = new ArrayList<>();

    private double total;

    public Pedido() {
    }

    public Pedido(int usuarioId, List<LineaPedido> lineas) {
        this.usuarioId = usuarioId;
        this.lineas = lineas;
        this.estado = "pendiente";
        this.fecha = LocalDate.now();
        calcularTotal();
    }

    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedido> lineas) {
        this.lineas = lineas;
        calcularTotal();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void calcularTotal() {
        this.total = 0;
    }
}
