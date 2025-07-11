package com.techlab.ecommerce.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;

@Embeddable
public class LineaPedido {

    @Positive(message = "El ID del producto debe ser mayor que cero")
    private int productoId;

    @Positive(message = "La cantidad debe ser mayor que cero")
    private int cantidad;

    public LineaPedido() {
    }

    public LineaPedido(int productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
